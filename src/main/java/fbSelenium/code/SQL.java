package fbSelenium.code;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SQL {

    private static int posicaoSave = 0;
    private static boolean primeiraExecucao = true;
    static Connection conexao;

    //iniciar sql
    SQL() throws SQLException {
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

    //gravar posts escaneados pela class FacebookClass para não fazer escaneamentos repetidos
    synchronized public static void gravarPost(String urlPost) throws SQLException {
        PreparedStatement gravar = conexao.prepareStatement("INSERT INTO posts values('"+urlPost+"',0);");
        gravar.executeUpdate();
    }

    //excluir tabela que armazena o src dos posts
    synchronized public static void excluirPost(String urlPost) throws SQLException {
        PreparedStatement excluir = conexao.prepareStatement("DELETE FROM posts WHERE tipo=0;");
        excluir.executeUpdate();
    }

    //retorna um boolean caso o post mencionado ja tenha sido escaneado
    synchronized static boolean verificarPost(String postAtual) throws SQLException {
        PreparedStatement query = conexao.prepareStatement("SELECT * FROM posts");
        ResultSet qry = query.executeQuery();

        Set<String> url = new HashSet<>();

        while(qry.next()){
            url.add(qry.getString("url"));
        }

        return url.contains(postAtual);
    }

    //método filter usado para o user poder filtrar os dados
    public static void filter(String cmdFalse, String cmdTrue) throws SQLException {

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

        PreparedStatement filter = null;
        ResultSet rs = null;

        if(primeiraExecucao) {

            try {
                filter = conexao.prepareStatement("SELECT * FROM pessoasnofilter WHERE " + statement.toString().trim() + ";");
                rs = filter.executeQuery();
            } catch (SQLException ignore) {
            }
        }else{

            try {
                filter = conexao.prepareStatement("SELECT * FROM temp"+(posicaoSave-1)+" WHERE " + statement.toString().trim() + ";");
                rs = filter.executeQuery();
            } catch (SQLException ignore) {
            }
        }

        if(rs.next()) {
            while (rs.next()) {
                auxConsulta(rs);
            }
            posicaoSave++;
        }
    }

    //auxiliar de consulta que exclui e grava os dados obtidos para que se possa atualizar as tabelas temp
    private static void auxConsulta(ResultSet resultSet) throws SQLException{
        primeiraExecucao = false;
        if(posicaoSave == 10){ posicaoSave = 0; }

        PreparedStatement excluir = conexao.prepareStatement("DELETE FROM temp"+posicaoSave+" WHERE tipo=0;");
        excluir.executeUpdate();

        PreparedStatement gravar = conexao.prepareStatement("INSERT INTO temp"+posicaoSave+" values(" +
                "'"+resultSet.getString("nome")+"'," +
                "'"+resultSet.getString("comentario")+"'," +
                "'"+resultSet.getString("url")+"'," +
                "0);");
        gravar.executeUpdate();
    }

    //exclui todos os dados contidos na tabela temp
    public static void apagarDadosTemp() throws SQLException{
        for(int i = 0; i < 10; i++) {
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

    public static void main(String[] args) throws SQLException {
        new SQL();
        filter("jesess","o,s");
    }
}
