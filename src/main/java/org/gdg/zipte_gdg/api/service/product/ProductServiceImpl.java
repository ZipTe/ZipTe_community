package org.gdg.zipte_gdg.api.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.product.request.ProductRequestDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.domain.product.Product;
import org.gdg.zipte_gdg.domain.product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<Product> byId = productRepository.findById(id);
        Product product = byId.orElseThrow();

        return entityToDto(product);
    }

    @Override
    public PageResponseDto<ProductResponseDto> findAll(PageRequestDto pageRequestDto) {
        log.info("=== getList ===");

        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());

        Page<Product> result = productRepository.findAll(pageable);

        log.info(result);

        // Review 엔티티를 ReviewResponseDto로 변환
        List<ProductResponseDto> dtoList = result.stream()
                .map(this::entityToDto)  // entityToDto 메서드 사용
                .collect(Collectors.toList());

        long total = result.getTotalElements();

        return new PageResponseDto<ProductResponseDto>(dtoList, pageRequestDto, total);
    }
}
