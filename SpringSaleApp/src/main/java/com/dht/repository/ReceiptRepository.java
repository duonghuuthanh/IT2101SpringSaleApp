/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository;

import com.dht.pojo.Cart;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ReceiptRepository {
    void addReceipt(List<Cart> carts);
}
