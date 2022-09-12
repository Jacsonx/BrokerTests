package com.ms.email.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {

    private String id;
    private String model;
    private String color;
}
