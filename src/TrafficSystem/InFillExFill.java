package TrafficSystem;

import java.util.ArrayList;

public class InFillExFill<Type> {

    private ArrayList<Type> exFills;
    private Type inFill;

    public InFillExFill(Type inFill){
        this.inFill = inFill;
        exFills = new ArrayList<>();
    }

    public InFillExFill(Type inFill, ArrayList<Type> exFills){
        this.inFill = inFill;
        this.exFills = exFills;
    }

    public void addExFill(Type newExFill){
        exFills.add(newExFill);
    }

    public Type getInFill() {
        return inFill;
    }

    public ArrayList<Type> getExFills() {
        return exFills;
    }
}
