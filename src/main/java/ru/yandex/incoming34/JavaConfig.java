package ru.yandex.incoming34;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("ru.yandex.incoming34")
public class JavaConfig {

    @Bean(name = "productRepository")
    public ProductRepository productRepository(){
        return  new ProductRepository();
    }

    @Bean (name = "product")
    public Product product(){
        return new Product();
    }
}
