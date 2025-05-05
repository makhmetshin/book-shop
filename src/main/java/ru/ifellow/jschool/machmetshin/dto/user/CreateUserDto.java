package ru.ifellow.jschool.machmetshin.dto.user;

import jakarta.persistence.Column;
import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String lastName;
    private String email;
}
