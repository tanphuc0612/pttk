package Entity;
// Generated Aug 3, 2020 8:58:34 PM by Hibernate Tools 4.3.1


import Database.DAO;
import Database.KhachHangDB;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Khachhang generated by hbm2java
 */
public class Khachhang  implements java.io.Serializable {


     private Integer maKhachHang;
     private String ten;
     private String email;
     private String diaChi;
     private String sdt;
     private String pass;
     private Set gopies = new HashSet(0);
     private Set dondathangs = new HashSet(0);
     private boolean bichan;

    public Khachhang() {
    }

	
    public Khachhang(Integer maKhachHang, String ten, String email, String diaChi, String sdt, String pass) {
        this.ten = ten;
        this.email = email;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.pass = pass;
        this.maKhachHang = maKhachHang;
        this.bichan = false;
    }    
    
    public Khachhang(String ten, String email, String diaChi, String sdt, String pass) {
        this.ten = ten;
        this.email = email;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.pass = pass;
         this.bichan = false;
    }
    
    public Khachhang(String ten, String email, String diaChi, String sdt, String pass, Set gopies, Set dondathangs) {
       this.ten = ten;
       this.email = email;
       this.diaChi = diaChi;
       this.sdt = sdt;
       this.pass = pass;
       this.gopies = gopies;
       this.dondathangs = dondathangs;
        this.bichan = false;
    }
   
    public Integer getMaKhachHang() {
        return this.maKhachHang;
    }
    
    public void setMaKhachHang(Integer maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    public String getTen() {
        return this.ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDiaChi() {
        return this.diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public String getSdt() {
        return this.sdt;
    }
    
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    public Set getGopies() {
        return this.gopies;
    }
    
    public void setGopies(Set gopies) {
        this.gopies = gopies;
    }
    public Set getDondathangs() {
        return this.dondathangs;
    }
    
    public void setDondathangs(Set dondathangs) {
        this.dondathangs = dondathangs;
    }

    public static List<Khachhang> DanhSach(String ten, String ma){
        String query = "From Khachhang where 1 = 1";
        if(!ma.isEmpty()){
            query += " and maKhachHang = " + ma;
        }
        if(!ten.isEmpty()){
            query += " and ten LIKE '%" + ten + "%'";
        }
        return KhachHangDB.Doc(query);
    }
    
    public static boolean ThemKhacHang(Khachhang h){
        if(!KiemTraTonTai(h)){
            KhachHangDB.Them(h);
            return true;
        }
        return false;
    }
    
    public static boolean KiemTraTonTai(Khachhang h){
        return (!KhachHangDB.Doc("From Khachhang where email = '" + h.getEmail() + "' or sdt = '" + h.getSdt() + "'").isEmpty());
    }
    
    public static boolean KiemTraTonTaiUpdate(Khachhang h){
        return (!KhachHangDB.Doc("From Khachhang where (email = '" + h.getEmail() + "' or sdt = '" + h.getSdt() + "') and maKhachHang != " + h.getMaKhachHang()).isEmpty());
    }
    
    public static boolean UpdateKhacHang(Khachhang h){
        if(!KiemTraTonTaiUpdate(h)){
            KhachHangDB.Update(h);
            return true;
        }
        return false;
    }
    public static boolean CheckEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
    
    public static boolean XoaKhacHang(Khachhang h){
        if(!Dondathang.KiemTraTonTai(h.maKhachHang)){
            KhachHangDB.Delete(h);
            return true;
        }
        return false;
    }
    public static boolean CheckPass(String email, String pass){
        String query = "From Khachhang where 1 = 1";
        if(!email.isEmpty()){
            query += " and email = '" + email + "'";
        }
        if(!pass.isEmpty()){
            query += " and pass = '" + pass + "'";
        }
        return (!KhachHangDB.Doc(query).isEmpty());
    }
    public static Khachhang LayKhachHang(String email){
        String query = "From Khachhang where 1 = 1";
        query += " and email = '" + email + "'";
        return KhachHangDB.Doc(query).get(0);
    }
    public boolean isBichan() {
        return bichan;
    }

    public void setBichan(boolean bichan) {
        this.bichan = bichan;
    }
}


