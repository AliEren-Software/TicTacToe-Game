import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();
        Scanner scan = new Scanner(System.in);
        game.setCellEmpty();                    // Sets all cells to 'EMPTY'
       System.out.print("Do you want to play with computer? (yes/no) :");
       String answer = scan.nextLine();

       while(true){                            // If user enters word instead of yes or no this loop ask until get yes or no

           if(!(answer.equals("yes")||answer.equals("no"))){
               System.out.print("Please write \"yes\" or \"no\" :");
               answer= scan.nextLine();
           }
           else {
               break;
           }
       }

       if (answer.equals("yes")){            // If user enters "yes" game will start Person VS Computer
           game.computerPlaygame();
       }
       else {                                // If enters "no" game will start Person VS Person
           game.twoplayerplaygame();

       }
       
    }
}