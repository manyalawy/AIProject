public class Actions {

    public static Node Up(Node node){
        //obtain Neo location from state(dimensions and maxToCarry and TB are removed from grid.genMatString())
        String[] state = node.state.split(";");
        int NeoX = Integer.parseInt(state[0].split(",")[0]);
        int NeoY = Integer.parseInt(state[0].split(",")[1]);
         //check if not at the most upper rows or agent up or a mutant agent is up
        if (NeoX>0 && ( !ActionsHelpers.mutantHostage(state, NeoX, NeoY, Operators.UP) || !ActionsHelpers.agentExists(state, NeoX, NeoY, Operators.UP))){
            //change Neo Location and decrement all hostages damages
        }
       
        return node;
    }
}
