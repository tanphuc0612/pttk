/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Gopy;
import Entity.GopyId;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Callback;
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
import javafx.scene.control.TableColumn.CellDataFeatures; 
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;


import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class QuanLyGopYController implements Initializable {
    @FXML
    private Button them;   
    @FXML
    private Button xoa; 
    @FXML
    private Button sua;     
    @FXML
    private Button search;    
    @FXML
    private TableView<Gopy> table;  
    @FXML
    private TableColumn<Gopy,Integer> maKhachHang;
    @FXML
    private TableColumn<Gopy,String> tenKhachHang;
    @FXML
    private TableColumn<Gopy,Integer> maMatHang;
    @FXML
    private TableColumn<Gopy,String> tenMatHang;
    @FXML
    private TableColumn<Gopy,String> cmt;
    @FXML
    private TextField search_field;     
    
    @FXML
    private void TableView(){
        ObservableList<Gopy> list = FXCollections.observableArrayList();
        for(Gopy o : Gopy.DanhSach(search_field.getText())){
            list.add(new Gopy(new GopyId(o.getKhachhang().getMaKhachHang(),o.getMathang().getMaMatHang()),o.getKhachhang(),o.getMathang(),o.getCmt()));
        }
        maKhachHang.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKhachhang().getMaKhachHang()).asObject());
        tenKhachHang.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKhachhang().getTen()));
                maMatHang.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMathang().getMaMatHang()).asObject());
                tenMatHang.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMathang().getTen()));
        cmt.setCellValueFactory(new PropertyValueFactory<>("cmt"));   

 
        //add your data to the table here.
        table.setItems(list);
    }
//    
//    private void SearchButton(){
//        search.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
//            TableView();
//        });
//    } 
//    public void Change(String string, int height, int width) throws IOException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource(string));
//        Parent View = loader.load();
//        Scene scene;
//        scene = new Scene(View,width,height); 
//        Stage s = UngDung.getPrimaryStage();
//        s.setScene(scene);
//    }
//    
//    private void ThemButton(){
//        them.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
//            try {
//                Change("ThemKhachHang.fxml",600,600);
//            } catch (IOException ex) {
//                Logger.getLogger(QuanLyGopYController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//    } 
//    private void CapNhatButton(){
//        sua.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
//            if(table.getSelectionModel().getSelectedItem() != null){
//                try {
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("SuaKhachHang.fxml"));
//                    Parent View = loader.load();
//                    Scene scene;
//                    scene = new Scene(View,650,500); 
//                    Stage s = UngDung.getPrimaryStage();
//                    SuaKhachHangController controll = loader.getController();
//                    controll.Init(table.getSelectionModel().getSelectedItem());
//                    s.setScene(scene);
//                } catch (IOException ex) {
//                    Logger.getLogger(QuanLyGopYController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    } 
//    private void XoaButton(){
//        xoa.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            if(table.getSelectionModel().getSelectedItem() != null){
//                if(Gopy.XoaKhacHang(table.getSelectionModel().getSelectedItem())){
//                    TableView();
//                    alert.setContentText("xóa thành công");
//                }else{
//                    alert.setContentText("có đơn đặt hàng nê không thể xóa");
//                }
//                alert.show();
//            }
//        });
//    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.TableView();
//        this.SearchButton();
//        this.ThemButton();
//        this.CapNhatButton();
//        this.XoaButton();
    }    
    
}
