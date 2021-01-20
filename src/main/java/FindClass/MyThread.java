package FindClass;

import static java.lang.Thread.sleep;

@SuppressWarnings("ALL")
public class MyThread {

    static int quantidadeDeNucleos = 0;
    static int quantidadeDeExecucoesNucleo = 0;
    int meuNumero;
    Find f = null;
    Nucleo n = new Nucleo();
    boolean iniciado = false;
    boolean processar = false;

    public MyThread(){
        this.meuNumero = quantidadeDeNucleos;
        quantidadeDeNucleos++;
    }

    public class Nucleo extends java.lang.Thread{
        public void run(){
            iniciado = true;
            while(true){
                try{sleep(1000);}catch (Exception e){}
                if(processar){
                    /*funções do núcleo*/

                    Bots.algoritimoExecutavel.apply(f);
                    alterarVariaveis();
                    processar = false;

                    /*funções do núcleo*/
                }
            }
        }
    }
    public synchronized void setNucleo(){
        this.f = null;
        this.f = new Find();
        this.f.numeroThread = meuNumero;
    }
    public synchronized void ligarNucleo(){
        setNucleo();
        if(this.iniciado){
            this.processar = true;
        }else{
            this.processar = true;
            this.n.start();
        }
    }
    public synchronized void encerrarNucleo(){
        this.n.stop();
    }
    public synchronized static void alterarVariaveis(){
        try{sleep(1);}catch (Exception e){}
        quantidadeDeExecucoesNucleo++;
    }
}