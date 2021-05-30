package ru.yandex.incoming34;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("ru.yandex.incoming34")
public class JavaConfig {

    @Bean(name = "productRepository")
    @Scope("singleton")
    public ProductRepository productRepository(){
        return  new ProductRepository();
    }

    @Bean (name = "product")
    @Scope("prototype")
    public Product product(){ return new Product();}

    @Bean (name = "cart")
    @Scope("prototype")
    public Cart cart(){ return new Cart(productRepository());}



}
