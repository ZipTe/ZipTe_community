package org.gdg.zipte_gdg.api.service.shopping.productManger;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.admin.shopping.request.ProductManagerRequest;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryNoChildrenResponse;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.DiscountProductResponse;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.ProductManagerResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.domain.product.categorySet.CategorySet;
import org.gdg.zipte_gdg.domain.product.product.Product;
import org.gdg.zipte_gdg.domain.product.product.ProductImage;
import org.gdg.zipte_gdg.domain.product.product.ProductRepository;
import org.gdg.zipte_gdg.domain.product.productManger.ProductManager;
import org.gdg.zipte_gdg.domain.product.productManger.ProductManagerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductMangerServiceImpl implements ProductMangerService {

    private final ProductManagerRepository productManagerRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductManagerResponse create(ProductManagerRequest request) {

        Product product = productRepository.findById(request.getProductId()).orElseThrow();

        // 기존 매니저가 있을 경우 처리
        ProductManager existingManager = productManagerRepository.findByProductId(product.getId());
        if (existingManager != null) {
            existingManager.setActive(false);
            productManagerRepository.save(existingManager);  // 기존 매니저 업데이트
        }

        ProductManager productManager = ProductManager.of(product, request.getDiscountRate(), request.isActive(),request.getDescription());
        productManager.setSaleStartDate(request.getSaleStartDate());
        productManager.setSaleEndDate(request.getSaleEndDate());
        productManager.setCouponCode(request.getCouponCode());
        ProductManager manager = productManagerRepository.save(productManager);

        return ProductManagerResponse.from(manager);
    }

    @Override
    public PageResponseDto<DiscountProductResponse> findAll(PageRequestDto pageRequestDto) {

        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());
        Page<Object[]> result = productManagerRepository.selectList(pageable);

        List<DiscountProductResponse> dtoList = result.get().map(arr -> {
            ProductManager productManager =(ProductManager) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            // 카테고리 정보 넣기
            CategorySet category = productManagerRepository.findCategoryByProductId(productManager.getProduct().getId());
            CategoryNoChildrenResponse responseNoChildren = CategoryNoChildrenResponse.from(category.getCategory());


            String imageStr = (productImage != null) ? productImage.getFileName() : "No image found";
            DiscountProductResponse discountProductResponse = DiscountProductResponse.from(productManager);
            discountProductResponse.getProduct().setUploadFileNames(Collections.singletonList(imageStr));
            discountProductResponse.setCategory(responseNoChildren);

            return discountProductResponse;
        }).toList();

        long total = result.getTotalElements();
        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }

    @Override
    public DiscountProductResponse findById(Long id) {

        ProductManager productManager = productManagerRepository.findByProductId(id);
        DiscountProductResponse discountProductResponse = DiscountProductResponse.from(productManager);

        CategorySet category = productManagerRepository.findCategoryByProductId(id);
        CategoryNoChildrenResponse responseNoChildren = CategoryNoChildrenResponse.from(category.getCategory());
        discountProductResponse.setCategory(responseNoChildren);


        List<ProductImage> productImages = productRepository.selectProductImages(id);
        discountProductResponse.getProduct().setUploadFileNames(productImages.stream().map(ProductImage::getFileName).collect(Collectors.toList()));

        return discountProductResponse;

    }
}
