import java.util.ArrayList;
import java.util.Collections;

public class Grid {
    Object[][] grid;
    Neo neo;
    TelelphoneBooth tb;
    ArrayList<Hostage> hostages;
    ArrayList<Pill> pills;
    ArrayList<Hostage> deadHostage;
    ArrayList<Agent> agents;
    ArrayList<Pad> pads;

    public Grid(boolean genManually) {
        if(genManually==false) {
            int dimensions = Helpers.genRandomNumber(5, 15);
            grid = new Object[dimensions][dimensions];

            // Create Neo
            int neoX = Helpers.genRandomNumber(0, dimensions - 1);
            int neoY = Helpers.genRandomNumber(0, dimensions - 1);
            neo = new Neo(neoX, neoY);
            grid[neoX][neoY] = neo;

            // Create telephone booth
            int tbX = Helpers.genRandomNumber(0, dimensions - 1);
            int tbY = Helpers.genRandomNumber(0, dimensions - 1);
            while (Helpers.isPosOccupied(tbX, tbY, grid) == true) {
                tbX = Helpers.genRandomNumber(0, dimensions - 1);
                tbY = Helpers.genRandomNumber(0, dimensions - 1);
            }
            tb = new TelelphoneBooth(tbX, tbY);
            grid[tbX][tbY] = tb;

            // Create hostages
            int numberOfHostages = Helpers.genRandomNumber(3, 7);
            hostages = new ArrayList<Hostage>();
            for (int i = 0; i < numberOfHostages; i++) {
                int hostageX = Helpers.genRandomNumber(0, dimensions - 1);
                int hostageY = Helpers.genRandomNumber(0, dimensions - 1);
                while (Helpers.isPosOccupied(hostageX, hostageY, grid) == true) {
                    hostageX = Helpers.genRandomNumber(0, dimensions - 1);
                    hostageY = Helpers.genRandomNumber(0, dimensions - 1);
                }
                int damage = Helpers.genRandomNumber(1, 99);
                Hostage hostage = new Hostage(hostageX, hostageY, damage);
                grid[hostageX][hostageY] = hostage;
                hostages.add(hostage);
            }

            // Create pills
            int numberOfPills = Helpers.genRandomNumber(1, numberOfHostages);
            pills = new ArrayList<Pill>();
            for (int i = 0; i < numberOfPills; i++) {
                int pillX = Helpers.genRandomNumber(0, dimensions - 1);
                int pillY = Helpers.genRandomNumber(0, dimensions - 1);
                while (Helpers.isPosOccupied(pillX, pillY, grid) == true) {
                    pillX = Helpers.genRandomNumber(0, dimensions - 1);
                    pillY = Helpers.genRandomNumber(0, dimensions - 1);
                }
                Pill pill = new Pill(pillX, pillY);
                grid[pillX][pillY] = pill;
                pills.add(pill);
            }

            int emptySlots = Helpers.numOfLeftCells(grid);
            int numberOfPads = Helpers.genRandomNumber(2, emptySlots);
            pads = new ArrayList<Pad>();
            if (numberOfPads % 2 != 0)
                numberOfPads--;
            ArrayList slots = Helpers.returnEmptySlots(grid);
            Collections.shuffle(slots);
            for (int i = 0; i < numberOfPads; i=i+2) {

                int [] slot1 = (int[]) slots.remove(0);
                int [] slot2 = (int[]) slots.remove(0);

                int pad1X = slot1[0];
                int pad1Y = slot1[1];
                int pad2X = slot2[0];
                int pad2Y = slot2[1];

                Pad pad1 = new Pad(pad1X, pad1Y, pad2X, pad2Y);
                Pad pad2 = new Pad(pad2X, pad2Y, pad1X, pad1Y);
                grid[pad1X][pad1Y] = pad1;
                grid[pad2X][pad2Y] = pad2;
                pads.add(pad1);
                pads.add(pad2);
            }

            // Create agents
            emptySlots = Helpers.numOfLeftCells(grid);
            agents = new ArrayList<Agent>();
            slots = Helpers.returnEmptySlots(grid);
            Collections.shuffle(slots);
            int numberOfAgents = Helpers.genRandomNumber(1, emptySlots);
            for (int i = 0; i < numberOfAgents; i++) {
                int [] slot = (int[]) slots.remove(0);
                int agentX = slot[0];
                int agentY = slot[1];
                Agent agent = new Agent(agentX, agentY);
                grid[agentX][agentY] = agent;
                agents.add(agent);
            }
        }
    }

    public String genMatString(){
        String result = "";
        result = result + grid.length + "," + grid.length + ";";
        result = result + this.neo.x + "," + this.neo.y + ";";
        result = result + tb.x + "," + tb.y + ";";

        for (int i = 0; i < agents.size(); i++) {
            Agent agent = agents.get(i);
            result = result + agent.x + "," + agent.y;
        }
        result = result + ";";

        for (int i = 0; i < pills.size(); i++) {
            Pill pill = pills.get(i);
            result = result + pill.x + "," + pill.y;
        }
        result = result + ";";

        for (int i = 0; i < pads.size(); i++) {
            Pad pad = pads.get(i);
            result = result + pad.x + "," + pad.y;
            result = result + pad.goesToX + "," + pad.goesToY;
        }
        result = result + ";";

        for (int i = 0; i < hostages.size(); i++) {
            Hostage hostage = hostages.get(i);
            result = result + hostage.x + "," + hostage.y + "," + hostage.damage;
        }

        return result;
    }
}
