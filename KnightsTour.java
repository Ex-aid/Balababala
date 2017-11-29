
/**
 * Write a description of class s here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class KnightsTour
{
    long startTime = System.currentTimeMillis();
    private int[][] board;
    private int row;
    private int col;
    public int count;
    public int counter;
    private static final int[] Horizontal = {1,2,2,1,-1,-2,-2,-1};
    private static final int[] Vertical = {-2,-1,1,2,2,1,-1,-2};
    public KnightsTour()
    {
        board = new int[8][8];
        row = 0;
        col = 0;
        count = 1;
        counter = 0;
        board[0][0] = 1;
    }

    public boolean testMove(int row, int col)
    {
        if (row < 8 && row >= 0 && col < 8 && col >= 0)
        {
            if (board[row][col] == 0)
            {
                return true;
            }
        }
        return false;
    }

    public boolean noMoreMoves()
    {
        for (int i = 0; i < 7; i++)
        {
            if (testMove(row + Horizontal[i], col + Vertical[i])) 
            {
                return false;
            }
        }
        return true;
    }

    public boolean maxMoves()
    {
        if (count < 64) 
        {
            return false;
        }
        else 
        {
            return true;
        }
    }

    public void reset()
    {
        board = new int[8][8];
        row = 0;
        col = 0;
        count = 1;
        counter++;
        board[0][0] = 1;
    }

    public void move()
    {
        int val = (int)(Math.random() * 8);
        int resultRow = row + Horizontal[val];
        int resultCol = col + Vertical[val];
        while (!testMove(resultRow,resultCol))
        {
            val = (int)(Math.random() * 8);
            resultRow = row - Horizontal[val];
            resultCol = col - Vertical[val];
        }
        row = resultRow;
        col = resultCol;
        count++;
        board[row][col] = count;
        while (!maxMoves() && noMoreMoves())
        {
            reset();
            move();
        }
    }

    public void print()
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                /*Formats the array to keep its shape with two-digit numbers.*/
                System.out.printf("%2d ",board[row][col]);
            }
            System.out.println();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(count + " squares were visited.");
        System.out.println("This program made " + counter + " attempts.");
        System.out.println("It took " + (endTime - startTime)/60000 + " minutes.");
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        KnightsTour knight = new KnightsTour();
        while (!knight.noMoreMoves()) knight.move();
        knight.print();
    }

}
