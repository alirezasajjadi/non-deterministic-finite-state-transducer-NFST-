public class Transition {
    private char input;
    private char output;
    private State nextState;

    public Transition(char input, char output, State nextState) {
        setInput(input);
        setOutput(output);
        setNextState(nextState);
    }

    public void setInput(char input) {
        this.input = input;
    }

    public void setOutput(char output) {
        this.output = output;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public char getInput() {
        return input;
    }

    public char getOutput() {
        return output;
    }

    public State getNextState() {
        return nextState;
    }
}
