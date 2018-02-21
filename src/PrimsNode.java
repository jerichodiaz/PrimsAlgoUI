import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * Created by Diaz, Jericho Hans
 * On 2/21/2018
 */
public class PrimsNode extends Circle{
    private String name;
    private Label label;
    private AnchorPane ap;
    public PrimsNode(String name, AnchorPane ap){
        this.name = name;
        this.ap = ap;
    }
    public void setName(){
        label = new Label(name);
        label.setLayoutX(getCenterX()-15);
        label.setLayoutY(getCenterY()-15);
        ap.getChildren().add(label);
    }
    public String getName(){
        return name;
    }
}
