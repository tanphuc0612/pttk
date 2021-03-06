package Entity;
// Generated Aug 3, 2020 8:58:34 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Dontrahang generated by hbm2java
 */
public class Dontrahang  implements java.io.Serializable {


     private int maDonTra;
     private Nhacungcap nhacungcap;
     private Nhanvien nhanvien;
     private Date ngayLap;
     private String lyDo;
     private Set chitietdontras = new HashSet(0);

    public Dontrahang() {
    }

	
    public Dontrahang(int maDonTra, Nhacungcap nhacungcap, Nhanvien nhanvien, Date ngayLap, String lyDo) {
        this.maDonTra = maDonTra;
        this.nhacungcap = nhacungcap;
        this.nhanvien = nhanvien;
        this.ngayLap = ngayLap;
        this.lyDo = lyDo;
    }
    public Dontrahang(int maDonTra, Nhacungcap nhacungcap, Nhanvien nhanvien, Date ngayLap, String lyDo, Set chitietdontras) {
       this.maDonTra = maDonTra;
       this.nhacungcap = nhacungcap;
       this.nhanvien = nhanvien;
       this.ngayLap = ngayLap;
       this.lyDo = lyDo;
       this.chitietdontras = chitietdontras;
    }
   
    public int getMaDonTra() {
        return this.maDonTra;
    }
    
    public void setMaDonTra(int maDonTra) {
        this.maDonTra = maDonTra;
    }
    public Nhacungcap getNhacungcap() {
        return this.nhacungcap;
    }
    
    public void setNhacungcap(Nhacungcap nhacungcap) {
        this.nhacungcap = nhacungcap;
    }
    public Nhanvien getNhanvien() {
        return this.nhanvien;
    }
    
    public void setNhanvien(Nhanvien nhanvien) {
        this.nhanvien = nhanvien;
    }
    public Date getNgayLap() {
        return this.ngayLap;
    }
    
    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    public String getLyDo() {
        return this.lyDo;
    }
    
    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
    public Set getChitietdontras() {
        return this.chitietdontras;
    }
    
    public void setChitietdontras(Set chitietdontras) {
        this.chitietdontras = chitietdontras;
    }




}


