//package com.example.model.dao.classroom;
//
//import com.example.model.entity.Classroom;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class ClassroomDAOImpl implements ClassroomDAO{
////    @Autowired
////    private SessionFactory sessionFactory;
//    @Override
//    public List<Classroom> findAll() {
//        Session session= sessionFactory.openSession();
//        List<Classroom>classroomList=new ArrayList<>();
//        try{
//            classroomList=session.createQuery("from Classroom ", Classroom.class).list();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//        return classroomList;
//    }
//}
