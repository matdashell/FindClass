import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class frame extends JFrame {

    int cont = 1;

    frame(){
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        JButton button = new JButton("b");
        JScrollPane scrollBar = new JScrollPane();
        panel.setLayout(null);
        scrollBar.setLayout(null);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setBounds(0,0,600,600);
        button.setBounds(0,0,100,100);
        scrollBar.setBounds(0,0,500,500);

        panel.add(button);
        scrollBar.add(panel);
        add(scrollBar);

        button.addActionListener(b -> {
            JButton a = new JButton();
            a.setBounds(0,100*cont,100,100);
            cont++;
            panel.add(a);
            repaint();
            validate();
        });

        validate();
        repaint();
    }

    public static void main(String[] args) {
        new frame();
    }
}
