package fbSelenium.frame;

import FindClass.Bots;
import FindClass.Find;
import org.openqa.selenium.Keys;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelaComentarios {

    private static JFrame frame;

    public TelaComentarios() {
        destruirFrame();
        config();
    }

    private static void destruirFrame(){
        if(frame != null) {
            frame.setVisible(false);
            frame.removeAll();
            frame.validate();
            frame = null;
        }
    }

    private static void config(){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1050,700);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.repaint();
        frame.validate();
    }

    public static void configComentarios(ResultSet resultSet){

        try {

            List<JPanel> jPanelList = new ArrayList<JPanel>();
            JPanel contentPanel = new JPanel(null);

            while (resultSet.next()) {

                JButton page = new JButton("Perfil");
                JLabel nome = new JLabel("Nome: "+resultSet.getString("nome"));
                JLabel url = new JLabel(resultSet.getString("url"));
                JLabel dias = new JLabel("Dias: "+resultSet.getString("dias"));
                JLabel fundo = new JLabel(new ImageIcon("C:\\RecursosPng\\FundoComent.png"));

                page.setBounds(916,23,90,25);
                nome.setBounds(10,10,500,50);
                url.setBounds(520,140,490,50);
                dias.setBounds(20,140,500,50);
                fundo.setBounds(0,0,1024,200);

                page.addActionListener(action -> {
                    try {
                        Find find = new Find();
                        find.init(true);
                        find.page(url.getText());
                    }catch (Exception ignore){ }

                });

                jPanelList.add(new JPanel(null));

                String comentario = resultSet.getString("comentario");

                if(comentario.length() > 149) {
                    int vezes = 0;
                    while (comentario.length() > 149) {
                        if(vezes > 4){ break; }
                        JLabel label = new JLabel();

                        if(comentario.length() > 149*(vezes+1)){
                            label.setText(comentario.substring(149*vezes, 149*(vezes+1)));
                        }else {
                            label.setText(comentario.substring(149*vezes, comentario.length()));
                        }

                        label.setBounds(17,60+(20*vezes),1000,25);
                        jPanelList.get(jPanelList.size() - 1).add(label);
                        comentario = comentario.substring(149*vezes, comentario.length());
                        vezes++;
                    }
                }else {
                    JLabel label = new JLabel(comentario);
                    label.setBounds(17,60,1000,25);
                    jPanelList.get(jPanelList.size() - 1).add(label);
                }

                jPanelList.get(jPanelList.size() - 1).add(page);
                jPanelList.get(jPanelList.size() - 1).add(nome);
                jPanelList.get(jPanelList.size() - 1).add(url);
                jPanelList.get(jPanelList.size() - 1).add(dias);
                jPanelList.get(jPanelList.size() - 1).add(fundo);

                jPanelList.get(jPanelList.size() - 1).setBounds(0,180*(jPanelList.size() - 1),1024,180);

                contentPanel.add(jPanelList.get(jPanelList.size() - 1));
            }

            contentPanel.setPreferredSize(new Dimension(1024,180* jPanelList.size()));
            JScrollPane scrollPane = new JScrollPane(contentPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            scrollPane.setBounds(0,0,1030,680);

            frame.add(scrollPane);
            frame.repaint();
            frame.validate();

        }catch (SQLException ignored) {

        }

    }
}
