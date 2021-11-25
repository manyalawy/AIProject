public class Matrix {

    TelelphoneBooth tb;
    int tbx;
    int tby;
    int[] hostagex;
    int[] hostagey;
    int[] pillx;
    int[] pilly;
    Pill[] allPills;
    int pills = 0;

    public void generategrid() {

        int gridsize = 5 + (int) (Math.random() * ((16 - 5) + 1)); // generate random n by m
        System.out.println("gridsize:" + gridsize);
        Object[][] grid = new Object[gridsize][gridsize]; // generate 2D array of objects

        // hostages
        int Hostages = 3 + (int) (Math.random() * ((10 - 3) + 1)); // generate random number of hostages
        Hostage[] HostagesArray = new Hostage[Hostages]; // generate array of hostages
        System.out.println("number of hostages:" + Hostages);
        hostagex = new int[Hostages];// x position of hostages
        hostagey = new int[Hostages];// y positions of hostages
        for (int i = 0; i < HostagesArray.length; i++) {
            int startingdamage = 1 + (int) (Math.random() * ((99 - 1) + 1)); // generate random starting damage
            int x = (int) (Math.random() * (gridsize - 0)); // generate x position of hostage
            int y = (int) (Math.random() * (gridsize - 0)); // generate random y position for the matrix
            hostagex[i] = x;
            hostagey[i] = y;
            for (int j = 0; j < hostagex.length; j++) {
                if (hostagex[j] == x && hostagey[j] == y) {
                    x = (int) (Math.random() * (gridsize - 0));
                    y = (int) (Math.random() * (gridsize - 0));
                }
            }
            HostagesArray[i] = new Hostage(x, y, startingdamage);
            grid[x][y] = HostagesArray[i];
        }

        // printing positions of the hostages
        for (int i = 0; i < HostagesArray.length; i++) {
            System.out.println("hostage position:" + "(" + HostagesArray[i].x + "," + HostagesArray[i].y + ")");
        } // printing hostages positions

        // telephone booth
        tbx = (int) (Math.random() * (gridsize - 0)); // generate random variable for x
        tby = (int) (Math.random() * (gridsize - 0));// generate random variable for y

        for (int i = 0; i < hostagex.length; i++) {
            if (hostagex[i] == tbx && hostagey[i] == tby) {
                tbx = (int) (Math.random() * (gridsize - 0));
                tby = (int) (Math.random() * (gridsize - 0));
            }
        }
        tb = new TelelphoneBooth(tbx, tby);
        grid[tbx][tby] = tb;
        System.out.println("TelephoneBooth:" + "(" + tb.x + "," + tb.y + ")");

    }

    public static void main(String[] args) {
        // Matrix x = new Matrix();
        // x.generategrid();
        // Object[] x = new Object[3];
        // System.out.println(x[0]);
        Grid grid = new Grid();
        System.out.println("Done");

    }
}