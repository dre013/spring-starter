package by.dre.spring.database.repository;

import by.dre.spring.database.entity.User;
import by.dre.spring.dto.QPredicates;
import by.dre.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static by.dre.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{
    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
//        var cb = entityManager.getCriteriaBuilder();
//        var criteria = cb.createQuery(User.class);
//
//        var user = criteria.from(User.class);
//        criteria.select(user);
//
//        List<Predicate> predicates = new ArrayList<>();
//        if (filter.firstname() != null && !filter.firstname().isBlank()) {
//            predicates.add(cb.like(user.get("firstName"), filter.firstname()));
//        }
//        if (filter.lastname() != null && !filter.lastname().isBlank()) {
//            predicates.add(cb.like(user.get("lastName"), filter.lastname()));
//        }
//        if (filter.birthDate() != null) {
//            predicates.add(cb.lessThan(user.get("birthDate"), filter.birthDate()));
//        }
//        criteria.where(predicates.toArray(Predicate[]::new));
//
//        return entityManager.createQuery(criteria).getResultList();
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstName::containsIgnoreCase)
                .add(filter.lastname(), user.lastName::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
