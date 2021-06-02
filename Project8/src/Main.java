import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static int pathWay(int y, int x){
        //create the array for memoization
        int[][] arr = new int [y][x];
        //start what we know it's true
        arr[0][0] = 0;
        //put all first columns and rows equal to 1
        for(int row = 1; row < x; row++)
            arr[0][row] = 1;
        for(int column = 1; column < y; column++)
            arr[column][0] = 1;
        //bottom-up loop
        for(int i = 1; i < y; i++){
            for(int j = 1; j< x ; j++){
                arr[i][j] = arr[i-1][j]+arr[i][j-1];
            }
        }
        return arr[y-1][x-1];
    }

    public static void main(String[] args) {
        try{
            File file = new File("input8.txt");
            Scanner scanner = new Scanner(file);
            //get x-axis and y-axis
            int y = scanner.nextInt();      //column
            int x = scanner.nextInt();      //row


            System.out.println(pathWay(y, x) + " ways");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
