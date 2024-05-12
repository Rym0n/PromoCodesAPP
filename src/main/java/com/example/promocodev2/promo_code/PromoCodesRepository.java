package com.example.promocodev2.promo_code;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoCodesRepository extends JpaRepository<PromoCode,Integer> {
     Optional<PromoCode> findByText(String text);
}
