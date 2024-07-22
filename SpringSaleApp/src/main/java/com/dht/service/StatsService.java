/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dht.service;

import com.dht.repository.*;
import java.util.List;

/**
 *
 * @author admin
 */
public interface StatsService {
    List<Object[]> statsRevenueByProduct();
    List<Object[]> statsRevenueByPeroid(int year, String peroid);
}
