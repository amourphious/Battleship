/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author ABC
 */
public class Battleship {

    Gameboard g;
    JFrame mainframe;
    JPanel board , input_panel,status_panel;
    JButton hit;
    JLabel box[][],label_r[],label_c[],status,chances_left,statuscaught;
    JTextField rno,cno;
    int col,row,no,size,shipcaught,chances,ht;
    String message;
    ArrayList <Integer> caughtship;
    

    public Battleship() {
        g = new Gameboard();
        col=9;
        row=9;
        no=3;
        size=3;
        shipcaught=0;
        chances = 3*no*size;
        ht = 0;
        caughtship = new ArrayList<Integer>();
        mainframe = new JFrame("Battle Field ............................................ By amourphious");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new JPanel(new GridLayout(row+1, col+1,2,2));
        box = new JLabel[(row)][(col)];
        label_r = new JLabel[row];
        label_c = new JLabel[col+1];
        label_c[0]=new JLabel(" ");
        board.add(label_c[0]);
        for (int k=1;k<col+1;k++){
            label_c[k-1]=new JLabel(String.valueOf(k-1));
            board.add(label_c[k-1]);
        }
        for (int i=0 ; i < (row); i++){
            label_r[i]=new JLabel(String.valueOf(i));
            board.add(label_r[i]);
            for (int j=0;j<col;j++){
                box[i][j]=new JLabel("*");
                board.add(box[i][j]);
            }
        }
        mainframe.add(board,BorderLayout.CENTER);
        input_panel = new JPanel(new FlowLayout());
        rno = new JTextField("row",3);
        cno = new JTextField("col",3);
        hit = new JButton("Hit");
        hit.addActionListener(new HitPressed());
        input_panel.add(rno);
        input_panel.add(cno);
        input_panel.add(hit);
        mainframe.add(input_panel,BorderLayout.EAST);
        status = new JLabel("New Game");
        statuscaught = new JLabel("0 : Ship Hit");
        chances_left = new JLabel("chances left :" + String.valueOf(chances));
        status_panel = new JPanel(new GridLayout(2,1));
        status_panel.add(chances_left);
        status_panel.add(statuscaught);
        mainframe.add(status_panel,BorderLayout.WEST);
        mainframe.add(status,BorderLayout.NORTH);
        mainframe.setSize(500, 500);
        mainframe.setVisible(true);
    }
    public class HitPressed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            try {
                Integer x = Integer.parseInt(rno.getText());
                Integer y = Integer.parseInt(cno.getText());
                if (chances == 0 ){
                    status.setText("You Lose");
                    hit.disable();
                    JOptionPane.showMessageDialog(mainframe, "Sorry !!\n \tYou Lose !!");
                    System.exit(0);
                }
                if(x<row && y<col){
                    hit(x, y);
                    chances--;
                    chances_left.setText("chances left :" + String.valueOf(chances));
                }
                else
                    status.setText("Enter valid co-ordinates");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    } 
    
    public void go(){
        g.set_board(no, size);
        System.out.print(" ");
        for (int u=0;u<g.col;u++){
            System.out.print(u);
        }
        System.out.println(" ");    
        for(int i=0;i<g.row;i++){
            System.out.print(i);
            for(int j=0;j<g.col;j++){
                System.out.print(g.gameboard[i][j]);
            }
            System.out.println(" ");
        
        }
    }
    public void ship_caught(){
        int ship_part = 0;
        //int loop_count = 0;
        for(int i=0;i<no;i++){
            for(int j = 0 ; j < 2*size ; j+=2){
                if (g.gameboard[g.horizontal[i][j]][g.horizontal[i][j+1]] == -1){
                    ship_part++;
                    ht++;
                    if(ship_part == size && ! caughtship.contains(i)){
                        shipcaught++;
                        caughtship.add(i);
                        System.out.println(shipcaught + " : Ship Hit");
                        statuscaught.setText(shipcaught + " : Ship Hit");
                    }
                }
            }
            ship_part = 0;
        }
        if(shipcaught == no ){
            System.out.println("You Win !!!");
            status.setText("You win !!!");
            JOptionPane.showMessageDialog(mainframe, "Congtatulations !!\n \tYou Win !!");
            System.exit(0);
        }
        /*for(int i=0;i<g.hid;i+=2){
            if(g.gameboard[g.horizontal[i]][g.horizontal[i+1]] == -1){
                ship_part++;
                ht++;
                if(loop_count == size+1){
                    ship_part = 1;
                }
                if(ht == (shipcaught +1)*size && ship_part == size ){
                    ship_part=0;
                    shipcaught++;
                    System.out.println(shipcaught + " : Ship Hit");
                    status.setText("Ship Hit !!!");
                    if(shipcaught == no){
                        System.out.println("You Win !!!");
                        status.setText("You win !!!");
                        JOptionPane.showMessageDialog(mainframe, "Congtatulations !!\n \tYou Win !!");
                        System.exit(0);
                    }
                }
            }
            loop_count++;
        }*/
    }
    
    public void hit(int row, int col){
        System.out.println(row);
        System.out.println(col);
        if (g.gameboard[row][col] == 1 || g.gameboard[row][col] == -1){
            g.gameboard[row][col] = -1;
            box[row][col].setText("X");
            System.out.println("Part Hit !!");
            status.setText("Part Hit !!");
        }
        else{
            box[row][col].setText("O");
            status.setText("Missed !!");
        }
        ship_caught();
    }
    public static void main(String[] args) {
        Battleship b = new Battleship();
        b.go();
    }
}
