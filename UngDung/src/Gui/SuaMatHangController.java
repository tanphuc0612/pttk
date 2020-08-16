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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SuaMatHangController implements Initializable {
    
    @FXML
    private Button sua;
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
    @FXML
    private Label ten_cu;
    @FXML
    private Label loai_cu;
    @FXML
    private Label gia_cu;
    @FXML
    private Label soLuong_cu;
    
    private Mathang mathang;
    
    public void Init(Mathang h){
        ten_cu.setText(h.getTen());
        loai_cu.setText(h.getLoaihang().toString());
        gia_cu.setText(Integer.toString(h.getGia()));
        soLuong_cu.setText(Integer.toString(h.getSoLuong()));
        mathang = h;
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
                Change("QuanLyMatHang.fxml",550,1080);
            } catch (IOException ex) {
                Logger.getLogger(SuaMatHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 
    
    private void SuaButton(){
        sua.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(!ten.getText().isEmpty()){
                mathang.setTen(ten.getText());
            }
            if(!loai.getText().isEmpty()){
                int maloaihang = Integer.parseInt(loai.getText());
                
                if (Loaihang.KiemTraTonTai(maloaihang)) {
                    
                    mathang.setLoaihang(maloaihang);
                }else{
                    alert.setContentText("Mã loại hàng không tồn tại");
                    alert.show();
                    
                    return;
                }
            }
            if(!gia.getText().isEmpty()){
                mathang.setGia(Integer.parseInt(gia.getText()));
            }
            if(!soLuong.getText().isEmpty()){
                mathang.setSoLuong(Integer.parseInt(soLuong.getText()));
            }
            if(Mathang.UpdateMatHang(mathang)){
                alert.setContentText("thành công");
                alert.show();
                Init(mathang);
            }else{
                alert.setContentText("bị trùng tên với mặt hàng khác");
                alert.show();                
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
        this.SuaButton();
    }    
    
}
