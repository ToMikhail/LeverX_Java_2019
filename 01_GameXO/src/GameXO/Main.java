package GameXO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int [] field = {0,0,0,
                            0,0,0,
                            0,0,0};

    public static void main(String[] args){

        boolean b;
        boolean isCurrentX = false;
        do {
            isCurrentX = !isCurrentX;
            makeField();
            System.out.println("mark " + (isCurrentX ? "X" : "O"));
            int n = getNumber();
            field[n] = isCurrentX ? 1 : 2;
            b = !isGameOver(n);
            if (isDraw()){
                System.out.println("Draw");
                return;
            }
        } while (b);
        makeField();
        System.out.println();

        System.out.println("Winner is " + (isCurrentX ? "X" : "O") + "!");
    }

    static int getNumber(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                int n = Integer.parseInt(reader.readLine());
                if (n >= 0 && n < field.length && field[n]==0){
                    return n;
                }
                System.out.println("Enter your number");
            } catch (NumberFormatException e) {
                System.out.println("Enter your number");
            } catch (IOException e) {
            }
        }
    }

    static boolean isGameOver(int n){

        int row = n-n%3;
        if (field[row]== field[row+1] &&
                field[row]== field[row+2]) return true;

        int column = n%3;
        if (field[column]== field[column+3])
            if (field[column]== field[column+6]) return true;

        if (n%2!=0) return false;

        if (n%4==0){

            if (field[0] == field[4] &&
                    field[0] == field[8]) return true;
            if (n!=4) return false;
        }
        return field[2] == field[4] &&
                field[2] == field[6];
    }

    static void makeField(){
        for (int i = 0; i < field.length; i++) {
            if (i!=0){
                if (i%3==0) {
                    System.out.println();
                    System.out.println("_____ _____ _____");
                }
                else
                    System.out.print("|");
            }

            if (field[i]==0) System.out.print("  " + i + "  ");
            if (field[i]==1) System.out.print("  X  ");
            if (field[i]==2) System.out.print("  O  ");
        }
        System.out.println();
    }

    public static boolean isDraw() {
        for (int n : field) if (n==0) return false;
        return true;
    }
    
}
