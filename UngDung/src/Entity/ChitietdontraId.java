package Entity;
// Generated Aug 3, 2020 8:58:34 PM by Hibernate Tools 4.3.1



/**
 * ChitietdontraId generated by hbm2java
 */
public class ChitietdontraId  implements java.io.Serializable {


     private int maMh;
     private int maDonTra;

    public ChitietdontraId() {
    }

    public ChitietdontraId(int maMh, int maDonTra) {
       this.maMh = maMh;
       this.maDonTra = maDonTra;
    }
   
    public int getMaMh() {
        return this.maMh;
    }
    
    public void setMaMh(int maMh) {
        this.maMh = maMh;
    }
    public int getMaDonTra() {
        return this.maDonTra;
    }
    
    public void setMaDonTra(int maDonTra) {
        this.maDonTra = maDonTra;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ChitietdontraId) ) return false;
		 ChitietdontraId castOther = ( ChitietdontraId ) other; 
         
		 return (this.getMaMh()==castOther.getMaMh())
 && (this.getMaDonTra()==castOther.getMaDonTra());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getMaMh();
         result = 37 * result + this.getMaDonTra();
         return result;
   }   


}


