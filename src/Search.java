

public abstract class Search {
    Operators operators;
    String initialState;
    public abstract boolean goalTest();
    public abstract String stateSpace();
    public abstract int pathCost();



    
}
