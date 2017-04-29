import java.util.Scanner;

public class TicTacToe

{
    static char PLACEHOLDER = '.';
    static Scanner keyboard = new Scanner(System.in);

    public static void drawBoard(char[][]board)
    {

        for(int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 3; column++)
            {
                System.out.print(board[row][column]);

                if(column != 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }

    }

    public static char[][] createEmptyBoard()
    {
        char gameBoard [][] = new char[3][3];

        for(int row = 0; row < 3; row++){  
            for(int column = 0; column < 3; column++){
                gameBoard[row][column] = PLACEHOLDER; 
            }
        }

        return gameBoard;
    }

    public static boolean isWin(char[][] board)
    {
        if (isHorizontalWin(board) || isVerticalWin(board) || isDiagonalWin(board))
        {
            return true;

        }
        else {

            return false;
        }

    }

    public static boolean isFull(char[][] board)
    {
        for(int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 3; column++)
            {
                if (board[row][column] == PLACEHOLDER)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isHorizontalWin(char[][] board)

    {

        for(int row = 0; row < 3; row++)
        {

            if (board[row][0] == 'X' && board[row][1] == 'X' && board[row][2] == 'X')
            {
                return true;
            }

            if (board[row][0] == 'O' && board[row][1] == 'O' && board[row][2] == 'O')
            {
                return true;
            }

        }
        return false;
    }

    public static boolean isVerticalWin(char[][] board)
    {

        for (int column = 0; column < 3; column++)
        {
            if (board[0][column] == 'X' && board[1][column] == 'X' && board[2][column] == 'X')
            {
                return true;
            }

            if (board[0][column] == 'O' && board[1][column] == 'O' && board[2][column] == 'O')
            {
                return true;
            }
        }

        return false;

    }  

    public static boolean isDiagonalWin(char[][] board)
    {

        if ((board[0][0] == 'X' & board [1][1] == 'X' && board [2][2] == 'X') ||
        (board[0][2] == 'X' && board [1][1] == 'X' && board [2][0] == 'X'))
        {
            return true;
        }
        if ((board[0][0] == 'O' & board [1][1] == 'O' && board [2][2] == 'O') ||
        (board[0][2] == 'O' && board [1][1] == 'O' && board [2][0] == 'O'))
        {
            return true;

        }
        return false;

    }

    public static void printWelcome()
    {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("To play, enter a number for which box to play in.");
        System.out.println("1 2 3");
        System.out.println("4 5 6");
        System.out.println("7 8 9");
        System.out.println("You'll need a buddy to play with.  Ready to begin?  Here we go.");
    }

    public static boolean wantsToPlayAgain()
    {  
        Scanner keyboard =new Scanner(System.in);

        System.out.println("Would you like to play again?");
        String enterYesOrNo;

        enterYesOrNo = keyboard.next();
        if(enterYesOrNo.equals("y"))
        {
            return true;
        }
        return false;

    }

    public static char getTokenAtPosition(int position, char[][] board)
    {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return board[row][col];
    }

    public static void placeToken(int position, char[][] board, boolean isXTurn)
    {

        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        if (isXTurn){
            board[row][col] = 'X';
        }

        if (!isXTurn){
            board[row][col] = 'O';
        }

    }

    public static boolean getPositionAndPlaceToken(char[][] board, boolean isXTurn)
    {
        Scanner keyboard =new Scanner(System.in);

        System.out.print("?");
        int position = 0;

        position = keyboard.nextInt();

        if(getTokenAtPosition(position, board) == PLACEHOLDER) 
        {
            placeToken(position, board, isXTurn);
            return true;
        } else {
            System.out.println("Sorry, " + position + " is already full. Try again.");
            return false;
        }
    }

    public static void game()
    {
        int xWins = 0;
        int oWins = 0;
        int draws = 0;
        boolean doesXStart = true;
        boolean isXTurn = doesXStart;

        printWelcome();


        do // all-games loop
        {
            if (doesXStart){

                System.out.println("X moves first.");

            }

            else{
                System.out.println("O moves first.");
            }

            char[][] board = createEmptyBoard();
            drawBoard(board);
            // initialize for the beginning of a new game.
            boolean gameStillGoing = true;

            // play the game until it's complete.  single-game loop.
            do {

                if(getPositionAndPlaceToken(board, isXTurn)) {            
                    if(isWin(board) && isXTurn) {
                        xWins++;
                        gameStillGoing = false;
                    } else if(isWin(board) && !isXTurn) {
                        oWins++;
                        gameStillGoing = false;
                    } else if(isFull(board)) {
                        draws++;
                        gameStillGoing = false;
                    } else {
                        gameStillGoing = true;
                    }

                    drawBoard(board);
                    isXTurn = !isXTurn;
                }
            } while (gameStillGoing);
            System.out.println("Score: X=" + xWins + ", O=" + oWins + ", draws=" + draws);
            doesXStart = !doesXStart;
        } while (wantsToPlayAgain());

        // They're done playing.  Method will return and program will finish.
    }

    public static void main(String[] args)
    {
        game();
        System.out.println("Goodbye!");
    }

}

