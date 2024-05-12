package com.example.promocodev2.promo_code;

import java.math.BigDecimal;
import java.util.Date;

public record PromoCodeDTO(Integer id, String text, BigDecimal discount_amount, String expiration_date, Integer max_usages,Integer code_used, String currency) {
}
