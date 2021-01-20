package fbSelenium.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaConfig {

    private static boolean permissao = false;

    public static JTextField pesquisas = new JTextField();
    public static JTextField quantidade = new JTextField();
    public static JTextField execMinimas = new JTextField();
    private static final JPanel contentPanelConfig = new JPanel();

    private static final Font fonteLabel = new Font("FreeMono", Font.ITALIC | Font.BOLD , 14);
    static final Font labelBotao = new Font("Futura", Font.ITALIC | Font.BOLD, 20);

    TelaConfig(){


    }

    static void inciar(){

        pesquisas.setBounds(100,50,465,25);
        quantidade.setBounds(100,100,50,25);
        execMinimas.setBounds(100,150,50,25);

        contentPanelConfig.add(pesquisas);
        contentPanelConfig.add(quantidade);
        contentPanelConfig.add(execMinimas);

        JLabel textPesquisa = new JLabel("Pesquisas");
        JLabel textThreads = new JLabel("num. Núc.");
        JLabel textQuantidade = new JLabel("num. Pesq.");
        JLabel salvar = new JLabel("Salvar");

        textPesquisa.setFont(fonteLabel);
        textThreads.setFont(fonteLabel);
        textQuantidade.setFont(fonteLabel);
        salvar.setFont(labelBotao);

        JLabel botaoStartTrue = new JLabel(new ImageIcon("C:\\RecursosPng\\simpleButtonTrue.png"));
        JLabel botaoStartFalse = new JLabel(new ImageIcon("C:\\RecursosPng\\simpleButtonFalse.png"));
        JLabel warningThread = new JLabel(new ImageIcon("C:\\RecursosPng\\warning.png"));
        JLabel warningExecucoes = new JLabel(new ImageIcon("C:\\RecursosPng\\warning.png"));

        textPesquisa.setBounds(10,50,90,25);
        textThreads.setBounds(10,100,90,25);
        textQuantidade.setBounds(10,150,90,25);
        salvar.setBounds(400,122,200,60);

        botaoStartFalse.setBounds(375,122,200,60);
        botaoStartTrue.setBounds(375,122,200,60);
        warningThread.setBounds(175,90, 50, 40);
        warningExecucoes.setBounds(175,140,50,40);
        botaoStartTrue.setVisible(false);
        warningThread.setVisible(false);
        warningExecucoes.setVisible(false);

        contentPanelConfig.setLayout(null);
        contentPanelConfig.setBounds(0,0,600,200);

        contentPanelConfig.add(salvar);
        contentPanelConfig.add(botaoStartTrue);
        contentPanelConfig.add(botaoStartFalse);
        contentPanelConfig.add(warningThread);
        contentPanelConfig.add(warningExecucoes);
        contentPanelConfig.add(textPesquisa);
        contentPanelConfig.add(textThreads);
        contentPanelConfig.add(textQuantidade);

        botaoStartFalse.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                boolean acess = true;

                try {
                    Integer.parseInt(quantidade.getText().trim());
                    warningThread.setVisible(false);
                }catch (Exception exception){
                    warningThread.setVisible(true);
                    acess = false;
                }

                try {
                    Integer.parseInt(execMinimas.getText().trim());
                    warningExecucoes.setVisible(false);
                }catch (Exception exception){
                    warningExecucoes.setVisible(true);
                    acess = false;
                }

                if(pesquisas.getText().equals("")){
                    acess = false;
                }

                if(acess){
                    permissao = true;
                    TelaInicial.configThread.setVisible(false);
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
                if(!pesquisas.getText().equals("")) {
                    try {
                        Integer.parseInt(execMinimas.getText());
                        Integer.parseInt(quantidade.getText());
                        botaoStartTrue.setVisible(true);
                    } catch (Exception ignored) {
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoStartTrue.setVisible(false);
            }
        });
    }

    static boolean permissao(){
        return permissao;
    }

    static JPanel getPanel(){
        return contentPanelConfig;
    }
}
