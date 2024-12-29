package org.gdg.zipte_gdg.api.service.categorySet;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.categorySet.request.CategorySetRequestDto;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.product.ProductImageService;
import org.gdg.zipte_gdg.api.service.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.api.service.categorySet.response.CategorySetResponse;
import org.gdg.zipte_gdg.domain.eCommerce.category.Category;
import org.gdg.zipte_gdg.domain.eCommerce.category.CategoryRepository;
import org.gdg.zipte_gdg.domain.eCommerce.product.Product;
import org.gdg.zipte_gdg.domain.eCommerce.product.ProductImage;
import org.gdg.zipte_gdg.domain.eCommerce.product.ProductRepository;
import org.gdg.zipte_gdg.domain.eCommerce.categorySet.CategorySet;
import org.gdg.zipte_gdg.domain.eCommerce.categorySet.CategorySetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CategorySetServiceImpl implements CategorySetService {

    private final CategorySetRepository categorySetRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageService productImageService;

    @Override
    public CategorySetResponse create(CategorySetRequestDto request) {

        // 상품 생성
        Product newProduct = Product.of(request.getPname(),
                request.getPdesc(), request.getPrice(), request.getStock());
        Product product = productRepository.save(newProduct);

        // 상품 사진
        List<String> uploads = productImageService.saveFiles(product, request.getFiles());

        // 현재 존재하는 카테고리 조회
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();

        CategorySet categorySet = CategorySet.of(product, category);
        CategorySet save = categorySetRepository.save(categorySet);

        CategorySetResponse categorySetResponse = entityToDto(save);
        categorySetResponse.getProduct().setUploadFileNames(uploads);

        return categorySetResponse;
    }

    @Override
    public PageResponseDto<ProductResponseDto> findAllById(Long id, PageRequestDto pageRequestDto) {

        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());
        Page<Object[]> result = categorySetRepository.findProductCategoriesById(id,pageable);

        List<ProductResponseDto> dtoList = result.get().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            String imageStr = (productImage != null) ? productImage.getFileName() : "No image found";
            ProductResponseDto dto = entityToDto(product);
            dto.setUploadFileNames(Collections.singletonList(imageStr));

            return dto;
        }).toList();

        long total = result.getTotalElements();
        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }

}
