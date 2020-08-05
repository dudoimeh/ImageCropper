import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ImageCropper {
    public static BufferedImage source;
    public static String fileName = "";
    public static ImageIcon image;
    public static void main(String[] args){
        //create the frame
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setTitle("Image Cropper");
        JPanel panel = new JPanel();
        JTextField textField = new JTextField();
        textField.setSize(40, 30);
        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // add action listener so when enter is pressed,
        // the current text is parsed as the file name
        JLabel label = new JLabel();
        textField.addActionListener(new GetImage(textField, label, panel, frame));

        // create a new window for the cropped image and commence with image cropping
        JFrame croppedImageFrame = new JFrame();
        label.addMouseListener(new CropImage(label, panel, frame, croppedImageFrame));
        croppedImageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public BufferedImage cropImage(BufferedImage source, int x1, int y1, int x2, int y2){
        int width = x2 - x1;
        int height = y2 - y1;
        BufferedImage croppedImage = source.getSubimage(x1, y1, width, height);
        return croppedImage;
    }

    public ImageCropper(){}
}
