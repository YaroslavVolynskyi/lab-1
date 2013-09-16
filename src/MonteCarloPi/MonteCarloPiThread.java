package MonteCarloPi;

import java.util.Random;

public class MonteCarloPiThread extends Thread{

    private int pointsAmount;
    private int lessThanOne;
    private Random random;

    public MonteCarloPiThread(int pointsAmount){
        this.pointsAmount = pointsAmount;
        lessThanOne = 0;
        random = new Random();
    }

    public void run(){
        for(int i = 0; i < pointsAmount; i++){
            if (pointInCircleCheck(random.nextDouble(), random.nextDouble())){
                lessThanOne++;
            }
        }
    }

    private boolean pointInCircleCheck(double x, double y){
        return Math.sqrt( x * x + y * y) <= 1;
    }

    public int getLessThanOne(){
        return lessThanOne;
    }
}

