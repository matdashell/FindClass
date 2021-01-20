package fbSelenium.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Thread.sleep;

public class TelaInicial {

     static TelaSQL telaSQL;
     static TelaInfoThread telaInfoThread;
     static JFrame frameTelaInicial = new JFrame();
     static JPanel contenPanel = new JPanel(null);

     private static final JLabel botaoIniciarTrue = new JLabel(new ImageIcon("C:\\RecursosPng\\IniciarTrue.png"));
     private static final JLabel botaoIniciarFalse = new JLabel(new ImageIcon("C:\\RecursosPng\\IniciarFalse.png"));
     private static final JLabel botaoInfoTrue = new JLabel(new ImageIcon("C:\\RecursosPng\\InfoTrue.png"));
     private static final JLabel botaoInfoFalse = new JLabel(new ImageIcon("C:\\RecursosPng\\InfoFalse.png"));
     private static final JLabel botaoConfigTrue = new JLabel(new ImageIcon("C:\\RecursosPng\\ConfigTrue.png"));
     private static final JLabel botaoConfigFalse = new JLabel(new ImageIcon("C:\\RecursosPng\\ConfigFalse.png"));
     private static final JLabel botaoSQLTrue = new JLabel(new ImageIcon("C:\\RecursosPng\\SQLTrue.png"));
     private static final JLabel botaoSQLFalse = new JLabel(new ImageIcon("C:\\RecursosPng\\SQLFalse.png"));
     private static final JLabel postCertta = new JLabel(new ImageIcon("C:\\RecursosPng\\logoCertta.png"));
     private static final JLabel telaInicial = new JLabel(new ImageIcon("C:\\RecursosPng\\TelaInicial.png"));

     private static final JLabel listenerIniciar = new JLabel();
     private static final JLabel listenerInfo = new JLabel();
     private static final JLabel listenerConfig = new JLabel();
     private static final JLabel listenerSQL = new JLabel();

     static volatile boolean iniciar = false;

     public TelaInicial(){
        configDemaisTelas();
        setBoundsComponentes();
        visibilidade();
        addToPanel();
        configTela();
        actionComponentes();
        confirm();
        while(!iniciar){}
        animacao();

     }

     private static void configDemaisTelas(){
        telaSQL = new TelaSQL();
        telaInfoThread = new TelaInfoThread();
        telaSQL.getFrame().setVisible(false);
     }

     private static void configTela(){
         contenPanel.setPreferredSize(new Dimension(1024,768));
         frameTelaInicial.setPreferredSize(contenPanel.getPreferredSize());
         frameTelaInicial.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         frameTelaInicial.setVisible(true);
         frameTelaInicial.setResizable(false);
         frameTelaInicial.add(contenPanel);
     }

     private static void setBoundsComponentes(){
         botaoIniciarTrue.setBounds(2,3,521,198);
         botaoIniciarFalse.setBounds(2,3,521,198);
         botaoInfoTrue.setBounds(523,3,260,201);
         botaoInfoFalse.setBounds(523,3,260,201);
         botaoConfigTrue.setBounds(783,3,226,200);
         botaoConfigFalse.setBounds(783,3,226,200);
         botaoSQLTrue.setBounds(11,490,272,266);
         botaoSQLFalse.setBounds(11,490,272,266);
         postCertta.setBounds(20,198,980,300);
         telaInicial.setBounds(0,0,1024,768);

         listenerIniciar.setBounds(2,3,521,198);
         listenerInfo.setBounds(523,3,260,201);
         listenerConfig.setBounds(783,3,226,200);
         listenerSQL.setBounds(11,490,272,266);
     }

     private static void addToPanel(){
         contenPanel.add(listenerIniciar);
         contenPanel.add(listenerInfo);
         contenPanel.add(listenerConfig);
         contenPanel.add(listenerSQL);
         contenPanel.add(botaoIniciarFalse);
         contenPanel.add(botaoInfoFalse);
         contenPanel.add(botaoConfigFalse);
         contenPanel.add(botaoSQLFalse);
         contenPanel.add(botaoIniciarTrue);
         contenPanel.add(botaoInfoTrue);
         contenPanel.add(botaoConfigTrue);
         contenPanel.add(botaoSQLTrue);
         contenPanel.add(postCertta);
         contenPanel.add(telaInfoThread.getPanel());
         contenPanel.add(telaInicial);
     }

     private static void visibilidade(){
         botaoIniciarFalse.setVisible(false);
         botaoInfoFalse.setVisible(false);
         botaoConfigFalse.setVisible(false);
         botaoSQLFalse.setVisible(false);
         botaoIniciarTrue.setVisible(false);
         botaoInfoTrue.setVisible(false);
         botaoConfigTrue.setVisible(false);
         botaoSQLTrue.setVisible(false);
     }

     private static void confirm(){
         frameTelaInicial.repaint();
         frameTelaInicial.validate();
         frameTelaInicial.pack();
     }

     private static void animacao(){
         while(postCertta.getLocation().getX() < 1024) {
             try{sleep(3);}catch (Exception ignored) { }
             postCertta.setLocation((int)(postCertta.getLocation().getX()+1), (int)(postCertta.getLocation().getY()));
         }
         postCertta.setLocation(1024,475);

         while(postCertta.getLocation().getX() > 283){
             try{sleep(3);}catch (Exception ignored) { }
             postCertta.setLocation((int)(postCertta.getLocation().getX()-1), (int)(postCertta.getLocation().getY()));
         }
     }

    private static void actionComponentes(){
        listenerIniciar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iniciar = true;
                botaoIniciarTrue.setVisible(false);
                telaSQL.getFrame().setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!iniciar) {
                    botaoIniciarTrue.setVisible(true);
                }else{
                    botaoIniciarFalse.setVisible(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!iniciar) {
                    botaoIniciarTrue.setVisible(false);
                }else{
                    botaoIniciarFalse.setVisible(false);
                }
            }
        });

        listenerInfo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoInfoTrue.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoInfoTrue.setVisible(false);
            }
        });

        listenerConfig.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(botaoConfigTrue.isVisible()){

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!iniciar) {
                    botaoConfigTrue.setVisible(true);
                }else{
                    botaoConfigFalse.setVisible(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!iniciar) {
                    botaoConfigTrue.setVisible(false);
                }else{
                    botaoConfigFalse.setVisible(false);
                }
            }
        });

        listenerSQL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(botaoSQLTrue.isVisible()){
                    telaSQL.getFrame().setVisible(true);
                    telaSQL.getFrame().setLocationRelativeTo(null);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!iniciar) {
                    botaoSQLTrue.setVisible(true);
                }else {
                    botaoSQLFalse.setVisible(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!iniciar) {
                    botaoSQLTrue.setVisible(false);
                }else{
                    botaoSQLFalse.setVisible(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        new TelaInicial();
    }
}
