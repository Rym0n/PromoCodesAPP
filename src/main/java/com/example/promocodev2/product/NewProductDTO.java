package com.example.promocodev2.product;

import java.math.BigDecimal;

public record NewProductDTO(String name, BigDecimal regular_price, String currency,String description) {
}

