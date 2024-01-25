package com.microservices.productservicems.service;


import com.microservices.productservicems.dto.ProductRequest;
import com.microservices.productservicems.dto.ProductResponse;
import com.microservices.productservicems.model.Product;
import com.microservices.productservicems.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
//        log.info("Product {} is saved", product.getId());
        System.out.println("Product is saved");
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()

                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public ProductResponse getSingleProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new IllegalArgumentException("Product Not found");
        return mapToProductResponse(product.get());
    }
}
