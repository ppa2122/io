package pl.kognitywistyka.io;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kognitywistyka.io.data.DataContext;
import pl.kognitywistyka.io.model.SimpleData;

import java.io.IOException;

public class HelloApplication extends Application {

    protected DataContext userData = new DataContext();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloController ctrl = fxmlLoader.getController();
        userData.setSimpleData(new SimpleData());
        ctrl.setDataContext(userData);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}