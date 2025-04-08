import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private enum Cell{X,O,Empty}
    private Cell[][][] arr = new Cell[4][4][4];
    private  int selectionXO =0;                          // In two player mode (twoplayerplaygame() method) decide printing X or O


    public void setCellEmpty(){                           // Sets all cells to 'EMPTY'
       for (int k =0;k<4;k++) {
           for (int i = 0; i < 4; i++) {
               for (int j = 0; j < 4; j++) {
                   arr[i][j][k] = Cell.Empty;
               }
           }
       }
    }

    public int selectXO(){                                // Increasing and decreasing variable "selectionXO" for printing and sets on the board X and O in orderly in two player mode( in twoplayerplaygame() method)
         if (this.selectionXO==0){
             selectionXO++;
             return 0;
         }
         else  {
             selectionXO--;
             return 1;
         }
    }

    public void printBoard(){                             // Prints all the board's cells
        System.out.println();
       for (int k=0;k<4;k++){
           System.out.println(String.format("-- Plane %d --",k+1));
        for(int i=0;i<4;i++) {
            System.out.print("|");

            for (int j = 0; j < 4; j++) {
                if (arr[i][j][k].equals(Cell.Empty)) {
                    System.out.print("   |");
                } else {
                    System.out.print(" " + arr[i][j][k] + " |");
                }
            }
            System.out.println();
            System.out.println("-----------------");
        }
        System.out.println();
       }
    }

    public boolean checkdraw(){                           // Checks all board's cells for draw situation
       for(int k=0;k<4;k++){
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j][k] == Cell.Empty) {
                    return false;
                }
            }
         }
        }
        System.out.println();
        System.out.println("DRAW!");
        return true;
    }

    public boolean checkWin(){                            // Checks X or O win situations on a plane

        for (int k = 0; k < 4; k++) {                                   //Checks every column one by one on plane
                for (int i = 0; i < 4; i++) {                           // Ex: X
                    for (int j = 0; j < 4; j++) {                       //     X O          (X WON !!)
                        if (!(arr[i][j][k].equals(Cell.Empty))) {       //     X   O
                                                                        //     X     O
                            try {
                                if (arr[i][j][k].equals(arr[i][j - 1][k]) && arr[i][j][k].equals(arr[i][j + 1][k])&&arr[i][j+2][k].equals(arr[i][j][k])) {
                                  System.out.println("===============================================================================");
                                  System.out.println("Final Table Is :");

                                    printBoard();
                                    System.out.println(arr[i][j][k] + " WON!!");
                                    return true;
                                }
                            }
                            catch (Exception e){
                                continue;
                            }
                        }
                    }
                }
            }

            //-------------------------------------------------------------------------

        for (int k = 0; k < 4; k++) {                                //Checks every row one by one on plane
            for (int i = 0; i < 4; i++) {                            // Ex: X X X X
                for (int j = 0; j < 4; j++) {                        //       O          (X WON !!)
                    if (!(arr[j][i][k].equals(Cell.Empty))) {        //         O
                                                                     //           O
                        try {
                            if (arr[j+1][i][k].equals(arr[j][i][k]) && arr[j][i][k].equals(arr[j-1][i][k])&&arr[j+2][i][k].equals(arr[j][i][k])) {
                                System.out.println("===============================================================================");
                                System.out.println("Final Table Is :");
                                printBoard();
                                System.out.println(arr[j][i][k] + " WON!!");
                                return true;
                            }
                        }
                        catch (Exception e){
                            continue;
                        }
                    }
                }
            }
        }

        //-------------------------------------------------------------------------

        for (int k = 0; k < 4; k++) {                                //Checks left diagonally win condition on plane
            for (int i = 0; i < 4; i++) {                            // Ex:  X O
                for (int j = 0; j < 4; j++) {                        //      O X           (X WON !!)
                    if (!(arr[i][j][k].equals(Cell.Empty))) {        //        O X
                                                                     //            X
                        try {
                            if (arr[i-1][j-1][k].equals(arr[i][j][k]) && arr[i+1][j+1][k].equals(arr[i][j][k]) && arr[i+2][j+2][k].equals(arr[i][j][k])) {
                                System.out.println("===============================================================================");
                                System.out.println("Final Table Is :");
                                printBoard();
                                System.out.println(arr[i][j][k] + " WON!!");
                                return true;
                            }
                        }
                        catch (Exception e){
                            continue;
                        }
                    }
                }
            }
        }

        //------------------------------------------------------------------------- right cross check

        for (int k = 0; k < 4; k++) {                                //Checks left diagonally win condition on plane
            for (int i = 0; i < 4; i++) {                            // Ex:        X
                for (int j = 0; j < 4; j++) {                        //      O O X          (X WON !!)
                    if (!(arr[i][j][k].equals(Cell.Empty))) {        //        X O
                                                                     //      X     X
                        try {
                            if (arr[i-1][j+1][k].equals(arr[i][j][k]) && arr[i+1][j-1][k].equals(arr[i][j][k])&&arr[i+2][j-2][k].equals(arr[i][j][k])) {
                                System.out.println("===============================================================================");
                                System.out.println("Final Table Is :");
                                printBoard();
                                System.out.println(arr[i][j][k] + " WON!!");

                                return true;
                            }
                        }
                        catch (Exception e){
                            continue;
                        }

                    }
                }
            }
        }

        return checkWin2();
    }
    public boolean checkWin2(){                          // Checks win conditions on 4 planes
         int y =0;
         int j =0;
            for (int i = 0; i < 4; i++) {                //Vertical Line Check, checks if in 4 planes have same item X or O on same vertical line
                for (j = 0; j < 4; j++) {
                    if (!(arr[i][j][y].equals(Cell.Empty))) {
                        if (arr[i][j][y].equals(arr[i][j][y+1])&&arr[i][j][y].equals(arr[i][j][y+2])&&arr[i][j][y].equals(arr[i][j][y+3])){
                            System.out.println("===============================================================================");
                            System.out.println("Final Table Is :");
                            printBoard();
                            System.out.println("\"Vertical line across all levels\" "+arr[i][j][y]+ " WON!!");
                            return true;
                        }
                    }
                }
            }

        //-------------------------------------------------------------------------

        j=0;
        for (int i = 0; i < 4; i++) {                //Horizontal Check, checks if in 4 planes have same item X or O on same horizontal

                if (!(arr[i][j][y].equals(Cell.Empty))) {
                    if (arr[i][j][y].equals(arr[i][j+1][y+1])&&arr[i][j][y].equals(arr[i][j+2][y+2])&&arr[i][j][y].equals(arr[i][j+3][y+3])){
                        System.out.println("===============================================================================");
                        System.out.println("Final Table Is :");
                        printBoard();
                        System.out.println("\"Horizontal across all levels\" "+arr[i][j][y]+ " WON!!");
                        return true;
                    }
                }

        }


        //-------------------------------------------------------------------------

        j=0;
        for (int i = 0; i < 4; i++) {                //Vertical Check, checks if in 4 planes have same item X or O on same horizontal

            if (!(arr[j][i][y].equals(Cell.Empty))) {
                if (arr[j][i][y].equals(arr[j+1][i][y+1])&&arr[j][i][y].equals(arr[j+2][i][y+2])&&arr[j][i][y].equals(arr[j+3][i][y+3])){
                    System.out.println("===============================================================================");
                    System.out.println("Final Table Is :");
                    printBoard();
                    System.out.println("\"Vertical across all levels\" "+arr[j][i][y]+ " WON!!");
                    return true;
                }
            }

        }

        //-------------------------------------------------------------------------
        int k=0;
        int i=0;


        if (!(arr[k][k][y].equals(Cell.Empty))) {                // Left Diagonally Check, checks if in 4 planes have same item X or O on same diagonal

            if(arr[k][k][y].equals(arr[k+1][k+1][y+1])&&arr[k][k][y].equals(arr[k+2][k+2][y+2])&&arr[k][k][y].equals(arr[k+3][k+3][y+3])){
                System.out.println("===============================================================================");
                System.out.println("Final Table Is :");
                printBoard();
                System.out.println("\"Diagonally all levels\" "+arr[k][k][y]+" WON!!");
                return true;
            }
        }


        //-------------------------------------------------------------------------


        if (!(arr[k][i+3][y].equals(Cell.Empty))) {               // Right Diagonally Check, checks if in 4 planes have same item X or O on same diagonal

            if(arr[k][i+3][y].equals(arr[k+1][i+2][y+1])&&arr[k][i+3][y].equals(arr[k+2][i+1][y+2])&&arr[k][i+3][y].equals(arr[k+3][i][y+3])){
                System.out.println("===============================================================================");
                System.out.println("Final Table Is :");
                printBoard();
                System.out.println("\"Diagonally all levels\" "+arr[k][i+3][y]+" WON!!");
                return true;

            }
        }

        return false;
    }
    
    public void computerPlaygame(){                               // Player VS Computer Mode
        Random rn = new Random();
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to start first? (yes/no) :");
        String reply = scan.nextLine();
        while (true){                                             // If user enters word instead of yes or no this loop ask until get yes or no

            if(!(reply.equals("yes")||reply.equals("no"))){
                System.out.print("Please write \"yes\" or \"no\" :");
                reply= scan.nextLine();
            }
            else {
                break;
            }

        }

        int onetime=0;
        while(true) {                        // Player VS Computer

            if (reply.equals("yes")) {       // Player starts with X against to computer

                if(onetime!=1){              // Prints the empty board for player decide.(It is just for start)
                    printBoard();
                    onetime++;
                }

                System.out.print("Please enter location " + Cell.X + " (row(0-3) column(0-3) plane(0-3)) (Ex:2 0 3) :");

                String location = scan.nextLine();                              // Takes location from player

                location = location.replace(","," ");          // If user enter location like "1,2,3" removes all the ","

                Scanner scan2 = new Scanner(location);

                int a=-1;
                int b=-1;
                int c=-1;

                int numbercounter=0;                              // For be sure that player enters 3 location number (row,column,plane)
                while (scan2.hasNext()){
                    try {
                        a = Integer.parseInt(scan2.next());        // Row location

                        b = Integer.parseInt(scan2.next());        // Column location

                        c = Integer.parseInt(scan2.next());        // Plane location

                    }
                    catch (Exception e){}
                   numbercounter+=3;
                }

                if(numbercounter>3){                                               // If user enters more than 3 number, program ask enter new 3 number from player
                    System.out.println("You have entered invalid location range please try again!");
                    continue;
                }

                if (!((a>=0&&a<=3)&&(b>=0&&b<=3)&&(c>=0&&c<=3))){                  // For user enters invalid position range
                    System.out.println("You have entered invalid location range please try again!");
                    continue;
                }

                if(arr[a][b][c].equals(Cell.Empty)) {                              // Sets player's location on board
                        arr[a][b][c] = Cell.X;
                }
                else{                                                              // If selected location not empty asks until take valid(EMPTY) location
                    System.out.println("Your selected position is not empty.Please try again!");
                    continue;
                }


                if(checkWin()){                       // Checks win condition
                    break;
                }

                if(checkdraw()){                      // Checks draw condition
                    break;
                }
                while(true){                          // Computer's turn, decides location until find an empty cell and sets on the board
                    a= rn.nextInt(0,4);
                    b= rn.nextInt(0,4);
                    c= rn.nextInt(0,4);
                    if (arr[a][b][c].equals(Cell.Empty)){
                        arr[a][b][c]=Cell.O;
                        break;
                    }
                }

                printBoard();                        // Prints the board

                if(checkWin()){                      // Checks win condition
                    break;
                }
                if(checkdraw()){                     // Checks draw condition
                    break;
                }

            }
        //--------------------------------------------------------------
            else{                                             // Player starts with O against to computer

               int a=0;
               int b=0;
               int c =0;

                while(true){                                 // Computer's turn, decides location until find an empty cell and sets on the board
                    a= rn.nextInt(0,4);
                    b= rn.nextInt(0,4);
                    c= rn.nextInt(0,4);
                    if (arr[a][b][c].equals(Cell.Empty)){
                        arr[a][b][c]=Cell.X;
                        break;
                    }
                }

                printBoard();                        // Prints the board

                if(checkWin()){                      // Checks win condition
                    break;
                }
                if(checkdraw()){                     // Checks draw condition
                    break;
                }
                while(true) {                                          // while loop for checking player's enters valid position

                System.out.print("Please enter location " + Cell.O + " (row(0-3) column(0-3) plane(0-3)) (Ex:2 0 3) :");

                String location = scan.nextLine();                      // Takes location from player

                location = location.replace(","," ");  // If user enter location like "1,2,3" removes all the ","

                Scanner scan2 = new Scanner(location);


                    a = -1;
                    b = -1;
                    c = -1;

                    int numbercounter = 0;                              // For be sure that player enters 3 location number (row,column,plane)

                    while (scan2.hasNext()) {
                        try {
                            a = Integer.parseInt(scan2.next());        // Row location

                            b = Integer.parseInt(scan2.next());        // Column location

                            c = Integer.parseInt(scan2.next());        // Plane location

                        } catch (Exception e) {
                        }
                        numbercounter += 3;
                    }

                    if (numbercounter>3) {                            // If user enters more than 3 number, program ask enter new 3 number from player

                        System.out.println("You have entered invalid location range please try again!");
                        continue;
                    }

                    if (!((a >= 0 && a <= 3) && (b >= 0 && b <= 3) && (c >= 0 && c <= 3))) {                  // For user enters invalid position range
                        System.out.println("You have entered invalid location range please try again!");
                        continue;
                    }

                    if (arr[a][b][c].equals(Cell.Empty)) {      // Sets player's location on board
                        arr[a][b][c] = Cell.O;
                    } else {                                      // If selected location not empty asks until take valid(EMPTY) location
                        System.out.println("Your selected position is not empty.Please try again!");
                        continue;
                    }
                    break;                           // if player's entered location was valid then while loop ends
                }

                if(checkWin()){                      // Checks win condition
                    break;
                }
                if(checkdraw()){                     // Checks draw condition
                    break;
                }
            }
        }
    }

    public void twoplayerplaygame(){                 // Player VS Player Mode
        Scanner scan = new Scanner(System.in);

        int onetime =0;
        while(true){

            if(onetime!=1){              // Prints the empty board for player decide.(It is just for start)
                printBoard();
                onetime++;
            }

            if(this.selectionXO==0){                 // It is for printing the table cells in order X and O
                System.out.print("Please enter location "+Cell.X+" (row(0-3) column(0-3) plane(0-3)) (Ex:2 0 3) :");

            }
            else {
                System.out.print("Please enter location "+Cell.O+" (row(0-3) column(0-3) plane(0-3)) (Ex:2 0 3):");

            }

            String location = scan.nextLine();                       // Takes location from player

            location = location.replace(","," ");   // If user enter location like "1,2,3" removes all the ","

            Scanner scan2 = new Scanner(location);


            int a=-1;
            int b=-1;
            int c=-1;

            int numbercounter=0;                              // For be sure that player enters 3 location number (row,column,plane)
            while (scan2.hasNext()){
                try {
                    a = Integer.parseInt(scan2.next());        // Row location


                    b = Integer.parseInt(scan2.next());        // Column location


                    c = Integer.parseInt(scan2.next());        // Plane location

                }
                catch (Exception e){}
                numbercounter+=3;
            }

            if(numbercounter>3){                                               // If user enters more than 3 number, program ask enter new 3 number from player
                System.out.println("You have entered invalid location range please try again!");
                continue;
            }

            if (!((a>=0&&a<=3)&&(b>=0&&b<=3)&&(c>=0&&c<=3))){                  // For user enters invalid position range
                System.out.println("You have entered invalid location range please try again!");
                continue;
            }

           if(arr[a][b][c].equals(Cell.Empty)) { // Sets player's location on board

               if (selectXO() == 0) {            // It is for sets the table cells in order X and O
                   arr[a][b][c] = Cell.X;
               } else {
                   arr[a][b][c] = Cell.O;
               }
           }
           else{                                 // If selected location not empty asks until take valid(EMPTY ) location
               System.out.println("Your selected position is not empty.Please try again!");
               continue;
           }

            printBoard();                        // Prints the board

            if(checkWin()){                      // Checks win condition
                break;
            }
            if(checkdraw()){                     // Checks draw condition
                break;
            }


        }

    }
}
