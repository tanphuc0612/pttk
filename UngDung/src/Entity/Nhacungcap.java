package Entity;
// Generated Aug 3, 2020 8:58:34 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Nhacungcap generated by hbm2java
 */
public class Nhacungcap  implements java.io.Serializable {


     private int maNcc;
     private String ten;
     private String diaChi;
     private Set dontrahangs = new HashSet(0);
     private Set donnhaphangs = new HashSet(0);

    public Nhacungcap() {
    }

	
    public Nhacungcap(int maNcc, String ten, String diaChi) {
        this.maNcc = maNcc;
        this.ten = ten;
        this.diaChi = diaChi;
    }
    public Nhacungcap(int maNcc, String ten, String diaChi, Set dontrahangs, Set donnhaphangs) {
       this.maNcc = maNcc;
       this.ten = ten;
       this.diaChi = diaChi;
       this.dontrahangs = dontrahangs;
       this.donnhaphangs = donnhaphangs;
    }
   
    public int getMaNcc() {
        return this.maNcc;
    }
    
    public void setMaNcc(int maNcc) {
        this.maNcc = maNcc;
    }
    public String getTen() {
        return this.ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getDiaChi() {
        return this.diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public Set getDontrahangs() {
        return this.dontrahangs;
    }
    
    public void setDontrahangs(Set dontrahangs) {
        this.dontrahangs = dontrahangs;
    }
    public Set getDonnhaphangs() {
        return this.donnhaphangs;
    }
    
    public void setDonnhaphangs(Set donnhaphangs) {
        this.donnhaphangs = donnhaphangs;
    }




}


