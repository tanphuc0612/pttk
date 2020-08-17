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
 * @author USER
 */
public class QuanLyMatHangController implements Initializable {
    @FXML
    private Button them;   
    @FXML
    private Button xoa; 
    @FXML
    private Button sua;     
    @FXML
    private Button search;    
    @FXML
    private TableView<Mathang> table;  
    @FXML
    private TableColumn<Mathang,Integer> maMathang;
    @FXML
    private TableColumn<Mathang,String> ten;
    @FXML
    private TableColumn<Mathang,Integer> loaiHang;
    @FXML
    private TableColumn<Mathang,Integer> gia;
    @FXML
    private TableColumn<Mathang,Integer> soLuong;
    @FXML
    private TextField ten_field;   
    @FXML
    private TextField ma_field;
    @FXML
    private TextField loai_field; 
    
    @FXML
    private void TableView(){
        ObservableList<Mathang> list = FXCollections.observableArrayList();
        for(Mathang o : Mathang.DanhSach(ten_field.getText(), ma_field.getText())){
            list.add(new Mathang(o.getMaMatHang(),o.getLoaihang(),o.getTen(),o.getGia(),o.getSoLuong()));
        }
        
        maMathang.setCellValueFactory(new PropertyValueFactory<>("MaMatHang"));
        ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        loaiHang.setCellValueFactory(new PropertyValueFactory<>("loaihang"));
        gia.setCellValueFactory(new PropertyValueFactory<>("Gia"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));

        
        table.setItems(list);
        
    }

    private void SearchButton(){
        search.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            TableView();
        });
    } 
    
    private void ThemButton(){
        them.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("ThemMatHang.fxml",600,600);
            } catch (IOException ex) {
                Logger.getLogger(QuanLyMatHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 
    
    private void CapNhatButton(){
        sua.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if(table.getSelectionModel().getSelectedItem() != null){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("SuaMatHang.fxml"));
                    Parent View = loader.load();
                    Scene scene;
                    scene = new Scene(View,650,500); 
                    Stage s = UngDung.getPrimaryStage();
                    SuaMatHangController controll = loader.getController();
                    controll.Init(table.getSelectionModel().getSelectedItem());
                    s.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(QuanLyMatHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    } 
    
    private void XoaButton(){
        xoa.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(table.getSelectionModel().getSelectedItem() != null){
                if(Mathang.XoaMatHang(table.getSelectionModel().getSelectedItem())){
                    TableView();
                    alert.setContentText("xóa thành công");
                }else{
                    alert.setContentText("có đơn đặt hàng nê không thể xóa");
                }
                alert.show();
            }
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.TableView();
        this.SearchButton();
        this.ThemButton();
        this.CapNhatButton();
        this.XoaButton();
    }    
    
}
