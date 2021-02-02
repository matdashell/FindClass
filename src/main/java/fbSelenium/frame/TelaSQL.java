package fbSelenium.frame;

import fbSelenium.code.SQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class TelaSQL {

    private static SQL sql;

    private static JFrame frameSQL;

    public static final JLabel labelNumero = new JLabel("0");
    private static final JLabel planoFundo = new JLabel(new ImageIcon("C:\\RecursosPng\\SQL.png"));
    private static final JButton botaoSalvar = new JButton("Exibir");
    private static final JButton botaoFiltrar = new JButton("Filtrar");
    private static final JTextPane textFieldNaoConter = new JTextPane();
    private static final JTextPane textFieldConter = new JTextPane();
    private static final JTextPane textFieldNeutro = new JTextPane();
    private static final JPanel contentPanelSQL = new JPanel(null);

    private static JScrollPane scrollPaneConter;
    private static JScrollPane scrollPaneNaoConter;
    private static JScrollPane scrollPaneNeutro;

    private static final Font font = new Font("arial",Font.PLAIN,14);

    TelaSQL() {
        initSQL();
        addToPanel();
        setBoundsComponentes();
        configurarTela();
        actionComponentes();
        addActionText();
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

        scrollPaneConter.setBounds(170,30,397,30);
        scrollPaneNaoConter.setBounds(170,108,397,30);
        scrollPaneNeutro.setBounds(170,180,397,30);
        botaoFiltrar.setBounds(450,250,115,32);
        botaoSalvar.setBounds(310,250,115,32);
        labelNumero.setBounds(20,240,120,50);
        planoFundo.setBounds(0,0,600,300);

        textFieldNeutro.setFont(font);
        textFieldConter.setFont(font);
        textFieldNaoConter.setFont(font);
    }

    private static void addToPanel(){

        scrollPaneConter = new JScrollPane(textFieldConter);
        scrollPaneNaoConter = new JScrollPane(textFieldNaoConter);
        scrollPaneNeutro = new JScrollPane(textFieldNeutro);

        scrollPaneConter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneConter.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneNaoConter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneNaoConter.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneNeutro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneNeutro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        contentPanelSQL.add(scrollPaneConter);
        contentPanelSQL.add(scrollPaneNaoConter);
        contentPanelSQL.add(scrollPaneNeutro);
        contentPanelSQL.add(botaoFiltrar);
        contentPanelSQL.add(botaoSalvar);
        contentPanelSQL.add(labelNumero);
        contentPanelSQL.add(planoFundo);



    }

    private static void addActionText(){
        textFieldConter.addMouseListener(new MouseListener() {
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
                scrollPaneConter.setBounds(170,30,397,60);
                scrollPaneConter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

                frameSQL.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                scrollPaneConter.setBounds(170,30,397,30);
                scrollPaneConter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                frameSQL.repaint();
            }
        });

        textFieldNaoConter.addMouseListener(new MouseListener() {
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
                scrollPaneNaoConter.setBounds(170, 108, 397, 60);
                scrollPaneNaoConter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

                frameSQL.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                scrollPaneNaoConter.setBounds(170,108,397,30);
                scrollPaneNaoConter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                frameSQL.repaint();
            }
        });

        textFieldNeutro.addMouseListener(new MouseListener() {
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
                scrollPaneNeutro.setBounds(170,180,397,60);
                scrollPaneNeutro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

                frameSQL.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                scrollPaneNeutro.setBounds(170,180,397,30);
                scrollPaneNeutro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                frameSQL.repaint();
            }
        });
    }

    private static void confirm(){
        frameSQL.validate();
        frameSQL.repaint();
        frameSQL.pack();
    }

    private static void actionComponentes(){

        botaoSalvar.addActionListener(actin -> {
            if(!textFieldNeutro.getText().equals("") || !textFieldNaoConter.getText().equals("") || !textFieldConter.getText().equals("")) {
                try {
                    setAllDisable();
                    sql.filter(textFieldNeutro.getText(), textFieldNaoConter.getText(), textFieldConter.getText(), true);
                    setAllEnable();
                } catch (SQLException ignore) { }
            }else{
                JOptionPane.showMessageDialog(null, "Erro: Sem filtro definido");
            }
        });

        botaoFiltrar.addActionListener(actin -> {

            System.out.println(textFieldNaoConter.getText());

            if(textFieldConter.getText().trim().equals("/v")){
                textFieldNeutro.setText("carro,veiculo,cominhonete,caminhão,caminhoneta,gasolina,acidente,pedestre," +
                        "moto,motocicleta,exportiva,exportivo,4 rodas,cavalos,amassado,quebrado,seguro,segurança," +
                        "tenho um_,bat____,cami_____,fogo,aciden____,_ carro _");

                textFieldNaoConter.setText("maravilhos_,beleza,tivesse dinheiro,quero esse carro,comprar carro," +
                        "comprar esse carro,quero um carro,qual carro,qual marca,que carro,controle remoto," +
                        "tomar cerveja,estaciona sozinho,manobra sozinho,ja tem,nossa que carro,comprar um carro," +
                        "tesla,1G,2G,3G,4G,5G,apartamento,_Gb,__Gb,_ Gb,__ Gb,celular,cllr,cell,beleza,cerveja,Testa," +
                        "sonho,amor,amo,manobrando sozinho,compra um carro assim,anda sozinho");

                textFieldConter.setText("");
            }

            if(textFieldConter.getText().trim().equals("/t")){
                textFieldNeutro.setText("0___________,1___________,2___________,3___________" +
                        ",4___________,5___________,6___________,7___________,8___________,9___________," +
                        "0_______,1_______,2_______,3_______,4_______," +
                        "5_______,6_______,7_______,8_______,9_______,9________");

                textFieldConter.setText("");

                textFieldConter.setText("");
            }

            if(textFieldConter.getText().trim().equals("/del")){
                textFieldConter.setText("");
                textFieldNaoConter.setText("");
                textFieldNeutro.setText("");
            }

            if((!textFieldNeutro.getText().equals("") || !textFieldNaoConter.getText().equals("")) || !textFieldConter.getText().equals("")) {
                try {
                    setAllDisable();
                    sql.filter(textFieldNeutro.getText(), textFieldNaoConter.getText(), textFieldConter.getText(), false);
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
