public class Neo {
    int x;
    int y;
    int health;
    int maxToCarry;
    int numberOfHostages;
    int numberOfCarriedHostages;

    public Neo(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.maxToCarry = Helpers.genRandomNumber(1, 4);
        this.numberOfCarriedHostages = 0;
    }

}
