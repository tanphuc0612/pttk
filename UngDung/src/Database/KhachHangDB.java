/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Entity.Khachhang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class KhachHangDB {
    public static List<Khachhang> Doc(String query){
            List<Object[]> list = DAO.excuteQuery(query);
            List<Khachhang> result = new ArrayList<>();
            if(list != null)
            {
                for(Object o:list){
                    result.add((Khachhang)o);
                }
            }
            return result;
    }
    public static boolean Them(Khachhang h){
        return DAO.Them(h);
    }
    
    public static boolean Update(Khachhang h){
        return DAO.Update(h);
    }
}
