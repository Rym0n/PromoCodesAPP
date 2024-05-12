package com.example.promocodev2.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO addProduct(NewProductDTO request){
        Product product = new Product();
        product.setName(request.name());
        product.setRegular_price(request.regular_price());
        product.setCurrency(request.currency());
        product.setDescription(request.description());
        return map(productRepository.save(product));
    }
    public List<ProductDTO> getALL(){
        return productRepository.findAll().stream().map(this::map).toList();
    }
    @Transactional
    public ProductDTO updateProduct(ProductDTO request){
        Product product = productRepository.getById(request.id());
        product.setName(request.name());
        product.setRegular_price(request.regular_price());
        product.setCurrency(request.currency());
        product.setDescription(request.description());
        return map(product);
    }
    public Optional<Product> getProduct(Integer id){
        return productRepository.findById(id);
    }

    private ProductDTO map(Product product){
        return new ProductDTO(product.getProductID(), product.getName(),product.getRegular_price(),product.getCurrency(),product.getDescription());
    }
}
