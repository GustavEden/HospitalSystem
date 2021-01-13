package Patient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Authority extends Application {

    public void start( Stage stage) throws Exception{
        Parent root= (Parent) FXMLLoader.load(getClass().getResource("PatientLogIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hospital system");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
