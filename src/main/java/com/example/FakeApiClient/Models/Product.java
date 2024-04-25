package com.example.FakeApiClient.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product extends BaseModel {
    private Long id;
    private String title;
    private float price;
    private String description;
    private Category category;
    private String image;
}
