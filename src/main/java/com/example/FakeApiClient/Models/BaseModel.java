package com.example.FakeApiClient.Models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastUpdateAt;
    private boolean isDelete;
}
