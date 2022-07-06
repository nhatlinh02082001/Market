
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.oumarket.quanlychuoisieuthibanle;

import com.oumarket.pojo.NhanVien;
import com.oumarket.services.NhanVienServices;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author anhtuan
 */
public class FXMLQuanLyNhanVienController implements Initializable {
    private static final NhanVienServices n = new NhanVienServices();
    
    @FXML private TextField txtKeyword;
    @FXML private TableView<NhanVien> tbNhanVien;
    
    @FXML private TextField txtMaNhanVien;
    @FXML private TextField txtHoTen;
    @FXML private DatePicker dpNamSinh;
    @FXML private TextField txtSDT;
    @FXML private TextField txtEmail;
    @FXML private RadioButton rdoNam;
    @FXML private RadioButton rdoNu;
    @FXML private TextArea txtDiaChi;
    
    private String rdoLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdoNam.setOnAction(e ->{
            rdoLabel = rdoNam.getText();
        });
        rdoNu.setOnAction(e ->{
            rdoLabel = rdoNu.getText();
        });
        
        this.loadTableView();
        this.loadTableData(null);
        
        this.txtKeyword.textProperty().addListener((evt) -> {
            this.loadTableData(this.txtKeyword.getText());
        });
    }  
    
    private void loadTableData(String kw) {
        try {
            this.tbNhanVien.setItems(FXCollections.observableList(n.getNhanViens(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuanLyNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableView() {
        TableColumn colMaNV = new TableColumn("Mã nhân viên");
        colMaNV.setCellValueFactory(new PropertyValueFactory("maNV"));
        
        TableColumn colTenNV = new TableColumn("Tên nhân viên");
        colTenNV.setCellValueFactory(new PropertyValueFactory("tenNV"));
        
        TableColumn colNamSinh = new TableColumn("Năm sinh");
        colNamSinh.setCellValueFactory(new PropertyValueFactory("namSinh"));
        
        TableColumn colSDT = new TableColumn("SĐT");
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
        
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        
        TableColumn colGioiTinh = new TableColumn("Giới tính");
        colGioiTinh.setCellValueFactory(new PropertyValueFactory("gioiTinh"));
        
        TableColumn colDiaChi = new TableColumn("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
        colDiaChi.setPrefWidth(250);
        
        TableColumn colDel = new TableColumn();
        colDel.setCellFactory((p) -> {
            Button btn = new Button("Xoá");
            
            btn.setOnAction((evt) -> {
                TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                NhanVien nv = (NhanVien) c.getTableRow().getItem();
                
                try {
                    if (n.deleteNhanVien(nv.getMaNV()) == true) {
                        Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
                        this.loadTableData(null);
                    } else {
                        Utils.getBox("Xoá không thành công", Alert.AlertType.ERROR).show();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLQuanLyNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
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
                NhanVien nv = (NhanVien) c.getTableRow().getItem();
                
                this.txtMaNhanVien.setText(nv.getMaNV());
                this.txtHoTen.setText(nv.getTenNV());
                this.dpNamSinh.setValue(nv.getNamSinh().toLocalDate());
                this.txtSDT.setText(nv.getSdt());
                if (nv.getGioiTinh().equals("Nam"))
                    this.rdoNam.setSelected(true);
                else
                    this.rdoNu.setSelected(true);
                this.txtEmail.setText(nv.getEmail());
                this.txtDiaChi.setText(nv.getDiaChi());
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbNhanVien.getColumns().addAll(colMaNV, colTenNV, colNamSinh, 
                                            colSDT, colEmail, colGioiTinh, colDiaChi, colDel, colEdit);
    }
    
    public void clearInput(ActionEvent event) {
        this.txtMaNhanVien.setText(null);
        this.txtHoTen.setText(null);
        this.dpNamSinh.setValue(null);
        this.rdoNam.setSelected(false);
        this.rdoNu.setSelected(false);
        this.txtSDT.setText(null);
        this.txtEmail.setText(null);
        this.txtDiaChi.setText(null);
    }
    
    public void themNhanVienHandler(ActionEvent event) {
        NhanVien n = new NhanVien(UUID.randomUUID().toString(), this.txtHoTen.getText(), 
                                java.sql.Date.valueOf(this.dpNamSinh.getValue()), this.txtSDT.getText(), 
                                this.txtEmail.getText(), rdoLabel, this.txtDiaChi.getText());
        
        NhanVienServices nv = new NhanVienServices();
        try {
            nv.themNhanVien(n);
            Utils.getBox("Thêm nhân viên thành công", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            this.txtHoTen.setText(null);
            this.dpNamSinh.setValue(null);
            this.rdoNam.setSelected(false);
            this.rdoNu.setSelected(false);
            this.txtSDT.setText(null);
            this.txtEmail.setText(null);
            this.txtDiaChi.setText(null);
        } catch (SQLException ex) {
            Utils.getBox("Thêm nhân viên không thành công", Alert.AlertType.WARNING).show();
        }
   }
    
    public void editNhanVienHandler(ActionEvent event) {
        NhanVien n = new NhanVien(this.txtMaNhanVien.getText(), this.txtHoTen.getText(), 
                                java.sql.Date.valueOf(this.dpNamSinh.getValue()), this.txtSDT.getText(), 
                                this.txtEmail.getText(), rdoLabel, this.txtDiaChi.getText());
        NhanVienServices nv = new NhanVienServices();
        try {
            nv.editNhanVien(n);
            Utils.getBox("Sửa nhân viên thành công", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            this.txtMaNhanVien.setText(null);
            this.txtHoTen.setText(null);
            this.dpNamSinh.setValue(null);
            this.rdoNam.setSelected(false);
            this.rdoNu.setSelected(false);
            this.txtSDT.setText(null);
            this.txtEmail.setText(null);
            this.txtDiaChi.setText(null);
        } catch (SQLException ex) {
            Utils.getBox("Sửa nhân viên không thành công", Alert.AlertType.WARNING).show();
        }
    }
}
