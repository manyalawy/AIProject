package code;
import java.util.*;


public class Matrix extends Search {
    static Grid grid;
    // public HashSet<String> stateSpace = new HashSet<String>();
    static int nodesExpandedCount = 0;
    

    public static String gridGen(){
       // grid = new Grid(false);
       String grid0 = "5,5;2;3,4;1,2;0,3,1,4;2,3;4,4,0,2,0,2,4,4;2,2,91,2,4,62";
       String grid1 = "5,5;1;1,4;1,0;0,4;0,0,2,2;3,4,4,2,4,2,3,4;0,2,32,0,1,38";
       String grid2 = "5,5;2;3,2;0,1;4,1;0,3;1,2,4,2,4,2,1,2,0,4,3,0,3,0,0,4;1,1,77,3,4,34";
       String grid3 = "5,5;1;0,4;4,4;0,3,1,4,2,1,3,0,4,1;4,0;2,4,3,4,3,4,2,4;0,2,98,1,2,98,2,2,98,3,2,98,4,2,98,2,0,1";
       String grid4 = "5,5;1;0,4;4,4;0,3,1,4,2,1,3,0,4,1;4,0;2,4,3,4,3,4,2,4;0,2,98,1,2,98,2,2,98,3,2,98,4,2,98,2,0,98,1,0,98";
       String grid5 = "5,5;2;0,4;3,4;3,1,1,1;2,3;3,0,0,1,0,1,3,0;4,2,54,4,0,85,1,0,43";
       String grid6 = "5,5;2;3,0;4,3;2,1,2,2,3,1,0,0,1,1,4,2,3,3,1,3,0,1;2,4,3,2,3,4,0,4;4,4,4,0,4,0,4,4;1,4,57,2,0,46";
       String grid7 = "5,5;3;1,3;4,0;0,1,3,2,4,3,2,4,0,4;3,4,3,0,4,2;1,4,1,2,1,2,1,4,0,3,1,0,1,0,0,3;4,4,45,3,3,12,0,2,88";
       String grid8 = "5,5;2;4,3;2,1;2,0,0,4,0,3,0,1;3,1,3,2;4,4,3,3,3,3,4,4;4,0,17,1,2,54,0,0,46,4,1,22";
       String grid9 = "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
       String grid10 = "5,5;4;1,1;4,1;2,4,0,4,3,2,3,0,4,2,0,1,1,3,2,1;4,0,4,4,1,0;2,0,0,2,0,2,2,0;0,0,62,4,3,45,3,3,39,2,3,40";

        return grid2;
        
        // return grid.genMatString();
    }

    public static String solve(String grid, String strategy, boolean visualize){

        //  String initialState = Helpers.changeStateFormat("5,5;2;3,4;1,2;0,3,1,4;2,3;4,4,0,2,0,2,4,4;2,2,91,2,4,62");
        // Node parent = new Node(null,null,initialState,0,0);
        Matrix m = new Matrix();
        String rootState = Helpers.changeStateFormat(grid);
        Node root = new Node(null, null, rootState, 0);
        
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
            case "ID":  goal = iterativeDepth(root);
            			break;
            case "UC":  goal = uniformCost(root);
                break;
            case "GR1": goal = greedy(root,"1");
                break;
            case "GR2": goal = greedy(root, "2");
                break;
            case "AS1": goal = astar(root, "1");
                break;
            case "AS2": goal = astar(root, "2");
                break;
        }
        //get the path from root to goal node ; deaths; kills; number of nodes
        // return SearchHelpers.getPath(goal);
        return goal;
    }

    public Node astar(Node parent, String heurisic) {
        ArrayList<Node> arr = new ArrayList<>();
        arr.add(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true) {
            if (arr.isEmpty()) {
                return null;            // RETURN NO SOLUTION
            }

            Node front = arr.remove(0);
            nodesExpandedCount++;

            Grid grid = Helpers.stateToGrid(front.state);
            if (ActionsHelpers.reachedTestGoal(grid) == true) {
                return front;
            }

            //UP,DOWN,RIGHT,LEFT,TAKEPILL,CARRY,DROP,KILL,FLY
            if (!repeatedState(front.state)) {
                fly = Actions.fly(front);
                if (fly != null)
                    arr.add(fly);

                up = Actions.Up(front);
                if (up != null)
                    arr.add(up);

                down = Actions.Down(front);
                if (down != null)
                    arr.add(down);

                right = Actions.Right(front);
                if (right != null)
                    arr.add(right);

                left = Actions.Left(front);
                if (left != null)
                    arr.add(left);

                takepill = Actions.takePill(front);
                if (takepill != null)
                    arr.add(takepill);

                drop = Actions.drop(front);
                if (drop != null)
                    arr.add(drop);

                kill = Actions.kill(front);
                if (kill != null)
                    arr.add(kill);

                carry = Actions.carry(front);
                if (carry != null)
                    arr.add(carry);

            }
            if (heurisic == "1") {
                Collections.sort(arr, new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        int heuristic1 = o1.heuristic1 + o1.pathCost;
                        int heuristic2  = o2.heuristic1 + o2.pathCost;
                        if (heuristic1 > heuristic2) {
                            return 1;
                        }
                        if (heuristic1 < heuristic2) {
                            return -1;
                        }
                        return 0;
                    }
                });
            }

            if (heurisic == "2") {
                Collections.sort(arr, new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        int heuristic1 = o1.heuristic2 + o1.pathCost;
                        int heuristic2  = o2.heuristic2 + o2.pathCost;
                        if (heuristic1> heuristic2) {
                            return 1;
                        }
                        if (heuristic1< heuristic2) {
                            return -1;
                        }
                        return 0;
                    }
                });
            }
        }
    }

    public Node breadthFirst(Node parent){
        Queue<Node> bfQueue = new LinkedList<Node>();
        bfQueue.add(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        Node front;
        Grid grid;
        while(true){
            if (bfQueue.isEmpty()){
                return null;            // RETURN NO SOLUTION
            }

            front = bfQueue.remove();
            nodesExpandedCount++;     
            System.out.print(front.operator + ", ");  

            grid = Helpers.stateToGrid(front.state);
            if (ActionsHelpers.reachedTestGoal(grid)==true){
                return front;
            }

            //UP,DOWN,RIGHT,LEFT,TAKEPILL,CARRY,DROP,KILL,FLY
            if(!repeatedState(front.state)){

                carry=Actions.carry(front);
                if (carry!=null)
                    bfQueue.add(carry);

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
        dfStack.push(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true){
            if (dfStack.empty()){
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
                fly=Actions.fly(front);
                if (fly!=null)
                    dfStack.push(fly);

                carry=Actions.carry(front);
                if (carry!=null)
                    dfStack.push(carry);

                kill=Actions.kill(front);
                if (kill!=null)
                    dfStack.push(kill);

                drop=Actions.drop(front);
                if (drop!=null)
                    dfStack.push(drop);

                up= Actions.Up(front);
                if (up!=null)
                    dfStack.push(up);

                down=Actions.Down(front);
                if (down!=null)
                    dfStack.push(down);

                right=Actions.Right(front);
                if (right!=null)
                    dfStack.push(right);

                left=Actions.Left(front);
                if (left!=null)
                    dfStack.push(left);

                takepill=Actions.takePill(front);
                if (takepill!=null)
                    dfStack.push(takepill);
                
            }
        }
    }
    
    public Node iterativeDepth(Node parent){

        int depth = -1;
        
        Stack<Node> dfStack = new Stack<Node>();
        dfStack.push(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true)
        {
            depth++;
            while(true && parent.depth <= depth){

                if (dfStack.empty()){
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
                    fly=Actions.fly(front);
                    if (fly!=null)  
                    dfStack.push(fly);
                    
                    up= Actions.Up(front);
                    if (up!=null)  
                    dfStack.push(up);
                    
                    down=Actions.Down(front);
                    if (down!=null)  
                    dfStack.push(down);
                    
                    right=Actions.Right(front);
                    if (right!=null)  
                    dfStack.push(right);
                    
                    left=Actions.Left(front);
                    if (left!=null)  
                    dfStack.push(left);
                    
                    takepill=Actions.takePill(front);
                    if (takepill!=null)  
                    dfStack.push(takepill);
                    
                    drop=Actions.drop(front);
                    if (drop!=null)  
                    dfStack.push(drop);
                    
                    kill=Actions.kill(front);    
                    if (kill!=null)  
                    dfStack.push(kill);
                    
                    carry=Actions.carry(front);
                    if (carry!=null)  
                    dfStack.push(carry); 
                    
                }
            }
        }
    }

    public Node uniformCost(Node parent){
        ArrayList<Node> arr = new ArrayList<>();
        arr.add(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true){
            if (arr.isEmpty()){
                return null;            // RETURN NO SOLUTION
            }

            Node front = arr.remove(0);
            nodesExpandedCount++;

            Grid grid = Helpers.stateToGrid(front.state);
            if (ActionsHelpers.reachedTestGoal(grid)==true){
                return front;
            }

            //UP,DOWN,RIGHT,LEFT,TAKEPILL,CARRY,DROP,KILL,FLY
            if(!repeatedState(front.state)){
                fly=Actions.fly(front);
                if (fly!=null)
                    arr.add(fly);

                carry=Actions.carry(front);
                if (carry!=null)
                    arr.add(carry);

                kill=Actions.kill(front);
                if (kill!=null)
                    arr.add(kill);

                drop=Actions.drop(front);
                if (drop!=null)
                    arr.add(drop);

                up= Actions.Up(front);
                if (up!=null)
                    arr.add(up);

                down=Actions.Down(front);
                if (down!=null)
                    arr.add(down);

                right=Actions.Right(front);
                if (right!=null)
                    arr.add(right);

                left=Actions.Left(front);
                if (left!=null)
                    arr.add(left);

                takepill=Actions.takePill(front);
                if (takepill!=null)
                    arr.add(takepill);
            }
            Collections.sort(arr);
        }
    }

    public Node greedy(Node parent, String heurisic){
        ArrayList<Node> arr = new ArrayList<>();
        arr.add(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true) {
            if (arr.isEmpty()) {
                return null;            // RETURN NO SOLUTION
            }

            Node front = arr.remove(0);
            nodesExpandedCount++;

            Grid grid = Helpers.stateToGrid(front.state);
            if (ActionsHelpers.reachedTestGoal(grid) == true) {
                return front;
            }

            //UP,DOWN,RIGHT,LEFT,TAKEPILL,CARRY,DROP,KILL,FLY
            if (!repeatedState(front.state)) {
                fly=Actions.fly(front);
                if (fly!=null)
                    arr.add(fly);

                carry=Actions.carry(front);
                if (carry!=null)
                    arr.add(carry);

                kill=Actions.kill(front);
                if (kill!=null)
                    arr.add(kill);

                drop=Actions.drop(front);
                if (drop!=null)
                    arr.add(drop);

                up= Actions.Up(front);
                if (up!=null)
                    arr.add(up);

                down=Actions.Down(front);
                if (down!=null)
                    arr.add(down);

                right=Actions.Right(front);
                if (right!=null)
                    arr.add(right);

                left=Actions.Left(front);
                if (left!=null)
                    arr.add(left);

                takepill=Actions.takePill(front);
                if (takepill!=null)
                    arr.add(takepill);

            }
            if (heurisic == "1") {
                Collections.sort(arr, new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if (o1.heuristic1 > o2.heuristic1) {
                            return 1;
                        }
                        if (o1.heuristic1 < o2.heuristic1) {
                            return -1;
                        }
                        return 0;
                    }
                });
            }

            if (heurisic == "2") {
                Collections.sort(arr, new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if (o1.heuristic2 > o2.heuristic2) {
                            return 1;
                        }
                        if (o1.heuristic2 < o2.heuristic2) {
                            return -1;
                        }
                        return 0;
                    }
                });
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


        solve("5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80", "BF", false);


    
    }


}