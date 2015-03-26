/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xs_ans_os;

/**
 *
 * @author deborah
 */
import java.util.Scanner;

public class XnO {
    public static char [][] board=new char[3][3]; 
    public static int row, col; //used for storing user input
    public static Scanner scan=new Scanner(System.in);
    
    public static void main(String[] args) {
        int p1score=0, p2score=0;
        String stop="";
        do
        {
            setup();
            int turns=0;
            boolean p1turn=true;
            boolean gameWon=false;
            
            do{
                selectPosition();
                board[row][col]=(p1turn)?'x':'o';//take go
                turns++;
                gameWon=win();
                if(!gameWon){
                    p1turn=!p1turn;//change turn
                } 
            }while(!gameWon&&turns<9); //while no winner or no spaces left

            System.out.print(boardToString());
            if(gameWon)
            {
                if(p1turn)
                {
                    p1score++;
                }
                else{
                    p2score++;
                }
                System.out.println("player "+((p1turn)?"1":"2")+" wins");
            }
            else{
                System.out.println("Draw");
            }
            
            System.out.println("Player 1 score: "+p1score);
            System.out.println("Player 2 score: "+p2score);
            System.out.println("\nDo you want to play again?");
            stop=scan.next();
        }while(stop.charAt(0)=='y');
          
    }
    
    public static void selectPosition()
    {
        do{
            System.out.print(boardToString());   
            System.out.println("Enter the row and column of the square that you want to take");
            System.out.println("(This should be between 0 and 3 and not a square that is already taken)");
            row=scan.nextInt();
            col=scan.nextInt();
        }while((row<0||row>2||col<0||col>2)||board[row][col]!=' '); //keep asking for input while row or col is not between 0 and 8

    }
    public static boolean win(){
        //check rows
        for (int i = 0; i < board.length; i++) {
           if(board[i][0]!=' '&&board[i][0]==board[i][1]&&board[i][0]==board[i][2])
           {
               return true;
           }          
        }
        
        //check columns
        for (int i = 0; i < board.length; i++) {
           if(board[0][i]!=' '&&board[0][i]==board[1][i]&&board[1][i]==board[2][i])
           {
               return true;
           }          
        }
        
        //check angles
        if(board[0][0]!=' '&&board[0][0]==board[1][1]&&board[1][1]==board[2][2])
        {
            return true;
        } 
        
        if(board[0][2]!=' '&&board[0][2]==board[1][1]&&board[1][1]==board[2][0])
        {
            return true;
        } 
        
        return false;
              
    }
            
            
    public static void setup()
    {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j]=' ';          
            }       
        }
    }
    public static String boardToString() {
        String s="   0   1   2\n";
        for(int i=0;i<3;i++)
        {
            s+=i+" "+((i==2)?" ":"_");
            for(int j=0;j<3;j++){
                s+=board[i][j];
                if(i==2){
                    s+=(j==2)?" ":" | ";
                }
                else{
                    s+=(j==2)?"_":"_|_";
                }
            }
            s+="\n";
        }
        return s;     
    }
}
