package com.example.FakeApiClient.Services;

import com.example.FakeApiClient.Clients.FakeClient;
import com.example.FakeApiClient.Clients.FakeClientProductDto;
import com.example.FakeApiClient.DTOs.ProductDto;
import com.example.FakeApiClient.Models.Category;
import com.example.FakeApiClient.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeProductClientService implements FakeProductClientImpl{

    RestTemplateBuilder restTemplateBuilder;
    FakeClient fakeClient;
    public FakeProductClientService(RestTemplateBuilder restTemplateBuilder, FakeClient fakeClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeClient = fakeClient;
    }

    // Get all products from fake store api
    @Override
    public Optional<List<Product>> getAllProducts(){
        List<FakeClientProductDto> fakeClientProductDtos = fakeClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for(FakeClientProductDto productDto : fakeClientProductDtos){
            products.add(createProductDtoFromClientDto(productDto));
        }
        return Optional.of(products);
    }

    // Update product in fake store api
    @Override
    public Optional<Product> updateProduct(Long productID, Product product) {
        FakeClientProductDto fakeClientProductDto = createClientDtoFromProduct(product);
        ResponseEntity<FakeClientProductDto> response = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{Id}", fakeClientProductDto, FakeClientProductDto.class, productID);
        if (response.getBody() == null) {
            return Optional.empty();
        }
        return Optional.of(createProductDtoFromClientDto(response.getBody()));
    }


    // Get product by id from fake store api
    @Override
    public Optional<Product> getProductById(Long productID) {
        ResponseEntity<FakeClientProductDto> response = requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/{Id}", null, FakeClientProductDto.class, productID);
        if (response.getBody() == null) {
            return Optional.empty();
        }
        return Optional.of(createProductDtoFromClientDto(response.getBody()));
    }





    @Override
    public Product addProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);

        productDto = response.getBody();
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImage(productDto.getImage());
        return product;
    }



    @Override
    public String deleteProduct() {
        return "";
    }


    private  Product createProductDtoFromClientDto(FakeClientProductDto fakeClientProductDto){
        Product product = new Product();
        product.setId(fakeClientProductDto.getId());
        product.setTitle(fakeClientProductDto.getTitle());
        product.setPrice(fakeClientProductDto.getPrice());
        product.setDescription(fakeClientProductDto.getDescription());

        Category category = new Category();
        category.setName(fakeClientProductDto.getCategory());
        product.setCategory(category);
        product.setImage(fakeClientProductDto.getImage());
        return product;
    }

    private FakeClientProductDto createClientDtoFromProduct(Product product){
        FakeClientProductDto fakeClientProductDto = new FakeClientProductDto();
        fakeClientProductDto.setId(product.getId());
        fakeClientProductDto.setTitle(product.getTitle());
        fakeClientProductDto.setPrice(product.getPrice());
        fakeClientProductDto.setDescription(product.getDescription());
        fakeClientProductDto.setCategory(product.getCategory().getName());
        fakeClientProductDto.setImage(product.getImage());
        return fakeClientProductDto;
    }
}
