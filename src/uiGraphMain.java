import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Diaz, Jericho Hans
 * On 2/22/2018
 */
public class uiGraphMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uiGraph.fxml"));

        primaryStage.setTitle("DEALGO");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();

    }
}
