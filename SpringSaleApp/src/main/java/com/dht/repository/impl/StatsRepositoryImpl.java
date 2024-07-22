/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository.impl;

import com.dht.pojo.OrderDetail;
import com.dht.pojo.Product;
import com.dht.pojo.SaleOrder;
import com.dht.repository.StatsRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> statsRevenueByProduct() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rP = q.from(Product.class);
        Root rD = q.from(OrderDetail.class);

        q.where(b.equal(rP.get("id"), rD.get("productId")));

        q.multiselect(rP.get("id"), rP.get("name"),
                b.sum(b.prod(rD.get("quantity"),
                        rD.get("unitPrice"))));
        q.groupBy(rP.get("id"));

        Query query = s.createQuery(q);

        return query.getResultList();

    }

    @Override
    public List<Object[]> statsRevenueByPeroid(int year, String peroid) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rO = q.from(SaleOrder.class);
        Root rD = q.from(OrderDetail.class);

        q.where(b.equal(rO.get("id"), rD.get("orderId")),
                b.equal(b.function("YEAR", Integer.class, rO.get("createdDate")), year));

        q.multiselect(b.function(peroid, Integer.class, rO.get("createdDate")),
                b.sum(b.prod(rD.get("quantity"), rD.get("unitPrice"))));
        q.groupBy(b.function(peroid, Integer.class, rO.get("createdDate")));

        Query query = s.createQuery(q);

        return query.getResultList();
    }

}
