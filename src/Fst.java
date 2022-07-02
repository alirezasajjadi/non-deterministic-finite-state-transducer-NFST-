import java.util.*;

public class Fst {
    private State start;
    private char[] input_string;
    private ArrayList<String> outStrings;
    private ArrayList<State> states = null;

    private String output_string = "";
    private StringBuffer sb = new StringBuffer(output_string);

    public Fst(State start) {
        setStart(start);
        states = new ArrayList<State>();
        outStrings = new ArrayList<>();
    }

    public void add_state(String state_name, boolean is_final) {
        states.add(new State(state_name, is_final));
    }

    public void add_state(State s) {
        states.add(s);
    }

    public void addTransition(String in_state_name, char input, char output, String out_state_name) {
        State outState = null;
        State inState = null;

        for (State state : states) {
            if (state.getName().equalsIgnoreCase(out_state_name))
                outState = state;
            if (state.getName().equalsIgnoreCase(in_state_name))
                inState = state;
        }

        if (inState != null && outState != null) {
            inState.getTransitions().add(new Transition(input, output, outState));
        }
    }

    public void add_set_transition(String in_state_name, char[] input_set, String out_state_name) {
        for (char input : input_set) {
            addTransition(in_state_name, input, input, out_state_name);
        }
    }

    public ArrayList<String> parse_input(String input_string) {

        for (Transition t : start.getTransitions()) {

            if (t.getInput() == input_string.charAt(0)) {

                search(t.getNextState(), t.getOutput(), input_string.substring(1));
            }

            /* lambda transition */
            if (t.getInput() == '$')
                search(t.getNextState(), t.getOutput(), input_string);
        }

        return outStrings;
    }

    public void search(State currentState, char out, String input_string) {
        if (out != '$')
            sb.append(out);

        if (currentState.isFinal() &&
                input_string.equalsIgnoreCase(""))       // i == in_length means all chars in input has been tracked
            outStrings.add(sb.toString());

        for (Transition t : currentState.getTransitions()) {
            if (input_string.length() > 0 && t.getInput() == input_string.charAt(0)) { // means the string is not end and equality
                search(t.getNextState(), t.getOutput(), input_string.substring(1));
            }

            /* lambda transition */
            if (t.getInput() == '$') {
                search(t.getNextState(), t.getOutput(), input_string);
            }
        }


        if (sb.length() != 0 && out != '$') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return;

    }

    public void setStart(State start) {
        this.start = start;
    }

}
