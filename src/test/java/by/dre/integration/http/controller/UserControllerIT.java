package by.dre.integration.http.controller;

import by.dre.annotation.IT;
import by.dre.spring.database.entity.Role;
import by.dre.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static by.dre.spring.dto.UserCreateEditDto.Fields.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
@WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "USER"})
public class UserControllerIT {
    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
//                .andExpect(model().attribute("users", IsCollectionWithSize.hasSize(3)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(username, "test@gmail.com")
                        .param(birthDate, "2000-01-01")
                        .param(firstname, "test")
                        .param(lastname, "test")
                        .param(role, "ADMIN")
                        .param(companyId, "1")
                )
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}"));
    }
}
