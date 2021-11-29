public class Actions {


    public static Node Up(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if (node.operator==Operators.DOWN) {
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true){
            return null;
        }
        if (neo.x <= 0) {
            return null;
        }
        if (ActionsHelpers.agentExists(grid, Operators.UP) || ActionsHelpers.mutantHostage(grid, Operators.UP)) {
            return null;
        }
        neo.x -= 1;
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.UP, state, node.depth + 1, 1);

        return newNode;
    }

    public static Node Down(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if(node.operator==Operators.UP) {
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.x >= grid.dimensions - 1) {
            return null;
        }
        if (ActionsHelpers.agentExists(grid, Operators.DOWN) || ActionsHelpers.mutantHostage(grid, Operators.DOWN)) {
            return null;
        }
        neo.x += 1;
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.DOWN, state, node.depth + 1, 1);

        return newNode;
    }

    public static Node Left(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if(node.operator==Operators.RIGHT){
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.y <= 0) {
            return null;
        }
        if (ActionsHelpers.agentExists(grid, Operators.LEFT) || ActionsHelpers.mutantHostage(grid, Operators.LEFT)) {
            return null;
        }
        neo.y -= 1;
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.LEFT, state, node.depth + 1, 1);

        return newNode;
    }

    public static Node Right(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if(node.operator==Operators.LEFT) {
            return null;
        }
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (neo.y >= grid.dimensions - 1) {
            return null;
        }
        if (ActionsHelpers.agentExists(grid, Operators.RIGHT) || ActionsHelpers.mutantHostage(grid, Operators.RIGHT)) {
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
            if (pill.x == neo.x && pill.y == neo.y) {
                grid.pills.remove(i);
                break;
            }
        }
        state = Helpers.gridToState(grid);
        System.out.println(state);
        Node newNode = new Node(node, Operators.TAKEPILL, state, node.depth + 1, 1);

        return newNode;
    }

    public static Node kill(Node n) {

        String state = n.state;
        Grid grid = Helpers.stateToGrid(state);
        if (ActionsHelpers.isNeoDead(grid)) {
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
                }

                if (grid.grid[neoX - 1][neoY] instanceof Hostage) {
                    Hostage hostage = (Hostage) grid.grid[neoX - 1][neoY];
                    if (hostage.damage >= 100 && hostage.carried == false && hostage.dropped == false) {
                        grid = ActionsHelpers.removeHostage(grid, neoX - 1, neoY);
                        didNeoKill = true;
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
                }

                if (grid.grid[neoX + 1][neoY] instanceof Hostage) {
                    Hostage hostage = (Hostage) grid.grid[neoX + 1][neoY];
                    if (hostage.damage >= 100 && hostage.carried == false && hostage.dropped == false) {
                        grid = ActionsHelpers.removeHostage(grid, neoX + 1, neoY);
                        didNeoKill = true;
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
                }

                if (grid.grid[neoX][neoY + 1] instanceof Hostage) {
                    Hostage hostage = (Hostage) grid.grid[neoX][neoY + 1];
                    if (hostage.damage >= 100 && hostage.carried == false && hostage.dropped == false) {
                        grid = ActionsHelpers.removeHostage(grid, neoX, neoY + 1);
                        didNeoKill = true;
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
                }

                if (grid.grid[neoX - 1][neoY] instanceof Hostage) {
                    Hostage hostage = (Hostage) grid.grid[neoX - 1][neoY];
                    if (hostage.damage >= 100 && hostage.carried == false && hostage.dropped == false) {
                        grid = ActionsHelpers.removeHostage(grid, neoX, neoY - 1);
                        didNeoKill = true;
                    }
                }
            }
        }



        if (didNeoKill == true) {
            grid.neo.damage = grid.neo.damage+20;
            grid = ActionsHelpers.timeStep(grid);
            state = Helpers.gridToState(grid);
            return new Node(n, Operators.KILL, state, n.depth + 1, 1);
        } else {
            return null;
        }
    }

    public static Node carry(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);

        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;

        if (grid.neo.numberOfCarriedHostages >= grid.neo.maxToCarry)
            return null;

        if (grid.grid[grid.neo.x][grid.neo.y] instanceof Hostage == false )
            return null;
            
        Hostage hostage = (Hostage) grid.grid[grid.neo.x][grid.neo.y];      //
        if (hostage.carried)                                                // Hostage is already carried
            return null;

        for (int i = 0; i < grid.hostages.size(); i++) {
            if (grid.hostages.get(i) == hostage) {
                grid.hostages.get(i).carried = true;
                break;
            }
        }
        grid.neo.numberOfCarriedHostages += 1;
        

        ActionsHelpers.timeStep(grid);

        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.CARRY, state, node.depth + 1, 1);
        return newNode;
    }

    public static Node drop(Node node) {
     
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);

        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;

        if (grid.grid[grid.neo.x][grid.neo.y] instanceof TelelphoneBooth == false)
            return null;
        

        for (int i = 0; i < grid.hostages.size(); i++) {
            if (grid.hostages.get(i).carried) {
                grid.hostages.get(i).dropped = true;    // Set dropped to TRUE
                grid.hostages.remove(i);
            }
        }
        grid.neo.numberOfCarriedHostages = 0;
   
        ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.DROP, state, node.depth + 1, 1);

        return newNode;
    }
    
    public static Node fly(Node node) {

        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;

        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;

        if (grid.grid[grid.neo.x][grid.neo.y] instanceof Pad == false)
            return null;

        if(node.operator == Operators.FLY)
            return null;

        Pad pad = (Pad) grid.grid[grid.neo.x][grid.neo.y];
        grid.neo.x = pad.goesToX;
        grid.neo.y = pad.goesToY;

        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        System.out.println(state);
        Node newNode = new Node(node, Operators.FLY, state, node.depth + 1, 1);

        return newNode;
    }
}
