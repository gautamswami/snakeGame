import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.util.Random;
import java.util.function.BiConsumer;
import java.awt.event.*;
import java.util.PropertyResourceBundle;

public class gamepanel extends JPanel implements ActionListener {
    static final int screen_width = 600;
    static final int screen_height = 600;
    static final int unit_size = 25;
    static final int game_units = (screen_width*screen_height)/unit_size;
    static final int delay = 75;
  int x[]= new int[game_units];
    int y[]= new int[game_units];
    int bodyparts = 6;
    int appleseaten;
    int appleX;
    int appleY;
    char direction = 'r';
    boolean running =false;
    Timer timer;
    Random random;
    gamepanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(screen_width,screen_height));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startgame();
       
    }
    public void startgame(){
        newApple();
        running = true;
        timer = new Timer(delay,this);
        timer.start();
    }
    public void restartgame(){
        running = true;
        appleseaten=0;
        bodyparts=2;
        
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, unit_size, unit_size);
        // for(int i = 0;i<screen_height/unit_size;i++){
        //     g.setColor(Color.black);
        //     g.drawLine(i*unit_size, 0, i*unit_size, screen_height);
        //     g.drawLine(0, i*unit_size,screen_width ,i*unit_size );    
        // }
        for(int i=0;i<bodyparts;i++){
            if(i==0){
                g.setColor(Color.green);

                g.fillRect(x[i], y[i], unit_size, unit_size);
            }
            else{
                g.setColor(Color.pink);
                g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));  
                g.fillRect(x[i], y[i], unit_size, unit_size);

            }
        }
                g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,15));
        FontMetrics metrics = getFontMetrics(g.getFont());
       
        g.drawString("Your Score : " + appleseaten,(screen_width - metrics.stringWidth("Your Score : " + appleseaten))/2,g.getFont().getSize());
    }
    else{
        gameover(g);
    }

    }
    public void newApple(){
        appleX = random.nextInt((int)(screen_width/unit_size))*unit_size;
        appleY = random.nextInt((int)(screen_height/unit_size))*unit_size;
    }
    public void move(){
        for(int i = bodyparts;i>0;i--){ 
            x[i]=x[i-1];
            y[i]=y[i-1];
            } 
            switch (direction){
                case 'u': y[0] = y[0] - unit_size;
                break;
                case 'd': y[0] = y[0] + unit_size;
                break;
                case 'l': x[0] = x[0] - unit_size;
                break;
                case 'r': x[0] = x[0] + unit_size;
                break;
            }
            
    }
    public void checkApple(){
        if((x[0]==appleX)&&(y[0]==appleY)){
            bodyparts++;
            appleseaten++;
            newApple();
        }

    }
    public void checkCollisions() {
        //checks body-head collision
        for(int i = bodyparts;i>0;i--){
            if((x[0]==x[i])&& (y[0]==y[i])){
                running = false;
            }
        }
        //checks boundary collision
        if(x[0]<0){
            running = false;
        }
        if(x[0]>screen_width){
            running = false;
        }
        if(y[0]<0){
            running = false;
        }
        if(y[0]>screen_height){
            running = false;
        }
    }
    public void gameover(Graphics g) {

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over",(screen_width - metrics.stringWidth("Game Over"))/2,(screen_height)/2);
        g.drawString("Your Score : " + appleseaten,(screen_width - metrics.stringWidth("Your Score : " + appleseaten))/2,g.getFont().getSize());


    }
    @Override
    public void actionPerformed(ActionEvent e) {
            if (running){
                move();
                checkApple();
                checkCollisions();
                }
             
                    repaint();
        
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
       
 public void keyPressed(KeyEvent k){
     
            switch(k.getKeyCode()){
                case KeyEvent.VK_LEFT:
                if(direction != 'r'){
                    direction = 'l';
                }
                break; case KeyEvent.VK_RIGHT:
                if(direction != 'l'){
                    direction = 'r';
                }
                break;
                case KeyEvent.VK_UP:
                if(direction != 'd'){
                    direction = 'u';
                }
                break;  case KeyEvent.VK_DOWN:
                if(direction != 'u'){
                    direction = 'd';
                }
                break;
               case KeyEvent.VK_R:
                    restartgame();
                    break;
                    
            }
        }

        }

    }
    

