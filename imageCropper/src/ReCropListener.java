import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReCropListener implements ActionListener {
    private JButton button;
    private JFrame frame;

    public ReCropListener(JButton button, JFrame frame){
        this.button = button;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) { frame.dispose(); }
}
