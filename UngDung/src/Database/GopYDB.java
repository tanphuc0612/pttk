/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Entity.Gopy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class GopYDB {
    public static List<Gopy> Doc(String query){
            List<Object[]> list = DAO.excuteQuery(query);
            List<Gopy> result = new ArrayList<>();
            if(list != null)
            {
                for(Object o:list){
                    result.add((Gopy)o);
                }
            }
            return result;
    }
    public static boolean Them(Gopy h){
        return DAO.Them(h);
    }
    
    public static boolean Update(Gopy h){
        return DAO.Update(h);
    }
    
    public static boolean Delete(Gopy h){
        return DAO.Delete(h);
    }
}
