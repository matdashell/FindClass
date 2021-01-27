package fbSelenium.frame;

import fbSelenium.code.SQL;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaSQL {

    private static SQL sql;

    private static JFrame frameSQL;

    public static final JLabel labelNumero = new JLabel("0");
    private static final JLabel planoFundo = new JLabel(new ImageIcon("C:\\RecursosPng\\SQL.png"));
    private static final JButton botaoSalvar = new JButton("Exibir");
    private static final JButton botaoFiltrar = new JButton("Filtrar");
    private static final JTextField textFieldConter = new JTextField();
    private static final JTextField textFieldNaoConter = new JTextField();
    private static final JPanel contentPanelSQL = new JPanel(null);

    TelaSQL() {
        initSQL();
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
        } catch (SQLException ignored) {
        }
    }

    private static void configurarTela(){
        frameSQL = new JFrame();
        frameSQL.setVisible(false);
        frameSQL.setPreferredSize(new Dimension(610,340));
        frameSQL.setResizable(false);
        frameSQL.setTitle("SQL");
        frameSQL.add(contentPanelSQL);
    }

    private static void setBoundsComponentes(){

        textFieldNaoConter.setBounds(170,30,397,30);
        textFieldConter.setBounds(170,108,397,30);
        botaoFiltrar.setBounds(450,250,115,32);
        botaoSalvar.setBounds(310,250,115,32);
        labelNumero.setBounds(20,240,120,50);
        planoFundo.setBounds(0,0,600,300);

    }

    private static void addToPanel(){
        contentPanelSQL.add(textFieldNaoConter);
        contentPanelSQL.add(textFieldConter);
        contentPanelSQL.add(botaoFiltrar);
        contentPanelSQL.add(botaoSalvar);
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

        botaoSalvar.addActionListener(actin -> {
            if(!textFieldConter.getText().equals("") || !textFieldNaoConter.getText().equals("")) {
                try {
                    setAllDisable();
                    sql.filter(textFieldConter.getText(), textFieldNaoConter.getText(), true);
                    TelaComentarios.getFrame().setLocationRelativeTo(null);
                    TelaComentarios.getFrame().setVisible(true);
                    setAllEnable();
                } catch (SQLException ignore) { }
            }else{
                JOptionPane.showMessageDialog(null, "Erro: Sem filtro definido");
            }
        });

        botaoFiltrar.addActionListener(actin -> {
            if(!textFieldConter.getText().equals("") || !textFieldNaoConter.getText().equals("")) {
                try {
                    setAllDisable();
                    sql.filter(textFieldConter.getText(), textFieldNaoConter.getText(), false);
                    setAllEnable();
                } catch (SQLException ignore) { }
            }
        });
    }

    private static void setAllDisable(){
        botaoSalvar.setEnabled(false);
        botaoFiltrar.setEnabled(false);
    }
    private static void setAllEnable(){
        botaoSalvar.setEnabled(true);
        botaoFiltrar.setEnabled(true);
    }

}
