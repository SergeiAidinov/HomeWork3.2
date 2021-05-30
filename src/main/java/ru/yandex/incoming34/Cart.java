package ru.yandex.incoming34;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Component("cart")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {
    private ArrayList<Product> itemsInCart = new ArrayList<Product>();
    @Autowired(required = true)
    ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        System.out.println("Cart created: " + this.hashCode());
        this.productRepository = productRepository;
    }

    public Cart() {

    }

    public void procedure() {
        try {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                if (scanner.hasNext()) {
                    String userInput = scanner.next();

                    if (userInput.length() < 3) {
                        System.out.println("Неверный формат команды");
                        continue;
                    } else {
                        String command = userInput.substring(0, 3);
                        System.out.println("command: " + command);
                        switch (command) {
                            case "SHW": {
                                showItems();
                                break;
                            }

                            case "ADD": {
                                addItem(userInput);
                                break;

                            }
                            default: {
                                System.out.println("Неверный формат команды default");
                                break;
                            }
                        }

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showItems() {
        itemsInCart.trimToSize();
        if (itemsInCart.size() == 0) {
            System.out.println("В корзинке нет товаров.");
        } else {
            System.out.println("В корзинке находятся следующие товары:");
            Iterator<Product> iterator = itemsInCart.listIterator();
            while (iterator.hasNext()) {
                Product currentProduct = iterator.next();
                System.out.println("In CART: Item ID: " + currentProduct.getId() + " " + currentProduct.getDescription()
                        + " price: " + currentProduct.getPrice());
            }
        }

    }

    private void addItem(String userInput) {
        int itemId;

        if (userInput.length() < 4) {
            System.out.println("Требуется указать номер товара");
            return;
        } else {
            try {
                itemId = Integer.parseInt(userInput.substring(3, userInput.length()));
            } catch (NumberFormatException nfex) {
                System.out.println("После имени команды необходимо указать номер товара!");
                return;
            }
            Iterator<Product> iterator = productRepository.getListOfProducts().iterator();
            System.out.println("List: " + productRepository.getListOfProducts().size());
            while (iterator.hasNext()) {
                Product currentProduct = iterator.next();
                if (currentProduct.getId() == itemId) {
                    itemsInCart.add(currentProduct);
                    System.out.println("Item ID: " + currentProduct.getId() + " " + currentProduct.getDescription()
                            + " price: " + currentProduct.getPrice() + " добавлен в корзину.");
                    return;
                }
            }
            System.out.println("Товара с  идентификатором " + itemId + " не существует!");


        }
    }
}

