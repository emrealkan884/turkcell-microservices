package com.turkcell.productservice.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseForSubmitOrder {
  private String inventoryCode;
  private Boolean hasStock;
  private int stockAmount;
}
