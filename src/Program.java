import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        State start = new State("s0",false);
        Fst fst = new Fst(start);

        fst.add_state(start);
        fst.add_state("s1", false);
        fst.add_state("s2", true);
//        fst.add_state("s3", false);
//        fst.add_state("s4", false);
//        fst.add_state("s5", false);
//        fst.add_state("s6", false);
//        fst.add_state("s7", true);
//        fst.add_state("s8", true);

        /*          important: '$' is lambda symbol          */

        fst.add_set_transition("s0", new char[]{'c','h','e','s','b','u'},"s0");
        fst.addTransition("s0",'s','s',"s1");
        fst.add_set_transition("s0", new char[]{'c','h','e','b','u'},"s2");
        fst.addTransition("s1",'$','$',"s2");
        fst.addTransition("s1",'s','$',"s2");
//        fst.addTransition("s0",'$','c',"s5");
//        fst.addTransition("s5",'l','a',"s5");
//        fst.addTransition("s5",'l','d',"s6");
//        fst.addTransition("s6",'i','f',"s7");
//        fst.addTransition("s6",'i','k',"s8");

        ArrayList<String> outs = fst.parse_input("bus");
        if(!outs.isEmpty()) {
            System.out.print("Accept, the outputs are:  ");
            for (String s:
                    outs) {
                System.out.print(s);
                System.out.print(" ");
            }
        }
        else
            System.out.println("Reject");

    }
}
