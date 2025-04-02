package midterm;
import mvc.Model;
import mvc.Utilities;

public class Casino extends Model{
    private int dice1;
    private int dice2;
    private int sum;
    private int score = 0;
    public Casino(){
        dice1 = 1;
        dice2 = 2;
        score = 0;
    }
    public void roll(){
        dice1 = Utilities.rng.nextInt(6) + 1;
        dice2 = Utilities.rng.nextInt(6) + 1;
        sum = dice1 + dice2;
        if(sum == 3 || sum ==12)
            score = 0;
        else if(sum == 7 || sum == 11)
            score++;
        changed();
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
