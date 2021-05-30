package ru.yandex.incoming34;


import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ProductRepository productRepository =
                context.getBean("productRepository", ProductRepository.class);

        Product product1 = context.getBean("product", Product.class);
        product1.setPrice(150);
        product1.setId(1);
        product1.setDescription("book");
        productRepository.addProduct(product1);
        Product product2 = context.getBean("product", Product.class);
        product2.setPrice(15);
        product2.setId(2);
        product2.setDescription("pencil");
        productRepository.addProduct(product2);
        Product product3 = context.getBean("product", Product.class);
        product3.setPrice(20);
        product3.setId(3);
        product3.setDescription("ruler");
        productRepository.addProduct(product3);
        Product product4 = context.getBean("product", Product.class);
        product4.setPrice(50);
        product4.setId(4);
        product4.setDescription("notebook");
        productRepository.addProduct(product4);
        Product product5 = context.getBean("product", Product.class);
        product5.setPrice(10);
        product5.setId(5);
        product5.setDescription("ereaser");
        productRepository.addProduct(product5);
        productRepository.showProducts();
        Cart cart1 = context.getBean("cart", Cart.class);
        cart1.procedure();


    }
}
