package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import control.Controller;
/**
 * ��Ϸ�����
 */
public class MyJPanel extends JPanel
{

    private JLayeredPane layeredPane;

    private List<Panel> lays = null;
    private Background backgroundUI = null;
    private Lands landsUI = null;
    private Buildings buildingsUI = null;
    private Players playersUI = null;
    private Tip tip = null;
    private PlayersInfo playersInfo = null;
    private Dice dice = null;
    private Event event = null;
    private ShopUI shopUI = null;
    private Day day = null;

    private EndPlayerInfo endPlayerInfo = null;
    private Controller controller = null;

    /**
     * ȫ�����Ͻ�X
     */
    public int posX = 100;
    /**
     * ȫ�����Ͻ�Y
     */
    public int posY = 100;

    public MyJPanel()
    {
        setLayout(new BorderLayout());
        initGame();
    }

    /**
     * ��ʼ����Ϸ
     */
    private void initGame()
    {
        // ��ӿ�����
        controller = new Controller();
        initUI();
        // ��panel���������
        controller.setMainPanel(this);
    }

    public Controller getControl()
    {
        return controller;
    }

    /**
     * ��ʼ��UI
     */
    private void initUI()
    {
        this.backgroundUI = new Background(0, 0, 950, 650, controller.getBackground(), this);
        this.landsUI = new Lands(posX, posY, 950, 650, controller.getLand());
        this.buildingsUI = new Buildings(posX, posY, 950, 650, controller.getBuilding());
        this.playersUI = new Players(posX, posY, 950, 650, controller.getPlayers());
        this.playersInfo = new PlayersInfo(posX + 304, posY + 124, 170, 232, controller.getPlayers());
        this.tip = new Tip(0, 0, 950, 650, controller.getTip());
        this.dice = new Dice(posX + 514, posY + 124, 170, 90, controller);
        this.event = new Event(0, 0, 950, 650, controller.getEvents());
        this.shopUI = new ShopUI(0, 0, 750, 650, controller, this);
        this.day = new Day(640, 0, 300, 80, controller.getRunning());
        this.endPlayerInfo = new EndPlayerInfo(controller.getPlayers(), this);
        // �½�һ������������panel���
        lays = new ArrayList<>();
        lays.add(backgroundUI);
        lays.add(dice);
        lays.add(playersUI);
        lays.add(playersInfo);
        lays.add(buildingsUI);
        lays.add(landsUI);
        lays.add(backgroundUI);
        lays.add(day);

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        int count = 1;
        layeredPane.add(this.event, count++);
        layeredPane.add(this.tip, count++);
        layeredPane.add(this.dice, count++);
        layeredPane.add(this.playersUI, count++);
        layeredPane.add(this.playersInfo, count++);
        layeredPane.add(this.buildingsUI, count++);
        layeredPane.add(this.landsUI, count++);
        layeredPane.add(this.day, count++);
        layeredPane.add(this.backgroundUI, count++);
        layeredPane.add(this.shopUI, count++);
        layeredPane.add(this.endPlayerInfo, count);

        add(layeredPane);
    }


    public ShopUI getShop()
    {
        return this.shopUI;
    }

    public JLayeredPane getLayeredPane()
    {
        return layeredPane;
    }

    public EndPlayerInfo getEndPlayerInfo()
    {
        return endPlayerInfo;
    }

    /**
     * ��ʼ����ϷPanel
     */
    public void initGamePanel()
    {
        for (Panel temp : this.lays)
        {
            temp.initPanel();
        }
    }

}
