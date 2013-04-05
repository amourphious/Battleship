/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;
import java.util.*;

/**
 *
 * @author ABC
 */
public class Gameboard {
    public Integer gameboard[][];
    public Integer row,col;
    public Integer horizontal[][];
    int hid ;
    Gameboard(){
        gameboard = new Integer[9][9];
        row = 9;
        col = 9;
        horizontal = new Integer[100][100];
        hid = 0;
    }
    Gameboard(int r, int c){
        gameboard = new Integer [r][c];
        row = r;
        col = c;
        horizontal = new Integer[100][100];
        hid = 0;
    }
    public void set_board(int size, int no){
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                gameboard[i][j]=0;
        
        ArrayList<Integer> rlst = new ArrayList<Integer>();
        ArrayList<Integer> clst = new ArrayList<Integer>();
        
        for(int r=0;r<=row-size;r++)
            rlst.add(r);
        for(int c=0;c<=col-size;c++)
            clst.add(c);
        
        Collections.shuffle(rlst);
        Collections.shuffle(clst);
        
        for(int n=0;n<no;n++){
            int r = rlst.get(n);
            int c = clst.get(n);
            
            if(n%2==0){
                for(int s=0;s<size;s++){
                    gameboard[r][c+s] = 1;
                    //if(not_in_array(r, c+s) == 1){
                        horizontal[n][2*s]=r;
                        horizontal[n][2*s+1]=c+s;
                    //}
                }
            }
            else{
                for(int s=0;s<size;s++){
                    gameboard[r+s][c] = 1;
                    //if(not_in_array(r+s, c) == 1){
                        horizontal[n][2*s] = r+s;
                        horizontal[n][2*s+1] = c;
                    //}
                }
            }
        }
    }
}
