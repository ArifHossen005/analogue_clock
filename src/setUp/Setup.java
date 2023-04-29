package setUp;

import display.Display;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Setup implements Runnable {

    private Display display;
    private String title;
    private int size;
    private Thread thread;
    private BufferStrategy buffer;
    private Graphics2D g;
    int RED = 254, GREEN = 254, BLUE = 254;
    int inx = 0;
    String fontList[];
    String fontName = "arial";
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    
    public Setup(String title, int size) {
        this.title = title;
        this.size = size;
        fontList = ge.getAvailableFontFamilyNames();

    }
    


    
    public void init() {
        display = new Display(title, size);
        display.btn2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                               
                Random rand = new Random();
                RED = rand.nextInt(255);
                GREEN = rand.nextInt(255);
                BLUE = rand.nextInt(255);         
            }  
        });
        
        display.btn3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                if(inx >= fontList.length) inx = 0;
                fontName = fontList[inx];
                inx++;
                System.out.println(fontName);
            }  
        });
    }

    public void draw() {
        buffer = display.canvas.getBufferStrategy();
        if (buffer == null) {
            display.canvas.createBufferStrategy(3);
            return;

        }
        int center = size / 2;
        
        g = (Graphics2D) buffer.getDrawGraphics();
        g.clearRect(0, 0, size, size);
        
        //draw
        g.setColor(Color.black);
        g.fillOval(10, 10, size - 20, size - 20);
        g.setColor(new Color(RED,GREEN,BLUE));   
        
        g.fillOval(20, 20, size - 40, size - 40);
        
        //  Draw numbers 
        int angleX, angleY;
        int radius;
        double position;
        double time = System.currentTimeMillis();
        
        for (int i = 1; i <= 12; i++) {
            position = i / 12.0 * Math.PI * 2;
            radius = center - 65;
            angleX = (int) ((center) + (Math.sin(position) * radius));
            angleY = (int) ((center) - (Math.cos(position) * radius));
            g.setColor(Color.black);
            g.setFont(new Font(fontName, Font.BOLD, 33));
            String a = Integer.toString(i);
            g.drawString(a, angleX, angleY);

        }
        //  draw line in sec
        for (int i = 1; i <= 60; i++) {

            position = i / 60.0 * Math.PI * 2;
            radius = center - 20;

            angleX = (int) ((center) + (Math.sin(position) * radius));
            angleY = (int) ((center) - (Math.cos(position) * radius));
         
            radius = center - 45;
            int angleW = (int) ((center) + (Math.sin(position) * radius));
            int angleZ = (int) ((center) - (Math.cos(position) * radius));
            g.drawLine(angleW, angleZ, angleX, angleY);
  

        }
        
        //hour hand
        radius = center - 120;
        
        time = (System.currentTimeMillis()+21600000) / (60.0 * 12 * 60 * 1000.0) * Math.PI * 2;             
        
        angleX = (int) ((center) + (Math.sin(time) * radius));
        angleY = (int) ((center) - (Math.cos(time) * radius));
        g.setColor(Color.YELLOW);
        g.setStroke(new BasicStroke(12));
        g.drawLine(center, center, angleX, angleY);

        //minute hand
        radius = center - 90;
        time = System.currentTimeMillis() / (60.0 * 60 * 1000.0) * Math.PI * 2;
        angleX = (int) ((center) + (Math.sin(time) * radius));
        angleY = (int) ((center) - (Math.cos(time) * radius));
        g.setColor(Color.GREEN);
        g.setStroke(new BasicStroke(6));
        g.drawLine(center, center, angleX, angleY);
        // second hand
        radius = center - 50;
        time = System.currentTimeMillis() / (60.0 * 1000.0) * Math.PI * 2;
        angleX = (int) ((center) + (Math.sin(time) * radius));
        angleY = (int) ((center) - (Math.cos(time) * radius));
        g.setColor(Color.MAGENTA);
        g.drawLine(center, center, angleX, angleY);

        //center oval
        g.setColor(Color.BLACK);
        g.fillOval(center - 10, center - 10, 20, 20);

        //end 
        buffer.show();
        g.dispose();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        init();
        while (true) {
            draw();
        }
    }
}
