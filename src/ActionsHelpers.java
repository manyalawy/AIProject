public class ActionsHelpers {
    Grid grid;
    public static boolean agentExists(String[] state, int NeoX, int NeoY, Operators operator){
        
        return false;
    }
    public static boolean mutantHostage(String[] state, int NeoX, int NeoY, Operators operator){
        
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
