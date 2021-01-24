package fbSelenium.frame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaInfoThread {

    private static final JPanel contentPanelInforThread = new JPanel();
    private static final JPanel panelScroll = new JPanel();
    private static final List<JPanel> blocosInfo = new ArrayList<>();
    public static List<JLabel> numeroThread = new ArrayList<>();
    public static List<JLabel> pesquisa = new ArrayList<>();
    public static List<JLabel> quantidade = new ArrayList<>();
    public static List<JLabel> postNum = new ArrayList<>();
    public static List<JLabel> log = new ArrayList<>();
    public static List<JLabel> planoFundo = new ArrayList<>();

    private static final Font font = new Font("Cinzel", Font.ITALIC ,14);

    TelaInfoThread() {
        configurarComponentes();
    }

    public static JPanel getPanel(){
        return panelScroll;
    }

    private static void configurarComponentes(){

        int tamanho = TelaInicial.pesquisas.getText().split(",").length;

        for(int i = 0; i < tamanho; i++){

            blocosInfo.add(new JPanel(null));
            numeroThread.add(new JLabel("Numero: "+i));
            pesquisa.add(new JLabel("Pesq.:"));
            quantidade.add(new JLabel("Total:"));
            postNum.add(new JLabel("Post:"));
            log.add(new JLabel("Log:"));
            planoFundo.add(new JLabel(new ImageIcon("C:\\RecursosPng\\planoFundoInfoThread.png")));

            blocosInfo.get(i).setBounds(0,50*i,975,60);
            numeroThread.get(i).setBounds(10,20,100,25);
            pesquisa.get(i).setBounds(155,20,100,25);
            quantidade.get(i).setBounds(300,20,100,25);
            postNum.get(i).setBounds(450,20,100,25);
            log.get(i).setBounds(600,20,400,25);
            planoFundo.get(i).setBounds(0,0,975,60);

            numeroThread.get(i).setFont(font);
            pesquisa.get(i).setFont(font);
            quantidade.get(i).setFont(font);
            postNum.get(i).setFont(font);
            log.get(i).setFont(font);

            blocosInfo.get(i).add(numeroThread.get(i));
            blocosInfo.get(i).add(pesquisa.get(i));
            blocosInfo.get(i).add(quantidade.get(i));
            blocosInfo.get(i).add(postNum.get(i));
            blocosInfo.get(i).add(log.get(i));
            blocosInfo.get(i).add(planoFundo.get(i));

            contentPanelInforThread.add(blocosInfo.get(i));
        }
        contentPanelInforThread.setLayout(null);
        contentPanelInforThread.setPreferredSize(new Dimension(975, 51*tamanho));
        JScrollPane jScrollPane = new JScrollPane(contentPanelInforThread);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setPreferredSize(new Dimension(980, 240));
        panelScroll.add(jScrollPane);
        panelScroll.setBounds(22,240,980,240);
    }

    public static void main(String[] args) {
        new TelaInfoThread();
    }
}
