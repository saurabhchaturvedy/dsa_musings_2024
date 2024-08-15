package CodingSimplified.Array;

public class InsertElement {


    int count=0;


    public void insertElement(int[]arr,int val)
    {

        arr[count++] = val;
    }


    public void insertElementAtStart(int[]arr,int val)
    {



        for(int i=arr.length-1; i>0; i--)
        {

            arr[i] = arr[i-1];
        }

        arr[0] = val;
    }


    public void insertElementAtPosition(int[]arr,int val,int position)
    {

        for(int i=arr.length-1; i>position; i--)
        {

            arr[i] = arr[i-1];
        }

        arr[position] = val;

    }


    public void print(int[]arr)
    {

        for(int i=0; i<arr.length; i++)
        {

            System.out.print(arr[i]+" ");
        }
    }


    public static void main(String[] args) {


        int[]arr = new int[5];

        InsertElement insertElement = new InsertElement();

        for(int i=0; i<arr.length; i++)
        {

            insertElement.insertElement(arr,i+1);
        }

        insertElement.print(arr);


        insertElement.insertElementAtStart(arr,8);


        System.out.println();

        insertElement.print(arr);

        insertElement.insertElementAtPosition(arr,13,4);

        System.out.println();

        insertElement.print(arr);

    }
}
