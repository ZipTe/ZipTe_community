package org.gdg.zipte_gdg.api.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.product.request.ProductRequestDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.domain.product.Product;
import org.gdg.zipte_gdg.domain.product.ProductImage;
import org.gdg.zipte_gdg.domain.product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageService productImageService;

    @Override
    public ProductResponseDto register(ProductRequestDto productRequestDto) {
        Product product = dtoToEntity(productRequestDto);
        Product save = productRepository.save(product);

        List<String> uploads = productImageService.saveFiles(save, productRequestDto.getFiles());
        ProductResponseDto productResponseDto = entityToDto(save);
        productResponseDto.setUploadFileNames(uploads);

        return productResponseDto;
    }


    @Override
    public ProductResponseDto findById(Long id) {

        Product product = productRepository.findById(id).orElseThrow();

        List<ProductImage> productImages = productRepository.selectProductImages(id);
        log.info("MyLog"+ productImages);

        ProductResponseDto productResponseDto = entityToDto(product);

        productResponseDto.setUploadFileNames(productImages.stream().map(ProductImage::getFileName).collect(Collectors.toList()));

        return productResponseDto;
    }

    public PageResponseDto<ProductResponseDto> findAll(PageRequestDto pageRequestDto) {
        log.info("=== getList ===");

        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());
        Page<Object[]> result = productRepository.selectList(pageable);

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
