/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author admin
 */
public class GioHang
{
    private int maMathangGio;
    private String tenGio;
    private int soLuongGio;
    private int giaGio;
    private int thanhTien;

    public GioHang(int maMathangGio, String tenGio, int soLuongGio, int giaGio, int thanhTien) {
        this.maMathangGio = maMathangGio;
        this.tenGio = tenGio;
        this.soLuongGio = soLuongGio;
        this.giaGio = giaGio;
        this.thanhTien = thanhTien;
    }

    public int getMaMathangGio() {
        return maMathangGio;
    }

    public void setMaMathangGio(int maMathangGio) {
        this.maMathangGio = maMathangGio;
    }

    public String getTenGio() {
        return tenGio;
    }

    public void setTenGio(String tenGio) {
        this.tenGio = tenGio;
    }

    public int getSoLuongGio() {
        return soLuongGio;
    }

    public void setSoLuongGio(int soLuongGio) {
        this.soLuongGio = soLuongGio;
    }

    public int getGiaGio() {
        return giaGio;
    }

    public void setGiaGio(int giaGio) {
        this.giaGio = giaGio;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    

}
