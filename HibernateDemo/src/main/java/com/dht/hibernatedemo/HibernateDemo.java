/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.dht.hibernatedemo;

import com.dht.pojo.Cart;
import com.dht.repository.impl.CategoryRepositoryImpl;
import com.dht.repository.impl.ReceiptRepositoryImpl;
import com.dht.repository.impl.StatsRepositoryImpl;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author admin
 */
public class HibernateDemo {

    public static void main(String[] args) {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(1, "iPhone 7", 2, 200l));
        
        ReceiptRepositoryImpl r = new ReceiptRepositoryImpl();
        r.addReceipt(carts);
//        StatsRepositoryImpl s = new StatsRepositoryImpl();
//        s.statsRevenueByProduct().forEach(o -> System.out.printf("%d - %s: %d VND\n", o[0], o[1], o[2]));
//        
//        s.statsRevenueByProduct(2020, "QUARTER").forEach(o -> System.out.printf("%d: %d VND\n", o[0], o[1]));
//        CategoryRepositoryImpl s = new CategoryRepositoryImpl();
//        s.getCates().forEach(c -> System.out.println(c.getName()));
//        
//        System.out.println("===");
//        Map<String, String> params = new HashMap<>();
//        params.put("page", "2");
//        params.put("fromPrice", "30000000");
        
//        ProductRepositoryImpl s2 = new ProductRepositoryImpl();
//        s2.getProducts(params).forEach(p -> System.out.printf("%s - %.1f\n", p.getName(), p.getPrice()));
    }
}
