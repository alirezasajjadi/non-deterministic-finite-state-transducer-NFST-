import java.util.ArrayList;

public class State {
    private String name;
    private boolean isFinal;
    private ArrayList<Transition> transitions = null;
    public State(String state_name, boolean is_final) {
        setName(state_name);
        setFinal(is_final);
        this.transitions = new ArrayList<Transition>();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public String getName() {
        return name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }
}
