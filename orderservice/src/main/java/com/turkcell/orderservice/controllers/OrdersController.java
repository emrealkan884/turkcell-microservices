package com.turkcell.orderservice.controllers;

import com.turkcell.orderservice.dtos.requests.CreateOrderRequest;
import com.turkcell.orderservice.dtos.responses.SubmitOrderResponse;
import com.turkcell.orderservice.services.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {
  private final OrderService orderService;

  @PostMapping("/submit-order")
  public List<SubmitOrderResponse> submitOrder(@RequestBody List<CreateOrderRequest> requests) {
    return orderService.submitOrderWorkshop(requests);
  }
}
