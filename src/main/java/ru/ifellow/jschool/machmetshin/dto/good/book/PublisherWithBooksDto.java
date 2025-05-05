package ru.ifellow.jschool.machmetshin.dto.good.book;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PublisherWithBooksDto extends PublisherDto {
    
    private Set<Book> books;
}
