package com.turkcell.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableEurekaClient => yeni versiyonda gerekmiyor..
public class ProductserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductserviceApplication.class, args);
  }
}
