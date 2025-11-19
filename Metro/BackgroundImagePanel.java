package Metro;

import javax.swing.*; // for accessing components like JPanel
import java.awt.*; // give access to graphics classes like Graphics and Images
import java.io.File; // access file
import java.io.IOException; // handles errors when reading image
import javax.imageio.ImageIO; // load image

public class BackgroundImagePanel extends JPanel { // inheritance 

    private Image backgroundImage;

    public BackgroundImagePanel(LayoutManager layout, String imagePath) {
        super(layout);
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}