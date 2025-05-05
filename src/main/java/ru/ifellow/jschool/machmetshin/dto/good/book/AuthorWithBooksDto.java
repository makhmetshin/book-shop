package ru.ifellow.jschool.machmetshin.dto.good.book;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorWithBooksDto extends AuthorDto {

    private Set<BookDto> bookDtos;
}
