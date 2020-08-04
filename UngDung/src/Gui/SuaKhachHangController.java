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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class SuaKhachHangController implements Initializable {

    @FXML
    private Button sua;
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
    @FXML
    private Label ten_cu;
    @FXML
    private Label email_cu;
    @FXML
    private Label dia_chi_cu;
    @FXML
    private Label sdt_cu;
    @FXML
    private Label pass_cu;
    
    private Khachhang khach;
    
    public void Init(Khachhang h){
        ten_cu.setText(h.getTen());
        email_cu.setText(h.getEmail());
        dia_chi_cu.setText(h.getDiaChi());
        sdt_cu.setText(h.getSdt());
        pass_cu.setText(h.getPass());
        khach = h;
    }
    
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
        quan_ly.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("QuanLyKhachHang.fxml",550,1080);
            } catch (IOException ex) {
                Logger.getLogger(SuaKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 
    
    private void SuaButton(){
        sua.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(!ten.getText().isEmpty()){
                khach.setTen(ten.getText());
            }
            if(!email.getText().isEmpty()){
                khach.setEmail(email.getText());
            }
            if(!dia_chi.getText().isEmpty()){
                khach.setDiaChi(dia_chi.getText());
            }
            if(!sdt.getText().isEmpty()){
                khach.setSdt(sdt.getText());
            }
            if(!pass.getText().isEmpty()){
                khach.setPass(pass.getText());
            }
            if(Khachhang.UpdateKhacHang(khach)){
                alert.setContentText("thành công");
                alert.show();
                Init(khach);
            }else{
                alert.setContentText("bị trùng email hoặc sdt với khách hàng khác");
                alert.show();                
            }
        });
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.QuanLyButton();
        this.SuaButton();
    }    
    
}
