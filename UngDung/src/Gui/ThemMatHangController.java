/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import Entity.Mathang;
import Entity.Loaihang;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ThemMatHangController implements Initializable {
    
    @FXML
    private Button them;     
    @FXML
    private Button quan_ly;   
    @FXML
    private TextField ten;     
    @FXML
    private TextField loai;  
    @FXML
    private TextField gia;     
    @FXML
    private TextField soLuong;
    
    public void Change(String string, int height, int width) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(string));
        Parent View = loader.load();
        Scene scene;
        scene = new Scene(View,width,height); 
        Stage s = UngDung.getPrimaryStage();
        s.setScene(scene);
    }
    
    private void ThemButton(){
        them.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(ten.getText().isEmpty()||loai.getText().isEmpty()||gia.getText().isEmpty()||soLuong.getText().isEmpty()){
                alert.setContentText("Cần nhập đủ thông tin");
            }else{
                int maloaihang = Integer.parseInt(loai.getText());
                
                
//                Logger.getLogger(QuanLyMatHangController.class.getName()).log(Level.SEVERE, Loaihang.LayLoaiHang(maloaihang).toString());
                
                if (Loaihang.KiemTraTonTai(maloaihang)) {
                    if(Mathang.ThemMatHang(new Mathang(maloaihang,ten.getText(),Integer.parseInt(gia.getText()),Integer.parseInt(soLuong.getText())))){
                    alert.setContentText("Thêm thành công");
                    }else{
                        alert.setContentText("Khách hàng đã tồn tại");
                    }
                }else{
                    alert.setContentText("Mã loại hàng không tồn tại");
                }
                
                
            }
            alert.show();
        });
    } 
    
    private void QuanLyButton(){
        quan_ly.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("QuanLyMatHang.fxml",550,1080);
            } catch (IOException ex) {
                Logger.getLogger(ThemMatHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.QuanLyButton();
        this.ThemButton();
    }    
    
}
