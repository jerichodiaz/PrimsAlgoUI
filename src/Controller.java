import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diaz, Jericho Hans
 * On 2/21/2018
 */
public class Controller {
    @FXML AnchorPane ap;
    private boolean isStarted = false;
    private double ms = 500;
    String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    List<PrimsNode> nodes = new ArrayList<>();
    int x = 0;
    @FXML private void clearAP(){
        nodes.clear();
        x = 0; y = 0;
        isStarted = false;
        ap.getChildren().clear();
    }
    @FXML private void mouseClicked(MouseEvent ev){
        if(!isStarted) {
            PrimsNode newNode = new PrimsNode(names.charAt(x)+"", ap);
            x++;
            newNode.setCenterX(ev.getX());
            newNode.setCenterY(ev.getY());
            newNode.setName();
            nodes.add(newNode);
        }
    }
    @FXML private void startAlgorithm(){
        isStarted = true;
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < nodes.size(); i++){
            PrimsNode from = nodes.get(i);
            for(int j = 0; j < nodes.size(); j++){
                PrimsNode to = nodes.get(j);
                edges.add(new Edge(from, to, getDistance(from,to)));
            }
        }
        ArrayList<Edge> finallist = Prims.prims(Prims.createGraph(edges));

        createLines(finallist);
    }
    int y = 0;
    private void createLines(ArrayList<Edge> edges) {
        Edge edge = edges.get(y);
        y++;
        Line line = new Line();
        line.setStartX(edge.start.getCenterX());
        line.setStartY(edge.start.getCenterY());
        line.setEndX(edge.start.getCenterX());
        line.setEndY(edge.start.getCenterY());
        line.setStrokeWidth(4);

        Label label = new Label(Math.round(edge.weight)+"");
        label.setTextFill(Color.RED);
        label.setLayoutX((edge.start.getCenterX()+edge.end.getCenterX())/2);
        label.setLayoutY((edge.start.getCenterY()+edge.end.getCenterY())/2);
        label.setStyle("-fx-font-size: 20px;");

        ap.getChildren().add(line);

        Timeline tl = new Timeline();
        KeyValue kv = new KeyValue(line.endXProperty(), edge.end.getCenterX());
        KeyValue kv2 = new KeyValue(line.endYProperty(), edge.end.getCenterY());
        KeyFrame kf = new KeyFrame(new Duration(ms), kv);
        KeyFrame kf2 = new KeyFrame(new Duration(ms), kv2);
        tl.getKeyFrames().addAll(kf, kf2);
        tl.play();
        redrawCircles();
        tl.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(y!=edges.size()) {
                    createLines(edges);
                }
                ap.getChildren().add(label);
                redrawCircles();
            }
        });
    }

    private void redrawCircles(){
        nodes.forEach(c -> {
            ap.getChildren().removeAll(c.sp);
            ap.getChildren().addAll(c.sp);
        });
    }
    private double getDistance(PrimsNode node, PrimsNode node2){
        double x=0,y=0;
        x = node.getCenterX()-node2.getCenterX();
        y = node.getCenterY()-node2.getCenterY();
        x = Math.abs(x);
        y = Math.abs(y);
        return Math.sqrt(x*x+y*y);
    }
}
