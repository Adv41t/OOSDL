import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Simple Room class (must for TableView)
class Room {
    int roomNo;
    String type;
    String status;

    Room(int r, String t, String s) {
        roomNo = r;
        type = t;
        status = s;
    }

    public int getRoomNo() { return roomNo; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public void setStatus(String s) { status = s; }
}

public class HotelGUI extends Application {

    ObservableList<Room> list = FXCollections.observableArrayList();

    public void start(Stage stage) {

        // Inputs
        TextField roomNo = new TextField();
        ComboBox<String> type = new ComboBox<>();
        type.getItems().addAll("Single","Double","Deluxe");

        TextField bookRoom = new TextField();
        Label msg = new Label();

        // TableView
        TableView<Room> table = new TableView<>();

        TableColumn<Room,Integer> c1 = new TableColumn<>("Room");
        c1.setCellValueFactory(new PropertyValueFactory<>("roomNo"));

        TableColumn<Room,String> c2 = new TableColumn<>("Type");
        c2.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Room,String> c3 = new TableColumn<>("Status");
        c3.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(c1,c2,c3);
        table.setItems(list);

        // Buttons
        Button add = new Button("Add");
        Button book = new Button("Book");
        Button checkout = new Button("Checkout");

        // Add Room
        add.setOnAction(e -> {
            list.add(new Room(
                Integer.parseInt(roomNo.getText()),
                type.getValue(),
                "Available"
            ));
            msg.setText("Room Added");
        });

        // Book
        book.setOnAction(e -> {
            int r = Integer.parseInt(bookRoom.getText());
            for(Room x : list){
                if(x.getRoomNo()==r && x.getStatus().equals("Available")){
                    x.setStatus("Occupied");
                    msg.setText("Booked");
                    table.refresh();
                    return;
                }
            }
            msg.setText("Not Available");
        });

        // Checkout
        checkout.setOnAction(e -> {
            int r = Integer.parseInt(bookRoom.getText());
            for(Room x : list){
                if(x.getRoomNo()==r){
                    x.setStatus("Available");
                    msg.setText("Checked Out");
                    table.refresh();
                    return;
                }
            }
        });

        // Layout
        VBox root = new VBox(10,
                new Label("Room No"), roomNo,
                new Label("Type"), type,
                add,
                new Label("Room No"), bookRoom,
                book, checkout,
                table, msg
        );

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Hotel System");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
