/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.GioHang;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
 * @author admin
 */
public class XacNhanDonHangController implements Initializable {
    @FXML
    private List<GioHang> gioHang;
    @FXML
    private Button xacnhan;
    @FXML
    private TableView<GioHang> gio_hang;
    @FXML
    private TableColumn<GioHang,Integer> maMathangGio;
    @FXML
    private TableColumn<GioHang,String> tenGio;
    @FXML
    private TableColumn<GioHang,Integer> thanhTien;
    @FXML
    private TableColumn<GioHang,Integer> giaGio;
    @FXML
    private TableColumn<GioHang,Integer> soLuongGio;
    @FXML
    private ComboBox<String> httt;
    @FXML
    private TextField tongTien;
    public void init(List<GioHang> gioHang) {
       this.gioHang = gioHang;
       httt.getItems().addAll("Thẻ", "Tiền mặt");
       tongTien.setText("0");
       tongTien.setEditable(false);
    }
    private void GioHangView(){
        maMathangGio.setCellValueFactory(new PropertyValueFactory<>("maMathangGio"));
        tenGio.setCellValueFactory(new PropertyValueFactory<>("tenGio"));
        giaGio.setCellValueFactory(new PropertyValueFactory<>("giaGio"));
        soLuongGio.setCellValueFactory(new PropertyValueFactory<>("soLuongGio"));
        thanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
        long sum = 0;
        /*if(gioHang.size()>0)
            for(GioHang o: gioHang)
                    sum += o.getThanhTien();*/
        tongTien.setText(Long.toString(sum));
        ObservableList<GioHang> list = FXCollections.observableArrayList(gioHang);
        //gio_hang.setItems(list);
        System.out.println("pass2");
    }
    private void XacNhanButton(){
        xacnhan.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(gioHang.isEmpty())
            {
                alert.setHeaderText(null);
                alert.setContentText("Chưa có hàng nào trong giỏ");
                alert.show();
            }
            else
            {
                
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
        //this.init();
        this.GioHangView();
        this.XacNhanButton();
    }    

    
}
