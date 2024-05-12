package com.example.promocodev2.product;

import java.math.BigDecimal;

public record ProductDTO(Integer id, String name, BigDecimal regular_price, String currency,String description) {



}
