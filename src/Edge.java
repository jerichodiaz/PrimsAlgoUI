/**
 * Created by Diaz, Jericho Hans
 * On 2/21/2018
 */
public class Edge {
    public PrimsNode start;
    public PrimsNode end;
    public String startName;
    public String endName;
    public int startInt;
    public int endInt;
    public double weight;
    public Edge(PrimsNode start, PrimsNode end, double weight){
        this.start = start;
        this.end = end;
        startName = start.getName();
        endName = end.getName();
        this.weight = weight;
        startInt = getInt(startName);
        endInt = getInt(endName);
    }
    private int getInt(String toInt){
        String toIntUC = toInt.toUpperCase();
        String number="";
        for(char c : toIntUC.toCharArray()){
            int temp = (int)c;
            int temp_i = 64;
            if(temp<=90&& temp>=65)
                number += temp-temp_i;
        }
        return Integer.parseInt(number)-1;
    }
}
