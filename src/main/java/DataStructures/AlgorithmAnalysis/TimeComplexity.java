package DataStructures.AlgorithmAnalysis;

public class TimeComplexity {



    int sum(int n)
    {
        return n*(n+1)/2;
    }


    int sumLoop(int n)
    {
        int sum=0;

        for(int i=1; i<=n; i++)
        {
            sum+=i;
        }

        return sum;
    }


    public static void main(String[] args) {

        int x=5;

        TimeComplexity timeComplexity = new TimeComplexity();

        long now = System.currentTimeMillis();
        int sum = timeComplexity.sum(9999999); // toggle between sum and sumloop to see the difference



        System.out.println(" Time taken : "+(System.currentTimeMillis()-now)+" ms ");

        long now2 = System.currentTimeMillis();
        int sumLoop = timeComplexity.sumLoop(9999999); // toggle between sum and sumloop to see the difference



        System.out.println(" Time taken : "+(System.currentTimeMillis()-now2)+" ms ");
    }
}
