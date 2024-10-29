package org.gdg.zipte_gdg.api.service.product;

import org.gdg.zipte_gdg.domain.product.Product;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    List<String> saveFiles(Product product, List<MultipartFile> files);

    ResponseEntity<Resource> getFile(String fileName);

    void delelteFiles(List<String> fileNames);
}
