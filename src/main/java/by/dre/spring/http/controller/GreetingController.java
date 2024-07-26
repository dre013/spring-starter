package by.dre.spring.http.controller;

import by.dre.spring.database.entity.Role;
import by.dre.spring.database.repository.CompanyRepository;
import by.dre.spring.dto.LoginDto;
import by.dre.spring.dto.UserReadDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static by.dre.spring.database.entity.Role.ADMIN;

@Controller
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(Model model,
                        UserReadDto user) {
        model.addAttribute("user", user);
        return "greeting/hello";
    }

    @GetMapping("/hello/{id}")
    public String hello(@RequestParam("age") Integer age,
                        @RequestHeader String accept,
                        @CookieValue("JSESSIONID") String jsessionid,
                        @PathVariable("id") Integer id,
                        Model model,
                        UserReadDto user) {
        model.addAttribute("user", user);
        return "greeting/hello";
    }

    @GetMapping("/bye")
    public String bye(Model model,
                      @SessionAttribute("user") UserReadDto user) {
        return "greeting/bye";
    }
}
