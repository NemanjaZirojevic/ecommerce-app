package com.oldtimers.demo.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oldtimers.demo.entity.Products;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by nemanja.zirojevic on 07/05/2020.
 */
@Component
public class Utility {

    public   List<Products> createProductListFromJson(String productsString){
        Gson gson = new Gson();
        List<Products> products = gson.fromJson(productsString, new TypeToken<List<Products>>() {}.getType());
        products.removeIf(Objects::isNull);
        return products;
    }

    public  double summListItemPrice(List<Products> products){
        double total = products.stream()
                .mapToDouble(pr->pr.getProductQuantity()*pr.getProductPrice())
                .sum();
        return total;
    }



}
