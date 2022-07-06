/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.oumarket.quanlychuoisieuthibanle;

import com.oumarket.pojo.HangHoa;
import com.oumarket.services.HangHoaServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author anhtuan
 */
public class FXMLOUMarketController implements Initializable {
    private static final HangHoaServices s = new HangHoaServices();
  
    @FXML private TableView<HangHoa> tbHangHoa;
    @FXML private TableView<HangHoa> tbSelectedHangHoa;
    @FXML private TextField txtKeyword;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableView();
        this.loadTableData(null);
        
        this.txtKeyword.textProperty().addListener((evt) -> {
            this.loadTableData(this.txtKeyword.getText());
        });
    }
    
    private void loadTableData(String kw) {
        try {
            this.tbHangHoa.setItems(FXCollections.observableList(s.getHangHoasById(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLOUMarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    private void loadTableView() {
        TableColumn colMaHang = new TableColumn("Mã hàng");
        colMaHang.setCellValueFactory(new PropertyValueFactory("maHang"));
        
        TableColumn colTenHang = new TableColumn("Tên hàng");
        colTenHang.setCellValueFactory(new PropertyValueFactory("tenHang"));
        
        TableColumn colNguonGoc = new TableColumn("Nguồn gốc");
        colNguonGoc.setCellValueFactory(new PropertyValueFactory("nguonGoc"));
        
        TableColumn colDonGia = new TableColumn("Đơn giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("donGia"));
        
        TableColumn colAdd = new TableColumn();
        colAdd.setCellFactory((p) -> {
            Button btn = new Button("Chọn");
            
            btn.setOnAction((evt) -> {
                TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                HangHoa h = (HangHoa) c.getTableRow().getItem();
                
                boolean isMatched = this.tbSelectedHangHoa.getItems().stream().anyMatch(
                        (HangHoa hang) -> {
                            if(hang.getMaHang().equals(h.getMaHang())) {
                                hang.setSelectedCountHang(hang.getSelectedCount() + 1);
                            }
                            return hang.getMaHang().equals(h.getMaHang());
                        }
                );
                
                if(!isMatched) {
                    this.tbSelectedHangHoa.getItems().add(h);
                }
                this.tbSelectedHangHoa.refresh();
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbHangHoa.getColumns().addAll(colMaHang, colTenHang, colNguonGoc, colDonGia, colAdd);
        
        
        this.tbSelectedHangHoa.setEditable(true);
        TableColumn colTenHang1 = new TableColumn("Tên hàng");
        colTenHang1.setCellValueFactory(new PropertyValueFactory("tenHang"));
        colTenHang1.setPrefWidth(250);
        
        TableColumn colDonGia1 = new TableColumn("Đơn giá");
        colDonGia1.setCellValueFactory(new PropertyValueFactory("donGia"));
        
        TableColumn colSoLuong1 = new TableColumn("So luong");
        colSoLuong1.setCellValueFactory(new PropertyValueFactory("selectedCount"));
        colSoLuong1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colSoLuong1.setOnEditCommit(new EventHandler<CellEditEvent<HangHoa, Integer>>() {
            @Override
            public void handle(CellEditEvent<HangHoa, Integer> event) {
                HangHoa hanghoa = event.getRowValue();
                hanghoa.setSelectedCountHang(event.getNewValue());
            }
        });

        this.tbSelectedHangHoa.getColumns().addAll(colTenHang1, colDonGia1, colSoLuong1);
    }
    
    public void refreshTableHangHoa(ActionEvent event) {
        this.loadTableData(null);
    }
    
    public void hoaDonHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLHoaDon.fxml"));
        Parent hangHoaViewParent = loader.load();
        Scene scene = new Scene(hangHoaViewParent);
        
        FXMLHoaDonController controller = loader.getController();
        List<HangHoa> hoadon = tbSelectedHangHoa.getItems();
        controller.setHoaDon(hoadon);
        
        stage.setScene(scene);
    }
    
    public void quanLyChiNhanhHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLQuanLyChiNhanh.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản lý chi nhánh");
        stage.show();
    }
    
    public void quanLyNhanVienHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLQuanLyNhanVien.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản lý nhân viên");
        stage.show();
    }
    
    public void quanLyHangHoaHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLQuanLyHangHoa.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản lý hàng hoá");
        stage.show();
    }
}
