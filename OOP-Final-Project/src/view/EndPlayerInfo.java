package view;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.RunController;

import model.PlayerModel;

/**
 * 游戏结束时显示的玩家信息面板
 */

public class EndPlayerInfo extends JPanel
{

    private Image bg = new ImageIcon("images/end/bg.png").getImage();
    private Image str = new ImageIcon("images/end/str.png").getImage();
    private Image none = new ImageIcon("images/end/none.png").getImage();
    private Image win = new ImageIcon("images/end/win.png").getImage();
    private Image lose = new ImageIcon("images/end/lose.png").getImage();
    private Image[] numberIMG = {
            new ImageIcon("images/end/number/0.png").getImage(),
            new ImageIcon("images/end/number/1.png").getImage(),
            new ImageIcon("images/end/number/2.png").getImage(),
            new ImageIcon("images/end/number/3.png").getImage(),
            new ImageIcon("images/end/number/4.png").getImage(),
            new ImageIcon("images/end/number/5.png").getImage(),
            new ImageIcon("images/end/number/6.png").getImage(),
            new ImageIcon("images/end/number/7.png").getImage(),
            new ImageIcon("images/end/number/8.png").getImage(),
            new ImageIcon("images/end/number/9.png").getImage()
    };

    private int x, y, w, h;

    private Point origin = new Point();

    private List<PlayerModel> players;
    private MyJPanel panel;

    public EndPlayerInfo(List<PlayerModel> players, MyJPanel panel)
    {
        this.players = players;
        this.panel = panel;
        setLayout(null);
        initBounds();
        addListener();
    }

    private void initBounds()
    {
        this.x = (950 - bg.getWidth(null)) / 2;
        this.y = (650 - bg.getHeight(null)) / 2;
        this.w = bg.getWidth(null);
        this.h = bg.getHeight(null);
        setBounds(x, y, w, h);
    }

    /**
     * 将窗体显现
     */
    public void moveToFront()
    {
        this.panel.getLayeredPane().moveToFront(this);
    }

    public void paint(Graphics g)
    {
        this.setOpaque(false);
        g.drawImage(bg, 0, 0, w, h, this);
        drawPlayers(g);
    }

    private void drawPlayers(Graphics g)
    {
        int y = 92;
        for (PlayerModel a : players)
        {
            drawPlayer(g, a, y);
            y += 180;
        }
        String str = ""; //结束原因
        if (RunController.day >= RunController.GAME_DAY)
        {
            str = "达到游戏天数 " + RunController.GAME_DAY + " 天.";
        }
        PlayerModel p1 = players.get(0);
        PlayerModel p2 = players.get(1);
        if (RunController.MONEY_MAX > 0 && p1.getCash() >= RunController.MONEY_MAX)
        {
            str = "\"" + p1.getName() + "\" 金钱达到游戏金钱上限.";
        } else if (RunController.MONEY_MAX > 0 && p2.getCash() >= RunController.MONEY_MAX)
        {
            str = "\"" + p2.getName() + "\" 金钱达到游戏金钱上限.";
        }
        if (p1.getCash() < 0)
        {
            str = "\"" + p1.getName() + "\"破产.";
        } else if (p2.getCash() < 0)
        {
            str = "\"" + p2.getName() + "\"破产.";
        }
        FontMetrics fm = g.getFontMetrics();
        g.drawString("结束原因：" + str, 200 - fm.stringWidth(str) / 2, 86);
    }

    private void drawPlayer(Graphics g, PlayerModel player, int y)
    {
        Image img = player.getIMG("mini2");
        Image WinOrLose = (player.getCash() > player.getOtherPlayer().getCash()) ? win : lose;
        g.drawImage(str, 44 + 130, y + 40,
                44 + 130 + str.getWidth(null), y + 40 + str.getHeight(null),
                0, 0, str.getWidth(null), str.getHeight(null), null);
        g.drawImage(img, 44, y + 40 - 14,
                44 + img.getWidth(null), y + 40 - 14 + img.getHeight(null),
                0, 0, img.getWidth(null), img.getHeight(null), null);
        g.setFont(new Font(null, 1, 16));
        int posX = 44 + 130 + str.getWidth(null) + 10;
        int posY = y + 40 + 16;
        g.drawString(player.getName(), posX, posY);
        int cash = player.getCash();
        int cPosX = posX + 70;
        int cPosY = posY + 14;
        if (cash <= 0)
        {
            g.drawImage(none, cPosX - 16, cPosY - 10,
                    cPosX + none.getWidth(null), cPosY + none.getHeight(null),
                    0, 0, none.getWidth(null), none.getHeight(null), null);
        }
        while (cash > 0)
        {
            int num = cash % 10;
            g.drawImage(numberIMG[num], cPosX, cPosY,
                    cPosX + numberIMG[num].getWidth(null), cPosY + numberIMG[num].getHeight(null),
                    0, 0, numberIMG[num].getWidth(null), numberIMG[num].getHeight(null), null);
            cash /= 10;
            cPosX -= 16;
        }
        int ticket = player.getTicket();
        int nPosX = posX + 70;
        int nPosY = posY + 14 + 29;
        if (ticket <= 0)
        {
            g.drawImage(none, nPosX - 16, nPosY - 10,
                    nPosX + none.getWidth(null), nPosY + none.getHeight(null),
                    0, 0, none.getWidth(null), none.getHeight(null), null);
        }
        while (ticket > 0)
        {
            int num = ticket % 10;
            g.drawImage(numberIMG[num], nPosX, nPosY,
                    nPosX + numberIMG[num].getWidth(null), nPosY + numberIMG[num].getHeight(null),
                    0, 0, numberIMG[num].getWidth(null), numberIMG[num].getHeight(null), null);
            ticket /= 10;
            nPosX -= 16;
        }
        int buildings = player.getBuildings().size();
        int bPosX = posX + 70;
        int bPosY = posY + 14 + 29 * 2;
        if (buildings <= 0)
        {
            g.drawImage(none, bPosX - 16, bPosY - 10,
                    bPosX + none.getWidth(null), bPosY + none.getHeight(null),
                    0, 0, none.getWidth(null), none.getHeight(null), null);
        }
        while (buildings > 0)
        {
            int num = buildings % 10;
            g.drawImage(numberIMG[num], bPosX, bPosY,
                    bPosX + numberIMG[num].getWidth(null), bPosY + numberIMG[num].getHeight(null),
                    0, 0, numberIMG[num].getWidth(null), numberIMG[num].getHeight(null), null);
            buildings /= 10;
            bPosX -= 16;
        }
        g.drawImage(WinOrLose, 44 + 130 + 187, y + 40 - 28,
                44 + 130 + 187 + WinOrLose.getWidth(null), y + 40 - 28 + WinOrLose.getHeight(null),
                0, 0, WinOrLose.getWidth(null), WinOrLose.getHeight(null), null);
    }

    private void addListener()
    {
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            { // 按下
                origin.x = e.getX();
                origin.y = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            { // 拖动
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
