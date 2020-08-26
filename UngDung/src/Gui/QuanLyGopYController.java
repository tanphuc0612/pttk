/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Gopy;
import Entity.Khachhang;
import Entity.GopyId;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
    private Button btnXoa; 
    @FXML
    private Button btnChan; 
    @FXML
    private Button btnTot; 
    @FXML
    private Button btnXau; 
    @FXML
    private Button btnSearch; 
    @FXML
    private Button btnQuayLai;
    @FXML
    private Button btnThongKe;    
    @FXML
    private TableView<Gopy> tableGopY;  
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
    private TextField inputSearch;     
    
    @FXML
    private void tableGopY_view(){
        ObservableList<Gopy> list = FXCollections.observableArrayList();
        for(Gopy o : Gopy.DanhSach(inputSearch.getText())){
            list.add(new Gopy(new GopyId(o.getMathang().getMaMatHang(),o.getKhachhang().getMaKhachHang()),o.getKhachhang(),o.getMathang(),o.getCmt(),o.getDanhgia(),o.getNgay()));
        }
        
        maKhachHang.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKhachhang().getMaKhachHang()).asObject());
        tenKhachHang.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKhachhang().getTen()));
                maMatHang.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMathang().getMaMatHang()).asObject());
                tenMatHang.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMathang().getTen()));
        cmt.setCellValueFactory(new PropertyValueFactory<>("cmt"));   
         danhGia.setCellValueFactory(new PropertyValueFactory<>("danhgia"));   
        ngay.setCellValueFactory(new PropertyValueFactory<>("ngay"));   
        


 
        //add your data to the table here.
        tableGopY.setItems(list);
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
    private void btnSearch_click(){
        btnSearch.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            tableGopY_view();
        });
    } 

    private void btnXoa_click(){
        btnXoa.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(tableGopY.getSelectionModel().getSelectedItem() != null){
                if((tableGopY.getSelectionModel().getSelectedItem()).XoaGopY()){
                    tableGopY_view();
                    alert.setContentText("xóa thành công");
                }
                alert.show();
            }
        });
    }
    
    private void btnQuayLai_click(){
        btnQuayLai.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("NhanVienHome.fxml",500,600);
            } catch (IOException ex) {
                Logger.getLogger(QuanLyGopYController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
    }

   private void btnThongKe_click(){
//        thongke.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
//        PrinterJob printerJob = PrinterJob.createPrinterJob();
//        if(printerJob.showPrintDialog(UngDung.getPrimaryStage().getOwner()) && printerJob.printPage(table))
//        printerJob.endJob();
//        });
        btnThongKe.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                Change("ThongKeGopY.fxml",550,1080);
            } catch (IOException ex) {
                Logger.getLogger(QuanLyGopYController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
   }
    private void btnDanhGia_click(){
        btnTot.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                if(tableGopY.getSelectionModel().getSelectedItem() != null){
                    Gopy a = tableGopY.getSelectionModel().getSelectedItem();
                    a.setDanhgia("Tốt");
                    Gopy.UpdateGopy(a);
                    tableGopY_view();
                }
        });
        btnXau.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                if(tableGopY.getSelectionModel().getSelectedItem() != null){
                    Gopy a = tableGopY.getSelectionModel().getSelectedItem();
                    a.setDanhgia("Xấu");
                    Gopy.UpdateGopy(a);
                    tableGopY_view();
                }
        });
    }
    private void btnChan_click(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        btnChan.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                if(tableGopY.getSelectionModel().getSelectedItem() != null){
                    Gopy a = tableGopY.getSelectionModel().getSelectedItem();
                   if(a.getKhachhang().isBichan())
                    {
                        alert.setHeaderText(null);
                            alert.setContentText("Khách hàng này đã bị chặn góp ý rồi");
                            alert.show();
                            return;
                    }
                   else
                    {
                        Khachhang b = a.getKhachhang();
                        b.setBichan(true);
                        Khachhang.UpdateKhacHang(b);
                        alert.setContentText("Chặn góp ý cho khách hàng " + b.getTen()+" thành công");
                            alert.show();
                            return;
                    }
                     
                }
        });
        btnXau.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                if(tableGopY.getSelectionModel().getSelectedItem() != null){
                    Gopy a = tableGopY.getSelectionModel().getSelectedItem();
                    a.setDanhgia("Xấu");
                    Gopy.UpdateGopy(a);
                    tableGopY_view();
                }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tableGopY_view();
        this.btnSearch_click();
        this.btnDanhGia_click();
        this.btnQuayLai_click();
        this.btnThongKe_click();
        this.btnXoa_click();
        this.btnChan_click();
    }    
    
}
