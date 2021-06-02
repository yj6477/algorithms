import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static long swaps = 0;

    public static void main(String[] args) {
        int size = 0;                       //initialize the size of the array
        try{
            File file = new File("input3.txt");
            Scanner input = new Scanner(file);
            ArrayList<Integer> inputList=  new ArrayList<Integer>();
            while(input.hasNextInt()){
                inputList.add(input.nextInt());
            }
            size = inputList.size();

            int[] arr  = new int[size];
            for(int i = 0; i < size; i++){
                arr[i] = inputList.get(i);
            }
            //recursive kick off
            //MergeSort getSwap = new MergeSort();
            mergeSort(arr,0, size-1);
            System.out.println("Number of Swaps "+ swaps);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }


        //printing sorter array

    }

    public static void mergeSort(int[] a,int start, int end) {
        //base case - if the size of the array is 1 or less...return
        if (start < end) {
            int mid = (start + end)/2;                  //define mid point


            //recursive calls
            mergeSort(a,start, mid);              //taking the new left array and recursing until merged
            mergeSort(a, mid+1, end);          //taking the new right array and recursing until merged

            merge(a, start, mid, end);   //merge both the left and the right after they have recursed
        }



    }

    public static void merge(int[] a, int start,int mid, int end) { //compare to professor's merge algorithm, I have to bring the mid value to calculate the swaps
        int i = 0;
        int j = 0;
        int k = start;
        int[] left = Arrays.copyOfRange(a, start, mid+1);   //fill in the left sub array
        int[] right = Arrays.copyOfRange(a, mid+1, end+1); //fill in the right subarray


        while (i < left.length && j < right.length) {
            //if the value in left is less or equal to the value in right
            if (left[i] <= right[j]) {
                //we put the value in left in the new array first, and update i and k
                a[k++] = left[i++];
            }
            //if the value in right is less than the value in left
            else {
                //we put the value in right in the new array first, and update j and k
                a[k++] = right[j++];
                swaps += (mid + 1) - (start + i);                                       //increment the count of swaps.
            }
        }
        while (i < left.length) {//fill in the rest of left subarray
            a[k++] = left[i++];
        }
        while (j < right.length) {//fill in the rest of right subarray
            a[k++] = right[j++];
        }
    }
}


