package fbSelenium.frame;

import FindClass.Bots;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Thread.sleep;

public class TelaInicial {

    public static volatile boolean iniciado = false;

    private static final JFrame frame = new JFrame();

    public static JDialog infoTread;
    public static JDialog configThread;

    static final Font labelBotao = new Font("Arial", Font.ITALIC , 20);

    private static final TelaInfoThread panelInfoTread = new TelaInfoThread();
    private static final TelaConfig panelConfigThread = new TelaConfig();

    public TelaInicial(){

        configurarTela();
        setFrames();
    }

    private static void setFrames(){
        panelConfigThread.inciar();
        configThread = newPopUp(panelConfigThread.getPanel(), "Configurações", true);

        setBots();
        panelInfoTread.iniciar();
        infoTread = newPopUp(panelInfoTread.getPanel(), "Informações Thread", false);
    }

    private static void setBots(){
        while(!iniciado){}
        Bots.numeroDeExecucoes = Integer.parseInt(TelaConfig.execMinimas.getText().trim());
        Bots.numeroDeThreads = Integer.parseInt(TelaConfig.quantidade.getText().trim());
        Bots.listaPesquisa = TelaConfig.pesquisas.getText().split(",");
    }

    public static void configurarTela(){

        frame.setLayout(null);
        frame.setSize(800,500);
        frame.setTitle("BotProgram");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel botaoConfigTrue = new JLabel(new ImageIcon("C:\\RecursosPng\\simpleButtonTrue.png"));
        JLabel botaoConfigFalse = new JLabel(new ImageIcon("C:\\RecursosPng\\simpleButtonFalse.png"));
        JLabel configuracoes = new JLabel("Config.");
        JLabel iniciar = new JLabel("iniciar");
        JLabel SQL = new JLabel("SQL");

        configuracoes.setFont(labelBotao);
        iniciar.setFont(labelBotao);
        SQL.setFont(labelBotao);

        configuracoes.setBounds(75,253,200,30);
        iniciar.setBounds(325,253,200,30);
        SQL.setBounds(575,253,200,30);

        frame.add(configuracoes);
        frame.add(iniciar);
        frame.add(SQL);

        botaoConfigFalse.setBounds(50,240,200,60);
        botaoConfigTrue.setBounds(50,240,200,60);
        botaoConfigTrue.setVisible(false);
        frame.add(botaoConfigTrue);
        frame.add(botaoConfigFalse);

        botaoConfigFalse.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!iniciado) {
                    configThread.setVisible(!configThread.isVisible());
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
                if(!iniciado) {
                    botaoConfigTrue.setVisible(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoConfigTrue.setVisible(false);
            }
        });

        JLabel botaoStartTrue = new JLabel(new ImageIcon("C:\\RecursosPng\\simpleButtonTrue.png"));
        JLabel botaoStartFalse = new JLabel(new ImageIcon("C:\\RecursosPng\\simpleButtonFalse.png"));

        botaoStartFalse.setBounds(300,240,200,60);
        botaoStartTrue.setBounds(300,240,200,60);
        botaoStartTrue.setVisible(false);
        frame.add(botaoStartTrue);
        frame.add(botaoStartFalse);

        botaoStartFalse.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(panelConfigThread.permissao()) {
                    if(!iniciado) {
                        iniciado = true;
                        iniciar.setText("Informações");
                        try{sleep(1000);}catch (Exception ignore){}
                    }else {
                        infoTread.setVisible(!infoTread.isVisible());
                        infoTread.setLocationRelativeTo(null);
                    }
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
                botaoStartTrue.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoStartTrue.setVisible(false);
            }
        });

        JLabel botaoSQLTrue = new JLabel(new ImageIcon("C:\\RecursosPng\\simpleButtonTrue.png"));
        JLabel botaoSQLFalse = new JLabel(new ImageIcon("C:\\RecursosPng\\simpleButtonFalse.png"));

        botaoSQLFalse.setBounds(550,240,200,60);
        botaoSQLTrue.setBounds(550,240,200,60);
        botaoSQLTrue.setVisible(false);
        frame.add(botaoSQLTrue);
        frame.add(botaoSQLFalse);

        botaoSQLFalse.addMouseListener(new MouseListener() {
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
                botaoSQLTrue.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoSQLTrue.setVisible(false);
            }
        });

        JLabel planoFundo = new JLabel(new ImageIcon("C:\\RecursosPng\\planoInicial.jpg"));
        planoFundo.setBounds(0,0,815,600);
        frame.add(planoFundo);
        frame.validate();
        frame.repaint();
    }

    static JDialog newPopUp(JPanel jp ,String nomeTela, boolean visivel){
        JDialog popUp = new JDialog();
        popUp.setTitle(nomeTela);
        popUp.setLayout(null);
        popUp.setResizable(false);
        popUp.setPreferredSize(new Dimension(jp.getWidth()+15, jp.getHeight()+25));
        popUp.add(jp);
        popUp.pack();
        popUp.setVisible(visivel);
        popUp.setLocationRelativeTo(null);

        return popUp;
    }
}
