package FindClass;

import fbSelenium.code.FacebookClass;
import fbSelenium.frame.TelaInicial;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Bots {

    public static Function<Find, Exception> algoritimoExecutavel = null;
    public static boolean paginaVisivel = true;
    public static int numeroDeExecucoes;
    public static int numeroDeThreads = 4;
    public static int tempoDeEsperaDriver = 20;

    //localProject->

    public static List<FacebookClass> facebook = new ArrayList<>();
    public static String[] listaPesquisa;
    public static String[] senhas = ;
    public static String[] emails = ;

    public static void main(String[] args) {

        new TelaInicial();

        config();

        algoritimoExecutavel = find -> {
            try { find.init(paginaVisivel); } catch (AWTException e) { e.printStackTrace(); }
            /*Area de Código*/

            sincronizador(find.getNumero()).
                    obterComentarios(
                    listaPesquisa[find.getNumero()].trim(),
                    emails[find.getNumero()],
                    senhas[find.getNumero()],
                    5,
                    find
            );

            find.exit();

            /*Area de Código*/
            return null;
        };
        new OrdenarThreads();
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
