package com.example.FakeApiClient.Clients;

import com.example.FakeApiClient.Services.FakeProductClientService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FakeClient {
    RestTemplateBuilder restTemplateBuilder;
    FakeProductClientService fakeProductClientService;
    public FakeClient(RestTemplateBuilder restTemplateBuilder, FakeProductClientService fakeProductClientService){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeProductClientService = fakeProductClientService;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod method, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables){
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, method, requestCallback, responseExtractor, uriVariables);
    }

    public List<FakeClientProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeClientProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeClientProductDto[].class);
        List<FakeClientProductDto> productList = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return productList;
    }
}
