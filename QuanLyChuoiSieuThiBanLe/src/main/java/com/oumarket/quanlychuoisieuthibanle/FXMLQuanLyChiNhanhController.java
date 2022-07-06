/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.oumarket.quanlychuoisieuthibanle;

import com.oumarket.pojo.ChiNhanh;
import com.oumarket.services.ChiNhanhServices;
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
public class FXMLQuanLyChiNhanhController implements Initializable {
    private static final ChiNhanhServices n = new ChiNhanhServices();
    
    @FXML private TableView<ChiNhanh> tbChiNhanh;
    @FXML private TextField txtKeyword;
    
    @FXML private TextField txtMaChiNhanh;
    @FXML private TextField txtSoNha;
    @FXML private TextField txtDuong;
    @FXML private TextField txtPhuong;
    @FXML private TextField txtQuan;
    @FXML private TextField txtThanhPho;
    
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
            this.tbChiNhanh.setItems(FXCollections.observableList(n.getChiNhanhs(kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuanLyChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableView() {
        TableColumn colMaChiNhanh = new TableColumn("Mã chi nhánh");
        colMaChiNhanh.setCellValueFactory(new PropertyValueFactory("maChiNhanh"));
        
        TableColumn colSoNha = new TableColumn("Số nhà");
        colSoNha.setCellValueFactory(new PropertyValueFactory("soNha"));
        
        TableColumn colDuong = new TableColumn("Đường");
        colDuong.setCellValueFactory(new PropertyValueFactory("duong"));
        
        TableColumn colPhuong = new TableColumn("Phường");
        colPhuong.setCellValueFactory(new PropertyValueFactory("phuong"));
        
        TableColumn colQuan = new TableColumn("Quận");
        colQuan.setCellValueFactory(new PropertyValueFactory("quan"));
        
        TableColumn colThanhPho = new TableColumn("Thành phố");
        colThanhPho.setCellValueFactory(new PropertyValueFactory("thanhPho"));
        
        TableColumn colDel = new TableColumn();
        colDel.setCellFactory((p) -> {
            Button btn = new Button("Xoá");
            
            btn.setOnAction((evt) -> {
                TableCell c = (TableCell)((Button)evt.getSource()).getParent();
                ChiNhanh cn = (ChiNhanh) c.getTableRow().getItem();
                
                try {
                    if (n.deleteChiNhanh(cn.getMaChiNhanh()) == true) {
                        Utils.getBox("Xoá thành công", Alert.AlertType.INFORMATION).show();
                        this.loadTableData(null);
                    } else {
                        Utils.getBox("Xoá không thành công", Alert.AlertType.ERROR).show();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLQuanLyChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
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
                ChiNhanh cn = (ChiNhanh) c.getTableRow().getItem();
                
                this.txtMaChiNhanh.setText(cn.getMaChiNhanh());
                this.txtSoNha.setText(cn.getSoNha());
                this.txtDuong.setText(cn.getDuong());
                this.txtPhuong.setText(cn.getPhuong());
                this.txtQuan.setText(cn.getQuan());
                this.txtThanhPho.setText(cn.getThanhPho());
           });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbChiNhanh.getColumns().addAll(colMaChiNhanh, colSoNha, colDuong, 
                                            colPhuong, colQuan, colThanhPho, colDel, colEdit);
    }
    
    public void clearInput(ActionEvent event) {
        this.txtMaChiNhanh.setText(null);
        this.txtSoNha.setText(null);
        this.txtDuong.setText(null);
        this.txtPhuong.setText(null);
        this.txtQuan.setText(null);
        this.txtThanhPho.setText(null);
    }
    
    public void themChiNhanhHandler(ActionEvent event) {
        ChiNhanh c = new ChiNhanh(UUID.randomUUID().toString(), this.txtSoNha.getText(), 
                                this.txtDuong.getText(), this.txtPhuong.getText(), 
                                this.txtQuan.getText(), this.txtThanhPho.getText());
        
        ChiNhanhServices cs = new ChiNhanhServices();
        try {
            cs.themChiNhanh(c);
            Utils.getBox("Thêm chi nhánh thành công", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            this.txtMaChiNhanh.setText(null);
            this.txtSoNha.setText(null);
            this.txtDuong.setText(null);
            this.txtPhuong.setText(null);
            this.txtQuan.setText(null);
            this.txtThanhPho.setText(null);
        } catch (SQLException ex) {
            Utils.getBox("Thêm chi nhánh không thành công", Alert.AlertType.WARNING).show();
        }
    }
    
    public void editChiNhanhHandler(ActionEvent event) {
        ChiNhanh c = new ChiNhanh(this.txtMaChiNhanh.getText(), this.txtSoNha.getText(), 
                                this.txtDuong.getText(), this.txtPhuong.getText(), 
                                this.txtQuan.getText(), this.txtThanhPho.getText());
        ChiNhanhServices cs = new ChiNhanhServices();
        try {
            cs.editChiNhanh(c);
            Utils.getBox("Sửa chi nhánh thành công", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            this.txtMaChiNhanh.setText(null);
            this.txtSoNha.setText(null);
            this.txtDuong.setText(null);
            this.txtPhuong.setText(null);
            this.txtQuan.setText(null);
            this.txtThanhPho.setText(null);
        } catch (SQLException ex) {
            Utils.getBox("Sửa chi nhánh không thành công", Alert.AlertType.WARNING).show();
        }
    }
}
