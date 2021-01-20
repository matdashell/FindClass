package fbSelenium.frame;

import FindClass.Bots;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaInfoThread {

    private static final JPanel contentPanelInforThread = new JPanel();
    private static final List<JPanel> blocosInfo = new ArrayList<>();
    public static List<JLabel> numeroThread = new ArrayList<>();
    public static List<JLabel> pesquisa = new ArrayList<>();
    public static List<JLabel> quantidade = new ArrayList<>();
    public static List<JLabel> restantes = new ArrayList<>();
    public static List<JLabel> log = new ArrayList<>();
    public static List<JLabel> planoFundo = new ArrayList<>();

    private static Font font = new Font("Cinzel", Font.ITALIC ,14);

    TelaInfoThread() {
        configurarComponentes();
    }

    public static JPanel getPanel(){
        return contentPanelInforThread;
    }

    private static void configurarComponentes(){

        for(int i = 0; i < Bots.numeroDeThreads; i++){

            blocosInfo.add(new JPanel(null));
            numeroThread.add(new JLabel("Numero:"));
            pesquisa.add(new JLabel("Pesq.:"));
            quantidade.add(new JLabel("Total:"));
            restantes.add(new JLabel("Restante:"));
            log.add(new JLabel("Log:"));
            planoFundo.add(new JLabel(new ImageIcon("C:\\RecursosPng\\planoFundoInfoThread.png")));

            blocosInfo.get(i).setBounds(0,50*i,900,50);
            numeroThread.get(i).setBounds(10,11,100,25);
            pesquisa.get(i).setBounds(145,11,100,25);
            quantidade.get(i).setBounds(280,11,100,25);
            restantes.get(i).setBounds(420,11,100,25);
            log.get(i).setBounds(555,11,400,25);
            planoFundo.get(i).setBounds(0,0,900,50);

            numeroThread.get(i).setFont(font);
            pesquisa.get(i).setFont(font);
            quantidade.get(i).setFont(font);
            restantes.get(i).setFont(font);
            log.get(i).setFont(font);

            blocosInfo.get(i).add(numeroThread.get(i));
            blocosInfo.get(i).add(pesquisa.get(i));
            blocosInfo.get(i).add(quantidade.get(i));
            blocosInfo.get(i).add(restantes.get(i));
            blocosInfo.get(i).add(log.get(i));
            blocosInfo.get(i).add(planoFundo.get(i));

            contentPanelInforThread.add(blocosInfo.get(i));
        }
        contentPanelInforThread.setLayout(null);
        contentPanelInforThread.setBounds(60,245,900,200);
    }

    public static void main(String[] args) {
        new TelaInfoThread();
    }
}
