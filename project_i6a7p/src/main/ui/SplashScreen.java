package ui;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    private JPanel myPanel;

    // MODIFIES: panel
    // EFFECTS: creates a SplashScreen
    public SplashScreen() throws InterruptedException {
        //ImagePanel img = new ImagePanel("./Resource/Welcome.jpg");
        ImagePanel panel = new ImagePanel(
                new ImageIcon("./Resource/Welcome.jpg").getImage()
        );


        setContentPane(panel);
        setTitle("Calorie Smart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        Thread.sleep(5000);

        dispose(); //Destroy the JFrame object




    }

}

