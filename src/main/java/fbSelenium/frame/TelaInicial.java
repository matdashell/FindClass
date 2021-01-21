package fbSelenium.frame;

import FindClass.Bots;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class TelaInicial {

     static TelaSQL telaSQL;
     static TelaInfoThread telaInfoThread;
     static TelaConfig telaConfig;
     static JFrame frameTelaInicial = new JFrame();
     static JPanel contentPanel = new JPanel(null);

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

     public static final JTextField pesquisas = new JTextField();

     private static final Font font = new Font("Cinzel", Font.ITALIC ,18);

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
        config();
        animacao();

     }

     private static void config(){
         telaInfoThread = new TelaInfoThread();
     }

     public static void end(){

     }

     private static void configDemaisTelas(){
        telaSQL = new TelaSQL();
        telaSQL.getFrame().setVisible(false);
        telaConfig = new TelaConfig();
        telaConfig.getFrame().setVisible(false);
     }

     private static void configTela(){
         contentPanel.setPreferredSize(new Dimension(1024,768));
         frameTelaInicial.setPreferredSize(contentPanel.getPreferredSize());
         frameTelaInicial.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         frameTelaInicial.setVisible(true);
         frameTelaInicial.setResizable(false);
         frameTelaInicial.add(contentPanel);
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
         pesquisas.setBounds(314,592,670,68);

         pesquisas.setFont(font);

         listenerIniciar.setBounds(2,3,521,198);
         listenerInfo.setBounds(523,3,260,201);
         listenerConfig.setBounds(783,3,226,200);
         listenerSQL.setBounds(11,490,272,266);
     }

     private static void addToPanel(){
         contentPanel.add(listenerIniciar);
         contentPanel.add(listenerInfo);
         contentPanel.add(listenerConfig);
         contentPanel.add(listenerSQL);
         contentPanel.add(botaoIniciarFalse);
         contentPanel.add(botaoInfoFalse);
         contentPanel.add(botaoConfigFalse);
         contentPanel.add(botaoSQLFalse);
         contentPanel.add(botaoIniciarTrue);
         contentPanel.add(botaoInfoTrue);
         contentPanel.add(botaoConfigTrue);
         contentPanel.add(botaoSQLTrue);
         contentPanel.add(postCertta);
         contentPanel.add(telaInfoThread.getPanel());
         contentPanel.add(pesquisas);
         contentPanel.add(telaInicial);
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

         pesquisas.setVisible(false);
     }

    private static void actionComponentes(){
        listenerIniciar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!pesquisas.getText().equals("")) {

                    int resposta = JOptionPane.showConfirmDialog(null,"Deseja Iniciar o programa? Certifique-se de ter configurado antes!");

                    if(resposta == 0) {
                        botaoIniciarTrue.setVisible(false);
                        telaSQL.getFrame().setVisible(false);
                        telaConfig.getFrame().setVisible(false);
                        iniciar = true;
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Erro: Necessário definir pesquisa.");
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
                    if(!pesquisas.getText().equals("")) {
                        botaoIniciarTrue.setVisible(true);
                    }else{
                        botaoIniciarFalse.setVisible(true);
                    }
                }else{
                    botaoIniciarFalse.setVisible(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!iniciar) {
                    if(!pesquisas.getText().equals("")) {
                        botaoIniciarTrue.setVisible(false);
                    }else{
                        botaoIniciarFalse.setVisible(false);
                    }
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
                    telaConfig.getFrame().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Só é possível configurar antes de iniciar o programa.");
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
                telaSQL.getFrame().setVisible(true);
                telaSQL.getFrame().setLocationRelativeTo(null);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botaoSQLTrue.setVisible(true);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoSQLTrue.setVisible(false);

            }
        });
    }

    public static void main(String[] args) {
        new TelaInicial();
    }
}
