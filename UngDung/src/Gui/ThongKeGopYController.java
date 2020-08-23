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
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.TableCell;
import javafx.print.Paper;
import javafx.print.PrinterJob;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ThongKeGopYController implements Initializable {

    private Button quaylai;
    @FXML
    private Button thongke;    
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
    private TableColumn<Gopy,String> danhGia;
    @FXML
    private TableColumn<Gopy,Date> ngay;
    @FXML
    private TextField search_field;     
    
    @FXML
    private void TableView(){
        ObservableList<Gopy> list = FXCollections.observableArrayList();
        for(Gopy o : Gopy.DanhSach(search_field.getText())){
            list.add(new Gopy(new GopyId(o.getMathang().getMaMatHang(),o.getKhachhang().getMaKhachHang()),o.getKhachhang(),o.getMathang(),o.getCmt(),o.getDanhgia(),o.getNgay()));
        }
        maKhachHang.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKhachhang().getMaKhachHang()).asObject());
        tenKhachHang.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKhachhang().getTen()));
                maMatHang.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMathang().getMaMatHang()).asObject());
                tenMatHang.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMathang().getTen()));
        cmt.setCellValueFactory(new PropertyValueFactory<>("cmt"));   
         danhGia.setCellValueFactory(new PropertyValueFactory<>("danhgia"));   
        ngay.setCellValueFactory(new PropertyValueFactory<Gopy,Date>("ngay"));   
        


 
        //add your data to the table here.
        table.setItems(list);
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
   
    private void QuayLaiButton(){
        quaylai.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("QuanLyGopY.fxml",550,1080);
            } catch (IOException ex) {
                Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
    }

   private void ThongKeButton(){
        thongke.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if(printerJob.showPrintDialog(UngDung.getPrimaryStage().getOwner()) && printerJob.printPage(table))
        printerJob.endJob();
        });
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.TableView();
        this.QuayLaiButton();
        this.ThongKeButton();
    }    
    
}
