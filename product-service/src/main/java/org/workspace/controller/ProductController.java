package org.workspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.workspace.dto.Coupon;
import org.workspace.model.Product;
import org.workspace.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${couponService.url}")
    private String couponServiceURL;

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        Coupon coupon = restTemplate.getForObject(couponServiceURL+product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return productRepo.save(product);
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId){
        return productRepo.findById(productId).orElseGet(Product::new);
    }

}
