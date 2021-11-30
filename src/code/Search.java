package code;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Search {
    public static HashSet<String> repeatedStates = new HashSet<String>();

    public static boolean repeatedState(String state){
        //should remove damage of hostages from string state
        if (repeatedStates.contains(state)) {
            return true;
        } else {
            repeatedStates.add(state);
            return false;
        }
    }
    public static String search(String strategy,Node root){
        Node goal=null;
        switch(strategy){
            case "BF":  goal = breadthFirst(root);
                        break;
            case "DF":  goal = depthFirst(root);
                        break;
            case "ID": goal = iterativeDeepening(root);
        
        }
        //get the path from root to goal node ; deaths; kills; number of nodes
        return SearchHelpers.getPath(goal);
    }
    public static Node breadthFirst(Node parent){
        Queue<Node> bfQueue = new LinkedList<Node>();
        bfQueue.add(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true){
            if (bfQueue.isEmpty()){
                return null;
            }

            Node front = bfQueue.remove();
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

    public static Node depthFirst(Node parent){
        Stack<Node> dfStack = new Stack<Node>();
        dfStack.push(parent);
        Node up,down,right,left,takepill,carry,drop,kill,fly =null;
        while(true){
            if (dfStack.isEmpty()){
                return null;
            }

            Node front = dfStack.pop();
            Grid grid = Helpers.stateToGrid(front.state);
            if (ActionsHelpers.reachedTestGoal(grid)==true){
                return front;
            }

            //UP,DOWN,RIGHT,LEFT,TAKEPILL,CARRY,DROP,KILL,FLY
            if(!repeatedState(front.state)){
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

                carry=Actions.carry(front);
                if (carry!=null)  
                    dfStack.push(carry); 
                
                drop=Actions.drop(front);
                if (drop!=null)  
                    dfStack.push(drop);
            
                kill=Actions.kill(front);    
                if (kill!=null)  
                    dfStack.push(kill);

                fly=Actions.fly(front);
                if (fly!=null)  
                    dfStack.push(fly);
            }
        }
    }
 
    public static Node iterativeDeepening(Node parent) {
        
        return null;
    }
}
