package by.dre.spring.dto;

import by.dre.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    String image;
    CompanyReadDto company;
}
