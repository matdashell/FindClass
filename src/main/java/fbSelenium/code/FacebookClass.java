package fbSelenium.code;

import FindClass.Find;
import fbSelenium.frame.TelaInfoThread;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FacebookClass {

    Find find;
    String numeroComentarioDoPost;
    List<WebElement> posts;
    static SQL sql;
    public static int total = 0;
    int contTotal = 0;

    static {
        try {
            sql = new SQL();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //local do email e senha
    private static final String email_senha_m = "css [class='inputtext _55r1 _6luy']";

    //barra de pesquisa do facebook
    private static final String pesquisa_o = "css input[type='search']";

    //adicionar publicação
    private static final String adicionarPublicacao = "css span[class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql rrkovp55 a8c37x1j keod5gw0 nxhoafnm aigsh9s9 d3f4x2em fe6kdd0r mau55g9w c8b282yb iv3no6db jq4qci2q a3bd9o3v lrazzd5p bwm1u5wc']";

    //blocos de posts após a pesquisa
    private static final String blocosPosts_m = "css div[class='rq0escxv l9j0dhe7 du4w35lb qmfd67dx hpfvmrgz gile2uim buofh1pr g5gj957u aov4n071 oi9244e8 bi6gxh9e h676nmdw aghb5jc5']>div";

    //local do href (atributo único de cada post)
    private static final String atributoUnico_inPost_o = "css div[class='l9j0dhe7']>div[class='l9j0dhe7']>div>a";

    //iten que todos os posts tem em comum
    private static final String atributoEmComumPosts = "css div[class='rq0escxv l9j0dhe7 du4w35lb j83agx80 g5gj957u rj1gh0hx buofh1pr hpfvmrgz taijpn5t bp9cbjyn owycx6da btwxx1t3 d1544ag0 tw6a2znq jb3vyjys dlv3wnog rl04r1d5 mysgfdmx hddg9phg qu8okrzs g0qnabr5']";

    //spans post (botões do post[likes, comentarios, compartilhamentos])
    private static final String spanPost_inPost_m = "css span[class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql rrkovp55 a8c37x1j keod5gw0 nxhoafnm aigsh9s9 d3f4x2em fe6kdd0r mau55g9w c8b282yb iv3no6db jq4qci2q a3bd9o3v knj5qynh m9osqain']";

    //span interno de bloco de comentarios para comentar
    private static final String spanComentar_inBlocoComents_o = "css div[class='oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 q9uorilb p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl m9osqain gpro0wi8 n3ffmt46 l9j0dhe7']";

    //spans internos dentro do post(botões internos[resposta, Ver mais comentários])
    private static final String spanPostInternos_inComentarios_m = "css span[class='j83agx80 fv0vnmcu hpfvmrgz']>span";

    //blocos de comentarios com a primeira div sendo o proprio comentario e a segunda div o comentario das outras pessoas
    private static final String blocosDeComentarios_inPost_m = "css div[class='g3eujd1d ni8dbmo4 stjgntxs hv4rvrfc']";

    //tempo do comentario dentro do bloco de comentario
    private static final String tempoComentario_inBlocoComents_i = "css a > span[class='tojvnm2t a6sixzi8 abs2jz4q a8s20v7p t1p8iaqh k5wvi7nf q3lfd5jv pk4s997a bipmatt0 cebpdrjk qowsmv63 owwhemhu dp1hu0rb dhp61c6y iyyx5f41']";

    //nome de usuário dentro do bloco (getText retorna o nome e getAtribute pelo 'href' retorna o link de perfil)
    private static final String nomes_inBlocosComents_m = "css a[class='oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl gmql0nx0 gpro0wi8']";

    //comentarios dentro do bloco
    private static final String comentarios_inBlocosComents_m = "css span[class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql rrkovp55 a8c37x1j keod5gw0 nxhoafnm aigsh9s9 d3f4x2em fe6kdd0r mau55g9w c8b282yb iv3no6db jq4qci2q a3bd9o3v knj5qynh oo9gr5id']>div[class='kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x c1et5uql']";

    //limitador de ver mais comentários
    private static final String limitadorComentarios = "css span[class='d2edcug0 hpfvmrgz qv66sw1b c1et5uql rrkovp55 a8c37x1j keod5gw0 nxhoafnm aigsh9s9 d9wwppkn fe6kdd0r mau55g9w c8b282yb mdeji52x sq6gx45u a3bd9o3v knj5qynh m9osqain']";


    /*
    Método com função de logar na conta do facebook
     */
    private void logar(String email, String senha){
        editarLogCaixaGrafica("Logando...");
        find.page("https://www.facebook.com");
        find.more(FacebookClass.email_senha_m).get(0).sendKeys(email);
        find.more(FacebookClass.email_senha_m).get(1).sendKeys(senha);
        find.more(FacebookClass.email_senha_m).get(1).sendKeys(Keys.ENTER);
        find.waitWhileDisable(pesquisa_o);
        find.time(10000);
        if(find.driver.getCurrentUrl().equals("https://www.facebook.com")) {
            editarLogCaixaGrafica("Logado!");
        }else{
            editarLogCaixaGrafica("Erro com informações de login!");
        }
    }

    /*
    Método com função de efetuar a pesquisa no facebook de acordo com o termo estabelecido
     */
    private void pesquisar(String pesquisa){
        find.one(FacebookClass.pesquisa_o).sendKeys("#"+pesquisa);
        find.one(FacebookClass.pesquisa_o).sendKeys(Keys.ENTER);
        editarLogCaixaGrafica("Aguardando pesquisa...");
        find.time(10000);
    }

    /*
    Método com função de enviar o botão 'PAGE_DOWN' para a pagina assim descendo e carregando os elementos
     */
    private void descer(int quantidade){
        WebElement pag = find.one("css html");
        for(int i = 0; i < quantidade; i++){
            pag.sendKeys(Keys.PAGE_DOWN);
            find.time(250);
        }
    }

    /*
    Método com função de fixar a página recarregando os seus elementos e chamando o método com função de
    verificar se os posts da página foram carregados.
     */
    private void fixPage(){
        editarLogCaixaGrafica("Erro na pagina, recarregando...");
        find.reload();
        find.time(5000);
        verificarSePostCarregou();
    }

    /*
    Metodo que verifica se os posts recorrentes das pesquisas foram carregados com sucesso com acesso ao
    método find 'visible' que tem retorno booleano com a existência ou não do WebElement.
     */
    private void verificarSePostCarregou(){
        while (true){
            find.time(500);
            if(find.visible(blocosPosts_m)){
                posts = new ArrayList<>(find.more(blocosPosts_m));
                break;
            }
        }
        editarLogCaixaGrafica("Pesquisa feita com sucesso!");
    }

    /*
    Metodo com função de verificar se o index procurado existe na lista de posts, caso não, ele carrega
    a página ate o size de post se expandir, em caso de erros o método fixPage é chamado para que possa
    recaregar a página e voltar aos processos anteriores. Em caso de reiniciar ele ira voltar exatamente
    para o post anterior processador assim dando continuidade ao processo.
     */
    private void verificarSizePost(int index){
        try {
            posts = new ArrayList<>(find.more(blocosPosts_m));
            while (index >= posts.size() - 5) {
                editarLogCaixaGrafica("Carregando Size Posts...");
                descer(5);
                posts = new ArrayList<>(find.more(blocosPosts_m));
                if(posts == null){
                    fixPage();
                }
                find.time(2000);
            }
        }catch (Exception e){
            fixPage();
        }
    }

    /*
    Controlador principal com função de ordenar as pesquisas e fazer o controle dos posts obtidos
     */
    public void obterComentarios(String pesqisa, String email, String senha, int limite, Find find) {

        this.find = find;
        logar(email, senha);
        pesquisar(pesqisa);
        verificarSePostCarregou();

        editarLogCaixaGrafica("Iniciando Coleta!");

        //ciclo for simples para maior controle
        for (int i = 0; i < limite; i++) {

            verificarSizePost(i);
            TelaInfoThread.postNum.get(getNumber()).setText("Post: "+i);

            try{
                if (verificarSePossuiComentarios(posts.get(i))) {
                    verificarSePossuiVerMaisComentarios(posts.get(i));
                    expandirRespostas(posts.get(i));
                    capturarComentarios(posts.get(i));
                } else {
                    limite++;
                    descer(1);
                }

            }catch (Exception e) {
                fixPage();
                i--;
            }
        }
        find.exit();
    }

    private boolean verificarSePossuiComentarios(WebElement post){
        /*
        Lista de WebElement com os spans do post é instancida e logo em seguida com um ciclo for o programa ira
        procurar se existe algum spam que termine com a palavra 'comentários', caso sim, clica no spam e o expande,
        logo em segui o metodo waitWhileDisable é chamado procuranod um elemento dentro do post que só pode ser
        encontrado caso os comentarios estejam abertos... Assim tendo um aguardo dinâmico do carregamento dos
        comentários. Caso nenhum span com função de exibir comentários seja encontrada o método retorna false e
        o programa segue capturando o proximo post.
         */
        if(find.visible(post, spanPost_inPost_m)) {
            editarLogCaixaGrafica("Verificando Post...");
            int contador = 0;
            List<WebElement> spanOutPost = new ArrayList<>(find.more(post, spanPost_inPost_m));

            for (WebElement span : spanOutPost) {
                if (span.getText().endsWith("comentários") || span.getText().endsWith("comentário")) {
                    numeroComentarioDoPost = span.getText().split(" ")[0];

                    try {
                        span.click();
                    }catch (Exception e){
                        find.time(500);
                        try{
                            span.click();
                        }catch (Exception f){
                            return false;
                        }
                    }

                    while(true){
                        find.time(1000);
                        contador++;
                        if(find.visible(post, nomes_inBlocosComents_m)){ break; }
                        if(contador == 5){
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

     /*
        Método com função de exibir todos os comentários possíveis no post exceto as respostas, co carregamento
        dinâmico, ou seja, assim que os dados estiverem disponíveis o programa indentifica e continua o processo.
     */
    private void verificarSePossuiVerMaisComentarios(WebElement post){

        editarLogCaixaGrafica("Verificando comentários do post...");
        boolean verificar = true;
        int numeroDeComentariosAntes = 0;
        int numeroDeComentariosDepois = 0;

        if(find.visible(post, spanPostInternos_inComentarios_m)){
            while(verificar) {
                verificar = false;
                Set<WebElement> spanInPost = new HashSet<>(find.more(post, spanPostInternos_inComentarios_m));
                numeroDeComentariosAntes = find.more(post, nomes_inBlocosComents_m).size();

                for (WebElement span : spanInPost) {
                    find.time(50);
                    if (span.getText().startsWith("Ver")) {
                        try {
                            verificar = true;
                            span.click();

                            while (numeroDeComentariosAntes >= numeroDeComentariosDepois) {
                                find.time(250);
                                numeroDeComentariosDepois = find.more(post, nomes_inBlocosComents_m).size();
                                if (find.visible(post, limitadorComentarios)) {
                                    break;
                                }
                            }
                            find.time(500);
                        }catch (Exception e){ verificar = false; }
                        break;
                    }
                }
                editarLogCaixaGrafica("Carregando Comentários: "+numeroDeComentariosDepois+" de "+numeroComentarioDoPost);
            }
        }
        if(numeroDeComentariosDepois>numeroDeComentariosAntes) {
            editarLogCaixaGrafica("Comentários Obtidos:   [ "+numeroDeComentariosDepois+" de "+this.numeroComentarioDoPost+" ]");
        }else{
            editarLogCaixaGrafica("Comentários Obtidos:   [ "+ numeroDeComentariosAntes+" de "+this.numeroComentarioDoPost+" ]");
        }
        find.time(2000);
    }

    /*
    Método com função de expndir respostas encontradas nos comentarios
     */
    private void expandirRespostas(WebElement post){
        if(find.visible(post, spanPostInternos_inComentarios_m, 500)) {
            int cont = 0;
            editarLogCaixaGrafica("Expandindo comentários...");
            Set<WebElement> spanRespostas = new HashSet<>(find.more(post, spanPostInternos_inComentarios_m));
            editarLogCaixaGrafica("Expansões feitas: [ " + cont + " de " + spanRespostas.size() + "]");
            find.time(1000);
            for (WebElement span : spanRespostas) {
                if (span.getText().endsWith("respostas") || span.getText().endsWith("resposta")) {
                    try {
                        span.click();
                        cont++;
                        editarLogCaixaGrafica("Expansões feitas: [ " + cont + " de " + spanRespostas.size() + "]");
                        find.time(250);
                    } catch (Exception ignored) {
                    }
                }
            }
            editarLogCaixaGrafica("Aguardando expansão...");
            find.time(5000);
        }
    }

    /*
    Metodo com função de output dos dados obtidos.
     */
    private void capturarComentarios(WebElement post){
        Set<WebElement> blocosComentarios = new HashSet<>(find.more(post, blocosDeComentarios_inPost_m));
        int cont = 0;
        int tamanho = blocosComentarios.size();
        contTotal += blocosComentarios.size();
        atualizarTotal(contTotal);
        editarComentariosObtidos(contTotal);

        for(WebElement bloco : blocosComentarios){
            cont++;
            editarLogCaixaGrafica(String.format("Gravados : %d de %d",cont,tamanho));

            if(find.visible(bloco, comentarios_inBlocosComents_m, 1)){

                String[] tempoDoComentario = find.one(bloco, tempoComentario_inBlocoComents_i).getText().split(" ");
                int dias;

                switch (tempoDoComentario[1]) {
                    case "h":
                    case "min":
                        dias = 1;
                        break;
                    case "d":
                        dias = Integer.parseInt(tempoDoComentario[0]);
                        break;
                    case "sem":
                        dias = (Integer.parseInt(tempoDoComentario[0])) * 7;
                        break;
                    default:
                        dias = 120;
                        break;
                }

                System.out.println(tempoDoComentario[1]+" "+dias);

                gravarUser(find.one(bloco, nomes_inBlocosComents_m).getText(), find.one(bloco, comentarios_inBlocosComents_m).getText(), find.one(bloco, nomes_inBlocosComents_m).getAttribute("href").split("[0]")[0], dias);
            }
        }
    }

    public int getNumber(){
        return find.getNumero();
    }

    private void editarLogCaixaGrafica(String textoInfo){
        TelaInfoThread.log.get(getNumber()).setText("Log: "+textoInfo);
        System.out.println(textoInfo);
    }

    private void editarComentariosObtidos(int num){
        TelaInfoThread.quantidade.get(getNumber()).setText("Total: "+num);
    }

    synchronized private static void gravarUser(String user, String texto, String urlUser, int dias){
        try {
            sql.setUser(user, texto, urlUser, dias);
        }catch (Exception ignored) {

        }
    }

    synchronized static private void atualizarTotal(int num){
        total += num;
    }
}


