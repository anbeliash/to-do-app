package com.example.projektproo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Statement statement;
    DatabaseMethods mt = new DatabaseMethods();

    @FXML
    private TableColumn<Task, String> CategoryColumn;

    @FXML
    private TableColumn<Task, String> DedlineColumn;

    @FXML
    private TableColumn<Task, String> DescriptionColumn;

    @FXML
    private TableView<Task> Table;

    @FXML
    private TableColumn<Task, String> TitleColumn;

    @FXML
    private TableColumn<Task, String> idColumn;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private TableColumn<Task, String> isCompletedColumn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField taskCategory;

    @FXML
    private TextField taskDeadline;

    @FXML
    private TextField taskDescription;

    @FXML
    private TextField taskIsComplete;

    @FXML
    private TextField taskTitle;

    private Integer id;
    private String UserId = "1";

    @FXML
    void addBtnClick(ActionEvent event) {
        String Title, Description, Deadline, Category, IsCompleted;
        Title = taskTitle.getText();
        Description = taskDescription.getText();
        Deadline = taskDeadline.getText();
        Category = taskCategory.getText();
        IsCompleted = taskIsComplete.getText();
        mt.insertTask(Title, Description,  Category, Deadline, IsCompleted, UserId);
        ResultSet rset = mt.selectTasks(UserId);
        table(rset);
    }

    @FXML
    void deleteBtnClick(ActionEvent event) {
        int myIndex = Table.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(Table.getItems().get(myIndex).getId()));
        mt.deleteTask(id);
        ResultSet rset = mt.selectTasks(UserId);
        table(rset);
    }

    @FXML
    void editBtnClick(ActionEvent event) {
        int myIndex = Table.getSelectionModel().getSelectedIndex();
        String Title, Description, Deadline, Category, IsCompleted;
        id = Integer.parseInt(String.valueOf(Table.getItems().get(myIndex).getId()));
        Title = taskTitle.getText();
        Description = taskDescription.getText();
        Deadline = taskDeadline.getText();
        Category = taskCategory.getText();
        IsCompleted = taskIsComplete.getText();
        mt.editTask(Title, Description,  Category, Deadline, IsCompleted, id);
        ResultSet rset = mt.selectTasks(UserId);
        table(rset);
    }

    @FXML
    void searchBtnClick(ActionEvent event) {
        String Title, Category, IsCompleted;
        Title = taskTitle.getText();
        IsCompleted = taskIsComplete.getText();
        Category = taskCategory.getText();
        ResultSet rset = mt.filterTasks(Title, IsCompleted, Category, UserId);
        table(rset);
    }

    @FXML
    void allBtnClick(ActionEvent event) {
        ResultSet rset = mt.selectTasks(UserId);
        table(rset);
    }

    private void parseData()
    {
        Table.setRowFactory( tv -> {
            TableRow<Task> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = Table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(Table.getItems().get(myIndex).getId()));
                    taskTitle.setText(Table.getItems().get(myIndex).getTitle());
                    taskCategory.setText(Table.getItems().get(myIndex).getCategory());
                    taskDescription.setText(Table.getItems().get(myIndex).getDescription());
                    taskIsComplete.setText(Table.getItems().get(myIndex).getIsCompleted());
                    taskDeadline.setText(Table.getItems().get(myIndex).getDeadline());
                }
            });
            return myRow;
        });
    }

    private void resetData()
    {
        taskTitle.setText("");
//        taskCategory.setText(Table.getItems().get(myIndex).getCategory());
//        taskDescription.setText(Table.getItems().get(myIndex).getDescription());
//        taskIsComplete.setText(Table.getItems().get(myIndex).getIsCompleted());
//        taskDeadline.setText(Table.getItems().get(myIndex).getDeadline());

    }

    void table(ResultSet rset)  {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        try{
            while (rset.next())
            {
            String Title = rset.getString("Title");
            String Description = rset.getString("Description");
            String Deadline = rset.getString("Deadline");
            String Category = rset.getString("Category");
            String IsCompleted = rset.getString("IsCompleted");
            String UserId = rset.getString("UserId");
            String TaskId = rset.getString("TaskId");
            Task task = new Task();
            task.setTitle(Title);
            task.setUserId(UserId);
            task.setIsCompleted(IsCompleted);
            task.setCategory(Category);
            task.setDeadline(Deadline);
            task.setDescription(Description);
            task.setId(TaskId);
            tasks.add(task);
            }
            Table.setItems(tasks);
            TitleColumn.setCellValueFactory(f -> f.getValue().titleProperty());
            CategoryColumn.setCellValueFactory(f -> f.getValue().categoryProperty());
            DedlineColumn.setCellValueFactory(f -> f.getValue().deadlineProperty());
            DescriptionColumn.setCellValueFactory(f -> f.getValue().descriptionProperty());
            isCompletedColumn.setCellValueFactory(f -> f.getValue().isCompletedProperty());
            idColumn.setCellValueFactory(f -> f.getValue().idProperty());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        parseData();
    }

    @Override
    public void initialize(URL location,
                           ResourceBundle resources) {
        statement = DatabaseConnector.getConnection().getKey();
        ResultSet rset = mt.selectTasks(UserId);
        table(rset);
    }
}
