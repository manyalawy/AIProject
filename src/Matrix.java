import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;



public class Matrix extends Search {
    static Grid grid;
    public HashSet<String> repeatedStates = new HashSet<String>();
    static int nodesExpandedCount = 0;
    

    public static String gridGen(){
        grid = new Grid(false);
        String grid0 = Helpers.changeStateFormat("5,5;2;3,4;1,2;0,3,1,4;2,3;4,4,0,2,0,2,4,4;2,2,91,2,4,62");
        String grid1 = Helpers.changeStateFormat("5,5;1;1,4;1,0;0,4;0,0,2,2;3,4,4,2,4,2,3,4;0,2,32,0,1,38");
        String grid2 = Helpers.changeStateFormat("5,5;2;3,2;0,1;4,1;0,3;1,2,4,2,4,2,1,2,0,4,3,0,3,0,0,4;1,1,77,3,4,34");

        return grid0;
        
        // return grid.genMatString();
    }

    public static String solve(String grid, String strategy, boolean visualize){

        //  String initialState = Helpers.changeStateFormat("5,5;2;3,4;1,2;0,3,1,4;2,3;4,4,0,2,0,2,4,4;2,2,91,2,4,62");
        // Node parent = new Node(null,null,initialState,0,0);
        Matrix m = new Matrix();
        String rootState = Helpers.changeStateFormat(grid);
        Node root = new Node(null, null, rootState, 0, 0);
        
        Node result = m.search(strategy, root);
        String path = SearchHelpers.getPath(result);
        System.out.println("\n" + "\n" + path);
        
        if(result != null)
            return path;
        else
            return "No Solution"; 
        // String result = Search.search(strategy,parent);
        //left,fly,right,carry,left,fly,down,right,drop,left,left,kill,left,left,up,carry,down,down,kill,up,right,right,right,right,drop;1;3;1246837 (plan,deaths,kills,nodes)
    }



    public boolean repeatedState(String state){
        //should remove damage of hostages from string state
        if (repeatedStates.contains(state)) {
            return true;
        } else {
            repeatedStates.add(state);
            return false;
        }
    }

    public Node search(String strategy,Node root){
        Node goal=null;
        switch(strategy){
            case "BF":  goal = breadthFirst(root);
                        break;
            case "DF":  goal = depthFirst(root);
                        break;
        }
        //get the path from root to goal node ; deaths; kills; number of nodes
        // return SearchHelpers.getPath(goal);
        return goal;
    }

    public Node breadthFirst(Node parent){
        

        Queue<Node> bfQueue = new LinkedList<Node>();
        bfQueue.add(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true){
            if (bfQueue.isEmpty()){
                return null;            // RETURN NO SOLUTION
            }

            Node front = bfQueue.remove();
            nodesExpandedCount++;       

            Grid grid = Helpers.stateToGrid(front.state);
            if (ActionsHelpers.reachedTestGoal(grid)==true){
                return front;
            }

            //UP,DOWN,RIGHT,LEFT,TAKEPILL,CARRY,DROP,KILL,FLY
            if(!repeatedState(front.state)){
                up= Actions.Up(front);
                if (up!=null)  
                    bfQueue.add(up);

                down=Actions.Down(front);
                if (down!=null)  
                    bfQueue.add(down);

                right=Actions.Right(front);
                if (right!=null)  
                    bfQueue.add(right);

                left=Actions.Left(front);
                if (left!=null)  
                    bfQueue.add(left);
                takepill=Actions.takePill(front);
                if (takepill!=null)  
                    bfQueue.add(takepill);

                carry=Actions.carry(front);
                if (carry!=null)  
                    bfQueue.add(carry); 
                
                drop=Actions.drop(front);
                if (drop!=null)  
                    bfQueue.add(drop);
            
                kill=Actions.kill(front);    
                if (kill!=null)  
                    bfQueue.add(kill);

                fly=Actions.fly(front);
                if (fly!=null)  
                    bfQueue.add(fly);
            }
        }
    }



    public Node depthFirst(Node parent){
        return parent;
    }

    @Override
    public boolean goalTest() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String stateSpace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int pathCost() {
        // TODO Auto-generated method stub
        return 0;
    }


    public static void main(String[] args) {


        solve(gridGen(), "BF", false);

    
    }


}