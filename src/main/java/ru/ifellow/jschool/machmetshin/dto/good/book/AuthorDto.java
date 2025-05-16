package ru.ifellow.jschool.machmetshin.dto.good.book;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.time.LocalDate;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
