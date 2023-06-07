package com.example.freshly;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPage implements Initializable,LoginChecker {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Pane headerMainPage;

    @FXML
    private Pane headerMainPageUnlogined;

    @FXML
    private Button allProducts;

    @FXML
    private Button dairy;

    @FXML
    private Button drinks;

    @FXML
    private Button groceries;

    @FXML
    private Button nuts;

    @FXML
    private Button proteins;
    Client client;
    public static Customer customer=null;
    private Socket socket;
    private int port = 8080;
    private String hostIp = "localhost";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            socket = new Socket(hostIp,port);
            System.out.println("Connected to Server");
            client = new Client(socket);
            String msg = Client.in.readLine();
            System.out.println(msg);

            scrollPane.fitToWidthProperty();
            scrollPane.fitToHeightProperty();
            changeHeaderOfLogin();
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println("Error creating Client ... ");
            System.exit(2);
        }

    }

    @Override
    public boolean isLogin() {
        return customer != null;
    }
    public void changeHeaderOfLogin(){
        if (isLogin()){
            headerMainPage.setVisible(true);
            headerMainPageUnlogined.setVisible(false);
        }else {
            headerMainPage.setVisible(false);
            headerMainPageUnlogined.setVisible(true);
        }
    }

    public void onFilterButtonsClicked(ActionEvent event){
        try {
            if (event.getSource() == proteins) {
                ShowingProducts.type = "مواد پروتئینی";
                changeScene();
            }
            if (event.getSource() == drinks) {
                ShowingProducts.type = "نوشیدنی";
                changeScene();
            }
            if (event.getSource() == nuts) {
                ShowingProducts.type = "تنقلات";
                changeScene();
            }
            if (event.getSource() == dairy) {
                ShowingProducts.type = "لبنیات";
                changeScene();
            }
            if (event.getSource() == groceries) {
                ShowingProducts.type = "کالاهای اساسی و خواروبار";
                changeScene();
            }
            if (event.getSource() == allProducts) {
                //ShowingProducts.type = "";
                changeScene();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void changeScene() throws IOException {
        proteins.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("ShowingProducts.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        //stage.setTitle("Login Page");
                    /*stage.setOnHiding(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent we) {
                            stage.hide();
                            // نمایش پنجره قبلی
                            stage.getOwner().showingProperty();
                        }
                    });*/


        stage.setScene(scene);
        stage.show();

    }
}
