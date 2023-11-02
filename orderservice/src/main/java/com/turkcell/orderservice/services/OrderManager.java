package com.turkcell.orderservice.services;

import com.turkcell.orderservice.dtos.requests.CreateOrderRequest;
import com.turkcell.orderservice.dtos.responses.SubmitOrderResponse;
import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.repositories.OrderRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {

  private final WebClient.Builder webClientBuilder;
  private final OrderRepository orderRepository;

  public Boolean submitOrder(CreateOrderRequest request) {
    // Web istekleri default async
    // sync
    Boolean hasStock =
        webClientBuilder
            .build()
            .get()
            .uri(
                "http://product-service/api/v1/products/check-stock",
                (uriBuilder) ->
                    uriBuilder
                        .queryParam("invCode", request.getInventoryCode())
                        .queryParam("amount", request.getAmount())
                        .build())
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();
    return hasStock;
  }

  @Override
  public List<SubmitOrderResponse> submitOrderWorkshop(List<CreateOrderRequest> requests) {

    List<SubmitOrderResponse> submitOrderResponses = new ArrayList<>();

    for (CreateOrderRequest request : requests) {

      String inventoryCode = request.getInventoryCode();
      int requiredStock = request.getAmount();

      Boolean hasStock =
          webClientBuilder
              .build()
              .get()
              .uri(
                  "http://product-service/api/v1/products/check-stock",
                  (uriBuilder) ->
                      uriBuilder
                          .queryParam("invCode", request.getInventoryCode())
                          .queryParam("requiredStock", request.getAmount())
                          .build())
              .retrieve()
              .bodyToMono(Boolean.class)
              .block();

      Integer stockAmount =
          webClientBuilder
              .build()
              .get()
              .uri(
                  "http://product-service/api/v1/products/get-stock",
                  (uriBuilder) ->
                      uriBuilder.queryParam("invCode", request.getInventoryCode()).build())
              .retrieve()
              .bodyToMono(Integer.class)
              .block();

      Order order = Order.builder().build();
      if (hasStock == true) {
        order =
            Order.builder()
                .inventoryCode(request.getInventoryCode())
                .amount(request.getAmount())
                .orderDate(LocalDate.now())
                .build();
        order = orderRepository.save(order);
      }

      SubmitOrderResponse submitOrderResponse =
          SubmitOrderResponse.builder()
              .inventoryCode(order.getInventoryCode())
              .hasStock(hasStock)
              .stockAmount(stockAmount)
              .build();
      submitOrderResponses.add(submitOrderResponse);
    }
    return submitOrderResponses;
  }
}
