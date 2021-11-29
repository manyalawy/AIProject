public class Matrix {
    static Grid grid;

    public static String gridGen(){
        grid = new Grid(false);
        return grid.genMatString();
    }

    public static void solve(String grid, String strategy, boolean visualize){

    }


    public static void main(String[] args) {

        String state = Helpers.changeStateFormat("5,5;2;3,1;2,1;2,0,0,4,0,3,0,1;3,1,3,2;4,4,3,3,3,3,4,4;4,0,99");
        Grid grid = Helpers.stateToGrid(state);
        System.out.println(state);
        Node n = new Node(null,null,state, 0, 0);
        n = Actions.takePill(n);
        Grid newGrid = Helpers.stateToGrid(n.state);
        System.out.println();



    
    }
}