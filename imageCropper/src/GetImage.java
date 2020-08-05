import jdk.internal.jimage.ImageStrings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetImage implements ActionListener {
    private JTextField textField;
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    public GetImage(JTextField textField, JLabel label, JPanel panel, JFrame frame){
        this.textField = textField;
        this.label = label;
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // search for the file matching the name in the text field
            String fileName = textField.getText();
            BufferedImage source = ImageIO.read(new File("images\\" +fileName));
            int width = source.getWidth();
            int height = source.getHeight();
            // resizing the image so it fits in the frame
            while(width > 1200 || height > 1200){
                width /= 2;
                height /= 2;
            }
            // using scaled instance to resize the image was found on stack overflow:
            // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
            Image scaled = source.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(scaled);
            label.setIcon(image);
            panel.add(label, BorderLayout.CENTER);
            frame.add(panel, BorderLayout.NORTH);
            frame.setMinimumSize(new Dimension(width+20, height+60));
            frame.pack();
        } catch (FileNotFoundException exception){
            JOptionPane.showMessageDialog(frame, "error: file not found");
        } catch (Exception exception){
            exception.printStackTrace();
            JOptionPane.showMessageDialog(frame, "there was another error");
        }
    }
}
