package CodingSimplified.Array;

public class SearchAnElement {






    public int searchIndex(int[]arr,int val)
    {

        if(arr.length==0)
            return -1;


        for(int i=0; i<arr.length; i++)
        {

            if(arr[i]==val)
            {
                return i;
            }
        }


        return -1;
    }


    public static void main(String[] args) {


        int[]arr = {1,2,3,4,5};

        SearchAnElement searchAnElement = new SearchAnElement();

        int index = searchAnElement.searchIndex(arr,4);


        System.out.println(" value index = "+index);


    }
}
