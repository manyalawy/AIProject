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

}
