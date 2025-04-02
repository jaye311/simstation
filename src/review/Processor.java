package review;

import java.util.List;

public class Processor {
    private int accumulator = 0;
    private List<Instruction> program;
    public int getAccumulator(){
        return accumulator;
    }
    public void setAccumulator(int accumulator){
        this.accumulator = accumulator;
    }
    public void execute(List<Instruction> p){
        program = p;
        for(Instruction i: program){
            i.execute(this);
        }
    }
}
interface Instruction {
    public void execute(Processor p);
}
class Add implements Instruction{
    private int arg;
    public Add(int arg){
        this.arg = arg;
    }
    @Override
    public void execute(Processor p) {
        p.setAccumulator(p.getAccumulator() + arg);
    }
}
class Mul implements Instruction{
    private int arg;
    public Mul(int arg){
        this.arg = arg;
    }
    @Override
    public void execute(Processor p) {
        p.setAccumulator(p.getAccumulator() * arg);
    }
}
