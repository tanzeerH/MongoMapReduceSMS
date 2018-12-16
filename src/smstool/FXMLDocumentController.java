/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smstool;

import com.mongodb.BasicDBObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;
import wsu.mongo.Util;
import wsu.mongo.SMS;
import wsu.mongo.Connection;
import wsu.mongo.MapReduceByContent;
import wsu.mongo.MapReduce;


/**
 *
 * @author hossa
 */
public class FXMLDocumentController implements Initializable {
    
   
    @FXML
    TableView tbAll;
    private  ObservableList<SMS> data;
    ArrayList<SMS> smsList;
    
    @FXML
    TextField tvSender;
     @FXML
    TextField tvDate;
     
      @FXML
    TextField tvLength;
      
       @FXML
    TextField tvisSpam;
       
        @FXML
    TextField tvContent;
        
     @FXML
    TextField tvSampleContent;
    @FXML
    TextField tvRes;
    
    @FXML
    TextField tvSampleLength;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
       // label.setText("Hello World!");
    }
    @FXML
    private void handleInsert(ActionEvent event) {
        System.out.println("You clicked Insert!");
       // label.setText("Hello World!");
       BasicDBObject basicDBObject = new BasicDBObject();
       String sender = tvSender.getText().toString();
       basicDBObject.put("sender", sender);
       
        String date = tvDate.getText().toString();
       basicDBObject.put("date", date);
       
        String length = tvLength.getText().toString();
       basicDBObject.put("length", length);
       
        String isSpam = tvisSpam.getText().toString();
       basicDBObject.put("isSpam", isSpam);
       
        String content = tvContent.getText().toString();
       basicDBObject.put("content", content);
       
        Connection.getDataBaseInstance().getCollection(Connection.LISTING).insert(basicDBObject);
        JOptionPane.showMessageDialog(null, "Document Inserted");
        
        refresh();
    }
     @FXML
    private void handleq1(ActionEvent event) {
         System.out.println("handle q1");
         String res = MapReduceByContent.sampleMapReduce(tvSampleContent.getText().toString());
         tvRes.setText(res);
    }
      @FXML
    private void handleq2(ActionEvent event) {
         System.out.println("handle q2");
         
         String res = MapReduce.sampleMapReduce(tvSampleLength.getText().toString());
         tvRes.setText(res);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // Util.insertion();
        refresh();
    }   
    private void refresh()
    {
        smsList =Util.getAllList();
        System.out.println("list.size"+smsList.size());
        data = FXCollections.observableArrayList(smsList);
        initTableView();
    }
    
    private void initTableView()
    {
        tbAll.getColumns().clear();
        int column_maxWidth = 300;
        tbAll.setEditable(true);
        
         TableColumn Sender = new TableColumn("Sender"); 
        Sender.setPrefWidth(column_maxWidth);
        Sender.setCellValueFactory(
                new PropertyValueFactory<SMS, String>("sender"));  
        Sender.setCellFactory(TextFieldTableCell.forTableColumn());
         Sender.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<SMS, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SMS, String> t) {
                  String val= t.getNewValue();
                  int row = t.getTablePosition().getRow();
                  int col = t.getTablePosition().getColumn();
                    BasicDBObject updateDocument = new BasicDBObject();
                    updateDocument.append("$set", new BasicDBObject().append("sender", val.toString()));
                    
                    System.out.println("ObjectId("+data.get(row)._id+")");
                    BasicDBObject searchQuery2 = new BasicDBObject().append("_id", new ObjectId(data.get(row)._id) );

                    Connection.getDataBaseInstance().getCollection(Connection.LISTING).update(searchQuery2, updateDocument);
                    JOptionPane.showMessageDialog(null, "update Successful");
                }
            }
    );
        
        
        TableColumn Date = new TableColumn("Date"); 
        Date.setPrefWidth(column_maxWidth);
        Date.setCellValueFactory(
                new PropertyValueFactory<SMS, String>("date"));  
        Date.setCellFactory(TextFieldTableCell.forTableColumn());
        
         Date.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<SMS, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SMS, String> t) {
                  String val= t.getNewValue();
                  int row = t.getTablePosition().getRow();
                  int col = t.getTablePosition().getColumn();
                    BasicDBObject updateDocument = new BasicDBObject();
                    updateDocument.append("$set", new BasicDBObject().append("date", val.toString()));
                    
                    System.out.println("ObjectId("+data.get(row)._id+")");
                    BasicDBObject searchQuery2 = new BasicDBObject().append("_id", new ObjectId(data.get(row)._id) );

                    Connection.getDataBaseInstance().getCollection(Connection.LISTING).update(searchQuery2, updateDocument);
                    JOptionPane.showMessageDialog(null, "update Successful");
                }
            }
    );
        
         TableColumn Length = new TableColumn("Length"); 
        Length.setPrefWidth(100);
        Length.setCellValueFactory(
                new PropertyValueFactory<SMS, String>("length"));  
        Length.setCellFactory(TextFieldTableCell.forTableColumn());
        
         Length.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<SMS, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SMS, String> t) {
                  String val= t.getNewValue();
                  int row = t.getTablePosition().getRow();
                  int col = t.getTablePosition().getColumn();
                    BasicDBObject updateDocument = new BasicDBObject();
                    updateDocument.append("$set", new BasicDBObject().append("length", val.toString()));
                    
                    System.out.println("ObjectId("+data.get(row)._id+")");
                    BasicDBObject searchQuery2 = new BasicDBObject().append("_id", new ObjectId(data.get(row)._id) );

                    Connection.getDataBaseInstance().getCollection(Connection.LISTING).update(searchQuery2, updateDocument);
                    JOptionPane.showMessageDialog(null, "update Successful");
                }
            }
    );
        
        
        TableColumn isSpam = new TableColumn("isSpam"); 
        isSpam.setPrefWidth(100);
        isSpam.setCellValueFactory(
                new PropertyValueFactory<SMS, String>("isSpam"));  
        isSpam.setCellFactory(TextFieldTableCell.forTableColumn());
        
         isSpam.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<SMS, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SMS, String> t) {
                  String val= t.getNewValue();
                  int row = t.getTablePosition().getRow();
                  int col = t.getTablePosition().getColumn();
                    BasicDBObject updateDocument = new BasicDBObject();
                    updateDocument.append("$set", new BasicDBObject().append("isSpam", val.toString()));
                    
                    System.out.println("ObjectId("+data.get(row)._id+")");
                    BasicDBObject searchQuery2 = new BasicDBObject().append("_id", new ObjectId(data.get(row)._id) );

                    Connection.getDataBaseInstance().getCollection(Connection.LISTING).update(searchQuery2, updateDocument);
                    JOptionPane.showMessageDialog(null, "update Successful");
                }
            }
    );
        
         TableColumn Content = new TableColumn("content"); 
        Content.setPrefWidth(500);
        Content.setCellValueFactory(
                new PropertyValueFactory<SMS, String>("content"));  
        Content.setCellFactory(TextFieldTableCell.forTableColumn());
        
         Content.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<SMS, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<SMS, String> t) {
                  String val= t.getNewValue();
                  int row = t.getTablePosition().getRow();
                  int col = t.getTablePosition().getColumn();
                    BasicDBObject updateDocument = new BasicDBObject();
                    updateDocument.append("$set", new BasicDBObject().append("content", val.toString()));
                    
                    System.out.println("ObjectId("+data.get(row)._id+")");
                    BasicDBObject searchQuery2 = new BasicDBObject().append("_id", new ObjectId(data.get(row)._id) );

                    Connection.getDataBaseInstance().getCollection(Connection.LISTING).update(searchQuery2, updateDocument);
                    JOptionPane.showMessageDialog(null, "update Successful");
                }
            }
    );
        
        tbAll.setItems(data);
        // tbAll.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
         TableColumn Remove = new TableColumn("Delete");
         Remove.setCellValueFactory(new PropertyValueFactory<SMS, String>("delete"));
         Remove.setPrefWidth(200);
         
         tbAll.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = tbAll.getSelectionModel().getSelectedCells();

        
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                int row = tbAll.getSelectionModel().getSelectedIndex();
                int col = tablePosition.getColumn();
                if (val.toString().equals("Delete")) {
                   // String s = ((Drug) tableDrug.getItems().get(i)).getSsn();
                   // System.out.println(s);
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialog_result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                    if (dialog_result == 0) {
                       // String id = data.get(row)._id;
                        BasicDBObject basicDBObject = new BasicDBObject().append("_id", new ObjectId(data.get(row)._id) );
                        Connection.getDataBaseInstance().getCollection(Connection.LISTING).remove(basicDBObject);
                        JOptionPane.showMessageDialog(null, "Document Deleted.");
                        refresh();
                        System.out.println("Yes option");
                    } else {
                        System.out.println("No Option");
                    }
                }
            }
        });
        tbAll.getColumns().addAll(Remove);
        tbAll.getColumns().addAll(Sender, Date,Length,isSpam,Content);
    }
    
}
