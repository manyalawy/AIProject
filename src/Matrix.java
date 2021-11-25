public class Matrix {


    public static String gridGen(){
        Grid grid = new Grid(false);
        return grid.genMatString();
    }



    public static void main(String[] args) {
        System.out.println(gridGen());
    }
}