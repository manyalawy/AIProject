package code;
import java.util.HashSet;

public abstract class Search {
    Operators operators;
    String initialState;
    HashSet<String> stateSpace = new HashSet<String>();
    public abstract boolean goalTest();
    public abstract int pathCost();



    
}
