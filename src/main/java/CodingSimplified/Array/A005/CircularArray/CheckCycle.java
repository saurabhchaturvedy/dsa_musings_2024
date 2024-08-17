package CodingSimplified.Array.A005.CircularArray;

public class CheckCycle {



    public static boolean circularArrayLoop(int[] nums) {



        for(int i=0; i<nums.length; i++)
        {


            int slow=i;
            int fast=i;
            boolean ifForward=nums[i]>=0;

            while(true)
            {

                slow = getNextPosition(nums,slow,ifForward);
                if(slow==-1)
                {
                    break;
                }

                fast = getNextPosition(nums,fast,ifForward);
                if(slow==-1)
                {
                    break;
                }

                fast = getNextPosition(nums,fast,ifForward);
                if(fast==-1)
                {
                    break;
                }

                if(fast==slow)
                {
                    return true;
                }
            }
        }


        return false;
    }


    static int getNextPosition(int[]arr,int index,boolean ifForward)
    {

        boolean currentDirection = arr[index]>=0;


        if(ifForward!=currentDirection)
        {
            return -1;
        }



        int nextIndex = (index + arr[index])%arr.length;

        if(nextIndex<0)
        {

            nextIndex = nextIndex + arr.length;
        }

        if(nextIndex==index)
        {

            return -1;
        }

        return nextIndex;

    }


    public static void main(String[] args) {


        int[]arr = {-1,-2,-3,-4,-5,6};

        boolean loop = circularArrayLoop(arr);

        System.out.println(" has loop ? "+loop);
    }
}
