import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean GreedyReach(Integer[] arr){
        //set availableSteps
        int availableSteps = 0;
        //if first index is zero return false
        if(arr[0] == 0) {
            return false;
        }
        //initialize available steps
        else {
            availableSteps = arr[0];
        }
        //go through the array
        for(int i = 0; i < arr.length; i++){
            //if index reach to  the end return true
            if(i == arr.length-1)
                return true;
            //take most steps available at the current index
            availableSteps = Math.max(availableSteps, arr[i]);
            if(availableSteps <= 0)
                return false;
            //decrement availablesteps as index increment
            availableSteps--;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            //get file
            File file = new File("input7.txt");
            Scanner scanner = new Scanner(file);
            ArrayList<Integer> input = new ArrayList<Integer>();
            //get the numbers in array
            while (scanner.hasNextInt()) {
                input.add(scanner.nextInt());
            }
            //get size of the array
            int size = input.size();
            Integer[] arr = new Integer[size];

            for(int i = 0; i < size; i++){
                arr[i] = input.get(i);
            }
        if(GreedyReach(arr))
            System.out.println("yes");
        else
            System.out.println("no");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
