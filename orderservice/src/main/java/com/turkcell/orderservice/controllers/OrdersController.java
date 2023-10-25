package com.turkcell.orderservice.controllers;

import com.turkcell.orderservice.dtos.requests.CreateOrderRequest;
import com.turkcell.orderservice.services.OrderService;
import com.turkcell.productservice.dto.responses.ResponseForSubmitOrder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {
  private final OrderService orderService;

  @PostMapping("/submit-order")
  public List<ResponseForSubmitOrder> submitOrder(@RequestBody List<CreateOrderRequest> requests) {
    return orderService.submitOrder(requests);
  }
}
