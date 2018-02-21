import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Diaz, Jericho Hans
 * On 2/21/2018
 */
public class Prims {
    /*public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A","C",4));
        edges.add(new Edge("A", "D",4));
        edges.add(new Edge("B","D",3));
        edges.add(new Edge("B", "F",3));
        edges.add(new Edge("C","D",2));
        edges.add(new Edge("D","E",2));
        edges.add(new Edge("D","F",4));
        edges.add(new Edge("E","F",3));



        ArrayList<Edge> finallist = prims(createGraph(edges));
        for(Edge edge : finallist)
            System.out.println(edge.start +" - "+edge.end + " : "+ edge.weight);
    }*/

    public static List<List<Edge>> createGraph(List<Edge> edges){
        List<List<Edge>> graph = new ArrayList<>();
        int length = edges.size()*2;
        for(int i = 0; i < length; i++)
            graph.add(new ArrayList<>());
        for(Edge edge : edges){
            Edge otherSide = new Edge(edge.end, edge.start, edge.weight);
            graph.get(edge.startInt).add(edge);
            graph.get(edge.endInt).add(otherSide);
        }
        return graph;
    }

    public static ArrayList<Edge> prims(List<List<Edge>> graph){
        if(!graph.isEmpty()){
            ArrayList<Edge> mst = new ArrayList<>();
            PriorityQueue<Edge> pq = new PriorityQueue<>((Object o1, Object o2) -> {
                Edge f = (Edge)o1;
                Edge s = (Edge)o2;

                if(f.weight<s.weight)
                    return -1;
                else if(s.weight<f.weight)
                    return 1;
                else
                    return 0;
            });
            for(Edge edge : graph.get(0)){
                pq.add(edge);
            }
            boolean[] marked = new boolean[graph.size()];
            marked[0] = true;
            while(!pq.isEmpty()){
                Edge e = pq.peek();

                pq.poll();
                if(marked[e.startInt] && marked[e.endInt])
                    continue;
                for(Edge edge : graph.get(e.endInt)){
                    if(!marked[e.endInt])
                        pq.add(edge);
                }
                marked[e.endInt] = true;
                mst.add(e);
            };
            return mst;
        }
        else return null;
    }
}
