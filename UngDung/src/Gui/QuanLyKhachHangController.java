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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class QuanLyKhachHangController implements Initializable {
    @FXML
    private Button them;   
    @FXML
    private Button xoa; 
    @FXML
    private Button sua;     
    @FXML
    private Button search;    
    @FXML
    private Button home;    
    @FXML
    private TableView<Khachhang> table;  
    @FXML
    private TableColumn<Khachhang,Integer> maKhachHang;
    @FXML
    private TableColumn<Khachhang,String> ten;
    @FXML
    private TableColumn<Khachhang,String> email;
    @FXML
    private TableColumn<Khachhang,String> diaChi;
    @FXML
    private TableColumn<Khachhang,String> sdt;
    @FXML
    private TableColumn<Khachhang,String> pass;
    @FXML
    private TextField ten_field;   
    @FXML
    private TextField ma_field;    
    
    @FXML
    private void TableView(){
        ObservableList<Khachhang> list = FXCollections.observableArrayList();
        for(Khachhang o : Khachhang.DanhSach(ten_field.getText(), ma_field.getText())){
            list.add(new Khachhang(o.getMaKhachHang(),o.getTen(),o.getEmail(),o.getDiaChi(),o.getSdt(),o.getPass()));
        }
        maKhachHang.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
        ten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        sdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));  
        pass.setCellValueFactory(new PropertyValueFactory<>("pass"));   
        //add your data to the table here.
        table.setItems(list);
    }
    
    private void SearchButton(){
        search.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            TableView();
        });
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
    
    private void ThemButton(){
        them.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("ThemKhachHang.fxml",600,600);
            } catch (IOException ex) {
                Logger.getLogger(QuanLyKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }     
    
    private void HomeButton(){
        home.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("NhanVienHome.fxml",500,600);
            } catch (IOException ex) {
                Logger.getLogger(QuanLyKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 
    private void CapNhatButton(){
        sua.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if(table.getSelectionModel().getSelectedItem() != null){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("SuaKhachHang.fxml"));
                    Parent View = loader.load();
                    Scene scene;
                    scene = new Scene(View,650,500); 
                    Stage s = UngDung.getPrimaryStage();
                    SuaKhachHangController controll = loader.getController();
                    controll.Init(table.getSelectionModel().getSelectedItem());
                    s.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(QuanLyKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    } 
    private void XoaButton(){
        xoa.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(table.getSelectionModel().getSelectedItem() != null){
                if(Khachhang.XoaKhacHang(table.getSelectionModel().getSelectedItem())){
                    TableView();
                    alert.setContentText("xóa thành công");
                }else{
                    alert.setContentText("có đơn đặt hàng nê không thể xóa");
                }
                alert.show();
            }
        });
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.TableView();
        this.SearchButton();
        this.ThemButton();
        this.CapNhatButton();
        this.XoaButton();
        this.HomeButton();
    }    
    
}
