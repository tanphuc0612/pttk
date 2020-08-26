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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.DatePicker;
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
import javafx.scene.text.Text;
import java.lang.Integer; 
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ThongKeGopYController implements Initializable {

    @FXML
    private Button quaylai;
    @FXML
    private Button thongke;
    @FXML
    private Button inthongke;
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
    private DatePicker fromngay; 
    @FXML
    private DatePicker tongay; 
    @FXML
    private Text text_kh_gop_y_nhieu_nhat;
    @FXML
    private Text text_kh_gop_y_tot_nhieu_nhat;
    @FXML
    private Text text_sp_gop_y_tot_nhieu_nhat;
    @FXML
    private Text text_sp_gop_y_xau_nhieu_nhat;
    @FXML
    private Text text_tong_so_gop_y_tot;
    @FXML
    private Text text_tong_so_gop_y_xau;
    @FXML
    private Text text_tong_so_gop_y_chua_phan_loai;
    
    
    
    private void TableView(){
        ObservableList<Gopy> list = FXCollections.observableArrayList();
        LocalDate localDate = fromngay.getValue();
        LocalDate localDate2 = tongay.getValue();
        if(localDate!=null&&localDate2!=null)
        {
             Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date fromngayDate = Date.from(instant);
            Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
            Date tongayDate = Date.from(instant2);
            for(Gopy o : Gopy.DanhSach("")){
                if(o.getNgay().compareTo(fromngayDate)>=0&&o.getNgay().compareTo(tongayDate)<=0)
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
        else
        {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập khoảng thời gian thống kê");
                alert.show();
                return;
        }
    }
    private void ThongKeView(){
            String khachHangGopYNhieuNhat = "...";
            int maKhachHangGopYNhieuNhat = -1;
            int slKhachHangGopYNhieuNhat = 0;
            Map<Integer, Integer> mapkhachHangGopYNhieuNhat = new HashMap<Integer, Integer>();


            String khachHangGopYTotNhieuNhat = "...";
            int maKhachHangGopYTotNhieuNhat = -1;
            int slKhachHangGopYTotNhieuNhat = 0;
            Map<Integer, Integer> mapkhachHangGopYTotNhieuNhat = new HashMap<Integer, Integer>();

            String sanPhamGopYXauNhieuNhat = "...";
            int maSanPhamGopYXauNhieuNhat = -1;
            int slSanPhamGopYXauNhieuNhat = 0;
            Map<Integer, Integer> mapsanPhamGopYXauNhieuNhat = new HashMap<Integer, Integer>();
            
            String sanPhamGopYTotNhieuNhat = "...";
            int maSanPhamGopYTotNhieuNhat = -1;
            int slSanPhamGopYTotNhieuNhat = 0;
            Map<Integer, Integer> mapsanPhamGopYTotNhieuNhat = new HashMap<Integer, Integer>();
            
            int slGopYTot = 0;
            int slGopYXau = 0;
            int slGopYChuaPhanLoai = 0;
            
            ObservableList<Gopy> list = FXCollections.observableArrayList();
            LocalDate localDate = fromngay.getValue();
            LocalDate localDate2 = tongay.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date fromngayDate = Date.from(instant);
            Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
            Date tongayDate = Date.from(instant2);
            int temp = 0;
            for(Gopy o : Gopy.DanhSach("")){
                if(o.getNgay().compareTo(fromngayDate)>=0&&o.getNgay().compareTo(tongayDate)<=1)
                {
                    if(mapkhachHangGopYNhieuNhat.get(o.getKhachhang().getMaKhachHang())==null)
                    {
                        mapkhachHangGopYNhieuNhat.put(o.getKhachhang().getMaKhachHang(),1);
                         if(slKhachHangGopYNhieuNhat==0) 
                       {
                           slKhachHangGopYNhieuNhat = 1;
                           khachHangGopYNhieuNhat = o.getKhachhang().getTen();
                           maKhachHangGopYNhieuNhat = o.getKhachhang().getMaKhachHang();
                       }
                    }
                    else
                    {
                        temp  = mapkhachHangGopYNhieuNhat.get(o.getKhachhang().getMaKhachHang());
                       if(temp+1>slKhachHangGopYNhieuNhat) 
                       {
                           slKhachHangGopYNhieuNhat = temp;
                           khachHangGopYNhieuNhat = o.getKhachhang().getTen();
                           maKhachHangGopYNhieuNhat = o.getKhachhang().getMaKhachHang();
                       }
                       else
                       {
                           temp = temp+1;
                           mapkhachHangGopYNhieuNhat.put(o.getKhachhang().getMaKhachHang(),temp);
                       }
                    }
                    if("Xấu".equals(o.getDanhgia()))
                  {
                                       
                 
                  //3
                   if(mapsanPhamGopYXauNhieuNhat.get(o.getMathang().getMaMatHang())==null)
                    {
                        mapsanPhamGopYXauNhieuNhat.put(o.getMathang().getMaMatHang(),1);
                       if(slSanPhamGopYXauNhieuNhat==0) 
                       {
                           slSanPhamGopYXauNhieuNhat = 1;
                           sanPhamGopYXauNhieuNhat = o.getMathang().getTen();
                           maSanPhamGopYXauNhieuNhat = o.getMathang().getMaMatHang();
                       }
                    }
                    else
                    {
                        temp  = mapsanPhamGopYXauNhieuNhat.get( o.getMathang().getMaMatHang());
                       if(temp+1>slSanPhamGopYXauNhieuNhat) 
                       {
                           slSanPhamGopYXauNhieuNhat = temp;
                           sanPhamGopYXauNhieuNhat = o.getMathang().getTen();
                           maSanPhamGopYXauNhieuNhat = o.getMathang().getMaMatHang();
                       }
                        else
                       {
                           temp = temp+1;
                           mapsanPhamGopYXauNhieuNhat.put(o.getMathang().getMaMatHang(),temp);
                       }
                    }
                   slGopYXau++;
                  }
                      if("Chưa phân loại".equals(o.getDanhgia()))
                  {
                      slGopYChuaPhanLoai++;
                  }
                  if("Tốt".equals(o.getDanhgia()))
                  {
                      slGopYTot++;
                         //2
                  if(mapkhachHangGopYTotNhieuNhat.get(o.getKhachhang().getMaKhachHang())==null)
                    {
                        mapkhachHangGopYTotNhieuNhat.put(o.getKhachhang().getMaKhachHang(),1);
                       if(slKhachHangGopYTotNhieuNhat==0) 
                       {
                           slKhachHangGopYTotNhieuNhat = 1;
                           khachHangGopYTotNhieuNhat = o.getKhachhang().getTen();
                           maKhachHangGopYTotNhieuNhat = o.getKhachhang().getMaKhachHang();
                       }
                    }
                    else
                    {
                        temp  = mapkhachHangGopYTotNhieuNhat.get(o.getKhachhang().getMaKhachHang());
                       if(temp+1>slKhachHangGopYTotNhieuNhat) 
                       {
                           slKhachHangGopYTotNhieuNhat = temp;
                           khachHangGopYTotNhieuNhat = o.getKhachhang().getTen();
                           maKhachHangGopYTotNhieuNhat = o.getKhachhang().getMaKhachHang();
                       }
                        else
                       {
                           temp = temp+1;
                           mapkhachHangGopYTotNhieuNhat.put(o.getKhachhang().getMaKhachHang(),temp);
                       }
                    }
                   //4
                   if(mapsanPhamGopYTotNhieuNhat.get(o.getMathang().getMaMatHang())==null)
                    {
                        mapsanPhamGopYTotNhieuNhat.put(o.getMathang().getMaMatHang(),1);
                        if(slSanPhamGopYTotNhieuNhat==0) 
                       {
                           slSanPhamGopYTotNhieuNhat = 1;
                           sanPhamGopYTotNhieuNhat = o.getMathang().getTen();
                           maSanPhamGopYTotNhieuNhat = o.getMathang().getMaMatHang();
                       }  
                    }
                    else
                    {
                        temp  = mapsanPhamGopYTotNhieuNhat.get( o.getMathang().getMaMatHang());
                       if(temp+1>slSanPhamGopYTotNhieuNhat) 
                       {
                           slSanPhamGopYTotNhieuNhat = temp;
                           sanPhamGopYTotNhieuNhat = o.getMathang().getTen();
                           maSanPhamGopYTotNhieuNhat = o.getMathang().getMaMatHang();
                       }  
                       else
                       {
                           temp = temp+1;
                           mapsanPhamGopYTotNhieuNhat.put(o.getMathang().getMaMatHang(),temp);
                       }
                    }
                  }
                }
            }
            if(slKhachHangGopYNhieuNhat==0) text_kh_gop_y_nhieu_nhat.setText("...");
            else
            text_kh_gop_y_nhieu_nhat.setText(Integer.toString(maKhachHangGopYNhieuNhat)+" - "+ khachHangGopYNhieuNhat+" ("+Integer.toString(slKhachHangGopYNhieuNhat)+" góp ý)");
            if(slKhachHangGopYTotNhieuNhat==0) text_kh_gop_y_tot_nhieu_nhat.setText("...");
            else
            text_kh_gop_y_tot_nhieu_nhat.setText(Integer.toString(maKhachHangGopYTotNhieuNhat)+" - "+ khachHangGopYTotNhieuNhat+" ("+Integer.toString(slKhachHangGopYTotNhieuNhat)+" góp ý)");
            if(slSanPhamGopYTotNhieuNhat==0) text_sp_gop_y_tot_nhieu_nhat.setText("...");
            else
            text_sp_gop_y_tot_nhieu_nhat.setText(Integer.toString(maSanPhamGopYTotNhieuNhat)+" - "+ sanPhamGopYTotNhieuNhat+" ("+Integer.toString(slSanPhamGopYTotNhieuNhat)+" góp ý)");
            if(slSanPhamGopYXauNhieuNhat==0) text_sp_gop_y_xau_nhieu_nhat.setText("...");
            else
            text_sp_gop_y_xau_nhieu_nhat.setText(Integer.toString(maSanPhamGopYXauNhieuNhat)+" - "+ sanPhamGopYXauNhieuNhat+" ("+Integer.toString(slSanPhamGopYXauNhieuNhat)+" góp ý)");

            text_tong_so_gop_y_tot.setText(Integer.toString(slGopYTot)+" Góp ý");
            text_tong_so_gop_y_xau.setText(Integer.toString(slGopYXau)+" Góp ý");
            text_tong_so_gop_y_chua_phan_loai.setText(Integer.toString(slGopYChuaPhanLoai)+" Góp ý");


//                @FXML
//    private Text ;
//    @FXML
//    private Text ;
//    @FXML
//    private Text text_sp_gop_y_tot_nhieu_nhat;
//    @FXML
//    private Text text_sp_gop_y_xau_nhieu_nhat;
//    @FXML
//    private Text text_tong_so_gop_y_tot;
//    @FXML
//    private Text text_tong_so_gop_y_xau;
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
            this.TableView();
            this.ThongKeView();
        });
         
    }
   private void InThongKeButton(){
        inthongke.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if(printerJob.showPrintDialog(UngDung.getPrimaryStage().getOwner()) && printerJob.printPage(table))
        printerJob.endJob();
        });
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ThongKeButton();
        this.QuayLaiButton();
        this.InThongKeButton();
    }    
    
}
