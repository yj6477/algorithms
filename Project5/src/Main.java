import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try{
            File file = new File ("input5.txt");
            Scanner input = new Scanner(file);
            ArrayList<Integer> inputList = new ArrayList<Integer>();
            //get the numbers in array
            while(input.hasNextInt()){
                inputList.add(input.nextInt());
            }
            //get size of the array
            int size = inputList.size();
            Integer[] arr = new Integer[size];
            //put it into array since Arrays.sort dont work on ArrayList
            for (int i = 0; i < size; i++){
                arr[i] = inputList.get(i);
            }
            //make custom comparator
            Arrays.sort(arr, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    //sort by absolute value
                    if(Math.abs(o1) < Math.abs(o2)){
                        return -1;                  //don't swap
                    }
                    else if (Math.abs(o1) > Math.abs(o2)){
                        return 1;                   //swap
                    }
                    //sort identical absolute values
                    else if(o1 < o2){
                        return -1;              //don't swap
                    }
                    else if (o1 > o2){
                        return 1;               //swap
                    }
                    else
                        return 0;
                }
            });
            //print array
            System.out.println(Arrays.toString(arr));

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
