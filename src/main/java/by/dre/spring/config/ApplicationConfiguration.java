package by.dre.spring.config;

import by.dre.spring.database.repository.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.*;

@Configuration
@ConfigurationPropertiesScan
public class ApplicationConfiguration {

    @Bean("connectionPool1")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool connectionPool(@Value("${db.username}") String username) {
        return new ConnectionPool(username, "123", 20, "localhost:5432");
    }
//    @Bean
//    @Profile("prod&web")
//    public ConnectionPool connectionPool2() {
//        return new ConnectionPool("mysql", "111", 100, "localhost:2345");
//    }
}
