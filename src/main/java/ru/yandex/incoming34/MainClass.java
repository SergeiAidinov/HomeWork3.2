package ru.yandex.incoming34;


import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
       ProductRepository productRepository =
                context.getBean("productRepository", ProductRepository.class);

        Product product1 = context.getBean("product", Product.class);
       product1.setPrice(50);
        product1.setPrice(1);
        product1.setDescription("book");
        productRepository.addProduct(product1);
        //productRepository.addProduct(product2);
       //productRepository.addProduct(product3);
       productRepository.showProducts();
    }
}
