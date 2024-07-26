package by.dre.integration.repository;

import by.dre.annotation.IT;
import by.dre.spring.database.entity.Role;
import by.dre.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@IT
@RequiredArgsConstructor
public class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var page = userRepository.findAllBy(pageable);
        page.forEach(user -> System.out.println(user.getId()));
        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(user -> System.out.println(user.getId()));
            System.out.println(page.getTotalPages());
        }
    }

    @Test
    void findFirst3ByTest() {
        var users = userRepository.findFirst3By(Sort.by("id").descending());
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(3);
    }

    @Test
    void findFirstByCompanyIsNotNullOrderByIdDescTest() {
        var user = userRepository.findFirstByCompanyIsNotNullOrderByIdDesc();
        assertTrue(user.isPresent());
        user.ifPresent(u -> assertEquals("Evgeny", u.getFirstName()));
    }

    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(1);
    }

    @Test
    void findAllByFirstnameContainingAndLastnameContainingTest() {
        var users = userRepository.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("a", "a");
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(2);
    }

    @Test
    void updateRoleTest() {
        var result = userRepository.updateRole(Role.USER, 2L, 3L);
        assertEquals(2, result);
    }
}
