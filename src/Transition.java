public class Transition {
    char input;
    char output;
    State nextState;

    public Transition(char input, char output, State nextState) {
        this.input = input;
        this.output = output;
        this.nextState = nextState;
    }

}
