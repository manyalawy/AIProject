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

        int dimensionsX = Integer.parseInt(states[0].split(",")[0]);
        int dimensionsY = Integer.parseInt(states[0].split(",")[1]);
        grid.grid = new Object[dimensionsX][dimensionsY];

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






        return grid;
    }

}
