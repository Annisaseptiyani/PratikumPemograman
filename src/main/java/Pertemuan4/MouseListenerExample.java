package Pertemuan4;

import javax.swing.*;
import java.awt.event.*;

public class MouseListenerExample {
    public static void main(String[]args){
        //membuat frame
        JFrame frame = new JFrame("MouseListener Example");
        
        //Membuat label untuk menamilkan pesan
        JLabel label = new JLabel("Arahkan dan klik mouse pada area ini.");
        label.setBounds(50,50,300,30);
        
        //Menambahkan MouseListener ke label
        label.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e){
                label.setText("Mouse Clicked at (" + e.getX() +","+ e.getY() + ")");
            }

            //Dijalankan ketika Mouse ditekan (tanpa dilepas)
            public void mousePressed(MouseEvent e){
                label.setText("Mouse Pressed at:(" + e.getX() + "," + e.getY()+ ")");
            }

            //Dijalankan ketika Mouse dilepas setelah ditekan
            public void mouseReleased(MouseEvent e){
                label.setText("Mouse Released at: ("+ e.getX() + "," + e.getY() + ")");
            }

            //Dijalankan ketika mouse masuk ke arah area komponen
            public void mouseEntered(MouseEvent e){
                label.setText("Mouse Entered the area.");
            }

            //Dijalankan ketika mouse keluar dari area komponen
            public void mouseExited(MouseEvent e){
                label.setText("Mouse Exited the area.");
            }
        });

        //Menambah label ke frame
        frame.add(label);
        
        //Setting frame
        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
