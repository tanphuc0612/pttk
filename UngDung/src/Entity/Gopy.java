package Entity;
// Generated Aug 3, 2020 8:58:34 PM by Hibernate Tools 4.3.1


import java.util.List;
import Database.GopYDB;
import java.util.Date;

/**
 * Gopy generated by hbm2java
 */


public class Gopy  implements java.io.Serializable {


     private GopyId id;
     private Khachhang khachhang;
     private Mathang mathang;
     private String danhgia;
     private String cmt;
     private Date ngay;
    public Gopy() {
    }


    public Gopy(GopyId id, Khachhang khachhang, Mathang mathang, String cmt,String danhgia,Date ngay) {
       this.id = id;
       this.khachhang = khachhang;
       this.mathang = mathang;
       this.cmt = cmt;
       this.danhgia = danhgia;
       this.ngay = ngay;

    }

    public GopyId getId() {
        return this.id;
    }
    public Date getNgay() {
        return this.ngay;
    }
    
    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
    public void setId(GopyId id) {
        this.id = id;
    }
    public Khachhang getKhachhang() {
        return this.khachhang;
    }
    
    public void setKhachhang(Khachhang khachhang) {
        this.khachhang = khachhang;
    }
    public Mathang getMathang() {
        return this.mathang;
    }
    
    public void setMathang(Mathang mathang) {
        this.mathang = mathang;
    }
    public String getCmt() {
        return this.cmt;
    }
    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
    public String getDanhgia() {
        return this.danhgia;
    }

    
    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }
    public static boolean UpdateGopy(Gopy g){
            GopYDB.Update(g);
        return true;
    }
    public static List<Gopy> DanhSach(String search){
        String query = "From Gopy where 1 = 1";
        if(!search.isEmpty()){
            query += " and  (MaSP LIKE  '%" + search+"%'"+" or MaKH LIKE '%" + search+"%')";
        }
        return GopYDB.Get(query);
    }
     public static List<Gopy> getById(GopyId id){
        String query = "From Gopy where MaSP="+id.getMaSp()+" and MaKH="+id.getMaKh();   
         System.out.print(query);  
        return GopYDB.Get(query);
    }

    public boolean XoaGopY(){
//        GopyId gid = g.getId();
//        Gopy a = Gopy.getById(gid).get(0);
            GopYDB.Delete(this);
            return true;
    }
    public static boolean ThemGopY(Gopy g){
//        GopyId gid = g.getId();
//        Gopy a = Gopy.getById(gid).get(0);
            GopYDB.Add(g);
            return true;
    }
    public static boolean KiemTraTonTai(Gopy g){
        return (!GopYDB.Get("From Gopy where MaKh = " + g.getKhachhang().getMaKhachHang() + " and MaSP = " + g.getMathang().getMaMatHang() + "").isEmpty());
    }

}


