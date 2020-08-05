import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SaveListener implements ActionListener {
    private JButton button;
    private JLabel label;
    private static int numCroppedImages;

    public SaveListener(JButton button, JLabel label) {
        this.button = button;
        this.label = label;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // method to get buffered image of a jlabel found on stack overflow:
        // https://stackoverflow.com/questions/15053214/converting-an-imageicon-to-a-bufferedimage
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, graphics, 0,0);
        graphics.dispose();

        // write the image to a file
        try {
            numCroppedImages = new File("images\\Cropped Pictures").listFiles().length;
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        numCroppedImages++;
        String fileName = "CI-" +String.valueOf(numCroppedImages); // CI for cropped images
        File outputFile = new File("images\\Cropped Pictures\\" +fileName +".jpg");
        try {
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
