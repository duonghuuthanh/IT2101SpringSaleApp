/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.dht.hibernatedemo;

import com.dht.pojo.Cart;
import com.dht.repository.impl.CategoryRepositoryImpl;
import com.dht.repository.impl.ProductRepositoryImpl;
import com.dht.repository.impl.ReceiptRepositoryImpl;
import com.dht.repository.impl.StatsRepositoryImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class HibernateDemo {

    public static void main(String[] args) {
        StatsRepositoryImpl s = new StatsRepositoryImpl();
        s.statsRevenueByPeroid(2020, "QUARTER").forEach(o -> System.out.printf("%s: %d\n", o[0], o[1]));
//        List<Cart> carts = new ArrayList<>();
//        carts.add(new Cart(1, "A", 2, 100l));
//        
//        ReceiptRepositoryImpl r = new ReceiptRepositoryImpl();
//        r.addReceipt(carts);
        
//        CategoryRepositoryImpl s = new CategoryRepositoryImpl();
//        s.getCates().forEach(c -> System.out.println(c.getName()));
//        Map<String, String> params = new HashMap<>();
//        params.put("q", "iPhone");
//        params.put("page", "2");

//        ProductRepositoryImpl s = new ProductRepositoryImpl();
//        s.getProducts(params).forEach(p -> System.out.printf("%s - %.1f\n", p.getName(), p.getPrice()));
    }
}
