/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Entity.Dondathang;
import Entity.Chitietdondat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class DonDatHangDB {
    public static List<Dondathang> Doc(String query){
            List<Object[]> list = DAO.excuteQuery(query);
            List<Dondathang> result = new ArrayList<>();
            if(list != null)
            {
                for(Object o:list){
                    result.add((Dondathang)o);
                }
            }
        return result;
    }
    public static int DocMaxMa(String query){
            List list = DAO.excuteQuery(query);
            return (int)list.get(0);
    }
    public static boolean Them(Dondathang d){
        return DAO.Them(d);
    }
    public static boolean ThemChiTiet(Chitietdondat ct){
        return DAO.Them(ct);
    }
}
