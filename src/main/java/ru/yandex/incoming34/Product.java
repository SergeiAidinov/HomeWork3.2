package ru.yandex.incoming34;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("product")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Product  {

    private int id;

    private String description;

    private int price;

    public Product(int id, String description, int price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Product(int id) {
        this.id = id;
    }

    @PostConstruct
    public void ready(){
        System.out.println("Bean created " +this.hashCode());
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
