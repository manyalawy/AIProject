public class ActionsHelpers {
    Grid grid;
    public static boolean agentExists(String[] state, int NeoX, int NeoY, Operators operator){
        //state ["NeoX","NeoY","AgentXi","AgentYi",..,"PillXi","PillYi",..,"StartPadXi","StartPadYi",..,"HostageXi","HostageYi","HostageDamagei",..]
        int numberOfAgents = Matrix.grid.numberOfAgents;
        //loop from state[2] till state[numberofAgents*2] and check if no agentX-1 = NeoX
        
        //else
        return false;
    }
    public static boolean mutantHostage(String[] state, int NeoX, int NeoY, Operators operator){
        //state ["NeoX","NeoY","AgentXi","AgentYi",..,"PillXi","PillYi",..,"StartPadXi","StartPadYi",..,"HostageXi","HostageYi","HostageDamagei",..]

        return false;
    }
}
