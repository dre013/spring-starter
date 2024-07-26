package by.dre.spring.service;

import by.dre.spring.database.entity.Company;
import by.dre.spring.database.repository.CompanyRepository;
import by.dre.spring.dto.CompanyReadDto;
import by.dre.spring.listener.AccessType;
import by.dre.spring.listener.EntityEvent;
import by.dre.spring.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CompanyReadMapper companyReadMapper;


    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id).map(entity -> {
            applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
            return new CompanyReadDto(entity.getId(), entity.getName());
        });
    }

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }
}
