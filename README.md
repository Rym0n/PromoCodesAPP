# PromoCodesAPP
Application to manage discount codes for sales or promotions (a.k.a promo codes).

# Application guidelines
Run app on  http://localhost:8080
H2 console available at '/h2-console'. login: sa without password. Url included at running app console.
Code is in master branch.

# REST API endpoints

1. Create a new product.
    Available at '/api/add-product'.
   ![image](https://github.com/Rym0n/PromoCodesAPP/assets/84449648/c533bcd9-c6de-48a2-8259-e59fa875e9d8)

2.Get all products.
    Available at '/api/get-products'
    ![image](https://github.com/Rym0n/PromoCodesAPP/assets/84449648/bdea024b-d57c-43fc-8bb3-c6d88236ab7f)

3.Update product data.
    Available at '/api/update-product'
    ![image](https://github.com/Rym0n/PromoCodesAPP/assets/84449648/1926e2b6-b407-418b-82a8-69780cdbd1cb)

4.Create a new promo code.
    Available at '/api/add-promocode'
![image](https://github.com/Rym0n/PromoCodesAPP/assets/84449648/ac3fad4b-d95e-4838-8dee-cdc20d91f4d8)
5. Get all promo codes.
    Available at '/api/get-promocodes'
    ![image](https://github.com/Rym0n/PromoCodesAPP/assets/84449648/82cbd0c0-fa75-4501-9cfb-9aa7d3144a8f)
6. Get one promo code's details by providing the promo code. The detail contain the number of usages.
    Available at '/api/promocode-detail'
    ![image](https://github.com/Rym0n/PromoCodesAPP/assets/84449648/6b07722d-6cc6-4e32-9d9a-6ba6870a8a19)
7. Get the discount price by providing a product and a promo code.
    Available at '/api/get-discount'. In params write the key and value of the product and             promoCode
    ![image](https://github.com/Rym0n/PromoCodesAPP/assets/84449648/55e073dc-a378-4439-a26c-5bc56429fdd7)
8. Simulate purchase
     Available at '/api/buy-product'.In params write the key and value of the product and             promoCode
     ![image](https://github.com/Rym0n/PromoCodesAPP/assets/84449648/44fdf404-2e8a-4255-a7cc-6252f4f0e186)
