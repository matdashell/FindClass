package fbSelenium.code;

import fbSelenium.frame.TelaComentarios;
import fbSelenium.frame.TelaSQL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class SQL {

    private static Connection conexao;

    //iniciar sql
    public SQL() throws SQLException {
        String url = "jdbc:mysql://localhost/infofind";
//        conexao = DriverManager.getConnection(url,"root", "abc");
        conexao = DriverManager.getConnection(url,"root", "TeWVEIoyDS9HrTWD");

        testarBanco();
    }

    //gravar todos os resultados obtidos dentro da tabela pessoasnofilter
    synchronized public static void setUser(String nome, String coment, String url, int dias) throws SQLException {

        if(nome.length() > 50){nome = nome.substring(0,49);}
        if(coment.length() > 500){coment = coment.substring(0,499);}
        if(url.length() > 100){url = url.substring(0,99);}

        PreparedStatement gravar = conexao.prepareStatement(String.format("INSERT INTO pessoasnofilter values('%s','%s','%s',0,%d);",nome,coment,url,dias));
        gravar.executeUpdate();
    }

    //método filter usado para o user poder filtrar os dados
    public static void filter(String cmdNeutro, String cmdFalse, String cmdTrue, boolean salvar) throws SQLException {

        StringBuilder statement = new StringBuilder();
        boolean aux = false;

        if(cmdFalse != null && !cmdFalse.trim().equals("")){

            String[] temp = cmdFalse.split(",");

            for(int i = 0; i < temp.length; i++){

                if(temp[i].contains("[") && temp[i].contains("]")){

                    String inicio = null, fim = null;

                    try {
                        inicio = temp[i].split("\\[")[0];
                    }catch (Exception ignore) { }

                    try {
                        fim = temp[i].split("]")[1];
                    }catch (Exception ignore) { }

                    String[] cmdOR = temp[i].split("\\[")[1].split("]")[0].split(";");

                    statement.append("(");

                    for(int j = 0; j < cmdOR.length; j++){
                        statement.append("comentario NOT LIKE '%");
                        if(inicio != null) {
                            statement.append(inicio);
                        }
                        statement.append(cmdOR[j].trim());

                        if(fim != null) {
                            statement.append(fim);
                        }
                        statement.append("%'");

                        if(j != cmdOR.length - 1){
                            statement.append(" OR ");
                        }else{
                            statement.append(")");
                        }
                    }

                }else {
                    statement.append("comentario NOT LIKE '%").append(temp[i].trim()).append("%'");
                }

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

                if(temp[i].contains("[") && temp[i].contains("]")){

                    String inicio = null, fim = null;

                    try {
                        inicio = temp[i].split("\\[")[0];
                    }catch (Exception ignore) { }

                    try {
                        fim = temp[i].split("]")[1];
                    }catch (Exception ignore) { }

                    String[] cmdOR = temp[i].split("\\[")[1].split("]")[0].split(";");

                    statement.append("(");

                    for(int j = 0; j < cmdOR.length; j++){
                        statement.append("comentario LIKE '%");
                        if(inicio != null) {
                            statement.append(inicio);
                        }
                        statement.append(cmdOR[j].trim());

                        if(fim != null) {
                            statement.append(fim);
                        }
                        statement.append("%'");

                        if(j != cmdOR.length - 1){
                            statement.append(" OR ");
                        }else{
                            statement.append(")");
                        }
                    }

                }else {
                    statement.append("comentario LIKE '%").append(temp[i].trim()).append("%'");
                }

                if(i != temp.length-1){
                    statement.append(" AND ");
                }
            }
        }

        if(cmdNeutro != null && !cmdNeutro.trim().equals("")){

            if(!cmdFalse.trim().equals("") || !cmdTrue.trim().equals("")){
                statement.append(" AND (");
            }else{
                statement.append("(");
            }

            String[] temp = cmdNeutro.split(",");

            for(int i = 0; i < temp.length; i++){

                if(temp[i].contains("[") && temp[i].contains("]")){

                    String inicio = null, fim = null;

                    try {
                        inicio = temp[i].split("\\[")[0];
                    }catch (Exception ignore) { }

                    try {
                        fim = temp[i].split("]")[1];
                    }catch (Exception ignore) { }

                    String[] cmdOR = temp[i].split("\\[")[1].split("]")[0].split(";");

                    statement.append("(");

                    for(int j = 0; j < cmdOR.length; j++){
                        statement.append("comentario LIKE '%");
                        if(inicio != null) {
                            statement.append(inicio);
                        }
                        statement.append(cmdOR[j].trim());

                        if(fim != null) {
                            statement.append(fim);
                        }
                        statement.append("%'");

                        if(j != cmdOR.length - 1){
                            statement.append(" OR ");
                        }else{
                            statement.append(")");
                        }
                    }

                }else {
                    statement.append("comentario LIKE '%").append(temp[i].trim()).append("%'");
                }

                if(i != temp.length-1){
                    statement.append(" OR ");
                }else{
                    statement.append(")");
                }
            }
        }

        statement.append(" AND dias<=").append(getConfig()).append(" AND url NOT LIKE '%https://www.facebook.com/profile.php?id=1%'");

        PreparedStatement filter;
        ResultSet rsFilter;

        if(!salvar) {
            try {

                System.out.println("SELECT DISTINCT COUNT(comentario) FROM pessoasnofilter WHERE " + statement.toString().trim() + ";");

                //Obter numero obtido pelo filtro
                filter = conexao.prepareStatement("SELECT DISTINCT COUNT(comentario) FROM pessoasnofilter WHERE " + statement.toString().trim() + ";");
                rsFilter = filter.executeQuery();

                int tamanhoPesquisa = 0;
                int tamanhoBanco = 0;

                while (rsFilter.next()) {
                    tamanhoPesquisa = rsFilter.getInt(1);
                }

                filter = conexao.prepareStatement("SELECT DISTINCT COUNT(comentario) FROM pessoasnofilter");
                rsFilter = filter.executeQuery();

                while(rsFilter.next()){
                    tamanhoBanco = rsFilter.getInt(1);
                }

                TelaSQL.labelNumero.setText(String.format("%d de %d",tamanhoPesquisa,tamanhoBanco));

            } catch (SQLException sqlException) {
                System.out.println("erro");
            }
        }else{
            salvar(statement);
        }

    }

    public static void salvar(StringBuilder statement){

        PreparedStatement filter;
        ResultSet rsFilter = null;

        try {

            //Obter pessoas que passaram pelo filtro
            filter = conexao.prepareStatement("SELECT DISTINCT * FROM pessoasnofilter WHERE " + statement.toString().trim() + ";");
            rsFilter = filter.executeQuery();

        }
        catch (Exception ignored){

        }

        salvarEmStringB(rsFilter);
    }

    private static void salvarEmStringB(ResultSet rsFilter){

        TelaComentarios telaComentarios = new TelaComentarios();
        telaComentarios.configComentarios(rsFilter);

    }

    //exclui dados da tabela pessoasnofilter
    public static void deleteDadosPessoasNoFilter(){
        try {
            PreparedStatement excluir = conexao.prepareStatement("DELETE FROM pessoasnofilter WHERE tipo=0;");
            excluir.executeUpdate();
        }catch (Exception ignored) { }
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

    public static void setConfig(int dias){
        PreparedStatement preparedStatement, apagar;
        try{

            preparedStatement = conexao.prepareStatement(String.format("INSERT INTO config values('%d',0)",dias));
            apagar = conexao.prepareStatement("DELETE FROM config WHERE tipo=0;");
            apagar.executeUpdate();
            preparedStatement.executeUpdate();

        }catch (Exception ignored){

        }
    }

    public static String getConfig(){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String dias = null;
        try{

            preparedStatement = conexao.prepareStatement("SELECT * FROM config");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                dias = String.valueOf(resultSet.getInt("dias"));
            }

        }catch (Exception ignored){
        }
        return dias;
    }

    private static void testarBanco(){
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM pessoasnofilter");
            preparedStatement.executeQuery();
        }catch (Exception e) {
            try {
                PreparedStatement preparedStatement = conexao.prepareStatement("CREATE TABLE pessoasnofilter(nome varchar(50),comentario varchar(500),url varchar(100),tipo int,dias int);");
                preparedStatement.executeUpdate();
            }catch (Exception ignored) { }
        }

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM config");
            preparedStatement.executeQuery();
        }catch (Exception e) {
            try {
                PreparedStatement preparedStatement = conexao.prepareStatement("CREATE TABLE config(dias int,tipo int);");
                preparedStatement.executeUpdate();
            }catch (Exception ignored) { }
        }

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM emails");
            preparedStatement.executeQuery();
        }catch (Exception e) {
            try {
                PreparedStatement preparedStatement = conexao.prepareStatement("CREATE TABLE emails(email varchar(50),senha varchar(50),tipo int);");
                preparedStatement.executeUpdate();
            }catch (Exception ignored) { }
        }
    }

    public void executeUpdate(String cmd){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conexao.prepareStatement(cmd);
            preparedStatement.executeUpdate();
        }catch (Exception ignored) {

        }
    }

    public void executeQuery(String cmd){
        PreparedStatement preparedStatement;
        ResultSet rs;
        try {
            preparedStatement = conexao.prepareStatement(cmd);
            rs = preparedStatement.executeQuery();

        }catch (Exception ignored) {

        }
    }

    public static int getCount(String table){
        int retorno = 0;
        try{
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT DISTINCT COUNT(*) FROM "+table+";");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                retorno = rs.getInt(1);
            }
        }catch (Exception ignored) { }

        return retorno;
    }

}
