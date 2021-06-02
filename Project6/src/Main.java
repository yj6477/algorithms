import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try{
            //get file
            File file = new File("input6.txt");
            Scanner NumStairs = new Scanner(file);
            if(NumStairs.hasNextInt()){
                //get the number in the file
                int n = NumStairs.nextInt();
                System.out.println("Number of stairs: " + n);
                System.out.println(climbStairs(n) + " ways to climb the stairs");
            }
            else
                System.out.println("Please put a file that has an input number");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
    //bottom up
    public static int climbStairs(int n){
        //memoization
        int arr[] = new int[n+1];

        //initialize what we know is true
     if ( n == 1){
         return 1;
     }
     arr[1] = 1;
     arr[2] = 2;

        //find all combinations
        for(int i =3; i <= n; i++){
          arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }

}
