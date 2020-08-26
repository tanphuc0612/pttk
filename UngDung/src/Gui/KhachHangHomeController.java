/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Entity.Gopy;
import Entity.GopyId;
import Entity.Dondathang;
import Entity.Khachhang;
import Entity.Mathang;
import Entity.GioHang;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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

public class KhachHangHomeController implements Initializable {
    public List<GioHang> gioHang;
    @FXML
    private Button them;   
    @FXML
    private Button xoa;    
    @FXML
    private Button search;
    @FXML
    private Button xacnhan;
    @FXML
    private Button out;
    @FXML
    private TableView<Mathang> ds_hang;
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
    private Spinner<Integer> sl_field;
    @FXML
    private TextField maChon_field;
    @FXML
    private TextField ma_field;
    @FXML
    private TextField ten_field;
    @FXML
    private TextField tongTien;
    @FXML
    private ComboBox<String> httt;
    @FXML
    private Label email;
    @FXML
    private TextField gop_y_field;
        @FXML
        private Button gopY;

    @FXML
    private void DSHangView(){
        ObservableList<Mathang> list = FXCollections.observableArrayList();
        for(Mathang o : Mathang.DanhSach(ten_field.getText(), ma_field.getText())){
            list.add(new Mathang(o.getMaMatHang(),o.getLoaihang(),o.getTen(),o.getGia(),o.getSoLuong()));
        }
        maMathang.setCellValueFactory(new PropertyValueFactory<>("maMatHang"));
        ten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        loaiHang.setCellValueFactory(new PropertyValueFactory<>("loaihang"));
        gia.setCellValueFactory(new PropertyValueFactory<>("gia"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
        ds_hang.setItems(list);
    }
    private void SearchButton(){
        search.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            DSHangView();
        });
    } 
    private void SelectRow(){
        ds_hang.setOnMouseClicked((MouseEvent event) -> {
            if(ds_hang.getSelectionModel().getSelectedItem() != null){
                maChon_field.setText(Integer.toString(ds_hang.getSelectionModel().getSelectedItem().getMaMatHang()));
            }
        });
    }
    public void getEmail(String username)
    {
        email.setText(username);
        System.out.println(email.getText());
    }
    private void init() {
        sl_field.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE));
        gioHang=new ArrayList<>();
        //gioHang = FXCollections.observableArrayList();
        maChon_field.setEditable(false);
        tongTien.setText("0");
        tongTien.setEditable(false);
        httt.getItems().addAll("Tiền mặt","Thẻ");
        httt.getSelectionModel().selectFirst();
    }
    private void ThemButton(){
        them.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Mathang temp = ds_hang.getSelectionModel().getSelectedItem();
            if(temp.getSoLuong()<sl_field.getValue())
            {
                alert.setHeaderText(null);
                alert.setContentText("Không đủ số lượng.");
                alert.show();
                return;
            }
            int i = 0;
            for(i = 0;i<gioHang.size();i++)
            {
                if(gioHang.get(i).getMaMathangGio() == temp.getMaMatHang())
                {
                    int sl = gioHang.get(i).getSoLuongGio()+sl_field.getValue();
                    if(temp.getSoLuong()>sl)
                    {
                        gioHang.get(i).setSoLuongGio(gioHang.get(i).getSoLuongGio()+sl_field.getValue());
                        gioHang.get(i).setThanhTien(gioHang.get(i).getSoLuongGio()*temp.getGia());
                        break;
                    }
                    else
                    {
                        alert.setHeaderText(null);
                        alert.setContentText("Không đủ số lượng.");
                        alert.show();
                    }
                    break;
                }
            }
            if(i==gioHang.size())
                gioHang.add(new GioHang(temp.getMaMatHang(),temp.getTen(),sl_field.getValue(),temp.getGia(),sl_field.getValue()*temp.getGia()));
            GioHangView();
        });
    }
    private void GioHangView(){
        maMathangGio.setCellValueFactory(new PropertyValueFactory<>("maMathangGio"));
        tenGio.setCellValueFactory(new PropertyValueFactory<>("tenGio"));
        giaGio.setCellValueFactory(new PropertyValueFactory<>("giaGio"));
        soLuongGio.setCellValueFactory(new PropertyValueFactory<>("soLuongGio"));
        thanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
        long sum = 0;
        for(GioHang o: gioHang)
                 sum += o.getThanhTien();
            tongTien.setText(Long.toString(sum));
        ObservableList<GioHang> list = FXCollections.observableArrayList(gioHang);
        gio_hang.setItems(list);
        gio_hang.refresh();
    }
    private void XoaButton(){
        xoa.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            GioHang temp = gio_hang.getSelectionModel().getSelectedItem();
            if(temp != null){
                int i = 0;
                for(i =0;i<gioHang.size();i++)
                {
                    if(temp.getMaMathangGio()== gioHang.get(i).getMaMathangGio())
                        break;
                }
                if(i<gioHang.size())
                    gioHang.remove(i);
                GioHangView();
            }
        });
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
                Dondathang.ThemDonHang(new Dondathang(email.getText(),true,httt.getValue().toString(),Long.parseLong(tongTien.getText()),"Tiếp nhận"),gioHang);
                alert.setHeaderText(null);
                alert.setContentText("Xác nhận đơn hàng thành công");
                alert.show();
                gioHang.clear();
                
            }
            DSHangView();
            GioHangView();
        });
    }
    private void OutButton(){
        out.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            try {
                    Change("DangNhap.fxml",400,600);
                } catch (IOException ex) {
                    Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void GopYButton()
    {
            gopY.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Mathang mh = ds_hang.getSelectionModel().getSelectedItem();
            if(mh!=null){
                 Khachhang kh = Khachhang.LayKhachHang(email.getText());
            Gopy a = (new Gopy(new GopyId(mh.getMaMatHang(),kh.getMaKhachHang()),kh,mh,gop_y_field.getText(),"Chưa phân loại",new Date()));
                if(!Gopy.KiemTraTonTai(a))
                {
                    if(Gopy.ThemGopY(a))
                    {
                        alert.setHeaderText(null);
                        alert.setContentText("Góp ý của bạn đã được lưu!");
                        alert.show();
                    }
                    else
                    {
                        alert.setHeaderText(null);
                        alert.setContentText("Góp ý không thành công");
                        alert.show();
                    }
                }
                else 
                        {
                        alert.setHeaderText(null);
                        alert.setContentText("Bạn đã góp ý sản phẩm này rồi");
                        alert.show();
                        }
            }
            else 
            {
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng chọn sản phẩm cần góp ý");
                alert.show();
            }
        });
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.DSHangView();
        this.SearchButton();
        this.ThemButton();
        this.SelectRow();
        this.init();
        this.GioHangView();
        this.XoaButton();
        this.OutButton();
        this.XacNhanButton();
        this.GopYButton();

    }    
}