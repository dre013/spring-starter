package by.dre.spring.database.repository;

import by.dre.spring.database.entity.Role;
import by.dre.spring.database.entity.User;
import by.dre.spring.dto.IPersonalInfo;
import by.dre.spring.dto.PersonalInfo;
import by.dre.spring.dto.UserFilter;
import by.dre.spring.dto.UserReadDto;
import by.dre.spring.mapper.UserCreateEditMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository,
        QuerydslPredicateExecutor<User> {

    Page<User> findAllBy(Pageable pageable);

    List<User> findFirst3By(Sort sort);

    Optional<User> findFirstByCompanyIsNotNullOrderByIdDesc();

    @Query(value = "select u.firstname, u.lastname, u.birth_date from users u where u.company_id = :companyId",
            nativeQuery = true)
    List<IPersonalInfo> findAllByCompanyId(Integer companyId);

    @Query("select u from User u where lower(u.firstName) like %:firstname% " +
            "and lower(u.lastName) like %:lastname% ")
    List<User> findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstname, String lastname);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.role = :role where u.id in :ids")
    int updateRole(Role role, Long... ids);

    Optional<User> findByUsername(String username);
}
