public class Matrix {
    static Grid grid;

    public static String gridGen(){
        grid = new Grid(false);
        return grid.genMatString();
    }

    public static void solve(String grid, String strategy, boolean visualize){
        String initialState = Helpers.changeStateFormat(grid);
        Node parent = new Node(null,null,initialState,0,0);
        String result = Search.search(strategy,parent);
        //left,fly,right,carry,left,fly,down,right,drop,left,left,kill,left,left,up,carry,down,down,kill,up,right,right,right,right,drop;1;3;1246837 (plan,deaths,kills,nodes)
    }


    public static void main(String[] args) {
        System.out.println(Helpers.changeStateFormat("5,5;2;4,3;2,1;2,0,0,4,0,3,0,1;3,1,3,2;4,4,3,3,3,3,4,4;4,0,17,1,2,54,0,0,46,4,1,22"));
    
    }
}