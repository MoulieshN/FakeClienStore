package com.example.FakeApiClient.Clients;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeClientProductDto {
    private Long id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
}
