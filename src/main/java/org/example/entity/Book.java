package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class Book {
    private Integer id;
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private int price;
    private String genre;
    private String description;
    private LocalDate publishedDate;



}
