/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository.impl;

import com.dht.pojo.Cart;
import com.dht.pojo.OrderDetail;
import com.dht.pojo.SaleOrder;
import com.dht.repository.ProductRepository;
import com.dht.repository.ReceiptRepository;
import com.dht.repository.UserRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private UserRepository useRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addReceipt(List<Cart> carts) {

        if (carts != null) {
            Session s = this.factory.getObject().getCurrentSession();
            SaleOrder order = new SaleOrder();
            order.setUserId(useRepo.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            order.setCreatedDate(new Date());
            s.save(order);

            for (Cart c : carts) {
                OrderDetail d = new OrderDetail();
                d.setUnitPrice(c.getUnitPrice());
                d.setQuantity(c.getQuantity());
                d.setProductId(productRepo.getProductById(c.getId()));
                d.setOrderId(order);

                s.save(d);
            }
        }
    }

}
