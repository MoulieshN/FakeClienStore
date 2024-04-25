package com.example.FakeApiClient.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category extends BaseModel{
    private Long id;
    private float price;
    private String name;
    private String description;
    private List<Product> products;
    private String image;
}
