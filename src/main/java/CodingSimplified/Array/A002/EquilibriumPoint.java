package CodingSimplified.Array.A002;

public class EquilibriumPoint {


    public int equilibriumPoint(int[] arr) {

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {

            sum = sum + arr[i];
        }

        int sum_from_right = 0;

        for (int i = 0; i < arr.length; i++) {

            sum = sum - arr[i];

            if (sum == sum_from_right) {
                return i;
            }

            sum_from_right = sum_from_right + arr[i];
        }

        return -1;
    }


    public static void main(String[] args) {


        int[] arr = {1, 7, 3, 6, 5, 6};

        EquilibriumPoint equilibriumPoint = new EquilibriumPoint();

        int equilibriumedPoint = equilibriumPoint.equilibriumPoint(arr);

        System.out.println(" Equilibrium index is " + equilibriumedPoint);
    }
}
