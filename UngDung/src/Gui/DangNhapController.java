/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Khachhang;
import Entity.Nhanvien;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DangNhapController implements Initializable {
    @FXML
    private Button dang_nhap;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;    
    
    public void Change(String string, int height, int width) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(string));
        Parent View = loader.load();
        Scene scene;
        scene = new Scene(View,width,height); 
        Stage s = UngDung.getPrimaryStage();
        s.setScene(scene);
    }
    
    private void DangNhapButton(){
        dang_nhap.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(username.getText().isEmpty()||pass.getText().isEmpty()){
                alert.setContentText("Cần điền đầy đủ thông tin");
                alert.show();
            }
            else if(Nhanvien.CheckPass(username.getText(), pass.getText())){
                try {
                    Change("NhanVienHome.fxml",500,600);
                } catch (IOException ex) {
                    Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(Khachhang.CheckPass(username.getText(), pass.getText()))
            {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("KhachHangHome.fxml"));
                    Parent View = loader.load();
                    Scene scene;
                    scene = new Scene(View,1340,590); 
                    Stage s = UngDung.getPrimaryStage();
                    KhachHangHomeController controll = loader.getController();
                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    s.setX((screenBounds.getWidth() - 1340) / 2);
                    s.setY((screenBounds.getHeight() - 590) / 2);
                    controll.getEmail(username.getText());
                    s.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                alert.setContentText("Sai pass hoặc sai username");
                alert.show();
            }
        });
    }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.DangNhapButton();
    }    
    
}
