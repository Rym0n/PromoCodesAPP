package com.example.promocodev2;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Transactional
    public Product addProduct(NewProductDTO request){
        Product product = new Product();
        product.setName(request.name());
        product.setRegular_price(request.regular_price());
        product.setCurrency(request.currency());
        return productRepository.save(product);
    }
    public List<Product> getALL(){
        return productRepository.findAll();
    }
    @Transactional
    public Product updateProduct(ProductDTO request){
        Product product = productRepository.getById(request.id());
        product.setName(request.name());
        product.setRegular_price(request.regular_price());
        product.setCurrency(request.currency());
        return product;
    }
}
