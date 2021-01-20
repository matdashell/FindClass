package fbSelenium.frame;

import FindClass.Bots;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaInfoThread {
    private static final JPanel infoThread = new JPanel();
    private static final JPanel contentPanelInfoThread = new JPanel();
    private static final List<JPanel> separators = new ArrayList<>();
    public static List<JLabel> comentarios = new ArrayList<>();
    public static List<JLabel> numeroThread = new ArrayList<>();
    public static List<JLabel> pesquisaThread = new ArrayList<>();
    public static List<JTextPane> texThread = new ArrayList<>();
    private static final List<ScrollPane> scrollTextThread = new ArrayList<ScrollPane>();
    private static final List<JButton> buttonList = new ArrayList<>();
    private static JScrollPane scrollInfoThread;

    private static final Font fonteLabelBold = new Font("FreeMono", Font.ITALIC | Font.BOLD, 14);
    private static final Font fonteLabel = new Font("FreeMono", Font.ITALIC, 14);

    TelaInfoThread(){


    }

    static void iniciar(){
        //set dos blocos de informam sobre as threads
        for(int i = 0; i < Bots.numeroDeThreads; i++){
            separators.add(new JPanel(null));
            numeroThread.add(new JLabel("num.: "+i));
            comentarios.add(new JLabel("| C.: 0"));
            pesquisaThread.add(new JLabel("pesq.: "+Bots.listaPesquisa[i]));
            texThread.add(new JTextPane());
            scrollTextThread.add(new ScrollPane());

            numeroThread.get(i).setFont(fonteLabelBold);
            pesquisaThread.get(i).setFont(fonteLabel);
            comentarios.get(i).setFont(fonteLabel);

            separators.get(i).setPreferredSize(new Dimension(600,25));
            comentarios.get(i).setBounds(75,0,75,25);
            numeroThread.get(i).setBounds(0,0,75,25);
            pesquisaThread.get(i).setBounds(0,25,150,25);
            scrollTextThread.get(i).setBounds(150,0,425,50);

            scrollTextThread.get(i).add(texThread.get(i));
            separators.get(i).add(comentarios.get(i));
            separators.get(i).add(numeroThread.get(i));
            separators.get(i).add(pesquisaThread.get(i));
            separators.get(i).add(scrollTextThread.get(i));
        }

        //setando infoThread
        infoThread.setLayout(null);
        infoThread.setPreferredSize(new Dimension(575, 50 * separators.size()));

        //adicionando os blocos separators dentro da Panel infoThread
        for(int i = 0; i < separators.size(); i++){
            separators.get(i).setBounds(0,50*i,600,50);
            infoThread.add(separators.get(i));
        }

        //setando informações do scrollInfoThread
        scrollInfoThread = new JScrollPane(infoThread);
        scrollInfoThread.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollInfoThread.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollInfoThread.getVerticalScrollBar().setUnitIncrement(5);
        scrollInfoThread.setBounds(1,0,600,300);

        //adicionando Scroll ao contentPanel
        contentPanelInfoThread.setBounds(0,0,600,300);
        contentPanelInfoThread.setLayout(null);
        contentPanelInfoThread.add(scrollInfoThread);
    }

    static JPanel getPanel(){
        return contentPanelInfoThread;
    }

    static void setPesquisa(String pesquisa, int thread){
        texThread.get(thread).setText(pesquisa);
    }
}
