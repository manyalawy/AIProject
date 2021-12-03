package code;
public class Actions {


    public static Node Up(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if (node.operator==Operators.down) {
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true){
            return null;
        }
        if (neo.x <= 0) {
            return null;
        }
        if (ActionsHelpers.agentExists(grid, Operators.up) || ActionsHelpers.mutantHostage(grid, Operators.up)) {
            return null;
        }
        if(grid.grid[neo.x-1][neo.y] instanceof Hostage){
            Hostage h = (Hostage) grid.grid[neo.x-1][neo.y];
            if(h.damage>=98)
                return null;
        }
        neo.x -= 1;
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.up, state, node.depth + 1);


        return newNode;
    }

    public static Node Down(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if(node.operator==Operators.up) {
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.x >= grid.dimensions - 1) {
            return null;
        }
        if (ActionsHelpers.agentExists(grid, Operators.down) || ActionsHelpers.mutantHostage(grid, Operators.down)) {
            return null;
        }
        if(grid.grid[neo.x+1][neo.y] instanceof Hostage){
            Hostage h = (Hostage) grid.grid[neo.x+1][neo.y];
            if(h.damage>=98)
                return null;
        }
        neo.x += 1;
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.down, state, node.depth + 1);

        return newNode;
    }

    public static Node Left(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if(node.operator==Operators.right){
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.y <= 0) {
            return null;
        }
        if (ActionsHelpers.agentExists(grid, Operators.left) || ActionsHelpers.mutantHostage(grid, Operators.left)) {
            return null;
        }
        if(grid.grid[neo.x][neo.y-1] instanceof Hostage){
            Hostage h = (Hostage) grid.grid[neo.x][neo.y-1];
            if(h.damage>=98)
                return null;
        }
        neo.y -= 1;
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.left, state, node.depth + 1);


        return newNode;
    }

    public static Node Right(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if(node.operator==Operators.left) {
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.y >= grid.dimensions - 1) {
            return null;
        }
        if (ActionsHelpers.agentExists(grid, Operators.right) || ActionsHelpers.mutantHostage(grid, Operators.right)) {
            return null;
        }
        if(grid.grid[neo.x][neo.y+1] instanceof Hostage){
            Hostage h = (Hostage) grid.grid[neo.x][neo.y+1];
            if(h.damage>=98)
                return null;
        }
        neo.y += 1;
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.right, state, node.depth + 1);


        return newNode;
    }

    public static Node takePill(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        if(node.operator==Operators.takePill)
            return null;
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
            if (pill.x == neo.x && pill.y == neo.y) {
                grid.pills.remove(i);
                break;
            }
        }
        state = Helpers.gridToState(grid);
     //   System.out.println(state);
         grid = ActionsHelpers.timeStep(grid);

        Node newNode = new Node(node, Operators.takePill, state, node.depth + 1);


        return newNode;
    }

    public static Node kill(Node n) {

        String state = n.state;
        Grid grid = Helpers.stateToGrid(state);
        if(n.operator == Operators.kill){
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid)) {
            return null;
        }
        if(grid.grid[grid.neo.x][grid.neo.y] instanceof Hostage){
            Hostage h = (Hostage) grid.grid[grid.neo.x][grid.neo.y];
            if(h.damage>=98)
                return null;
        }

        boolean didNeoKill = false;

        int neoX = grid.neo.x;
        int neoY = grid.neo.y;

        //check north
        if (neoX > 0) {
            if (grid.grid[neoX - 1][neoY] != null) {
                if (grid.grid[neoX - 1][neoY] instanceof Agent) {
                    grid = ActionsHelpers.removeAgent(grid, neoX - 1, neoY);
                    didNeoKill = true;
                    grid.kills++;
                }

                if (grid.grid[neoX - 1][neoY] instanceof Hostage) {
                    Hostage hostage = (Hostage) grid.grid[neoX - 1][neoY];
                    if (hostage.damage >= 100 && hostage.carried == false && hostage.dropped == false) {
                        grid = ActionsHelpers.removeHostage(grid, neoX - 1, neoY);
                        didNeoKill = true;
                        grid.kills++;
                    }
                }
            }
        }

        //check south
        if (neoX < grid.dimensions - 1) {
            if (grid.grid[neoX + 1][neoY] != null) {
                if (grid.grid[neoX + 1][neoY] instanceof Agent) {
                    grid = ActionsHelpers.removeAgent(grid, neoX + 1, neoY);
                    didNeoKill = true;
                    grid.kills++;
                }

                if (grid.grid[neoX + 1][neoY] instanceof Hostage) {
                    Hostage hostage = (Hostage) grid.grid[neoX + 1][neoY];
                    if (hostage.damage >= 100 && hostage.carried == false && hostage.dropped == false) {
                        grid = ActionsHelpers.removeHostage(grid, neoX + 1, neoY);
                        didNeoKill = true;
                        grid.kills++;
                    }
                }
            }
        }

        //check east
        if (neoY < grid.dimensions - 1) {
            if (grid.grid[neoX][neoY + 1] != null) {
                if (grid.grid[neoX][neoY + 1] instanceof Agent) {
                    grid = ActionsHelpers.removeAgent(grid, neoX, neoY + 1);
                    didNeoKill = true;
                    grid.kills++;
                }

                if (grid.grid[neoX][neoY + 1] instanceof Hostage) {
                    Hostage hostage = (Hostage) grid.grid[neoX][neoY + 1];
                    if (hostage.damage >= 100 && hostage.carried == false && hostage.dropped == false) {
                        grid = ActionsHelpers.removeHostage(grid, neoX, neoY + 1);
                        didNeoKill = true;
                        grid.kills++;
                    }
                }
            }
        }

        //check west
        if (neoY > 0) {
            if (grid.grid[neoX][neoY - 1] != null) {
                if (grid.grid[neoX][neoY - 1] instanceof Agent) {
                    grid = ActionsHelpers.removeAgent(grid, neoX, neoY - 1);
                    didNeoKill = true;
                    grid.kills++;
                }

                if (grid.grid[neoX][neoY - 1] instanceof Hostage) {
                    Hostage hostage = (Hostage) grid.grid[neoX][neoY - 1];
                    if (hostage.damage >= 100 && hostage.carried == false && hostage.dropped == false) {
                        grid = ActionsHelpers.removeHostage(grid, neoX, neoY - 1);
                        didNeoKill = true;
                        grid.kills++;
                    }
                }
            }
        }


        if (didNeoKill == true) {
            grid.neo.damage = grid.neo.damage + 20;
            grid = ActionsHelpers.timeStep(grid);
            state = Helpers.gridToState(grid);

            return new Node(n, Operators.kill, state, n.depth + 1);
        } else {
            return null;
        }

        

    }

    public static Node carry(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        if(node.operator == Operators.carry){
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;

        if (grid.neo.numberOfCarriedHostages >= grid.neo.maxToCarry)
            return null;

        if (grid.grid[grid.neo.x][grid.neo.y] instanceof Hostage == false)
            return null;

        Hostage hostage = (Hostage) grid.grid[grid.neo.x][grid.neo.y];      //
        if (hostage.carried)                                                // Hostage is already carried
            return null;

        for (int i = 0; i < grid.hostages.size(); i++) {
            if (grid.hostages.get(i).x == hostage.x && grid.hostages.get(i).y == hostage.y ) {
                grid.hostages.get(i).carried = true;
                break;
            }
        }
        grid.neo.numberOfCarriedHostages += 1;


        grid = ActionsHelpers.timeStep(grid);

        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.carry, state, node.depth + 1);
        return newNode;
    }

    public static Node drop(Node node) {

        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        if(node.operator==Operators.drop){
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;

        if (grid.grid[grid.neo.x][grid.neo.y] instanceof TelelphoneBooth == false)
            return null;

        if (grid.neo.numberOfCarriedHostages <= 0)
            return null;

        for (int i = 0; i < grid.hostages.size(); i++) {
            if (grid.hostages.get(i).carried) {
                grid.hostages.get(i).dropped = true;    // Set dropped to TRUE
                grid.hostages.remove(i);
                i--;
            }
        }
        grid.neo.numberOfCarriedHostages = 0;

        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.drop, state, node.depth + 1);
        return newNode;
    }

    public static Node fly(Node node) {

        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;
        if(node.operator==Operators.fly){
            return null;
        }

        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;

        if (grid.grid[grid.neo.x][grid.neo.y] instanceof Pad == false)
            return null;

        if (node.operator == Operators.fly)
            return null;

        Pad pad = (Pad) grid.grid[grid.neo.x][grid.neo.y];
        grid.neo.x = pad.goesToX;
        grid.neo.y = pad.goesToY;

        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        //System.out.println(state);
        Node newNode = new Node(node, Operators.fly, state, node.depth + 1);
        return newNode;
    }
}
