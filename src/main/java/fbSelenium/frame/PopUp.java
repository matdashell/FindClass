package fbSelenium.frame;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.*;

public class PopUp {

    private static JFrame frame = null;

    public static void getPopUpPanel(JPanel jPanel){

        System.out.println(jPanel.getHeight());

        frame = new JFrame();

        if(jPanel.getHeight() < 600) {
            frame.setPreferredSize(jPanel.getPreferredSize());
            frame.add(jPanel);
        }else {
            addScrool(jPanel);
        }

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.repaint();
        frame.validate();
    }

    private static void addScrool(JPanel jPanel){
        JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setPreferredSize(new Dimension(jPanel.getWidth()+25, 600));
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        frame.setPreferredSize(scrollPane.getPreferredSize());
        frame.add(scrollPane);
    }
}
