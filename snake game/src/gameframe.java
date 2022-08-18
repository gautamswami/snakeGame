import javax.swing.JFrame;

import javax.swing.JFrame;
public class gameframe extends JFrame {
    gameframe(){
         // gamepanel panel = new gamepanel();
         this.add(new gamepanel());
         this.setTitle("Snake");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setResizable(false);
         this.pack();
         this.setVisible(true);
        //  this.setLocation(500,300);
         this.setLocationRelativeTo(null);

    }
}
