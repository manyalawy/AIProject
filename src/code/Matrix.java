package code;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.lang.model.util.ElementScanner14;



public class Matrix extends Search {
    static Grid grid;
    // public HashSet<String> stateSpace = new HashSet<String>();
    static int nodesExpandedCount = 0;
    

    public static String gridGen(){
        grid = new Grid(false);
        String grid0 = "5,5;2;3,4;1,2;0,3,1,4;2,3;4,4,0,2,0,2,4,4;2,2,91,2,4,62";
        String grid1 = "5,5;1;1,4;1,0;0,4;0,0,2,2;3,4,4,2,4,2,3,4;0,2,32,0,1,38";
	    String grid2 = "5,5;2;3,2;0,1;4,1;0,3;1,2,4,2,4,2,1,2,0,4,3,0,3,0,0,4;1,1,77,3,4,34";
	    String grid3 = "5,5;1;0,4;4,4;0,3,1,4,2,1,3,0,4,1;4,0;2,4,3,4,3,4,2,4;0,2,98,1,2,98,2,2,98,3,2,98,4,2,98,2,0,1";


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
        
        if(visualize)
            displayGrid(result);

        if(result != null)
            return path;
        else
            return "No Solution"; 
        // String result = Search.search(strategy,parent);
        //left,fly,right,carry,left,fly,down,right,drop,left,left,kill,left,left,up,carry,down,down,kill,up,right,right,right,right,drop;1;3;1246837 (plan,deaths,kills,nodes)
    }

    public static void displayGrid(Node goalNode){
         // Loop through all rows
        Grid gridClass = Helpers.stateToGrid(goalNode.state);
        Object [][]grid =gridClass.grid;
        
        Object temp;
        int neoX = gridClass.neo.x;
        int neoY = gridClass.neo.y;
        Boolean printNeo;
        while(goalNode.parent!=null){
            printNeo = true;
            gridClass = Helpers.stateToGrid(goalNode.state);
            grid =gridClass.grid;
            neoX = gridClass.neo.x;
            neoY = gridClass.neo.y;
            
            for (int i = 0; i < grid.length; i++){
                for (int j = 0; j < grid[i].length; j++){   // Loop through all elements of current row
                    //  System.out.print(grid[i][j] + " ");
                    temp = grid[i][j];
                    
                    if(temp instanceof Hostage){
                        Hostage h = (Hostage)temp;
                        if(neoX == i && neoY == j && printNeo)
                        {
                            printNeo = false;
                            if(h.damage == 100)
                                System.out.print("Hostage," + h.damage + ",Neo");  
                            else if(h.damage <= 10)
                                System.out.print("Hostage," + h.damage + ",Neo ");
                            else
                                System.out.print("Hostage," + h.damage + ",Neo  ");
                        }
                    
                        else
                        {
                            if(h.damage >= 10)
                            System.out.print("Hostage," + h.damage + "     ");  
                            else
                            System.out.print("Hostage," + h.damage + "      ");
                        }
                    }
            
                    if(temp instanceof Agent)
                    System.out.print("Agent          ");
                    
                    if(temp instanceof Pad){
                        Pad pad = (Pad) temp;
                        int padX = pad.goesToX;
                        int padY = pad.goesToY;

                        System.out.print("Pad(" + padX +"," + padY + ")       ");
                    }                  
                    
                    if(temp instanceof Pill && neoX == i && neoY == j && printNeo){
                        printNeo = false;
                        System.out.print("Neo,Pill       ");
                    }
                    else if(temp instanceof Pill)
                        System.out.print("Pill           ");
                    else if(neoX == i && neoY == j && printNeo){
                        printNeo = false;
                        System.out.print("  Neo          ");
                    }              

                    if(temp instanceof TelelphoneBooth && neoX == i && neoY == j && printNeo){
                        printNeo = false;
                        System.out.print("Neo,TB         ");
                    }
                    else if(temp instanceof TelelphoneBooth)
                        System.out.print("  TB           ");
                    else if(neoX == i && neoY == j && printNeo){
                        printNeo = false;
                        System.out.print("  Neo          ");
                    }
            
                    if(temp == null )
                        System.out.print("  .            ");
                  
                    
                }
                System.out.println();
            }   
            System.out.println("-----------------------------------------------------------------------");
            goalNode = goalNode.parent;
        }
    }
    


    public boolean repeatedState(String state){
        //should remove damage of hostages from string state
        if (stateSpace.contains(state)) {
            return true;
        } else {
            stateSpace.add(state);
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
            System.out.print(front.operator + ", ");  

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
        
        Stack<Node> dfStack = new Stack<Node>();
        dfStack.add(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true){
            if (dfStack.isEmpty()){
                return null;            // RETURN NO SOLUTION
            }

            Node front = dfStack.pop();
            nodesExpandedCount++;       

            Grid grid = Helpers.stateToGrid(front.state);
            if (ActionsHelpers.reachedTestGoal(grid)==true){
                return front;
            }

            //UP,DOWN,RIGHT,LEFT,TAKEPILL,CARRY,DROP,KILL,FLY
            if(!repeatedState(front.state)){
                up= Actions.Up(front);
                if (up!=null)  
                    dfStack.add(up);

                down=Actions.Down(front);
                if (down!=null)  
                    dfStack.add(down);

                right=Actions.Right(front);
                if (right!=null)  
                    dfStack.add(right);

                left=Actions.Left(front);
                if (left!=null)  
                    dfStack.add(left);

                takepill=Actions.takePill(front);
                if (takepill!=null)  
                    dfStack.add(takepill);

                carry=Actions.carry(front);
                if (carry!=null)  
                    dfStack.add(carry); 
                
                drop=Actions.drop(front);
                if (drop!=null)  
                    dfStack.add(drop);
            
                kill=Actions.kill(front);    
                if (kill!=null)  
                    dfStack.add(kill);

                fly=Actions.fly(front);
                if (fly!=null)  
                    dfStack.add(fly);
            }
        }
    }
    
        
    @Override
    public boolean goalTest() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int pathCost() {
        // TODO Auto-generated method stub
        return 0;
    }


    public static void main(String[] args) {


        solve(gridGen(), "BF", true);

    
    }


}