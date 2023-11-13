package com.turkcell.orderservice.controllers;

import com.turkcell.orderservice.dtos.requests.CreateOrderRequest;
import com.turkcell.orderservice.dtos.responses.SubmitOrderResponse;
import com.turkcell.orderservice.services.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {
  private final OrderService orderService;

  // @CircuitBreaker anotasyonu, submitOrder methodunu bir Circuit Breaker ile sarar.
  // 'name' parametresi, kullanılacak Circuit Breaker'ın adını belirtir.
  // 'fallbackMethod' parametresi, devre açık durumda olduğunda çağrılacak olan fallback methodunu
  // belirtir.
  @PostMapping("/submit-order")
  @CircuitBreaker(name = "orderService", fallbackMethod = "fallbackSubmitOrder")
  public List<SubmitOrderResponse> submitOrder(@RequestBody List<CreateOrderRequest> requests) {
    return orderService.submitOrderWorkshop(requests);
  }

  // Fallback methodu, devre açık durumda olduğunda çağrılır.
  // Bu method içinde, devre kapalı durumda olduğunda yerine getirilecek işlemleri ekleyebilirsiniz.
  private List<SubmitOrderResponse> fallbackSubmitOrder(Exception e) {
    // Örnek: Bir hata durumunda döndürülecek varsayılan bir liste veya hata kaydını loglama gibi
    // işlemler yapabilirsiniz.
    return Collections.emptyList();
  }
}
