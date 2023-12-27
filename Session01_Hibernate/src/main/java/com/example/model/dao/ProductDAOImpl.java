package com.example.model.dao;

import com.example.model.entity.Product;
import com.mysql.cj.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> findAll() {
        Session session= sessionFactory.openSession();
        List<Product>products=new ArrayList<>();
        try{
            products=session.createQuery("from Product",Product.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return products;
    }

    @Override
    public Boolean save(Product product) {
        Session session= sessionFactory.openSession();
        try {
            session.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean update(Product product) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public Product findById(Integer id) {
        Session session= sessionFactory.openSession();
        try {
            Product product=session.get(Product.class,id);
            return product;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void changeStatus(Integer id) {

    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        Session session= sessionFactory.openSession();
        List<Product>products=new ArrayList<>();
        try{
            products= session.createNativeQuery("SELECT * from product where category_id=?", Product.class).setParameter(1,categoryId).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return products;
    }
}
