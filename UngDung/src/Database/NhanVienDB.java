/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.List;

/**
 *
 * @author Hp
 */
public class NhanVienDB {
    public static List<Object[]> Doc(int ma, String pass){
        return DAO.excuteQuery("From Nhanvien where maNhanVien = " + ma + " and pass = '" + pass + "'");
    }
}
