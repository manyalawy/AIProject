public class Matrix {
    static Grid grid;

    public static String gridGen(){
        grid = new Grid(false);
        return grid.genMatString();
    }

    public static void solve(String grid, String strategy, boolean visualize){

    }


    public static void main(String[] args) {

        //Notes:
        //Add something to reformat state according to our state definetion
        
        //booleans added manually we need to add carried for hostages in state string

//        Grid grid = Helpers.stateToGrid("5,5;2;4,3;2,1;2,0,0,4,0,3,0,1;3,1,3,2;4,4,3,3,3,3,4,4;4,0,17,true,1,2,54,true,0,0,46,true,4,1,22,false");
        long start = System.currentTimeMillis();
        String state = Helpers.changeStateFormat("5,5;2;3,4;2,1;2,0,0,4,0,3,0,1;3,1,3,2;4,4,3,3,3,3,4,4;4,0,17");
//        Grid grid = Helpers.stateToGrid(state);
        Node n = new Node(null,null,state, 0, 0);
        System.out.println(Actions.takePill(n));

//        grid.neo.health = 0;

//        state = Helpers.gridToState(grid);
//        System.out.println(ActionsHelpers.isNeoDead(grid));
//
//        long end = System.currentTimeMillis();
//        long elapsedTime = end - start;
//        System.out.println(grid.grid[4][3] instanceof  Neo);
    
    }
}