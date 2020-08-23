/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Entity.Mathang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class MatHangDB {
    public static List<Mathang> Doc(String query){
        List<Object[]> list = DAO.excuteQuery(query);
        List<Mathang> result = new ArrayList<>();
        if(list != null)
        {
            for(Object o:list){
                result.add((Mathang)o);
            }
        }
        return result;
    }
    
    public static boolean Them(Mathang h){
        return DAO.Them(h);
    }
    
    public static boolean Update(Mathang h){
        return DAO.Update(h);
    }
    
    public static boolean Delete(Mathang h){
        return DAO.Delete(h);
    }
    public static boolean UpdateSL(Mathang h){
        return DAO.Merge(h);
    }
}
