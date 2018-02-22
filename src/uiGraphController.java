import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Diaz, Jericho Hans
 * On 2/22/2018
 */
public class uiGraphController implements Initializable{
    @FXML private AnchorPane ap;
    @FXML private Button start;
    @FXML private TableView table;
    @FXML private TextField from, to, weight;
    private List<PrimsNode> nodes = new ArrayList<>();
    private ObservableList<NamedEdge> edges = FXCollections.observableArrayList();
    String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int x;

    @FXML private void mouseClicked(MouseEvent evt){
        PrimsNode pn = new PrimsNode(names.charAt(x)+"", ap);
        x++;
        pn.setCenterX(evt.getX());
        pn.setCenterY(evt.getY());
        pn.setRadius(5);
        pn.setName();
        nodes.add(pn);
        ap.getChildren().add(pn);
    }
    @FXML private void addEdge(){
        if(!from.getText().isEmpty() && !to.getText().isEmpty() && !weight.getText().isEmpty()){
            Optional<PrimsNode> a = nodes.stream().filter(node -> node.getName().equals(from.getText())).findFirst();
            Optional<PrimsNode> b = nodes.stream().filter(node -> node.getName().equals(to.getText())).findFirst();
            edges.add(new NamedEdge(a.get(),b.get(),Double.parseDouble(weight.getText())));
            drawLine(a.get(),b.get(), Double.parseDouble(weight.getText()));
        }
    }
    private void drawLine(PrimsNode a, PrimsNode b, double weight){
        Line line = new Line();
        line.setStroke(Color.BLACK);
        line.setStartX(a.getCenterX());
        line.setStartY(a.getCenterY());
        line.setEndX(a.getCenterX());
        line.setEndY(a.getCenterY());
        ap.getChildren().add(line);

        Label label = new Label(Math.round(weight)+"");
        label.setTextFill(Color.RED);
        label.setLayoutX((a.getCenterX()+b.getCenterX())/2);
        label.setLayoutY((a.getCenterY()+b.getCenterY())/2);
        ap.getChildren().add(label);

        Timeline tl = new Timeline();
        KeyValue kv = new KeyValue(line.endXProperty(), b.getCenterX());
        KeyValue kv2 = new KeyValue(line.endYProperty(), b.getCenterY());
        KeyFrame kf = new KeyFrame(new Duration(500), kv);
        KeyFrame kf2 = new KeyFrame(new Duration(500), kv2);
        tl.getKeyFrames().addAll(kf, kf2);
        tl.play();
    }
    int y=0;
    private void createFinalLines(ArrayList<Edge> edges){
        Edge edge = edges.get(y);
        y++;
        Line line = new Line();
        line.setStroke(Color.RED);
        line.setStrokeWidth(3);
        line.setStartX(edge.start.getCenterX());
        line.setStartY(edge.start.getCenterY());
        line.setEndX(edge.start.getCenterX());
        line.setEndY(edge.start.getCenterY());
        ap.getChildren().add(line);
        Timeline tl = new Timeline();
        KeyValue kv = new KeyValue(line.endXProperty(), edge.end.getCenterX());
        KeyValue kv2 = new KeyValue(line.endYProperty(), edge.end.getCenterY());
        KeyFrame kf = new KeyFrame(new Duration(500), kv);
        KeyFrame kf2 = new KeyFrame(new Duration(500), kv2);
        tl.getKeyFrames().addAll(kf, kf2);
        tl.play();
        tl.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(y!=edges.size()) {
                    createFinalLines(edges);
                }
            }
        });
    }

    @FXML private void startAlgorithm(){
        List<Edge> allEdges = new ArrayList<>();
        for(NamedEdge name : edges){
            allEdges.add(name.getEdge());
        }
        ArrayList<Edge> finallist = Prims.prims(Prims.createGraph(allEdges));

        createFinalLines(finallist);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
    }

    private void setupTable(){
        TableColumn<NamedEdge, String> from = new TableColumn("from");
        from.setCellValueFactory(new PropertyValueFactory<NamedEdge, String>("from"));

        TableColumn<NamedEdge, String> to = new TableColumn("to");
        to.setCellValueFactory(new PropertyValueFactory<NamedEdge, String>("to"));

        TableColumn<NamedEdge, String> weight = new TableColumn("weight");
        weight.setCellValueFactory(new PropertyValueFactory<NamedEdge, String>("weight"));

        table.getColumns().addAll(from, to, weight);
        table.setItems(edges);
    }
}
