package ru.ifellow.jschool.machmetshin.dto.good.book;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.util.Set;

@Data
@EqualsAndHashCode
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {

    private Integer id;
    private String name;
    private String address;
    private String city;


}
