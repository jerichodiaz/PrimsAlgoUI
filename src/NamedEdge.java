import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Diaz, Jericho Hans
 * On 2/22/2018
 */
public class NamedEdge {
    private PrimsNode pFrom, pTo;
    private StringProperty from;
    private StringProperty to;
    private StringProperty weight;
    private double dWeight;
    private Edge edge;
    public NamedEdge(PrimsNode pFrom, PrimsNode pTo, double weight){
        this.pFrom = (pFrom);
        from = new SimpleStringProperty(pFrom.getName());
        to = new SimpleStringProperty(pTo.getName());
        this.pTo = (pTo);
        dWeight = weight;
        this.weight = new SimpleStringProperty(weight+"");
        edge = new Edge(pFrom, pTo, weight);
    }
    public Edge getEdge(){
        return edge;
    }

    public String getFrom() {
        return from.get();
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public String getTo() {
        return to.get();
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public StringProperty fromProperty() {
        return from;
    }

    public StringProperty toProperty() {
        return to;
    }
}
