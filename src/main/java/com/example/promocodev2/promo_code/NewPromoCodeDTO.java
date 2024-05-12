package com.example.promocodev2.promo_code;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.Date;

public record NewPromoCodeDTO(String text, BigDecimal discount_amount, String expiration_date, Integer max_usages, String currency){}
