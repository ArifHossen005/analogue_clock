package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Display  {

    private String title;
    private int size;
    private JFrame frame;
    public static  Canvas canvas;
    public JButton btn;
    public JButton btn2;
    public JButton btn3;
    public JPanel pan;
    public Random rand;
    
    public Display(String title,int size) {
        this.title=title;
        this.size=size;
        createDisplay();
    }

    public void createDisplay() {
        rand = new Random();
        
        frame = new JFrame(title);
        frame.setSize(size,size+100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        pan = new JPanel();
        pan.setLayout(null);
        pan.setSize(new Dimension(size, size+100));
        
        btn = new JButton("Change Bacaground");
        btn2 = new JButton("Change Clock color");
        btn3 = new JButton("Change Font");
        
        
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(size,size));
        canvas.setBounds(0, 0, size, size);
        
        //canvas.setBackground(new Color (221,120,122));//background color
        
        btn.setBounds(10,520,150,30);
        btn2.setBounds(165,520,150,30);
        btn3.setBounds(320,520,150,30);
        
        btn.setBackground(Color.white);
        btn2.setBackground(Color.white);
        btn3.setBackground(Color.white);
        
        
        btn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                
                int a,b,c;
                
                a = rand.nextInt(255);
                b = rand.nextInt(255);
                c = rand.nextInt(255);
                
                pan.setBackground(new Color (a,b,c));//background color
                
            }  
        }); 
         
        
        
        
        pan.add(canvas);
        pan.add(btn);  
        pan.add(btn2);  
        pan.add(btn3);  
        frame.add(pan);
        //frame.pack();
        
    }
}
