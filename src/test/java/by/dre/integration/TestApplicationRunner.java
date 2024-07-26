package by.dre.integration;

import by.dre.spring.database.repository.pool.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;

@TestConfiguration
public class TestApplicationRunner {

    @SpyBean(name = "connectionPool1")
    private ConnectionPool pool1;
}
