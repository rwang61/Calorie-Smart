package ui;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image img;


    // EFFECTS: creates a new Image Panel
    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: draws the image
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }

}
