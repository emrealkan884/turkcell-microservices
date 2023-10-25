package com.turkcell.productservice.services;

import com.turkcell.productservice.dto.requests.CreateProductRequest;
import com.turkcell.productservice.dto.responses.CreatedProductResponse;
import com.turkcell.productservice.dto.responses.ResponseForSubmitOrder;
import com.turkcell.productservice.entities.Product;
import com.turkcell.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public CreatedProductResponse add(CreateProductRequest request) {
    Product product =
        Product.builder()
            .name(request.getName())
            .price(request.getPrice())
            .description(request.getDescription())
            .stock(request.getStock())
            .inventoryCode(request.getInventoryCode())
            .build();

    product = productRepository.save(product);

    CreatedProductResponse response =
        CreatedProductResponse.builder().id(product.getId()).name(product.getName()).build();
    return response;
  }

  @Override
  public ResponseForSubmitOrder getByInventoryCode(String code, int amount) {

    Boolean hasStock = true;

    Product product = productRepository.findByInventoryCodeQuery(code);

    if (product == null || product.getStock() < amount) {
      hasStock = false;
    }
    ResponseForSubmitOrder response =
        ResponseForSubmitOrder.builder()
            .inventoryCode(product.getInventoryCode())
            .stockAmount(product.getStock())
            .hasStock(hasStock)
            .build();

    return response;
  }
}
