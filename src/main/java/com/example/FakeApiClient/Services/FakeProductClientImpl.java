package com.example.FakeApiClient.Services;

import com.example.FakeApiClient.DTOs.ProductDto;
import com.example.FakeApiClient.Models.Product;

import java.util.List;
import java.util.Optional;

public interface FakeProductClientImpl {

     Optional<List<Product>> getAllProducts();

     Optional<Product> getProductById(Long productID);


     Product addProduct(ProductDto productDto);


     Optional<Product> updateProduct(Long productId, Product product);

     String deleteProduct();
}
