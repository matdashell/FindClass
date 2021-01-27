package fbSelenium.frame;

import fbSelenium.code.SQL;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelaConfig {

    private static SQL sql;

    private static JFrame frameConfig = null;

    private static final JButton salvar = new JButton("Salvar");
    private static final JButton excluirSQL = new JButton("Del SQL");
    private static final JLabel planoFundo = new JLabel(new ImageIcon("C:\\RecursosPng\\Config.png"));
    private static final JTextField emails = new JTextField();
    private static final JTextField senhas = new JTextField();
    private static final JTextField diasMin = new JTextField();

    TelaConfig() {

        try {
            sql = new SQL();
        } catch (SQLException ignored) { }

        setBoundsComponentes();
        configurarTela();
        addToFrame();
        carregarDados();
        addActionBotoes();
        confirm();
    }

    public static JFrame getFrame(){
        carregarDados();
        return frameConfig;
    }

    private static void configurarTela(){
        frameConfig = new JFrame();
        frameConfig.setVisible(false);
        frameConfig.setLayout(null);
        frameConfig.setTitle("Configurações");
        frameConfig.setSize(620,340);
        frameConfig.setResizable(false);
    }

    private static void setBoundsComponentes(){
        planoFundo.setBounds(0,0,600,300);
        excluirSQL.setBounds(317,243,106,40);
        salvar.setBounds(460,243,106,40);
        emails.setBounds(172,43,395,33);
        senhas.setBounds(172,102,395,33);
        diasMin.setBounds(172,160,107,33);
    }

    private static void addToFrame(){
        frameConfig.add(excluirSQL);
        frameConfig.add(salvar);
        frameConfig.add(emails);
        frameConfig.add(senhas);
        frameConfig.add(diasMin);
        frameConfig.add(planoFundo);
    }

    private static void confirm(){
        frameConfig.repaint();
        frameConfig.validate();
        frameConfig.setLocationRelativeTo(null);
    }

    private static void carregarDados(){
        List<String> emailsESenhas = sql.getEmailslESenhas();

        StringBuilder email = new StringBuilder();
        StringBuilder senha = new StringBuilder();

        for(int i = 0; i < emailsESenhas.size(); i++){
            if(i != emailsESenhas.size() - 1) {
                email.append(emailsESenhas.get(i).split(",")[0]).append(", ");
                senha.append(emailsESenhas.get(i).split(",")[1]).append(", ");
            }else {
                email.append(emailsESenhas.get(i).split(",")[0]);
                senha.append(emailsESenhas.get(i).split(",")[1]);
            }
        }

        emails.setText(email.toString());
        senhas.setText(senha.toString());
        diasMin.setText(sql.getConfig());
    }

    private static void addActionBotoes(){
        salvar.addActionListener(action -> {

            List<String> emailESenha = new ArrayList<>();
            String[] email = emails.getText().split(",");
            String[] senha = senhas.getText().split(",");

            if(email.length == senha.length){

                for(int i = 0; i < email.length; i++){
                    if(!email[i].trim().equals("") && !email[i].trim().equals("")) {
                        emailESenha.add(String.format("%s,%s", email[i], senha[i]));
                    }
                }

                sql.setEmailsESenhas(emailESenha);

            }else{
                JOptionPane.showMessageDialog(null, "Erro: Quantidade de emails e senhas diferentes");
            }

            try{
                if(!diasMin.getText().trim().equals("")) {
                    int dias = Integer.parseInt(diasMin.getText());
                    sql.setConfig(dias);

                    JOptionPane.showMessageDialog(null,"Dados salvos.");

                }else {
                    JOptionPane.showMessageDialog(null,"Erro: Campo de dias mínimos incorreto");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro: Campo de dias mínimos incorreto");
            }
        });

        excluirSQL.addActionListener(action -> {
            int resposta = JOptionPane.showConfirmDialog(null,"Deseja excluir base de dados?");

            if(resposta == 0){
                sql.deleteDadosPessoasNoFilter();
                JOptionPane.showMessageDialog(null,"Base de dados apagada com sucesso.");
            }else if(resposta == -1) {

            }else {
                JOptionPane.showMessageDialog(null,"Operação cancelada.");
            }
        });
    }

}
