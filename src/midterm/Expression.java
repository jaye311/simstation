package midterm;

import java.util.ArrayList;
import java.util.List;

public abstract class Expression {
    public abstract Double execute();
}
class ConditionalExpression extends Expression{
    Expression condition;
    Expression then;
    Expression else_;

    public ConditionalExpression(Expression condition, Expression then){
        this.condition = condition;
        this.then = then;
    }
    public ConditionalExpression(Expression condition, Expression then, Expression else_){
        this(condition, then);
        this.else_ = else_;
    }
    @Override
    public Double execute() {
        if(condition.execute() != 0)
            return then.execute();
        else if(else_ != null)
            return else_.execute();
        return null;
    }

    public String toString(){
        String ret = "if (" + condition.toString() + ") then " + then.toString();
        if(else_ != null)
            ret = ret + "else " + else_.toString();
        return ret;
    }
}
class ArithmeticExpression extends Expression{
    public ArithmeticExpression(){

    }
    @Override
    public Double execute() {
        return 0.0;
    }
}
class BlockExpression extends Expression{
    List<Expression> exps;
    public BlockExpression(Expression expression){
        exps = new ArrayList<>();
        exps.add(expression);
    }
    @Override
    public Double execute() {
        return exps.get(exps.size() - 1).execute();
    }

    @Override
    public String toString() {
        String ret = "{";
        for (int i = 0; i < exps.size() - 1; i++){
            ret = ret + exps.get(i).toString() + "; ";
        }
        return ret + exps.get(exps.size() - 1).toString() + "}";
    }
}
