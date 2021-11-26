public class Matrix {
    static Grid grid;

    public static String gridGen(){
        grid = new Grid(false);
        return grid.genMatString();
    }

    public static void solve(String grid, String strategy, boolean visualize){

    }


    public static void main(String[] args) {
        Helpers.stateToGrid("5,5;2;4,3;2,1;2,0,0,4,0,3,0,1;3,1,3,2;4,4,3,3,3,3,4,4;4,0,17,1,2,54,0,0,46,4,1,22");
    }
}