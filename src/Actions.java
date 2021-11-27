public class Actions {

    public static Node Up(Node node) {
        //obtain Neo location from statetogrid(dimensions and maxToCarry and TB are removed from grid.genMatString())

        //check if not at the most upper rows or agent up or a mutant agent is up

        //if (NeoX>0 && ( !ActionsHelpers.mutantHostage(state, NeoX, NeoY, Operators.UP) || !ActionsHelpers.agentExists(state, NeoX, NeoY, Operators.UP))){
        //change Neo Location and decrement all hostages damages
        //}

        return node;
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

        state = Helpers.gridToState(grid);
        Node newNode = new Node(node, Operators.TAKEPILL, state, node.depth + 1, 1);

        return node;
    }
}
