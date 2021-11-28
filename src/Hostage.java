public class Hostage {
    int x;
    int y;
    int damage;
    boolean carried = false;
    boolean dropped = false;

    public Hostage(int x, int y, int damage, boolean carried,boolean dropped) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.carried = carried;
        this.dropped = dropped;
    }
}