package by.dre.spring.database.repository;

import by.dre.spring.database.entity.User;
import by.dre.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
