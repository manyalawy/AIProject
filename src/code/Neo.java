package code;
public class Neo {
    int x;
    int y;
    int damage;
    int maxToCarry;
    int numberOfHostages;
    int numberOfCarriedHostages;

    public Neo(int x, int y) {
        this.x = x;
        this.y = y;
        this.damage = 0;
        this.maxToCarry = Helpers.genRandomNumber(1, 4);
        this.numberOfCarriedHostages = 0;
    }

    public Neo(int x, int y, int c, int damage, int numberOfCarriedHostages) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.maxToCarry = c;
        this.numberOfCarriedHostages = numberOfCarriedHostages;
    }

    public void setNeoLocation(int x,int y){
        this.x=x;
        this.y=y;
    }
    
}
