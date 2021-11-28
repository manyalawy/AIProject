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
        neo.x -= 1;
        grid = ActionsHelpers.timeStep(grid);
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
        neo.x += 1;
        grid = ActionsHelpers.timeStep(grid);
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
        grid = ActionsHelpers.timeStep(grid);
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
        neo.y += 1;
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.RIGHT, state, node.depth + 1, 1);

        return newNode;
    }


    public static Node takePill(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (grid.grid[grid.neo.x][grid.neo.y] instanceof Pill == false)
            return null;
        grid.neo.damage = grid.neo.damage - 20;
        if (grid.neo.damage < 0) {
            grid.neo.damage = 0;
        }
        for (int i = 0; i < grid.hostages.size(); i++) {
            if (grid.hostages.get(i).damage < 100) {
                grid.hostages.get(i).damage = grid.hostages.get(i).damage - 20;
                if (grid.hostages.get(i).damage < 0) {
                    grid.hostages.get(i).damage = 0;
                }
            }
        }
        for (int i = 0; i < grid.pills.size(); i++) {
            Pill pill = grid.pills.get(i);
            Neo neo = grid.neo;
            if(pill.x == neo.x && pill.y == neo.y){
                grid.pills.remove(i);
                break;
            }
        }

        state = Helpers.gridToState(grid);
        System.out.println(state);
        Node newNode = new Node(node, Operators.TAKEPILL, state, node.depth + 1, 1);

        return newNode;
    }

}
