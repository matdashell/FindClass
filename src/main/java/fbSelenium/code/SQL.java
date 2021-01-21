package fbSelenium.code;

import fbSelenium.frame.TelaSQL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class SQL {

    private static int posicaoSave = 0;
    private static boolean primeiraExecucao = true;
    static Connection conexao;

    //iniciar sql
    public SQL() throws SQLException {
        String url = "jdbc:mysql://localhost/infofind";
        conexao = DriverManager.getConnection(url,"root", "");
    }

    //gravar todos os resultados obtidos dentro da tabela pessoasnofilter
    synchronized public static void gravarUser(String nome, String coment, String url) throws SQLException {

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

    //exclui todos os dados contidos na tabela temp
    public static void apagarDadosTemp() throws SQLException{
        for(int i = 0; i < 2; i++) {
            PreparedStatement excluir = conexao.prepareStatement("DELETE FROM temp"+i+" WHERE tipo=0;");
            excluir.executeUpdate();
        }
    }

    //exclui dados da tabela pessoasnofilter
    public static void apagarDadosPessoasNoFilter() throws SQLException{
        PreparedStatement excluir = conexao.prepareStatement("DELETE FROM pessoasnofilter WHERE tipo=0;");
        excluir.executeUpdate();
    }

    //apagada dados do src dos posts verificados
    public static void apagarDadosPosts() throws SQLException{
        PreparedStatement excluir = conexao.prepareStatement("DELETE FROM posts WHERE tipo=0;");
        excluir.executeUpdate();
    }

    private static void gravarTXT(StringBuilder stringBuilder){
        Path path = Paths.get(String.format("C:\\Users\\Public\\Documents\\ResultS\\Pesquisa%f.rtf",Math.random()*50));
        byte[] save = stringBuilder.toString().getBytes();

        try {
            Files.write(path,save);
        }catch (Exception ignored){ }
    }

    public static void main(String[] args) throws SQLException {
        new SQL();
    }
}
