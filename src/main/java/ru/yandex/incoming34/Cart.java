package ru.yandex.incoming34;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
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
                System.out.println("Формат команды: SHW - показать товары в корзинке." + "\n"
                        + "ADD# - добавить товар в корзинку," + " где # - номер товара." + "\n"
                        + "DLT# - удалить товар из корзинки," + " где # - номер товара.");
                System.out.println("Ваш ввод: ");
                if (scanner.hasNext()) {
                    String userInput = scanner.next();


                    if (userInput.length() < 3) {
                        System.out.println("Неверный формат команды");
                        continue;
                    } else {
                        String command = userInput.substring(0, 3);
                        switch (command) {
                            case "SHW": {
                                showItems();
                                break;
                            }

                            case "ADD": {
                                addItem(userInput);
                                break;
                            }

                            case "DLT": {
                                deleteItem(userInput);
                                break;
                            }

                            default: {
                                System.out.println("Неверный формат команды.");
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

    private void deleteItem(String userInput) {
        int itemId = 0;
        if (!checkFormatOfCommand(userInput)) {
            return;
        }
        try {
            itemId = Integer.parseInt(userInput.substring(3, userInput.length()));
        } catch (NumberFormatException nfex) {
            System.out.println("После имени команды необходимо указать номер товара!");
            return;
        }
        itemsInCart.trimToSize();
        if (itemsInCart.size() == 0) {
            System.out.println("Корзинка пуста");
            return;
        }
        Iterator<Product> iterator = itemsInCart.iterator();

        while (iterator.hasNext()) {
            Product currentProduct = iterator.next();
            if (currentProduct.getId() == itemId) {
                itemsInCart.remove(currentProduct);
                System.out.println("Item ID: " + currentProduct.getId() + " " + currentProduct.getDescription()
                        + " price: " + currentProduct.getPrice() + " удален из корзинки.");
                return;
            }
        }
        System.out.println("Товара с  идентификатором " + itemId + " в корзинке нет!");
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
        int itemId = 0;
        if (!checkFormatOfCommand(userInput)) {
            return;
        }
        try {
            itemId = Integer.parseInt(userInput.substring(3, userInput.length()));
        } catch (NumberFormatException nfex) {
            System.out.println("После имени команды необходимо указать номер товара!");
            return;

        }
        Iterator<Product> iterator = productRepository.getListOfProducts().iterator();
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

    private boolean checkFormatOfCommand(String userInput) {
        if (userInput.length() < 4) {
            System.out.println("Требуется указать номер товара");
            return false;
        } else {
            return true;
        }

    }

}



