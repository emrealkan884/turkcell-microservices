package com.turkcell.orderservice.services;

import com.turkcell.orderservice.dtos.requests.CreateOrderRequest;
import com.turkcell.productservice.dto.responses.ResponseForSubmitOrder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderManager implements OrderService {

  private final WebClient webClient;

  public OrderManager(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://product-service").build();
  }

  public List<ResponseForSubmitOrder> submitOrder(List<CreateOrderRequest> requests) {

    List<ResponseForSubmitOrder> responses = new ArrayList<>();

    for (CreateOrderRequest request : requests) {
      ResponseForSubmitOrder response =
          webClient
              .get()
              .uri(
                  uriBuilder ->
                      uriBuilder
                          .path("/api/v1/products/check-stock")
                          .queryParam("invCode", request.getInventoryCode())
                          .queryParam("amount", request.getAmount())
                          .build())
              .retrieve()
              .bodyToMono(ResponseForSubmitOrder.class)
              .block();
      responses.add(response);
    }
    return responses;
  }
}
