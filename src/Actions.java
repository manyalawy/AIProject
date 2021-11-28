import java.util.ArrayList;

public class Actions {

    public static Node Up(Node node) {
        // obtain Neo location from statetogrid(dimensions and maxToCarry and TB are
        // removed from grid.genMatString())

        // check if not at the most upper rows or agent up or a mutant agent is up

        // if (NeoX>0 && ( !ActionsHelpers.mutantHostage(state, NeoX, NeoY,
        // Operators.UP) || !ActionsHelpers.agentExists(state, NeoX, NeoY,
        // Operators.UP))){
        // change Neo Location and decrement all hostages damages
        // }

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

    public static Node Fly(Node node) {
        String state = node.state;
        Grid grid = Helpers.stateToGrid(state);
        Neo neo = grid.neo;
        if (ActionsHelpers.isNeoDead(grid) == true)
            return null;
        if (grid.grid[grid.neo.x][grid.neo.y] instanceof Pad == false)
            return null;

        for (int i = 0; i < grid.pads.size(); i++) {
            if (neo.x == grid.pads.get(i).x && neo.y == grid.pads.get(i).y) {
                neo.x = grid.pads.get(i).goesToX;
                neo.y = grid.pads.get(i).goesToY;

            }
        }
        grid = ActionsHelpers.timeStep(grid);
        state = Helpers.gridToState(grid);
        System.out.println(state);
        Node newNode = new Node(node, Operators.FLY, state, node.depth + 1, 1);

        return newNode;
    }
}
