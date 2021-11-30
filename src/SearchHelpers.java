public class SearchHelpers {
    
    public static String getPath(Node node){
        String pathString=node.operator+"";
        //we should add number of deaths, kills,nodes to state ;1;3;1246837
        Grid grid = Helpers.stateToGrid(node.state);
        int kills=0;
        if (node.operator==Operators.KILL) 
            kills=1;
        int numberOfNodes=1;
        node=node.parent;
        while(node!=null){
            pathString= node.operator+ ","+ pathString;
            numberOfNodes++;
            if (node.operator==Operators.KILL)
                kills++;
            node = node.parent;
        }

        return pathString + ";" + grid.deaths + ";"+  kills + ";" + numberOfNodes;
    }
    
}
