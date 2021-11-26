import java.util.ArrayList;
import java.util.Random;

public class Helpers {

    public static int genRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max + 1 - min) + min;
        return randomNumber;
    }

    public static boolean isPosOccupied(int x, int y, Object[][] grid) {
        if (grid[x][y] == null) {
            return false;
        } else {
            return true;
        }
    }

    public static int numOfLeftCells(Object[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == null)
                    count++;
            }
        }
        return count;
    }

    public static ArrayList<int[]> returnEmptySlots(Object[][] grid) {
        ArrayList<int[]> slots = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == null) {
                    int [] slot = new int [2] ;
                    slot[0] = i;
                    slot[1] = j;
                    slots.add(slot);
                }
            }
        }
        return  slots;
    }

    public static Grid stateToGrid(String state){
        Grid grid = new Grid(true);
        String [] states = state.split(";");
        for(int i =0; i<states.length; i++){
            System.out.println(states[i]);
        }
        int dimensionsX = Integer.parseInt(states[0].split(",")[0]);
        int dimensionsY = Integer.parseInt(states[0].split(",")[1]);
        grid.grid = new Object[dimensionsX][dimensionsY];

        //index out of bound we need to add health and numberofcarriedhostages

        int neoX = Integer.parseInt(states[2].split(",")[0]);
        int neoY = Integer.parseInt(states[2].split(",")[1]);
        int maxToCarry = Integer.parseInt(states[1].split(",")[0]);
        int health = Integer.parseInt(states[2].split(",")[2]);
        int numberOfCarriedHostages = Integer.parseInt(states[2].split(",")[3]);
        grid.neo = new Neo(neoX, neoY, maxToCarry, health , numberOfCarriedHostages);
        grid.grid[neoX][neoY] = grid.neo;

        int tbX = Integer.parseInt(states[3].split(",")[0]);
        int tbY = Integer.parseInt(states[3].split(",")[1]);
        grid.tb = new TelelphoneBooth(tbX, tbY);
        grid.grid[tbX][tbY] = grid.tb;

        String [] agents = states[4].split(",");
        for (int i = 0; i < agents.length - 1; i=i+2) {
            int agentX = Integer.parseInt(agents[i]);
            int agentY = Integer.parseInt(agents[i+1]);
            Agent agent = new Agent(agentX, agentY);
            grid.agents.add(agent);
            grid.grid[agentX][agentY] = agent;
        }

        String[] pills = states[5].split(",");
        for (int i = 0; i < pills.length - 1; i=i+2) {
            int pillX = Integer.parseInt(pills[i]);
            int pillY = Integer.parseInt(pills[i+1]);
            Pill pill = new Pill(pillX, pillX);
            grid.pills.add(pill);
            grid.grid[pillX][pillY] = pill;
        }

        String[] pads = states[6].split(",");
        for (int i = 0; i < pads.length - 3; i=i+4) {
            int padX = Integer.parseInt(pads[i]);
            int padY = Integer.parseInt(pads[i+1]);
            int goesToX = Integer.parseInt(pads[i+2]);
            int goesToY = Integer.parseInt(pads[i+3]);
            Pad pad = new Pad(padX, padY,goesToX,goesToY);
            grid.pads.add(pad);
            grid.grid[padX][padY] = pad;
        }
       
        //we need to add carried to the state string after generating the map string
        String[] hostages = states[7].split(",");
        for (int i = 0; i < hostages.length-3; i=i+4) {
            int hostageX = Integer.parseInt(hostages[i]);
            int hostageY = Integer.parseInt(hostages[i+1]);
            int damage = Integer.parseInt(hostages[i+2]);
            boolean carried = Boolean.parseBoolean(hostages[i+3]);
            Hostage hostage = new Hostage(hostageX, hostageY,damage,carried);
            grid.hostages.add(hostage);
            grid.grid[hostageX][hostageY] = hostage;
        }

        return grid;
    }

}
