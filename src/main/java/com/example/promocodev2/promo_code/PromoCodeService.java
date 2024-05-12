package com.example.promocodev2.promo_code;


import com.example.promocodev2.product.Product;
import com.example.promocodev2.product.ProductDTO;
import com.example.promocodev2.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PromoCodeService {
    PromoCodesRepository promoCodesRepository;
    ProductRepository productRepository;

    public PromoCodeService(PromoCodesRepository promoCodesRepository, ProductRepository productRepository) {
        this.promoCodesRepository = promoCodesRepository;
        this.productRepository = productRepository;
    }
    @Transactional
    public PromoCodeDTO addPromoCode(NewPromoCodeDTO request){
        PromoCode promoCode = new PromoCode();
        promoCode.setText(request.text());
        promoCode.setDiscount_amount(request.discount_amount());
        promoCode.setExpiration_date(LocalDateTime.ofInstant(Instant.parse(request.expiration_date()),ZoneId.of("UTC")));
        promoCode.setMax_usages(request.max_usages());
        promoCode.setCode_used(0);
        promoCode.setCurrency(request.currency());
        return mapToPromoCodeDTO(promoCodesRepository.save(promoCode));
    }
    public List<PromoCodeNameDTO> getPromoCodeList(){
        return promoCodesRepository.findAll().stream().map(this::mapToPromoCodeNameDTO).toList();
    }
    public PromoCodeDTO getPromoCode(String name){
        Optional<PromoCode> promoCode = promoCodesRepository.findByText(name);
        if(promoCode.isPresent()){
            return  mapToPromoCodeDTO(promoCode.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }



    private PromoCodeDTO mapToPromoCodeDTO(PromoCode promoCode){
        return new PromoCodeDTO(promoCode.getPromo_codeID(),promoCode.getText(),promoCode.getDiscount_amount(),promoCode.getExpiration_date().toString(),promoCode.getMax_usages(),promoCode.getCode_used(),promoCode.getCurrency());
    }
    private PromoCodeNameDTO mapToPromoCodeNameDTO(PromoCode promoCode){
        return new PromoCodeNameDTO(promoCode.getPromo_codeID(),promoCode.getText());
    }

    public DiscountCalculation calculateDiscount(Integer productID, String promoCodeName) {
        Optional<Product> product = productRepository.findById(productID);
        if(product.isPresent()){
            Optional<PromoCode> promoCode = promoCodesRepository.findByText(promoCodeName);
            if(promoCode.isPresent()){
                DiscountCalculation discountCalculation = new DiscountCalculation(product.get().getRegular_price(),product.get().getRegular_price());
                if(Objects.equals(product.get().getCurrency(), promoCode.get().getCurrency()) && promoCode.get().getMax_usages() > promoCode.get().getCode_used() && promoCode.get().getExpiration_date().isAfter(LocalDateTime.now())){
                    BigDecimal x = product.get().getRegular_price().subtract(promoCode.get().getDiscount_amount());
                    if( x.compareTo(new BigDecimal(0)) == -1){
                        x = new BigDecimal(0);
                    }
                    discountCalculation = new DiscountCalculation(product.get().getRegular_price(), x);
                }
                return discountCalculation;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @Transactional
    public void addCodeUses(String promoCodeName) {
       Optional<PromoCode> promoCode = promoCodesRepository.findByText(promoCodeName);
       if(promoCode.isPresent()) {
           promoCode.get().setCode_used(promoCode.get().getCode_used() + 1);
       }
    }
}
