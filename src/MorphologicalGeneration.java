import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MorphologicalGeneration {

    public static void main(String[] args) {
        State start = new State("q0", false);
        Fst fst = new Fst(start);

        fst.add_state(start);
        fst.add_state("q1", false);
        fst.add_state("q2", false);
        fst.add_state("q4", false);
        fst.add_state("q5", false);
        fst.add_state("q6", false);
        fst.add_state("q7", false);
        fst.add_state("q8", false);
        fst.add_state("q10", false);


        fst.add_state("q13", false);
        fst.add_state("q14", false);


        fst.add_state("q3", true);

        fst.add_set_transition("q0",
                new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'},
                "q0");

        fst.add_set_transition("q0",
                new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 't', 'u', 'v', 'w', 'x', 'y', 'z'},
                "q1");
        fst.addTransition("q1", 's', 's', "q13");
        fst.addTransition("q13", '$', 'e', "q14");
        fst.addTransition("q14", '$', 's', "q3");

        fst.addTransition("q0", 's', 's', "q2");
        fst.addTransition("q2", 'h', 'h', "q13");
        fst.addTransition("q2", 's', 's', "q13");

        fst.addTransition("q0", 'c', 'c', "q4");
        fst.addTransition("q4", 'h', 'h', "q13");

        fst.addTransition("q0", 'x', 'x', "q13");
        fst.addTransition("q0", 'o', 'o', "q13");
        fst.addTransition("q0", 'z', 'z', "q13");

        fst.add_set_transition("q0", new char[]{'a', 'i', 'e', 'o', 'u'}, "q6");
        fst.addTransition("q6", 'y', 'y', "q14");

        fst.add_set_transition("q0",
                new char[]{'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'},
                "q5");
        fst.addTransition("q5", 'y', 'i', "q13");

        fst.addTransition("q0", 'f', 'v', "q7");
        fst.addTransition("q7", 'e', 'e', "q14");
        fst.addTransition("q7", '$', 'e', "q14");

        fst.add_set_transition("q0",
                new char[]{'a', 'b', 'd', 'g', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 't', 'u', 'v', 'w'},
                "q14");

        fst.add_set_transition("q0",
                new char[]{'a', 'b', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 't', 'u', 'v', 'w', 'x', 'y', 'z'},
                "q10");
        fst.addTransition("q10", 'h', 'h', "q14");

        fst.add_set_transition("q0",
                new char[]{'a', 'b', 'c', 'd', 'e', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'},
                "q8");
        fst.addTransition("q8", 'e', 'e', "q14");

        ArrayList<String> outs = new ArrayList<>();

        try {
            File file = new File("C:\\Users\\dell\\Desktop\\university\\term 4\\language theory\\project 2\\non-deterministic FST\\src\\test.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                outs = fst.parse_input(line);
            }
            fr.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s:
             outs) {
            System.out.println(s);
        }

    }
}
