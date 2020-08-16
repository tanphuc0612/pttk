/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author USER
 */

import Entity.Loaihang;
import java.util.ArrayList;
import java.util.List;

public class LoaiHangDB {
    public static List<Loaihang> Doc(String query){
        List<Object[]> list = DAO.excuteQuery(query);
        List<Loaihang> result = new ArrayList<>();
        if(list != null)
        {
            for(Object o:list){
                result.add((Loaihang)o);
            }
        }
        
        return result;
    }
}
