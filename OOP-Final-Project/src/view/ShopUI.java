package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.Controller;

import model.PlayerModel;

import model.building.Shop;

import model.card.Card;

/**
 * �̵����
 */
public class ShopUI extends JPanel
{
    /**
     * �̵걳��
     */
    private Image bg = new ImageIcon("images/shop/bg_ui.png").getImage();

    /**
     * �̵꿨Ƭ���ܿ�
     */
    private Image itemBg = new ImageIcon("images/shop/item_bg.png").getImage();

    /**
     * �̵�����
     * ������ʾ���п�Ƭ
     */
    private Image sideBarBg = new ImageIcon("images/shop/sidebar.png").getImage();

    /**
     * �̵괰�����ڵ�
     */
    private Point position = new Point(240, 100);

    /**
     * �Ӵ���λ��
     */
    private Point childPosition = new Point(230, 180);

    private List<ShopButton> card = new ArrayList<>();

    private ShopButton close;
    private ShopButton buy;
    private ShopButton cancel;
    private Shop shop;

    /**
     * �Ƿ���ʾ������ϸ����
     */
    private boolean showItemBg = true;
    /**
     * ��ǰ���µĿ�Ƭ
     */
    private int chooseCard = -1;

    private MyJPanel panel;

    private Controller controller;

    private int x, y, w, h;
    /**
     * ����ڴ����ϵ�λ��
     */
    private Point origin = new Point();

    protected ShopUI(int x, int y, int w, int h, Controller controller, MyJPanel panel)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setBounds(x, y, w, h);
        this.controller = controller;
        this.panel = panel;
        setLayout(null);
        initButton();
        addListener();
    }

    private void initButton()
    {
        this.close = new ShopButton(this, "close", position.x + 450,
                position.y + 3, controller);
        add(close);
        this.buy = new ShopButton(this, "buy", position.x + childPosition.x + 110,
                position.y + childPosition.y + 97, controller);
        add(buy);
        this.cancel = new ShopButton(this, "cancel", position.x + childPosition.x + 159,
                position.y + childPosition.y + 97, controller);
        add(cancel);
    }

    public int getChooseCard()
    {
        return chooseCard;
    }

    public Shop getShop()
    {
        return shop;
    }

    /**
     * ���������ɵĿ�Ƭ
     */
    public void setChooseCard(ShopButton button)
    {
        for (int i = 0; i < card.size(); i++)
        {
            if (card.get(i).equals(button))
            {
                chooseCard = i;
                return;
            }
        }
        chooseCard = -1;
    }

    /**
     * ��ʼ����Ƭ��ʾ
     */
    public void addCards(Shop shop)
    {
        removeAll();
        initButton();
        this.shop = shop;
        List<Card> cardTmp = shop.getCards();
        card = new ArrayList<>();
        card.add(new ShopButton(this, cardTmp.get(0).getName(),
                position.x + 85, position.y + 178, controller));
        card.add(new ShopButton(this, cardTmp.get(1).getName(),
                position.x + 85, position.y + 178 + 50 * 1, controller));
        card.add(new ShopButton(this, cardTmp.get(2).getName(),
                position.x + 85, position.y + 178 + 50 * 2, controller));
        for (ShopButton a : card)
        {
            add(a);
        }
    }

    public void paint(Graphics g)
    {
        drawShop(g);
        drawSideBar(g);
        for (ShopButton c : card)
        {
            c.update(g);
        }
        updateUI(g);
    }

    /**
     * ���Ʋ�߿�
     */
    private void drawSideBar(Graphics g)
    {
        Point sideBar = new Point(position.x - 125, position.y + 32);
        g.drawImage(sideBarBg, sideBar.x, sideBar.y,
                sideBar.x + sideBarBg.getWidth(null),
                sideBar.y + sideBarBg.getHeight(null), 0, 0,
                sideBarBg.getWidth(null), sideBarBg.getHeight(null), null);

        PlayerModel player = controller.getRunning().getCurPlayer();
        Image playerLogo = player.getIMG("normal");
        int posX = sideBar.x + 10;
        int posY = sideBar.y;

        g.drawImage(playerLogo, posX + 10, posY + 20, posX + 100, posY + 100, 0, 0,
                playerLogo.getWidth(null), playerLogo.getHeight(null), null);

        List<Card> cardsTmp = player.getCards();
        for (int i = 0; i < cardsTmp.size(); i++)
        {
            FontMetrics fm = g.getFontMetrics();
            String str = cardsTmp.get(i).getChName();
            g.setFont(new Font("����", Font.BOLD, 20));
            g.drawString(str, sideBar.x + 30,
                    sideBar.y + 184 + 50 * i);
        }
    }

    /**
     * ˢ��UI���
     */
    private void updateUI(Graphics g)
    {
        // �жϵ�ǰ�Ƿ���ʾ�Ӵ���
        if (chooseCard >= 0)
        {
            showItemBg = true;
        } else
        {
            showItemBg = false;
        }
        close.update(g);
        if (showItemBg)
        {
            drawItemBg(g);
        }
        buy.setEnabled(showItemBg);
        buy.update(g);
        cancel.setEnabled(showItemBg);
        cancel.update(g);
    }

    /**
     * ������ʾ��ϸ����UI
     */
    private void drawItemBg(Graphics g)
    {
        g.drawImage(itemBg, position.x + childPosition.x, position.y + childPosition.y,
                position.x + childPosition.x + itemBg.getWidth(null), position.y
                        + childPosition.y + itemBg.getHeight(null), 0, 0,
                itemBg.getWidth(null), itemBg.getHeight(null), null);
        Card cardTmp = this.shop.getCards().get(chooseCard);
        // ��Ƭ����
        String str = cardTmp.getInfo();
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.RED);
        g.setFont(new Font("����", Font.BOLD, 14));
        g.drawString(str, position.x + childPosition.x + 12,
                position.y + childPosition.y + 60);
        // ��Ƭ�۸�
        g.setColor(Color.WHITE);
        g.setFont(new Font("����", Font.BOLD, 16));
        String str1 = cardTmp.getPrice() + "��ȯ";
        g.drawString(str1, position.x + childPosition.x + 80 - fm.stringWidth(str1),
                position.y + childPosition.y + 110);

    }

    /**
     * ����������
     */
    public void moveToBack()
    {
        for (ShopButton a : card)
        {
            remove(a);
        }
        card.clear();
        this.panel.getLayeredPane().moveToBack(this);
    }

    /**
     * ����������
     */
    public void moveToFront()
    {
        this.panel.getLayeredPane().moveToFront(this);
    }

    /**
     * �����̵�
     */
    private void drawShop(Graphics g)
    {
        g.drawImage(bg, position.x, position.y, position.x + bg.getWidth(null),
                position.y + bg.getHeight(null), 0, 0, bg.getWidth(null),
                bg.getHeight(null), null);
        PlayerModel player = controller.getRunning().getCurPlayer();
        FontMetrics fm = g.getFontMetrics();
        g.drawString(player.getTicket() + "",
                position.x + 180 - fm.stringWidth(player.getTicket() + ""),
                position.y + 330);
        g.drawString("���ĵ�ȯ", position.x + 90, position.y + 330);
    }

    /**
     * ����һ����Ƭ��ť
     */
    public Image[] createCardImg(String name)
    {
        return new Image[]{
                new ImageIcon("images/shop/card/" + name + "/normal.png")
                        .getImage(),
                new ImageIcon("images/shop/card/" + name + "/mouseOver.png")
                        .getImage(),
                new ImageIcon("images/shop/card/" + name + "/pressed.png")
                        .getImage(),
                new ImageIcon("images/shop/card/" + name + "/disabled.png")
                        .getImage()};
    }

    public List<ShopButton> getCard()
    {
        return card;
    }

    public void setChooseCard(int chooseCard)
    {
        this.chooseCard = chooseCard;
    }

    private void addListener()
    {
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            { // ����
                origin.x = e.getX();
                origin.y = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            { // �϶�
                x += e.getX() - origin.x;
                y += e.getY() - origin.y;
                if (x < 0)
                {
                    x = 0;
                }
                if (x + w > 950)
                {
                    x = 950 - w;
                }
                if (y < 0)
                {
                    y = 0;
                }
                if (y + h > 650)
                {
                    y = 650 - h;
                }
                setBounds(x, y, w, h);
            }
        });
    }
}
