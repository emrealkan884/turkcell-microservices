package com.turkcell.productservice.controllers;

import com.turkcell.productservice.dto.requests.CreateProductRequest;
import com.turkcell.productservice.dto.responses.CreatedProductResponse;
import com.turkcell.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductsController {
  private final ProductService productService;
  private final KafkaTemplate<String, String> kafkaTemplate;

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public CreatedProductResponse add(@RequestBody CreateProductRequest request) {
    return productService.add(request);
  }

  @GetMapping("check-stock")
  public Boolean getByInventoryCode(@RequestParam String invCode, @RequestParam int requiredStock) {
    kafkaTemplate.send("notificationTopic", "Sipariş verildi..");
    return productService.getByInventoryCode(invCode, requiredStock);
  }

  @GetMapping("/deneme")
  public String deneme() {
    return "Deneme";
  }

  @GetMapping("get-stock")
  public Integer getByStock(@RequestParam String invCode) {
    return productService.findByStock(invCode);
  }
}
