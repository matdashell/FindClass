package fbSelenium.code;

import fbSelenium.frame.TelaSQL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class SQL {

    private static boolean emUso = false;
    private static Connection conexao;

    //iniciar sql
    public SQL() throws SQLException {
        String url = "jdbc:mysql://localhost/infofind";
        conexao = DriverManager.getConnection(url,"root", "1oGDm2ZttIHPucnu");
    }

    //gravar todos os resultados obtidos dentro da tabela pessoasnofilter
    synchronized public static void setUser(String nome, String coment, String url) throws SQLException {

        if(nome.length() > 50){nome = nome.substring(0,49);}
        if(coment.length() > 500){coment = coment.substring(0,499);}
        if(url.length() > 100){url = url.substring(0,99);}

        PreparedStatement gravar = conexao.prepareStatement("INSERT INTO pessoasnofilter values('"+nome+"','"+coment+"','"+url+"',0);");
        gravar.executeUpdate();
    }

    //método filter usado para o user poder filtrar os dados
    public static void filter(String cmdFalse, String cmdTrue, boolean salvar) throws SQLException {

        StringBuilder statement = new StringBuilder();
        boolean aux = false;

        if(cmdFalse != null && !cmdFalse.trim().equals("")){

            String[] temp = cmdFalse.split(",");

            for(int i = 0; i < temp.length; i++){

                statement.append("comentario NOT LIKE '%").append(temp[i].trim()).append("%'");
                if(i != temp.length-1){
                    statement.append(" AND ");
                }
            }
            aux = true;
        }

        if(cmdTrue != null && !cmdTrue.trim().equals("")){

            if(aux){statement.append(" AND ");}

            String[] temp = cmdTrue.split(",");

            for(int i = 0; i < temp.length; i++){

                statement.append("comentario LIKE '%").append(temp[i].trim()).append("%'");
                if(i != temp.length-1){
                    statement.append(" AND ");
                }
            }
        }

        PreparedStatement filter;
        ResultSet rsFilter;

        if(!salvar) {
            try {

                System.out.println("SELECT COUNT(comentario) FROM pessoasnofilter WHERE " + statement.toString().trim() + ";");

                //Obter numero obtido pelo filtro
                filter = conexao.prepareStatement("SELECT COUNT(comentario) FROM pessoasnofilter WHERE " + statement.toString().trim() + ";");
                rsFilter = filter.executeQuery();

                while (rsFilter.next()) {
                    System.out.println(rsFilter.getInt(1));
                    TelaSQL.labelNumero.setText(String.valueOf(rsFilter.getInt(1)));
                }


            } catch (SQLException sqlException) {
                System.out.println("erro");
            }
        }else{
            salvar(statement);
        }

    }

    public static void salvar(StringBuilder statement){

        StringBuilder stringBuilder = new StringBuilder();
        PreparedStatement filter;
        ResultSet rsFilter;

        try {

            //Obter pessoas que passaram pelo filtro
            filter = conexao.prepareStatement("SELECT * FROM pessoasnofilter WHERE " + statement.toString().trim() + ";");
            rsFilter = filter.executeQuery();

            //gravar os dados em arquivo txt
            while (rsFilter.next()) {
                stringBuilder.append(String.format("User: %s \n\n Comentou: %s \n\n Url: %s",
                        rsFilter.getString("nome"),
                        rsFilter.getString("comentario"),
                        rsFilter.getString("url")
                        )
                ).append("\n-----------------------------------------------------------------\n");
            }
        }
        catch (Exception ignored){

        }

        gravarTXT(stringBuilder);
    }

    //exclui dados da tabela pessoasnofilter
    public static void deleteDadosPessoasNoFilter(){
        try {
            PreparedStatement excluir = conexao.prepareStatement("DELETE FROM pessoasnofilter WHERE tipo=0;");
            excluir.executeUpdate();
        }catch (Exception ignored) { }
    }

    //consulta dados filtrados em pessoasnofilter e grava em um arquivo RTF
    private static void gravarTXT(StringBuilder stringBuilder){
        Path path = Paths.get(String.format("C:\\Users\\Public\\Documents\\ResultS\\Pesquisa%f.rtf",Math.random()*50));
        byte[] save = stringBuilder.toString().getBytes();

        try {
            Files.write(path,save);
        }catch (Exception ignored){ }
    }

    //carregar emails e senhas do banco de dados
    public static List<String> getEmailslESenhas(){
        List<String> emailESenhas = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
            try {
                preparedStatement = conexao.prepareStatement("SELECT * FROM emails");
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    emailESenhas.add(String.format("%s,%s",resultSet.getString("email"),resultSet.getString("senha")));
                }

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
        }
        return  emailESenhas;
    }

    //grvar emails e senhas em banco de dados
    public static void setEmailsESenhas(List<String> emaislESenhas){
        PreparedStatement preparedStatement , delete;
            try{

                delete = conexao.prepareStatement("DELETE FROM emails WHERE tipo=0");
                delete.executeUpdate();

                for(String list : emaislESenhas) {
                    preparedStatement = conexao.prepareStatement(String.format("INSERT INTO emails values('%s','%s',0);",
                            list.split(",")[0].trim(),
                            list.split(",")[1].trim()));
                    preparedStatement.executeUpdate();
                }

            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
    }

    public static void main(String[] args) throws SQLException {
        new SQL();
    }
}
