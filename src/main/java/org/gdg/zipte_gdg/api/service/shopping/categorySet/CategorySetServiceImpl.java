package org.gdg.zipte_gdg.api.service.shopping.categorySet;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.admin.shopping.request.CategorySetRequest;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryNoChildrenResponse;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.DiscountProductResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.product.ProductImageService;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.gdg.zipte_gdg.api.service.shopping.categorySet.response.CategorySetResponse;
import org.gdg.zipte_gdg.domain.shopping.category.Category;
import org.gdg.zipte_gdg.domain.shopping.category.CategoryRepository;
import org.gdg.zipte_gdg.domain.shopping.product.Product;
import org.gdg.zipte_gdg.domain.shopping.product.ProductImage;
import org.gdg.zipte_gdg.domain.shopping.product.ProductRepository;
import org.gdg.zipte_gdg.domain.shopping.categorySet.CategorySet;
import org.gdg.zipte_gdg.domain.shopping.categorySet.CategorySetRepository;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManagerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CategorySetServiceImpl implements CategorySetService {

    private final CategoryRepository categoryRepository;
    private final CategorySetRepository categorySetRepository;
    private final ProductRepository productRepository;
    private final ProductImageService productImageService;
    private final ProductManagerRepository productManagerRepository;

    @Override
    public CategorySetResponse create(CategorySetRequest request) {

        // 상품 생성
        Product newProduct = Product.of(request.getPname(),
                request.getPdesc(), request.getPrice(), request.getStock());
        Product product = productRepository.save(newProduct);

        //상품 매니저 저장
        ProductManager newProductManager = ProductManager.of(product, 0, true,"basic");
        productManagerRepository.save(newProductManager);

        // 상품 사진
        List<String> uploads = productImageService.saveFiles(product, request.getFiles());

        // 현재 존재하는 카테고리 조회
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();

        CategorySet categorySet = CategorySet.of(product, category);
        CategorySet save = categorySetRepository.save(categorySet);

        CategorySetResponse categorySetResponse = CategorySetResponse.of(save);
        categorySetResponse.getProduct().setUploadFileNames(uploads);

        return categorySetResponse;
    }

    @Override
    public PageResponseDto<ProductResponse> findAllAdmin(Long id, PageRequestDto pageRequestDto) {

        // 1. 카테고리 ID와 자식 카테고리 ID 목록 생성
        List<Long> categoryIds = findAllChildCategoryIds(id);

        // 2. 자식 카테고리를 포함한 ID 목록을 이용해 Product 조회
        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, pageRequestDto.getSize(), Sort.by("id").descending());
        Page<Object[]> result = categorySetRepository.findProductCategoriesByIds(categoryIds, pageable);

        // 3. 결과 리스트 변환
        List<ProductResponse> dtoList = result.get().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            String imageStr = (productImage != null) ? productImage.getFileName() : "No image found";
            ProductResponse dto = ProductResponse.of(product);
            dto.setUploadFileNames(Collections.singletonList(imageStr));

            return dto;
        }).toList();

        long total = result.getTotalElements();
        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }

    private List<Long> findAllChildCategoryIds(Long id) {
        List<Long> result = new ArrayList<>();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));

        result.add(category.getId()); // 현재 카테고리 ID 추가
        for (Category child : category.getChildren()) {
            result.addAll(findAllChildCategoryIds(child.getId())); // 재귀 호출로 자식 카테고리 ID 추가
        }
        return result;
    }

    @Override
    public PageResponseDto<DiscountProductResponse> findAll(Long id, PageRequestDto pageRequestDto) {
        // 1. 카테고리 ID와 자식 카테고리 ID 목록 생성
        List<Long> categoryIds = findAllChildCategoryIds(id);

        // 2. 자식 카테고리를 포함한 ID 목록을 이용해 Product 조회
        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, pageRequestDto.getSize(), Sort.by("id").descending());
        Page<Object[]> result = categorySetRepository.findProductManagerCategoriesByIds(categoryIds, pageable);

        // 3. 결과 리스트 변환
        List<DiscountProductResponse> dtoList = result.get().map(arr -> {
            ProductManager productManager = (ProductManager) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            // 카테고리 정보 넣기
            CategorySet category = productManagerRepository.findCategoryByProductId(productManager.getProduct().getId());
            CategoryNoChildrenResponse responseNoChildren = CategoryNoChildrenResponse.of(category.getCategory());

            String imageStr = (productImage != null) ? productImage.getFileName() : "No image found";
            DiscountProductResponse dto = DiscountProductResponse.of(productManager);
            dto.getProduct().setUploadFileNames((Collections.singletonList(imageStr)));
            dto.setCategory(responseNoChildren);


            return dto;
        }).toList();

        long total = result.getTotalElements();
        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }

}
