/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.oumarket.quanlychuoisieuthibanle;

import com.oumarket.pojo.GiamGia;
import com.oumarket.pojo.HangHoa;
import com.oumarket.pojo.PhanLoai;
import com.oumarket.services.GiamGiaServices;
import com.oumarket.services.HangHoaServices;
import com.oumarket.services.PhanLoaiServies;
import com.oumarket.utils.Utils;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author anhtuan
 */
public class FXMLQuanLyHangHoaController implements Initializable {
    private static final HangHoaServices s = new HangHoaServices();
    
    @FXML private TextField txtKeyword;
    @FXML private TableView<HangHoa> tbHangHoa;
    
    @FXML private TextField txtMaHang;
    @FXML private ComboBox<PhanLoai> cbPhanLoai;
    @FXML private ComboBox<GiamGia> cbGiamGia;
    @FXML private TextField txtTenHang;
    @FXML private TextField txtDonGia;
    @FXML private TextField txtSoLuong;
    @FXML private TextField txtNguonGoc;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PhanLoaiServies p = new PhanLoaiServies();
        GiamGiaServices g = new GiamGiaServices();
        try {
            this.cbPhanLoai.setItems(FXCollections.observableList(p.getPhanLoai()));
            this.cbGiamGia.setItems(FXCollections.observableList(g.getGiamGia()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuanLyHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadTableView();
        this.loadTableData(null);
        
        this.txtKeyword.textProperty().addListener((evt) -> {
            this.loadTableData(this.txtKeyword.getText());
        });
    }  
    
    private void loadTableData(String kw) {
        try {
            this.tbHangHoa.setItems(FXCollections.observableList(s.getHangHoas(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuanLyHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableView() {
        TableColumn colMaHang = new TableColumn("Mã hàng");
        colMaHang.setCellValueFactory(new PropertyValueFactory("maHang"));
        
        TableColumn colTenHang = new TableColumn("Tên hàng");
        colTenHang.setCellValueFactory(new PropertyValueFactory("tenHang"));
        
        TableColumn colSoLuong = new TableColumn("Số Lượng");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("soLuong"));
                
        TableColumn colDonGia = new TableColumn("Đơn giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("donGia"));
        
        TableColumn colNguonGoc = new TableColumn("Nguồn gốc");
        colNguonGoc.setCellValueFactory(new PropertyValueFactory("nguonGoc"));
        
        TableColumn colMaLoai = new TableColumn("Mã loại");
        colMaLoai.setCellValueFactory(new PropertyValueFactory("maLoai"));
        
        TableColumn colMaGiamGia = new TableColumn("Mã giảm giá");
        colMaGiamGia.setCellValueFactory(new PropertyValueFactory("maGiamGia"));
        
        TableColumn colDel = new TableColumn();
        colDel.setCellFactory((p) -> {
            Button btn = new Button("Xoá");
            
            btn.setOnAction((evt) -> {
                TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                HangHoa h = (HangHoa) c.getTableRow().getItem();
                
                try {
                    if (s.deleteHangHoa(h.getMaHang()) == true) {
                        Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
                        this.loadTableData(null);
                    } else {
                        Utils.getBox("Xoá không thành công", Alert.AlertType.ERROR).show();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLQuanLyHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        TableColumn colEdit = new TableColumn();
        colEdit.setCellFactory((p)-> {
            Button btn = new Button("Sửa");
            
            btn.setOnAction((evt) -> {
                TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                HangHoa h = (HangHoa) c.getTableRow().getItem();
                
                this.txtMaHang.setText(h.getMaHang());
                this.txtTenHang.setText(h.getTenHang());
                this.txtDonGia.setText(String.valueOf(h.getDonGia()));
                this.txtSoLuong.setText(String.valueOf(h.getSoLuong()));
                this.txtNguonGoc.setText(h.getNguonGoc());
                this.cbPhanLoai.getSelectionModel().select(h.getMaLoai()-1);
                this.cbGiamGia.getSelectionModel().select(h.getMaGiamGia()-1);
           });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbHangHoa.getColumns().addAll(colMaHang, colTenHang, colSoLuong, 
                                            colDonGia, colNguonGoc, colMaLoai, colMaGiamGia, colDel, colEdit);
    } 
    
    public void clearInput(ActionEvent event) {
        this.txtMaHang.setText(null);
        this.txtTenHang.setText(null);
        this.txtDonGia.setText(null);
        this.txtSoLuong.setText(null);
        this.txtNguonGoc.setText(null);
        this.cbPhanLoai.getSelectionModel().clearSelection();
        this.cbGiamGia.getSelectionModel().clearSelection();
    }
    
    public void themHangHoaHandler(ActionEvent event) {
        HangHoa h = new HangHoa(UUID.randomUUID().toString(), this.txtTenHang.getText(), 
                                Integer.parseInt(this.txtSoLuong.getText()), 
                                Float.parseFloat(this.txtDonGia.getText()), this.txtNguonGoc.getText(), 
                                this.cbPhanLoai.getSelectionModel().getSelectedItem().getMaLoai(), 
                                this.cbGiamGia.getSelectionModel().getSelectedItem().getMaGiamGia());
        
        HangHoaServices hs = new HangHoaServices();
        try {
            hs.themHangHoa(h);
            Utils.getBox("Thêm hàng hoá thành công", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            this.txtTenHang.setText(null);
            this.txtSoLuong.setText(null);
            this.txtDonGia.setText(null);
            this.txtNguonGoc.setText(null);
            this.cbPhanLoai.getSelectionModel().clearSelection();
            this.cbGiamGia.getSelectionModel().clearSelection();
        } catch (SQLException ex) {
            Utils.getBox("Thêm hàng hoá không thành công", Alert.AlertType.WARNING).show();
        }
    }
    
    public void editHangHoaHandler(ActionEvent event) {
        HangHoa h = new HangHoa(this.txtMaHang.getText(), this.txtTenHang.getText(), 
                                Integer.parseInt(this.txtSoLuong.getText()),
                                Float.parseFloat(this.txtDonGia.getText()), this.txtNguonGoc.getText(), 
                                this.cbPhanLoai.getSelectionModel().getSelectedItem().getMaLoai(), 
                                this.cbGiamGia.getSelectionModel().getSelectedItem().getMaGiamGia());
        HangHoaServices hs = new HangHoaServices();
        try {
            hs.editHangHoa(h);
            Utils.getBox("Sửa hàng hoá thành công", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            this.txtMaHang.setText(null);
            this.txtTenHang.setText(null);
            this.txtDonGia.setText(null);
            this.txtSoLuong.setText(null);
            this.txtNguonGoc.setText(null);
            this.cbPhanLoai.getSelectionModel().clearSelection();
            this.cbGiamGia.getSelectionModel().clearSelection();
        } catch (SQLException ex) {
            Utils.getBox("Sửa hàng hoá không thành công", Alert.AlertType.WARNING).show();
        }
    }
}
