package com.example.promocodev2.promo_code;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class PromoCode {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer promo_codeID;
    @Column(unique = true)
    private String text;
    private BigDecimal discount_amount;
    private LocalDateTime expiration_date;
    private Integer max_usages;

    public LocalDateTime getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDateTime expiration_date) {
        this.expiration_date = expiration_date;
    }

    private Integer code_used;
    private String currency;

    public Integer getPromo_codeID() {
        return promo_codeID;
    }

    public void setPromo_codeID(Integer promo_codeID) {
        this.promo_codeID = promo_codeID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(BigDecimal discount_amount) {
        this.discount_amount = discount_amount;
    }


    public Integer getMax_usages() {
        return max_usages;
    }

    public void setMax_usages(Integer max_usages) {
        this.max_usages = max_usages;
    }

    public Integer getCode_used() {
        return code_used;
    }

    public void setCode_used(Integer code_used) {
        this.code_used = code_used;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
