package com.example.FakeApiClient.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
}
