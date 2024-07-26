package by.dre.integration.service;

import by.dre.annotation.IT;
import by.dre.spring.database.entity.Role;
import by.dre.spring.dto.UserCreateEditDto;
import by.dre.spring.dto.UserReadDto;
import by.dre.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class UserServiceIT {
    private final static Long USER_1 = 2L;
    private final static Integer COMPANY_1 = 1;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(3);
    }

    @Test
    void findById() {
        Optional<UserReadDto> result = userService.findById(USER_1);
        assertTrue(result.isPresent());
        result.ifPresent(user -> assertEquals("tolik228@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );

        UserReadDto actualResult = userService.create(userDto);
        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
        assertSame(userDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(2L));
    }

}
