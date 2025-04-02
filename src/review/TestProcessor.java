package review;

import java.util.ArrayList;
import java.util.List;

public class TestProcessor {
    public static void main(String[] args) {
        List<Instruction> program1 = new ArrayList<Instruction>();
        program1.add(new Add(10));
        program1.add(new Mul(4));
        program1.add(new Add(2));
        Processor p = new Processor();
        p.execute(program1);
        System.out.println(p.getAccumulator()); // = 42
    }
}