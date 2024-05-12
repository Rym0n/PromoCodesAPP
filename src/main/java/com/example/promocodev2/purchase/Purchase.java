package com.example.promocodev2.purchase;

import com.example.promocodev2.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Purchase {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer purchaseID;
    private LocalDateTime dateOfPurchase;
    private BigDecimal regularPrice;
    private BigDecimal discount;
    @ManyToOne
    @JoinColumn(name = "product_product_id")
    private Product product;

    public Integer getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(Integer purchaseID) {
        this.purchaseID = purchaseID;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
