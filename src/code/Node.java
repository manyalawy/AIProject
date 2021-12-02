package code;

//to be pushed in queue in algorithms
public class Node implements Comparable<Node>{
    Node parent;
    Operators operator;
    String state; 
    int depth;
    int pathCost;
    int heuristic1;
    int heuristic2;
    
    public Node(){

    }

    public Node(Node parent,Operators operator, String state,int depth){
        this.parent = parent;
        this.operator = operator;
        this.state = state;
        this.depth = depth;

        Grid grid = Helpers.stateToGrid(state);
        this.pathCost = 2*grid.kills + 4*grid.deaths + depth;;
        int costPerMove;
        if(depth==0){
            costPerMove = 0;
        }
        else{
            costPerMove = (int) Math.floor((grid.deaths + grid.kills) / depth);
        }
        int distance = Math.abs(grid.neo.x - grid.tb.x) + Math.abs(grid.neo.y - grid.tb.y);
        this.heuristic1 = distance * costPerMove;
        this.heuristic2 = grid.kills + grid.deaths;
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