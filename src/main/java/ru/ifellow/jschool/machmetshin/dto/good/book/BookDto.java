package ru.ifellow.jschool.machmetshin.dto.good.book;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.ifellow.jschool.machmetshin.dto.good.GoodDto;
import ru.ifellow.jschool.machmetshin.entity.good.GoodType;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Publisher;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookDto extends GoodDto {
    private String ISBN;
    private String title;
    private Integer pagesAmount;
    private AuthorDto authorDto;
    private PublisherDto publisherDto;
    private String genre;
    private LocalDate publishedDate;
}
