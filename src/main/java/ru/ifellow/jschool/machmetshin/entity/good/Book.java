package ru.ifellow.jschool.machmetshin.entity.good;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends Good{

    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private LocalDate publishedDate;

}
