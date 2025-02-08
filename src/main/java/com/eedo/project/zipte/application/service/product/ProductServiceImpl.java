package com.eedo.project.zipte.application.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.zipte.application.port.in.product.ProductService;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.zipte.representation.response.ProductResponse;
import com.eedo.project.zipte.domain.product.Product;
import com.eedo.project.zipte.domain.product.ProductImage;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse findById(Long id) {

        Product product = productRepository.findById(id).orElseThrow();

        List<ProductImage> productImages = productRepository.selectProductImages(id);

        ProductResponse productResponse = ProductResponse.from(product);

        productResponse.setUploadFileNames(productImages.stream().map(ProductImage::getFileName).collect(Collectors.toList()));

        return productResponse;
    }

    public PageResponse<ProductResponse> findAll(PageRequest pageRequest) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage()-1, pageRequest.getSize(), Sort.by("id").descending());
        Page<Object[]> result = productRepository.selectList(pageable);

        List<ProductResponse> dtoList = result.get().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            String imageStr = (productImage != null) ? productImage.getFileName() : "No image found";
            ProductResponse dto = ProductResponse.from(product);
            dto.setUploadFileNames(Collections.singletonList(imageStr));

            return dto;
        }).toList();

        long total = result.getTotalElements();
        return new PageResponse<>(dtoList, pageRequest, total);
    }

}
