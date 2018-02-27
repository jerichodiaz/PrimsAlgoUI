import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Diaz, Jericho Hans
 * On 2/22/2018
 */
public class uiGraphMain extends Application {
    private uiGraphController graphController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uiGraph.fxml"));

        primaryStage.setTitle("DEALGO");
        graphController = new uiGraphController();
        loader.setControllerFactory( c -> graphController);
        Scene scene = new Scene(loader.load());

        primaryStage.setScene(scene);
        primaryStage.show();
        addShortcuts(scene);
    }
    private void addShortcuts(Scene scene){
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination reset = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
            @Override
            public void handle(KeyEvent event) {
                if(reset.match(event)){
                    graphController.clearBoard();
                }
            }
        });
    }
}
