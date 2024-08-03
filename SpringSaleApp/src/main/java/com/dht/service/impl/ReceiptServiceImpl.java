/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.service.impl;

import com.dht.pojo.Cart;
import com.dht.repository.ReceiptRepository;
import com.dht.service.ReceiptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private  ReceiptRepository receiptRepo;

    @Override
    public void addReceipt(List<Cart> carts) {
       this.receiptRepo.addReceipt(carts);
    }
    
}
