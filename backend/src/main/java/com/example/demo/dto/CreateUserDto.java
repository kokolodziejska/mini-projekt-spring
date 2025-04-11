package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserDto {
    private String name;
    private LocalDate birthDate;
    private String hobby;
    private String favoriteMovies;
}
