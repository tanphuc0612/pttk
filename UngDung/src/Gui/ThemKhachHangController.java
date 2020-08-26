/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Khachhang;
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
 * @author Hp
 */
public class ThemKhachHangController implements Initializable {

    @FXML
    private Button them;     
    @FXML
    private Button quan_ly;   
    @FXML
    private TextField ten;     
    @FXML
    private TextField email;  
    @FXML
    private TextField dia_chi;     
    @FXML
    private TextField sdt;  
    @FXML
    private TextField pass; 
    
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
            if(ten.getText().isEmpty()||email.getText().isEmpty()||dia_chi.getText().isEmpty()||sdt.getText().isEmpty()||pass.getText().isEmpty()){
                alert.setContentText("Cần nhập đủ thông tin");
            }else if(!Khachhang.CheckEmail(email.getText()))
            {
                alert.setContentText("email không hợp lệ");
            }
            else{
                if(Khachhang.ThemKhacHang(new Khachhang(ten.getText(),email.getText(),dia_chi.getText(),sdt.getText(),pass.getText()))){
                    alert.setContentText("Thêm thành công");
                }else{
                    alert.setContentText("Khách hàng đã tồn tại");
                }
            }
            alert.show();
        });
    } 
    
    private void QuanLyButton(){
        quan_ly.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("QuanLyKhachHang.fxml",550,1080);
            } catch (IOException ex) {
                Logger.getLogger(ThemKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.QuanLyButton();
        this.ThemButton();
    }    
    
}
