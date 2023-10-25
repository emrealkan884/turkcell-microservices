package com.turkcell.productservice.services;

import com.turkcell.productservice.dto.requests.CreateProductRequest;
import com.turkcell.productservice.dto.responses.CreatedProductResponse;
import com.turkcell.productservice.dto.responses.ResponseForSubmitOrder;

public interface ProductService {

  CreatedProductResponse add(CreateProductRequest request);

  ResponseForSubmitOrder getByInventoryCode(String code, int amount);
}
