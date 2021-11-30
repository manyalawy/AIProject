package code;

//to be pushed in queue in algorithms
public class Node{
    Node parent;
    Operators operator;
    String state; 
    int depth;
    int pathCost;
    
    public Node(){

    }

    public Node(Node parent,Operators operator, String state,int depth,int pathCost){
        this.parent = parent;
        this.operator = operator;
        this.state = state;
        this.depth = depth;
        this.pathCost = pathCost;
    }
}