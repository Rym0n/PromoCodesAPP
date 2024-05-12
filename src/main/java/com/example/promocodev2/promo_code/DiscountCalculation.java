package com.example.promocodev2.promo_code;

import java.math.BigDecimal;

public record DiscountCalculation(BigDecimal basicPrice, BigDecimal finalPrice) {}
