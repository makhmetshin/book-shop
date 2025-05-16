package ru.ifellow.jschool.machmetshin.dto.user;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrdersDto {
    private String username;
    private String name;
    private String surname;
    private String lastName;
    private List<Integer> ordersIds;
}
