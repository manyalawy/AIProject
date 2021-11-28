import java.util.ArrayList;

public class ActionsHelpers {
    public static boolean agentExists(Grid grid, Operators operator) {
        int neoX = grid.neo.x;
        int neoY = grid.neo.y;
        if (operator == Operators.UP) {
            for (int i = 0; i < grid.agents.size(); i++) {
                Agent a = grid.agents.get(i);
                if (a.x == neoX - 1 && a.y == neoY) {
                    return true;
                }
            }
        }
        if (operator == Operators.DOWN) {
            for (int i = 0; i < grid.agents.size(); i++) {
                Agent a = grid.agents.get(i);
                if (a.x == neoX + 1 && a.y == neoY) {
                    return true;
                }
            }
        }
        if (operator == Operators.LEFT) {
            for (int i = 0; i < grid.agents.size(); i++) {
                Agent a = grid.agents.get(i);
                if (a.y == neoY - 1 && a.x == neoX) {
                    return true;
                }
            }
        }
        if (operator == Operators.RIGHT) {
            for (int i = 0; i < grid.agents.size(); i++) {
                Agent a = grid.agents.get(i);
                if (a.y == neoY + 1 && a.x == neoX) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean mutantHostage(Grid grid, Operators operator) {
        int neoX = grid.neo.x;
        int neoY = grid.neo.y;
        if (operator == Operators.UP) {
            for (int i = 0; i < grid.hostages.size(); i++) {
                Hostage h = grid.hostages.get(i);
                if (h.x == neoX - 1 && h.y == neoY && h.damage >= 100 && h.carried == false && h.dropped == false) {
                    return true;
                }
            }
        }
        if (operator == Operators.DOWN) {
            for (int i = 0; i < grid.hostages.size(); i++) {
                Hostage h = grid.hostages.get(i);
                if (h.x == neoX + 1 && h.y == neoY && h.damage >= 100 && h.carried == false && h.dropped == false) {
                    return true;
                }
            }
        }
        if (operator == Operators.LEFT) {
            for (int i = 0; i < grid.hostages.size(); i++) {
                Hostage h = grid.hostages.get(i);
                if (h.y == neoY + 1 && h.x == neoX && h.damage >= 100 && h.carried == false && h.dropped == false) {
                    return true;
                }
            }
        }
        if (operator == Operators.RIGHT) {
            for (int i = 0; i < grid.hostages.size(); i++) {
                Hostage h = grid.hostages.get(i);
                if (h.y == neoY - 1 && h.x == neoX && h.damage >= 100 && h.carried == false && h.dropped == false) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isNeoDead(Grid grid) {
        if (grid.neo.damage >= 100) {
            return true;
        } else {
            return false;
        }
    }

    public static Grid timeStep(Grid grid) {
        for (int i = 0; i < grid.hostages.size(); i++) {
            grid.hostages.get(i).damage = grid.hostages.get(i).damage + 2;
            if(grid.hostages.get(i).damage>100){
                grid.hostages.get(i).damage = 100;
            }
        }
        return grid;
    }

    public static Grid removeAgent(Grid grid , int x, int y){

        for (int i = 0; i < grid.agents.size(); i++) {
            if(grid.agents.get(i).x == x && grid.agents.get(i).y == y) {
                grid.agents.remove(i);
                break;
            }
        }
        return grid;
    }

    public static Grid removeHostage(Grid grid , int x, int y){

        for (int i = 0; i < grid.hostages.size(); i++) {
            if(grid.hostages.get(i).x == x && grid.hostages.get(i).y == y) {
                grid.hostages.remove(i);
                break;
            }
        }
        return grid;
    }

}
