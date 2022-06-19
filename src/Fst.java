import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Fst {
    State start;
    String input_string;
    String output_string;
    ArrayList<State> states = null;

    public Fst(State start, String input_string, String output_string){
        setStart(start);
        setInput_string(input_string);

        states = new ArrayList<State>();
    }

    public void add_state(String state_name,boolean is_final){
        states.add(new State(state_name,is_final));
    }

    public void addTransition(String in_state_name,char input,char output,String out_state_name){
        State outState = null;

        for(State state : states){
            if(state.name.equalsIgnoreCase(out_state_name))
                outState = state;

        }
        for (State state : states) {
            if(state.name.equalsIgnoreCase(in_state_name)){
                state.transitions.add(new Transition(input, output, outState));
            }
        }
    }

    public void add_set_transition(String in_state_name,char [] input_set,String out_state_name){
        for (char input : input_set) {
            addTransition(in_state_name, input, input, out_state_name);
        }
    }



    public void setStart(State start) {
        this.start = start;
    }

    public void setInput_string(String input_string) {
        this.input_string = input_string;
    }


//    Set<String> final_state;
//    Map<String, Map<Character, List<String>>> transitions; // start -> (input char -> [next states])

}
