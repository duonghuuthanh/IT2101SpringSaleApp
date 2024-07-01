/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository.impl;

import com.dht.hibernatedemo.HibernateUtils;
import com.dht.pojo.Cart;
import com.dht.pojo.OrderDetail;
import com.dht.pojo.SaleOrder;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class ReceiptRepositoryImpl {
    private UserRepositoryImpl u = new UserRepositoryImpl();
    private ProductRepositoryImpl p = new ProductRepositoryImpl();
    
    public void addReceipt(List<Cart> carts) {
        try (Session s = HibernateUtils.getFactory().openSession()) {
            s.getTransaction().begin();
            SaleOrder receipt = new SaleOrder();
            receipt.setUserId(u.getUserById("dhthanh"));
            receipt.setCreatedDate(new Date());
            s.merge(receipt);
            
            for (var c: carts) {
                OrderDetail d = new OrderDetail();
                d.setQuantity(c.getQuantity());
                d.setUnitPrice(c.getUnitPrice());
                d.setOrderId(receipt);
                d.setProductId(p.getProductId(c.getId()));
                
                s.merge(d);
            }
            s.getTransaction().commit();
        }
    }
}
