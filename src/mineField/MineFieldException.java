package mineField;

public class MineFieldException extends Exception{
    boolean firstWin;
    public MineFieldException(String s){
        this(s, false);
    }
    public MineFieldException(String s, boolean b){
        super(s);
        firstWin = b;
    }
}
