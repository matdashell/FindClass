package FindClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.lang.Thread.sleep;

@SuppressWarnings("ALL")
public class Find {

    public WebDriver driver = null;
    Robot r = null;
    int meuNumero;
    int numeroThread;
    static int numeroDeBots = 0;
    static WebElement html = null;

    //Configuração do selenium usndo o navegador Chrome
    public Find() {
        meuNumero = Find.numeroDeBots;
        Find.numeroDeBots++;
    }


    //configuração para iniciar o driver
    public void init(boolean headless) throws AWTException {
        System.setProperty("webdriver.chrome.driver", "C:\\RecursosPng\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\CLIENTE\\Desktop\\recursosBotProgram\\recursos\\programas\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        if(!headless) { options.addArguments("--headless"); }
        driver = new ChromeDriver(options);
        r = new Robot();
        driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver, TimeUnit.SECONDS);
    }


    //retorno um unico elemento com base na busca
    //em caso de erro retorna null e adiciona o erro ao log com o código
    public WebElement one(String busca){
        try {
            return auxReturnWebDriver(driver, busca).get(0);

        }catch (Exception e){
            return null;
        }
    }


    //retorna um unico elemento com base na busca no elemento no input
    //em caso de erro retorna null e adiciona o erro ao log com o código
    //sobrecaga
    public static WebElement one(WebElement webElement ,String busca){
        try {
            return auxReturnWebElement(webElement, busca).get(0);

        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }


    //retorna um unico elemento com base na busca no driver no input
    //em caso de erro retorna null e adiciona o erro ao log com o código
    //sobrecaga
    public WebElement one(WebDriver webDriver ,String busca){
        try {
            return auxReturnWebDriver(webDriver, busca).get(0);

        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }


    //retorna uma lista de elemento com base na busca
    //em caso de erro retorna null e adiciona o erro ao log com o código
    public List<WebElement> more(String busca){
        try {
            return auxReturnWebDriver(driver, busca);

        }catch (Exception e){
            return null;
        }
    }


    //retorna uma lista de elementos com base na busca do elemento no input
    //em caso de erro retorna null e adiciona o erro ao log com o código
    //sobrecaga
    public static List<WebElement> more(WebElement webElement ,String busca){
        try {
            return auxReturnWebElement(webElement, busca);

        }catch (Exception e){
            return null;
        }
    }


    //retorna uma lista de elementos com base na busca no driver no input
    //em caso de erro retorna null e adiciona o erro ao log com o código
    //sobrecaga
    public List<WebElement> more(WebDriver webDriver ,String busca){
        try {
            return auxReturnWebDriver(webDriver, busca);

        }catch (Exception e){
            return null;
        }
    }


    //retorna o primeiro elemento com base na busca da lista no input
    //em caso de erro retorna null e adiciona o erro ao log com o código
    //sobrecaga
    public static WebElement one(List<WebElement> listaWE ,String busca){
        for(WebElement dados : listaWE){
            try {
                return auxReturnWebElement(dados, busca).get(0);

            }catch (Exception e){
                return null;
            }
        }
        return null;
    }


    //retorna uma lista de elementos com base na busca de todos os elemento da lista no input
    //em caso de erro retorna null e adiciona o erro ao log com o código
    //sobrecaga
    public static List<WebElement> more(List<WebElement> listaWE ,String busca){
        List<WebElement> retorno = new ArrayList<>();
        List<WebElement> temp = new ArrayList<>();

        for(WebElement dados : listaWE){
            try {
                temp = (auxReturnWebElement(dados, busca));

            }catch (Exception e){
                continue;
            }

            for(WebElement elementos : temp) {
                retorno.add(elementos);

            }
        }
        return retorno;
    }


    //tem função de aguardar enquanto o elemento procurado está ativo na tela
    public void waitWhileEnable(String busca){
        driver.manage().timeouts().implicitlyWait(200,TimeUnit.MILLISECONDS);
        boolean esperar = true;
        try{
            while(esperar) {
                time(500);
                esperar = false;
                if(one(busca) != null){
                    esperar = true;
                }
            }
        }catch (Exception ignored){ }
        driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
    }


    ////tem função de aguardar enquanto o elemento procurado está ativo na tela
    //sobrecarga
    public void waitWhileEnable(WebElement webElement ,String busca){
        driver.manage().timeouts().implicitlyWait(200,TimeUnit.MILLISECONDS);
        boolean esperar = true;
        try{
            while(esperar) {
                time(500);
                esperar = false;
                if(one(webElement ,busca) != null){
                    esperar = true;
                }
            }
        }catch (Exception ignored){ }
        driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
    }


    //tem função de aguardar enquanto o elemento procurado não está ativo na tela
    public void waitWhileDisable(String busca){
        driver.manage().timeouts().implicitlyWait(200,TimeUnit.MILLISECONDS);
        boolean esperar = true;
        try{
            while(esperar) {
                time(500);
                esperar = false;
                if(one(busca) == null){
                    esperar = true;
                }
            }
        }catch (Exception ignored){ }
        driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
    }


    //tem função de aguardar enquanto o elemento procurado não está ativo na tela
    //sobrecarga
    public void waitWhileDisable(WebElement webElement ,String busca){
        driver.manage().timeouts().implicitlyWait(200,TimeUnit.MILLISECONDS);
        boolean esperar = true;
        try{
            while(esperar) {
                time(500);
                esperar = false;
                if(one(webElement ,busca) == null){
                    esperar = true;
                }
            }
        }catch (Exception ignored){ }
        driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
    }


    //retorna um boolean com base da atividade do elemento procurado
    public boolean visible(String busca){
        driver.manage().timeouts().implicitlyWait(200,TimeUnit.MILLISECONDS);
        time(1000);

        if(one(busca) == null){
            driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
            return false;
        }
        else{
            driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
            return true;
        }

    }

    //retorna um boolean com base da atividade do elemento procurado
    public boolean visible(String busca, int tempoMLS){
        driver.manage().timeouts().implicitlyWait(tempoMLS,TimeUnit.MILLISECONDS);

        if(one(busca) == null){
            driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
            return false;
        }
        else{
            driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
            return true;
        }

    }

    //retorna um boolean com base da atividade do elemento procurado
    public boolean visible(WebElement webElement ,String busca){
        driver.manage().timeouts().implicitlyWait(200,TimeUnit.MILLISECONDS);
        time(1000);

        if(one(webElement ,busca) == null){
            driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
            return false;
        }
        else{
            driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
            return true;
        }
    }

    //retorna um boolean com base da atividade do elemento procurado
    public boolean visible(WebElement webElement ,String busca, int tempoMLS){
        driver.manage().timeouts().implicitlyWait(tempoMLS,TimeUnit.MILLISECONDS);

        if(one(webElement ,busca) == null){
            driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
            return false;
        }
        else{
            driver.manage().timeouts().implicitlyWait(Bots.tempoDeEsperaDriver,TimeUnit.SECONDS);
            return true;
        }
    }

    //navega até uma página https
    public void page(String url){
        driver.get(url);
        time(1000);
    }


    //retorna o driver do navegador
    public WebDriver driver(){
        return driver;
    }


    //encerrar a execução e mostrar os erros de execução
    public synchronized void exit(){
        driver.quit();
    }


    //tempo
    public void time(long milissegundo){
            try{sleep(milissegundo);}catch (Exception ignored){}
    }


    //recarregar a pagina
    public void reload(){
        time(1000);
        one("css html").sendKeys(Keys.F5);
        time((long) Bots.tempoDeEsperaDriver/2);
    }


    //obter o numero do bot (em caso de uso)
    public int getNumero(){
        return meuNumero;
    }

    //filtro de pesquisa de WebElements
    public static List<WebElement> auxReturnWebElement(WebElement webElement, String busca){

        if (busca.startsWith("x")) {
            return webElement.findElements(By.xpath(busca.substring(2)));
        } else if (busca.startsWith("i")) {
            return webElement.findElements(By.id(busca.substring(2)));
        } else if (busca.startsWith("n")) {
            return webElement.findElements(By.name(busca.substring(2)));
        } else if (busca.startsWith("css")) {
            return webElement.findElements(By.cssSelector(busca.substring(4)));
        } else if (busca.startsWith("c")) {
            return webElement.findElements(By.className(busca.substring(2)));
        } else if (busca.startsWith("t")) {
            return webElement.findElements(By.linkText(busca.substring(2)));
        } else if (busca.startsWith("p")) {
            return webElement.findElements(By.partialLinkText(busca.substring(2)));
        } else if (busca.startsWith("tn")) {
            return webElement.findElements(By.tagName(busca.substring(3)));
        } else{
            System.out.println("erro ao filtrar elemento ["+busca+"]");
            System.exit(0);
        }

        return null;
    }


    //filtro de pesquisa de WebElements usando o WebDriver
    public List<WebElement> auxReturnWebDriver(WebDriver webDriver, String busca) {

        if (busca.startsWith("x")) {
            return webDriver.findElements(By.xpath(busca.substring(2)));
        } else if (busca.startsWith("i")) {
            return webDriver.findElements(By.id(busca.substring(2)));
        } else if (busca.startsWith("n")) {
            return webDriver.findElements(By.name(busca.substring(2)));
        } else if (busca.startsWith("css")) {
            return webDriver.findElements(By.cssSelector(busca.substring(4)));
        } else if (busca.startsWith("c")) {
            return webDriver.findElements(By.className(busca.substring(2)));
        } else if (busca.startsWith("t")) {
            return webDriver.findElements(By.linkText(busca.substring(2)));
        } else if (busca.startsWith("p")) {
            return webDriver.findElements(By.partialLinkText(busca.substring(2)));
        } else if (busca.startsWith("tn")) {
            return webDriver.findElements(By.tagName(busca.substring(3)));
        } else {
            System.out.println("erro ao filtrar elemento [" + busca + "]");
            System.exit(0);
        }

        return null;
    }
}
