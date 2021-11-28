public class Actions {

    public static Node Up(Node node){
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;
        
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.x<=0){
            return null;
        }
        if( ActionsHelpers.agentExists(grid,Operators.UP) || ActionsHelpers.mutantHostage(grid,Operators.UP) ){
            return null;
        }
        neo.x += 1;
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.UP, state, node.depth + 1, 1);

        return newNode;
    }
    
    public static Node Down(Node node){
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;
        
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.x>=grid.dimensions-1){
            return null;
        }
        if(ActionsHelpers.agentExists(grid,Operators.DOWN) || ActionsHelpers.mutantHostage(grid,Operators.DOWN) ){
            return null;
        }
        neo.x -= 1;
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.DOWN, state, node.depth + 1, 1);

        return newNode;
    }

    public static Node Left(Node node){
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;
        
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.y<=0){
            return null;
        }
        if ( ActionsHelpers.agentExists(grid,Operators.LEFT) || ActionsHelpers.mutantHostage(grid,Operators.LEFT) ){
            return null;
        }
        neo.y -= 1;
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.LEFT, state, node.depth + 1, 1);

        return newNode;
    }
    public static Node Right(Node node){
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;
        
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.y>=grid.dimensions-1){
            return null;
        }
        if(ActionsHelpers.agentExists(grid,Operators.RIGHT) || ActionsHelpers.mutantHostage(grid,Operators.RIGHT)){
            return null;
        } 
        neo.y -= 1;
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.RIGHT, state, node.depth + 1, 1);

        return newNode;
    }

}
