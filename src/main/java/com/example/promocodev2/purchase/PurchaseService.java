package com.example.promocodev2.purchase;

import com.example.promocodev2.product.Product;
import com.example.promocodev2.product.ProductService;
import com.example.promocodev2.promo_code.DiscountCalculation;
import com.example.promocodev2.promo_code.PromoCodeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PurchaseService {
    PurchaseRepository purchaseRepository;
    ProductService productService;
    PromoCodeService promoCodeService;

    public PurchaseService(PurchaseRepository purchaseRepository, ProductService productService, PromoCodeService promoCodeService) {
        this.purchaseRepository = purchaseRepository;
        this.productService = productService;
        this.promoCodeService = promoCodeService;
    }

    public Boolean buyProduct(Integer productID, String promoCodeName){
        Optional<Product> product = productService.getProduct(productID);
        if(product.isPresent()){
            DiscountCalculation discountCalculation = promoCodeService.calculateDiscount(productID,promoCodeName);
            Purchase purchase = new Purchase();
            purchase.setDateOfPurchase(LocalDateTime.now());
            purchase.setRegularPrice(discountCalculation.basicPrice());
            purchase.setDiscount(discountCalculation.basicPrice().subtract(discountCalculation.finalPrice()));
            purchase.setProduct(product.get());
            purchaseRepository.save(purchase);
            if(discountCalculation.finalPrice() != discountCalculation.basicPrice()){
                promoCodeService.addCodeUses(promoCodeName);
            }
            return true;
        }
        return false;
    }
}
