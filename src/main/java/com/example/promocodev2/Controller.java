package com.example.promocodev2;

import com.example.promocodev2.product.NewProductDTO;
import com.example.promocodev2.product.ProductDTO;
import com.example.promocodev2.product.ProductService;
import com.example.promocodev2.promo_code.*;
import com.example.promocodev2.purchase.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/")
public class Controller {
    ProductService productService;
    PromoCodeService promoCodeService;
    PurchaseService purchaseService;

    public Controller(ProductService productService, PromoCodeService promoCodeService, PurchaseService purchaseService) {
        this.productService = productService;
        this.promoCodeService = promoCodeService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("home")
    public ResponseEntity<?> test (){
        return ResponseEntity.ok("Hello world");
    }
    @PostMapping("add-product")
    public ResponseEntity<?> addProduct(@RequestBody NewProductDTO request){

        try{
            ProductDTO product = productService.addProduct(request);
            if(product != null){
                return ResponseEntity.ok(product);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("get-products")
    public ResponseEntity<?> getProducts(){

        try{
            List<ProductDTO> products = productService.getALL();
            return ResponseEntity.ok(products);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @PostMapping("update-product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO request){
        try{
            ProductDTO product = productService.updateProduct(request);
            if(product != null){
                return ResponseEntity.ok(product);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    // PROMO CODES -----------------------------------------------------------------
    @PostMapping("add-promocode")
    public ResponseEntity<?> addPromoCode(@RequestBody NewPromoCodeDTO request){

        try{
            PromoCodeDTO promoCodeDTO = promoCodeService.addPromoCode(request);
            if(promoCodeDTO != null){
                return ResponseEntity.ok(promoCodeDTO);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("get-promocodes")
    public ResponseEntity<?> getPromoCodes(){

        try{
            List<PromoCodeNameDTO> promoCode = promoCodeService.getPromoCodeList();
            return ResponseEntity.ok(promoCode);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("promocode-detail")
    public ResponseEntity<?> getPromoCodeDetail(@RequestParam String name){

        try{
            PromoCodeDTO promoCode = promoCodeService.getPromoCode(name);
            if(promoCode != null){
                return ResponseEntity.ok(promoCode);
            }
            return ResponseEntity.badRequest().build();
        }catch(ResponseStatusException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
     @GetMapping("get-discount")
    public ResponseEntity<?> getDiscount (@RequestParam Integer product, @RequestParam String promoCode) {
         try {
            DiscountCalculation discount = promoCodeService.calculateDiscount(product,promoCode);
            if(Objects.equals(discount.basicPrice(), discount.finalPrice())){
                return ResponseEntity.ok("Promo code didnt work price is the same! "+discount.finalPrice());
            }
            return  ResponseEntity.ok("Final price is: "+ discount.finalPrice());
         } catch (ResponseStatusException e) {
             return ResponseEntity.notFound().build();
         } catch (Exception e) {
             return ResponseEntity.internalServerError().body(e.getMessage());
         }
    }

    @GetMapping("buy-product")
    public ResponseEntity<?> buyProduct(@RequestParam Integer product, @RequestParam String promoCode) {
        try {
            Boolean result = purchaseService.buyProduct(product,promoCode);
            if ( result ==true){
                return ResponseEntity.ok("Product succesflully purchased.");
            }
            return ResponseEntity.ok("Something went wrong.");
        } catch (ResponseStatusException e) {
        return ResponseEntity.notFound().build();
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
    }

}
