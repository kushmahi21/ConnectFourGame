import java.util.Scanner;
public class ConnectFour3 {
    public static int rall = 0; //this is row
    public static int call = 0; // this is columns
    public static int p = 0;    // this is position
    public static String[][] createPattern() {

        //Although the game is more like a table of 7 columns and 6 rows,
        // we're going to have to make a 2D array of 7 rows and 15 columns
        // because graphically there's an extra row to show the _ at the bottom
        // and you have double the columns that show | | | between each number
        Scanner scan1 = new Scanner(System.in);
        rall = scan1.nextInt();
        call =  scan1.nextInt();
        p = scan1.nextInt();
        int calltemp = 2 * call + 1;

        if(p <= 0 || p >((rall>call)?rall:call)) {
            System.out.println("Enter Valid P");
            p = scan1.nextInt();
        }
        String[][] f = new String[rall][calltemp];

        //Time to loop over each row from up to down

        for (int i = 0; i < f.length; i++) {

            //Time to loop over each column from left to right

            for (int j = 0; j < f[i].length; j++) {

                if (j % 2 == 0) f[i][j] = "|";

                else f[i][j] = " ";


                //Time to make our lowest row

                if (i == rall) f[i][j] = "-";
            }
        }
        return f;
    }


    //printing our game

    public static void printPattern(String[][] f) {

        for (int i = 0; i < f.length; i++) {

            for (int j = 0; j < f[i].length; j++) {

                System.out.print(f[i][j]);

            }

            System.out.println();

        }

    }


    //Here's are basic move, making the lowest empty row

    //of a specific column have a Red

    public static void dropRedPattern(String[][] f)
    {
        System.out.println("Drop a red disk at column (0– " + (call-1) + "): ");=
        Scanner scan = new Scanner(System.in);
        int c = 2 * scan.nextInt() + 1;
        for (int i = rall-1; i >= 0; i--)
        {
            if (f[i][c] == " ")
            {
                f[i][c] = "R";
                break;
            }
        }
    }


    //Same as the above step, just yellow

    public static void dropYellowPattern(String[][] f) {

        System.out.println("Drop a yellow disk at column (0–" +(call-1) + "): ");

        Scanner scan = new Scanner(System.in);

        int c = 2 * scan.nextInt() + 1;

        for (int i = rall-1; i >= 0; i--) {

            if (f[i][c] == " ")
            {
                f[i][c] = "Y";
                break;
            }
        }
    }

    //check winner
    public static String checkWinner(String[][] f,String player) {
        // Horizontal check
        for (int i = 0; i < rall; i++) {
            int count=0;
            for (int j = 0; j < call; j ++)
            {
                if (f[i][j*2+1].equals(player))
                    count++;
                else
                    count=0;

                if (count>=p)
                    return player;
            }


        }

        //Vertical Check
        for (int i = 0; i < call; i++) {
            int count=0;
            for (int j = 0; j < rall; j++) {
                if (f[j][i*2+1].equals(player))
                    count++;
                else
                    count = 0;

                if (count >= p)
                    return player;
            }
        }
        return null;
    }


    //Time to make a pattern

    public static void main(String[] args)
    {
        String[][] f = createPattern();
        //Time to make a condition for our game to keep on

        //playing

        boolean loop = true;

        //We need something to keep track of whose turn it is

        int count = 0;

        printPattern(f);

        while (loop) {

            if (count % 2 == 0) dropRedPattern(f);

            else dropYellowPattern(f);

            count++;
            printPattern(f);
            if (checkWinner(f,"R") != null)
            {
                if (checkWinner(f,"R") == "R")
                    System.out.println("The red player won.");
                else if (checkWinner(f,"Y") == "Y")
                    System.out.println("The yello player won.");
                loop = false;
            }
        }
    }
}
