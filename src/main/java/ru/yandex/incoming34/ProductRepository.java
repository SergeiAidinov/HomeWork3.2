package ru.yandex.incoming34;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component("productRepository")
@Scope("singleton")
public class ProductRepository {

    private List<Product> listOfProducts = new ArrayList<>();

    public void addProduct (Product oneProduct){
        listOfProducts.add(oneProduct);
    }

    public void showProducts(){
        Iterator<Product> iterator = listOfProducts.listIterator();
        while (iterator.hasNext()){
            Product currentProduct = iterator.next();
            System.out.println("Item ID: " + currentProduct.getId() + " " + currentProduct.getDescription()
                            + " price: " + currentProduct.getPrice());
        }
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }
}
