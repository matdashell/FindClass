package fbSelenium.frame;

import javax.swing.*;
import java.awt.*;

public class TelaInfo {

    private static JFrame frame = null;
    private static final JPanel conerPanel = new JPanel();
    private static final JLabel imagem = new JLabel(new ImageIcon("C:\\RecursosPng\\Info.png"));

    TelaInfo() {
        configComponentes();
        configFrame();
        confirm();
    }

    public static JFrame getFrame(){
        return frame;
    }

    private static void configComponentes(){
        conerPanel.add(imagem);
    }

    private static void configFrame(){
        frame = new JFrame();
        JScrollPane scrollPane = new JScrollPane(conerPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        frame.add(scrollPane);
        frame.setPreferredSize(new Dimension(1100,600));
        frame.setVisible(true);
    }

    private static void confirm(){
        frame.pack();
        frame.validate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new TelaInfo();
    }
}