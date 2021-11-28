public class ActionsHelpers {
    public static boolean agentExists(Grid grid, Operators operator){
        int neoX = grid.neo.x;
        int neoY = grid.neo.y;
        if (operator == Operators.UP){
            for(int i =0; i<grid.agents.size(); i++){
                if(grid.agents.get(i).x == neoX-1){
                    return true;
                }
            }
        }
        if (operator == Operators.DOWN){
            for(int i =0; i<grid.agents.size(); i++){
                if(grid.agents.get(i).x == neoX+1){
                    return true;
                }
            }
        }
        if (operator == Operators.LEFT){
            for(int i =0; i<grid.agents.size(); i++){
                if(grid.agents.get(i).y == neoY-1){
                    return true;
                }
            }
        }
        if (operator == Operators.RIGHT){
            for(int i =0; i<grid.agents.size(); i++){
                if(grid.agents.get(i).y == neoY+1){
                    return true;
                }
            }
        }
        
        return false;
    }
    public static boolean mutantHostage(Grid grid, Operators operator){
        int neoX = grid.neo.x;
        int neoY = grid.neo.y;
        if (operator == Operators.UP){
            for(int i =0; i<grid.hostages.size(); i++){
                Hostage h = grid.hostages.get(i);
                if(h.x == neoX-1 && h.damage>=100 && h.carried==false){
                    return true;
                }
            }
        }
        if (operator == Operators.DOWN){
            for(int i =0; i<grid.hostages.size(); i++){
                Hostage h = grid.hostages.get(i);
                if(h.x == neoX+1 && h.damage>=100 && h.carried==false){
                    return true;
                }
            }
        }
        if (operator == Operators.LEFT){
            for(int i =0; i<grid.hostages.size(); i++){
                Hostage h = grid.hostages.get(i);
                if(h.y == neoY-1 && h.damage>=100 && h.carried==false){
                    return true;
                }
            }
        }
        if (operator == Operators.RIGHT){
            for(int i =0; i<grid.hostages.size(); i++){
                Hostage h = grid.hostages.get(i);
                if(h.y == neoY+1 && h.damage>=100 && h.carried==false){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isNeoDead(Grid grid){
        if(grid.neo.damage>=100){
            return true;
        }
        else{
            return false;
        }
    }

    public static Grid timeStep(Grid grid){
        for (int i = 0; i < grid.hostages.size(); i++) {
            grid.hostages.get(i).damage = grid.hostages.get(i).damage + 2;
        }
        return grid;
    }
}
