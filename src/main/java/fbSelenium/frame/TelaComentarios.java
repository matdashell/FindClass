package fbSelenium.frame;

import FindClass.Find;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelaComentarios {

    private static boolean iniciado;
    private static JFrame frame;
    private static JPanel contetPanel = new JPanel(null);
    public static List<JPanel> panelsComents = new ArrayList<JPanel>();

    TelaComentarios() {
        confi();
    }

    static void confi(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(1050,720));
        frame.setVisible(false);
        frame.repaint();
        frame.validate();
        frame.pack();
    }

    public static void gerarComentarios(ResultSet rs){
        try {
             while(rs.next()){

                 String nome = rs.getString("nome");
                 String comentario = rs.getString("comentario");
                 String url = rs.getString("url");
                 int dias = rs.getInt("dias");

                 JButton getURL = new JButton("Perfil");
                 JLabel labelNome = new JLabel("Usuário: "+nome);
                 JLabel labelUrl = new JLabel("URL: "+url);
                 JLabel labelDias = new JLabel(String.valueOf("Dias: "+dias));
                 JLabel labelFundo = new JLabel(new ImageIcon("C:\\RecursosPng\\FundoComent.png"));
                 JTextPane textPaneComentario = new JTextPane(); textPaneComentario.setText(comentario);
                 JScrollPane scrollPaneComentario = new JScrollPane(textPaneComentario);

                 getURL.setBounds(916,23,90,25);
                 labelNome.setBounds(10,10,500,50);
                 scrollPaneComentario.setBounds(17,60,990,80);
                 labelUrl.setBounds(520,140,490,50);
                 labelDias.setBounds(20,140,500,50);
                 labelFundo.setBounds(0,0,1024,200);

                 getURL.addActionListener(action -> {
                     Find f = new Find();
                     try {
                         f.init(true);
                         f.page(url);
                     } catch (AWTException ignore) { }
                 });

                 panelsComents.add(new JPanel(null));
                 panelsComents.get(panelsComents.size() - 1).add(labelNome);
                 panelsComents.get(panelsComents.size() - 1).add(getURL);
                 panelsComents.get(panelsComents.size() - 1).add(scrollPaneComentario);
                 panelsComents.get(panelsComents.size() - 1).add(labelUrl);
                 panelsComents.get(panelsComents.size() - 1).add(labelDias);
                 panelsComents.get(panelsComents.size() - 1).add(labelFundo);

                 panelsComents.get(panelsComents.size() - 1).setBounds(0,(panelsComents.size()-1)*200,1024,200);
             }

             for(JPanel panel : panelsComents){
                 contetPanel.add(panel);
             }

             contetPanel.setPreferredSize(new Dimension(1024, panelsComents.size()*210));

             if(!iniciado) {
                 JScrollPane jScrollPane = new JScrollPane(contetPanel);
                 jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
                 jScrollPane.setBounds(0, 0, 1030, 758);
                 frame.add(jScrollPane);
                 iniciado = true;
             }

             frame.validate();
             frame.repaint();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void removeComponents(){

        panelsComents = null;
        panelsComents = new ArrayList<>();
        contetPanel = null;
        contetPanel = new JPanel();

    }

    static JFrame getFrame(){
        return frame;
    }
}
