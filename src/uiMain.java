import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Diaz, Jericho Hans
 * On 2/21/2018
 */

import java.io.IOException;

public class uiMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uiWeightedGraph.fxml"));

        primaryStage.setTitle("DEALGO");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();

    }
}
