package ru.ifellow.jschool.machmetshin.dto.servicesDto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FindBooksDto {
    String genre;
    String title;
    Integer authorId;
}
