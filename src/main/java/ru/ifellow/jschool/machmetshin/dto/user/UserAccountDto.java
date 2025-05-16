package ru.ifellow.jschool.machmetshin.dto.user;


import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {

    private String username;
    private String name;
    private String surname;
    private String lastName;
    private String email;
}
