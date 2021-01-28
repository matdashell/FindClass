package FindClass;

import fbSelenium.code.FacebookClass;
import fbSelenium.code.SQL;
import fbSelenium.frame.TelaInfoThread;
import fbSelenium.frame.TelaInicial;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Bots {

    public static Function<Find, Exception> algoritimoExecutavel = null;
    public static boolean paginaVisivel = true;
    public static int numeroDeExecucoes;
    public static int numeroDeThreads;
    public static int tempoDeEsperaDriver;

    //localProject->

    public static List<FacebookClass> facebook = new ArrayList<>();
    public static List<String> listaPesquisa = new ArrayList<>();
    public static List<String> senhas = new ArrayList<>();
    public static List<String> emails = new ArrayList<>();

    private static void setVar(){
        SQL sql = null;
        try {
            sql = new SQL();
        }catch (SQLException ignored) {

        }

        String[] temp = TelaInicial.pesquisas.getText().trim().split(",");

        numeroDeExecucoes = temp.length;
        numeroDeThreads = temp.length;

        tempoDeEsperaDriver = (temp.length)*7;

        for(int i = 0; i < temp.length; i++){
            temp[i] = temp[i].trim();
            listaPesquisa.add(temp[i]);
            TelaInfoThread.pesquisa.get(i).setText(temp[i]);
        }

        List<String> dados = sql.getEmailslESenhas();

        for (String dado : dados) {
            emails.add(dado.split(",")[0]);
            senhas.add(dado.split(",")[1]);
        }

        System.out.println(listaPesquisa);
        System.out.println(emails);
        System.out.println(senhas);
    }

    public static void main(String[] args) {

        TelaInicial telaInicial = new TelaInicial();
        setVar();
        config();

        algoritimoExecutavel = find -> {
            try { find.init(paginaVisivel); } catch (AWTException e) { e.printStackTrace(); }
            /*Area de Código*/

            sincronizador(find.getNumero()).
                    obterComentarios(
                    listaPesquisa.get(find.getNumero()).trim(),
                    emails.get(find.getNumero()),
                    senhas.get(find.getNumero()),
                    9999,
                    find
            );

            find.exit();

            /*Area de Código*/
            return null;
        };
        new OrdenarThreads();
        telaInicial.end();
    }

    synchronized static FacebookClass sincronizador(int i) {
        return  facebook.get(i);
    }
    static void config(){
        for(int i = 0; i < numeroDeThreads; i++){
            facebook.add(new FacebookClass());
        }
    }
}
