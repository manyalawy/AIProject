package code;

//to be pushed in queue in algorithms
public class Node implements Comparable<Node>{
    Node parent;
    Operators operator;
    String state; 
    int depth;
    int pathCost;
    
    public Node(){

    }

    public Node(Node parent,Operators operator, String state,int depth){
        this.parent = parent;
        this.operator = operator;
        this.state = state;
        this.depth = depth;

        Grid grid = Helpers.stateToGrid(state);
        this.pathCost = 2*grid.kills + 4*grid.deaths + depth;;

    }

    @Override
    public int compareTo(Node a) {
        if(a.pathCost> pathCost)
            return -1;
        if(a.pathCost<pathCost)
            return 1;
        return 0;
    }
}