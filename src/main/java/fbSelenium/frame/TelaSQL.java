package fbSelenium.frame;

import fbSelenium.code.SQL;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaSQL {

    private static SQL sql;

    private static JFrame frameSQL;

    private static final JLabel planoFundo = new JLabel(new ImageIcon("C:\\RecursosPng\\SQL.png"));
    private static final JLabel labelNumero = new JLabel("0");
    private static final JButton botatoRetroceder = new JButton("Voltar");
    private static final JButton botaoSalvar = new JButton("Salvar");
    private static final JButton botaoFiltrar = new JButton("Filtrar");
    private static final JTextField textFieldConter = new JTextField();
    private static final JTextField textFieldNaoConter = new JTextField();
    private static final JPanel contentPanelSQL = new JPanel(null);

    TelaSQL() {
        //initSQL();
        setBoundsComponentes();
        addToPanel();
        configurarTela();
        actionComponentes();
        confirm();
    }

    public static JFrame getFrame(){
        return frameSQL;
    }

    private static void initSQL() {
        try {
            sql = new SQL();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Necessário Iniciar program XAMPP");
            System.exit(0);
        }
    }

    private static void configurarTela(){
        frameSQL = new JFrame();
        frameSQL.setPreferredSize(new Dimension(610,320));
        frameSQL.setResizable(false);
        frameSQL.setTitle("SQL");
        frameSQL.add(contentPanelSQL);
        frameSQL.setVisible(true);
    }

    private static void setBoundsComponentes(){

        textFieldNaoConter.setBounds(170,30,397,30);
        textFieldConter.setBounds(170,108,397,30);
        botaoFiltrar.setBounds(450,250,115,32);
        botaoSalvar.setBounds(310,250,115,32);
        botatoRetroceder.setBounds(170,250,115,32);
        labelNumero.setBounds(70,240,100,50);
        planoFundo.setBounds(0,0,600,300);

    }

    private static void addToPanel(){
        contentPanelSQL.add(textFieldNaoConter);
        contentPanelSQL.add(textFieldConter);
        contentPanelSQL.add(botaoFiltrar);
        contentPanelSQL.add(botaoSalvar);
        contentPanelSQL.add(botatoRetroceder);
        contentPanelSQL.add(labelNumero);
        contentPanelSQL.add(planoFundo);
    }

    private static void setVisibilidade(){

    }

    private static void confirm(){
        frameSQL.validate();
        frameSQL.repaint();
        frameSQL.pack();
    }

    private static void actionComponentes(){

        botatoRetroceder.addActionListener(actin -> {

        });

        botaoSalvar.addActionListener(actin -> {

        });

        botaoFiltrar.addActionListener(actin -> {

        });
    }

    public static void main(String[] args) {
        new TelaSQL();
    }

}
