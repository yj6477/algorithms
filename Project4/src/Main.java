import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        try{
            int capacity = 0;
            File file = new File ("input4.txt");        //get file
            Scanner scanner = new Scanner(file);                    //scan the file
            capacity = scanner.nextInt();                           //get first line file in which is the capacity that it can hold the items
            ArrayList<Integer> items = new ArrayList<Integer>();
            while(scanner.hasNextInt()){
                items.add(scanner.nextInt());                       //get all
            }

            int valueList = items.size()/2;                         //since item size is holding both value and weight, it needs to divide by 2
            int value[] = new int [valueList];
            int weight[] =  new int [valueList];

            for (int i = 0; i < valueList; i++){
                value[i] = items.get(i);                            //get value number of items from first half of item array list
                weight[i] = items.get(i+valueList);                 //get weights of items that's in other half of list in item array list
            }

            System.out.println(CalculateTotalValue(capacity, weight, value, valueList));
            scanner.close();

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }


    }

    static int CalculateTotalValue(int capacity, int weight[], int value[], int valueList){
        //create the array for the memoization
        int knapSack[][] = new int[valueList+1][capacity+1];

        //bottom-up
        for(int i = 0; i <= valueList; i++){
            for(int j = 0; j <= capacity; j++){
                if (i == 0 || j == 0)
                    knapSack[i][j] = 0;                                     //we know that weight zero will equal 0 value since it can't hold anything. and 0 valueList is zero since we want to start  with valueList 1.
                else if(weight[i-1]<= j)
                    knapSack[i][j] = Math.max(value[i-1] + knapSack[i-1][j-weight[i-1]], knapSack[i-1][j]);     //comparing first initial value 1 and 2 added together and value 2 and 3 added together
                else
                    knapSack[i][j] = knapSack[i-1][j];
            }
        }
        return knapSack[valueList][capacity];
    }
}
