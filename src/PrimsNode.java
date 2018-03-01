import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Diaz, Jericho Hans
 * On 2/21/2018
 */
public class PrimsNode extends Circle{
    private String name;
    public Label label;
    private AnchorPane ap;
    public Circle circle;
    public StackPane sp;
    public PrimsNode(String name, AnchorPane ap){

        this.name = name;
        this.ap = ap;
        setRadius(20);
        setFill(Color.BLACK);
    }
    public void setName(){
        sp = new StackPane();
        sp.setLayoutX(getCenterX()-getRadius());
        sp.setLayoutY(getCenterY()-getRadius());
        circle = new Circle();
        circle.setCenterX(getCenterX());
        circle.setCenterY(getCenterY());
        circle.setFill(Color.WHITE);
        circle.setRadius(15);
        sp.getChildren().add(this);
        sp.getChildren().add(circle);
        label = new Label(name);
        label.setStyle("-fx-font-size: 30px;");
        label.setLayoutX(getCenterX()-10); //-15
        label.setLayoutY(getCenterY()-23);
        sp.getChildren().add(label);
        ap.getChildren().add(sp);
    }
    public String getName(){
        return name;
    }
}
