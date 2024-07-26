package by.dre.spring.dto;

import by.dre.spring.database.entity.Role;
import by.dre.spring.validator.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo
public class UserCreateEditDto {
    @Email
    String username;
    String rawPassword;

    LocalDate birthDate;

    @Size(min = 3, max = 30)
    String firstname;

    String lastname;
    Role role;
    Integer companyId;
    MultipartFile image;
}
