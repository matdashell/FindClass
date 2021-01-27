package fbSelenium.frame;

import javax.swing.*;
import java.awt.*;

public class TelaComentarios {

    private static JFrame frame;
    public static JTextPane jtextPane = new JTextPane();

    TelaComentarios() {
        confi();
    }

    static void confi(){
        frame = new JFrame();
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(1280,720));
        frame.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(jtextPane);

        frame.add(scrollPane);
        frame.repaint();
        frame.validate();
        frame.pack();
    }

    static JFrame getFrame(){
        return frame;
    }

    public static void main(String[] args) {
        new TelaComentarios();
    }
}
