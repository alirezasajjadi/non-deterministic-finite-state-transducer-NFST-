import java.util.*;

public class Fst {
    private State start;
    private char[] input_string;
    private ArrayList<String> outStrings;
    private ArrayList<State> states = null;
    private int i; // counter for inputString
//    Map<State, Map<Character, Map<Character, List<State>>>> transitions = null;   // state > input > output -> state

    private String output_string = "";
    private StringBuffer sb = new StringBuffer(output_string);
    private boolean checkLambda = false;

    public Fst(State start) {
        setStart(start);
        states = new ArrayList<State>();
        outStrings = new ArrayList<>();
        i = 0;
//        transitions = new TreeMap<State, Map<Character, Map<Character , List<State>>>>();
    }

    public void add_state(String state_name, boolean is_final) {
        states.add(new State(state_name, is_final));
    }

    public void add_state(State s) {
        states.add(s);
    }

    public void addTransition(String in_state_name, char input, char output, String out_state_name) {
//        Transition transition = new Transition(input,output);


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

//        if(!transitions.containsKey(inState))
//            transitions.put(inState, new TreeMap<Character, Map<Character, List<State>>>());
//
//        if(!transitions.get(inState).containsKey(input))
//            transitions.get(inState).put(input, new TreeMap<Character, List<State>>());
//
//        if (!transitions.get(inState).get(input).containsKey(output))
//            transitions.get(inState).get(input).put(output, new ArrayList<State>());
//
//        transitions.get(inState).get(input).get(output).add(outState);


//        for (State state : states) {
//            if(state.name.equalsIgnoreCase(in_state_name)){
//                state.transitions.add(new Transition(input, output, outState));
//            }
//        }
    }

    public void add_set_transition(String in_state_name, char[] input_set, String out_state_name) {
        for (char input : input_set) {
            addTransition(in_state_name, input, input, out_state_name);
        }
    }

    public ArrayList<String> parse_input(String input_string) {
        this.input_string = input_string.toCharArray();


//        Set<State> nextStates = new TreeSet<State>();
        for (Transition t : start.getTransitions()) {
            i = 0;
            if (t.getInput() == this.input_string[0]) {
                i++;
                search(t.getNextState(), t.getOutput());
            }

            /* lambda transition */
            if (t.getInput() == '$')
                search(t.getNextState(), t.getOutput());
        }

        return outStrings;
    }

    public void search(State currentState, char out) {
        boolean checkLambda = false;
        if (out != '$')
            sb.append(out);

        if (currentState.isFinal() &&
                i == this.input_string.length)       // i == in_length means all chars in input has been tracked
            outStrings.add(sb.toString());

//        if (i == input_string.length &&
//                !checkLambda) { // the third condition for check weather curState has lambda trans. or not
//            sb.deleteCharAt(sb.length() - 1);
//            i--;
//            return;
//        }

        for (Transition t : currentState.getTransitions()) {
            if (i != input_string.length && t.getInput() == this.input_string[i]) { // means the string is not end and equality
                i++;
                checkLambda = false;
                search(t.getNextState(), t.getOutput());
            }

            /* lambda transition */
            if (t.getInput() == '$') {
                checkLambda = true;
                search(t.getNextState(), t.getOutput());
            }
        }


        if (sb.length() != 0 && out != '$') {
            sb.deleteCharAt(sb.length() - 1);
            if (!checkLambda)
                i--;
        }


        return;

    }


    public void setStart(State start) {
        this.start = start;
    }

//    public void setInput_string(String input_string) {
//        this.input_string = input_string;
//    }


//    Set<String> final_state;
//    Map<String, Map<Character, List<String>>> transitions; // start -> (input char -> [next states])

}
