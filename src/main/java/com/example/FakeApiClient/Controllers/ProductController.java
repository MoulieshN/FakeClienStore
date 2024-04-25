package com.example.FakeApiClient.Controllers;

import com.example.FakeApiClient.DTOs.ProductDto;
import com.example.FakeApiClient.Exceptions.NotFoundException;
import com.example.FakeApiClient.Models.Category;
import com.example.FakeApiClient.Models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.example.FakeApiClient.Services.FakeProductClientImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final FakeProductClientImpl fakeProductClient;

    public ProductController(FakeProductClientImpl fakeProductClient) {
        this.fakeProductClient = fakeProductClient;
    }


    // GetAllProducts from fake store api
    @GetMapping("/all")
    private List<ProductDto> getAllProducts() {
        Optional<List<Product>> response = fakeProductClient.getAllProducts();
       if(response.isEmpty()){
            return new ArrayList<>();
        }
        List<ProductDto> result = new ArrayList<>();
        for(Product product : response.get()){
            result.add(createProductDtoFromProduct(product));
        }
        return result;
    }


    // Update product in fake store api
    @PutMapping("/{productID}")
    private ProductDto updateProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDTO ) throws NotFoundException{
        Optional<Product> product = fakeProductClient.updateProduct(productID, createProductFromProductDto(productDTO));
        if(product.isEmpty()){
            throw new NotFoundException("Product not found for id: " + productID);
        }
        return createProductDtoFromProduct(product.get());
    }

    @GetMapping("/{productID}")
    private ProductDto getProductById(@PathVariable("productID") Long productID) throws Exception {
        Optional<Product> product = fakeProductClient.getProductById(productID);
        if(product.isEmpty()){
            throw new NotFoundException("Product not found for id: " + productID);
        }
        return createProductDtoFromProduct(product.get());
    }

//    @PostMapping("")
//    private Product addProduct(@RequestBody ProductDto productDTO){
//        return fakeProductClient.addProduct(productDTO);
//    }
//

//
//
//    @DeleteMapping("/{productID}")
//    private String deleteProduct(@PathVariable("productID") Long productID) {
//        return "Product deleted " + productID;
//    }

    private ProductDto createProductDtoFromProduct(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImage());
        return productDto;
    }

    private Product createProductFromProductDto(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());

        Category category = new Category();
        category.setName(productDto.getCategory());

        product.setCategory(category);
        return product;
    }
}
