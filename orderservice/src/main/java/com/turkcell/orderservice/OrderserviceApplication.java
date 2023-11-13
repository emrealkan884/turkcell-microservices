package com.turkcell.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableDiscoveryClient => yeni versiyonda gerekmiyor..
public class OrderserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderserviceApplication.class, args);
  }
}
