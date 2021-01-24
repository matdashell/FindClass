package fbSelenium.frame;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.*;

public class PopUp {

    private static JFrame frame = null;

    public static JFrame getPopUpLabel(JLabel jLabel){

        jLabel.setPreferredSize(new Dimension(jLabel.getWidth()+25, jLabel.getHeight()+10));

        frame = new JFrame();

        if(jLabel.getHeight() < 600) {
            frame.setPreferredSize(jLabel.getPreferredSize());
            frame.add(jLabel);
        }else {
            addScrool(jLabel);
        }

        frame.setVisible(true);
        frame.setResizable(false);
        frame.repaint();
        frame.validate();
        frame.pack();
        frame.setLocationRelativeTo(null);

        return frame;
    }

    private static void addScrool(JLabel jLabel){
        JScrollPane scrollPane = new JScrollPane(jLabel);
        scrollPane.setPreferredSize(new Dimension(jLabel.getWidth()+25, 600));
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        frame.setPreferredSize(scrollPane.getPreferredSize());
        frame.add(scrollPane);
    }
}
