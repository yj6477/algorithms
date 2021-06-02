import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int StableSolCount = 0;
    private static int size = 0;

    static void swap(int possibleSol[][], int x, int y){
        int temp = possibleSol[x][1];
        possibleSol[x][1] = possibleSol[y][1];
        possibleSol[y][1] = temp;
    }

    static void permute(int possibleSol[][], int PreferList[][]){
        permute(possibleSol,PreferList,  1, possibleSol.length);
    }
    static void permute(int possibleSol[][],int PreferList[][], int i, int n){
        //each time it goes to this statement, it will generate one possible solution at a time and go to stable marriange function to see if it's stable
        if (i == n){
            //test out first that it produces all the possible solution
            /*
            System.out.print("{");
            for(int k = 1; k < n; k++){
                System.out.print("(" + possibleSol[k][0] + ", " + possibleSol[k][1] +"), ");
            }
            System.out.println("}");
            */
            if(stableMarriage(possibleSol, PreferList, n)){
                StableSolCount++;
            }
        }
        else{
            for (int j = i; j < n; j++){
                swap(possibleSol, i, j);
                permute(possibleSol,PreferList, i+1, n);
                swap(possibleSol, i, j);    //backtrack
            }
        }
    }

    static boolean stableMarriage(int possibleSol[][], int PreferList[][], int n){
        int MenPrefer[][] = new int [size][size];
        int WomenPrefer[][] = new int [size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                //fill in the men and women prefer lists
                MenPrefer[i][j] = PreferList[i][j];
                WomenPrefer[i][j] = PreferList[i+size][j];
            }
        }

        //Given the possible Solution, we will use men and women prefer lists to check if they are stable or not.
        int moveon = 0;
        int priority[] = new int [size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                int w1 = MenPrefer[i][j];
                int m1 = WomenPrefer[i][j];
                priority[j] = j;
                //check if men prefer women1 over current women
                if (possibleSol[i + 1][1] != w1) {
                    //find where women1 partnered with
                    int k = 0;
                    while(w1 != possibleSol[k+1][1]){k++;}

                        //check if women1 not satisfied current men
                        if (possibleSol[k + 1][0] != m1) {
                            //check if women1 want men that he called to
                            if (m1 == possibleSol[i + 1][0])
                                return false;
                        }
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        try{
            File file = new File("input1.txt");
            Scanner input = new Scanner(file);

            if (input.hasNextLine()){ //get size
                size = input.nextInt(); //first line is size
                input.nextLine();   //next line
                int PreferList[][] = new int[2*size][size]; //prefer lists
                System.out.println("size: " + size);
                int i = 0;
                while(input.hasNextInt()){

                    for(int j =0; j< size; j++){     //put all the prefer lists in the array
                        PreferList[i][j] = input.nextInt();
                    }
                    i++;
                }
               /*for(int k = 0; k < 2*size; k++){//checking to see if it inputted properly
                   for(int j =0; j<size; j++){
                       System.out.print(PreferList[k][j]);
                   }
                   System.out.println(" ");
               }*/
               int possibleSol[][] = new int [size+1][2];
               possibleSol[0][0] = 0;                       //I didnt want male0 and female 0  so I just set them 0 and it's not going to be in stable matching calculation
               possibleSol[0][1] = 0;
               for(int k = 1; k <= size; k++){
                   possibleSol[k][0] = k;
                   possibleSol[k][1] = k;
               }
               //go to the permutation first and then do stable matching
               permute(possibleSol, PreferList);
               System.out.println("Number of solutions " + StableSolCount);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
