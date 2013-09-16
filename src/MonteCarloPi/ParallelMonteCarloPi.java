package MonteCarloPi;

public class ParallelMonteCarloPi {

    public static void main(String[] args){
        int threadsAmount = Integer.parseInt(args[0]);
        int randomPointsAmount = (int)Math.pow(10, 8);
        long startTime = System.currentTimeMillis();
        double pi = getPI(randomPointsAmount, threadsAmount);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("PI is " + pi + "\nTHREADS " + threadsAmount +
                            "\nITERATIONS " + randomPointsAmount + "\nTIME " + duration + "ms");
    }

    private static double getPI(int randomPointsAmount, int threadsAmount){
        MonteCarloPiThread[] threads = new MonteCarloPiThread[threadsAmount];
        int pointsAmountPerThread = randomPointsAmount / threadsAmount;
        int lessThanOne = 0;

        for (int i = 0; i < threadsAmount; i++){
            threads[i] = new MonteCarloPiThread(pointsAmountPerThread);
            threads[i].start();
        }

        for (int i = 0; i < threadsAmount; i++){
            try {
                threads[i].join();
                lessThanOne += threads[i].getLessThanOne();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return (double)lessThanOne / (double)randomPointsAmount * 4;
    }
}
