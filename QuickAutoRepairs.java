import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuickAutoRepairs extends Application {
    private TextField nameEntry;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a pane
        Pane root = new Pane();
        
        // Set up the scene
        Scene scene = new Scene(root, 1366, 728);
        
        // Set window title
        primaryStage.setTitle("Welcome to Quick Auto Repairs");
        
        // Load and set background image
        Image logoImage = new Image("file:logo.jpg");
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(1366);
        logoView.setFitHeight(728);
        root.getChildren().add(logoView);
        
        // Create label
        Label lbl = new Label("Enter your Name");
        lbl.setStyle("-fx-font-family: Arial; -fx-font-size: 14pt;");
        lbl.setLayoutX(600);
        lbl.setLayoutY(200);
        root.getChildren().add(lbl);
        
        // Create name entry field
        nameEntry = new TextField();
        nameEntry.setPrefWidth(30 * 8); // Approximate width conversion
        nameEntry.setStyle("-fx-font-family: Arial; -fx-font-size: 12pt;");
        nameEntry.setLayoutX(600);
        nameEntry.setLayoutY(250);
        root.getChildren().add(nameEntry);
        
        // Check-in Button
        Button btnCheckIn = new Button("Check In");
        btnCheckIn.setStyle("-fx-font-family: Arial; -fx-font-size: 12pt;");
        btnCheckIn.setLayoutX(600);
        btnCheckIn.setLayoutY(300);
        btnCheckIn.setOnAction(e -> checkIn());
        root.getChildren().add(btnCheckIn);
        
        // Check-out Button
        Button btnCheckOut = new Button("Check Out");
        btnCheckOut.setStyle("-fx-font-family: Arial; -fx-font-size: 12pt;");
        btnCheckOut.setLayoutX(700);
        btnCheckOut.setLayoutY(300);
        btnCheckOut.setOnAction(e -> checkOut());
        root.getChildren().add(btnCheckOut);
        
        // Exit Button
        Button btnCancel = new Button("Exit");
        btnCancel.setStyle("-fx-font-family: Arial; -fx-font-size: 12pt; -fx-background-color: red; -fx-text-fill: white;");
        btnCancel.setLayoutX(650);
        btnCancel.setLayoutY(350);
        btnCancel.setOnAction(e -> primaryStage.close());
        root.getChildren().add(btnCancel);
        
        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void checkIn() {
        String name = nameEntry.getText().trim();
        if (!name.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
                writer.println(name + " - Check-in: " + formattedDateTime);
                System.out.println(name + " checked in at " + formattedDateTime);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            nameEntry.clear();
        } else {
            System.out.println("Please enter a name.");
        }
    }
    
    private void checkOut() {
        String name = nameEntry.getText().trim();
        if (!name.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
                writer.println(name + " - Check-out: " + formattedDateTime);
                System.out.println(name + " checked out at " + formattedDateTime);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            nameEntry.clear();
        } else {
            System.out.println("Please enter a name.");
        }
    }
}