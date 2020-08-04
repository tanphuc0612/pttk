/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Entity.Khachhang;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public class DAO {
    public static List<Object[]> excuteQuery(String query){
        try{
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Query q = s.createQuery(query);
            List<Object[]> result = q.list();
            s.getTransaction().commit();
            return result;
        }catch(HibernateException e){
            System.out.println(e.getMessage());   
        }
        return null;
    } 
    public static boolean Them(Object h){
        try{
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.persist(h);
            s.getTransaction().commit();
            s.close();
            return true;
        }catch(HibernateException e){
            System.out.println(e.getMessage());   
        }
        return false;
    }
    
    public static boolean Update(Object h){
        try{
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(h);
            s.getTransaction().commit();
            s.close();
            return true;
        }catch(HibernateException e){
            System.out.println(e.getMessage());   
        }
        return false;
    }
}
