//package com.example.model.dao.student;
//
//import com.example.model.entity.Student;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class StudentDAOImpl implements StudentDAO {
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Override
//    public List<Student> findAll() {
//        Session session = sessionFactory.openSession();
//        List<Student> list = new ArrayList<>();
//        try {
//            list = session.createQuery("from Student ", Student.class).list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return list;
//    }
//
//    @Override
//    public Boolean save(Student student) {
//        Session session = sessionFactory.openSession();
//        try {
//            session.save(student);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean update(Student student) {
//        Session session = sessionFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.save(student);
//            session.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return false;
//    }
//
//    @Override
//    public Student findById(Integer id) {
//        Session session = sessionFactory.openSession();
//        try {
//            return session.get(Student.class, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return null;
//    }
//}
