import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.security.spec.ECField;
import java.util.ArrayList;

public class CropImage extends MouseAdapter {
    private JLabel label;
    private JPanel panel;
    private JFrame ogFrame, croppedImageFrame;
    private ArrayList<MouseEvent> clicks = new ArrayList<>();
    public CropImage(JLabel label, JPanel panel, JFrame ogFrame,
                     JFrame croppedImageFrame){
        this.label = label;
        this.panel = panel;
        this.ogFrame = ogFrame;
        this.croppedImageFrame = croppedImageFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try{
            clicks.add(e);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        if(clicks.size() % 2 == 0){
            croppedImageFrame = new JFrame();
            int x1 = clicks.get(clicks.size() - 2).getX();
            int y1 = clicks.get(clicks.size() - 2).getY();
            int x2 = clicks.get(clicks.size() - 1).getX();
            int y2 = clicks.get(clicks.size() - 1).getY();

            // method to get buffered image of a jlabel found on stack overflow:
            // https://stackoverflow.com/questions/15053214/converting-an-imageicon-to-a-bufferedimage
            Icon icon = label.getIcon();
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.createGraphics();
            // paint the Icon to the BufferedImage.
            icon.paintIcon(null, graphics, 0,0);
            graphics.dispose();

            // crop the image and launch the new window to view the new image
            BufferedImage croppedImage = null;
            try {
                croppedImage = new ImageCropper().cropImage(image, x1, y1, x2, y2);
            } catch (Exception exception){
                JOptionPane.showMessageDialog(ogFrame,
                        "the second point must be lower and further right than " +
                                "the first point");
            }
            JLabel croppedImageLabel = new JLabel();
            croppedImageLabel.setIcon(new ImageIcon(croppedImage));
            croppedImageFrame.add(croppedImageLabel, BorderLayout.NORTH);
            JButton saveButton = new JButton("save");
            JPanel savePanel = new JPanel();
            saveButton.addActionListener(new SaveListener(saveButton, croppedImageLabel));
            savePanel.add(saveButton, BorderLayout.SOUTH);
            JButton reCropButton = new JButton("re-crop");
            reCropButton.addActionListener(new ReCropListener(reCropButton, croppedImageFrame));
            savePanel.add(reCropButton, BorderLayout.NORTH);
            croppedImageFrame.add(savePanel, BorderLayout.SOUTH);
            croppedImageFrame.pack();
            croppedImageFrame.setVisible(true);
            clicks = new ArrayList<>();
        }
    }
}
