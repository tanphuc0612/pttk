/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class NhanVienHomeController implements Initializable {
    @FXML
    private Button quan_ly_khach_hang;
    @FXML
    private Button quan_ly_gop_y;
    @FXML
    private Button quan_ly_mat_hang;
    @FXML
    private Button dang_xuat;
    public void Change(String string, int height, int width) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(string));
        Parent View = loader.load();
        Scene scene;
        scene = new Scene(View,width,height); 
        Stage s = UngDung.getPrimaryStage();
        s.setScene(scene);
    }
    
    private void QuanLyButton(){
        quan_ly_khach_hang.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("QuanLyKhachHang.fxml",550,1080);
            } catch (IOException ex) {
                Logger.getLogger(NhanVienHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        quan_ly_gop_y.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("QuanLyGopY.fxml",550,1080);
                 } catch (IOException ex) {
                Logger.getLogger(NhanVienHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        quan_ly_mat_hang.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("QuanLyMatHang.fxml",550,970);
            } catch (IOException ex) {
                Logger.getLogger(NhanVienHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        dang_xuat.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("DangNhap.fxml",550,550);
            } catch (IOException ex) {
                Logger.getLogger(NhanVienHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.QuanLyButton();
    }    
    
}
