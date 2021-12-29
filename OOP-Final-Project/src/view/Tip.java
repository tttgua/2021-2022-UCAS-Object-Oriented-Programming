package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import model.TipModel;

/**
 * 信息提示面板
 */
public class Tip extends Panel
{

    private TipModel tip;
    private Image bg = new ImageIcon("images/window/tip.png").getImage();

    private Point pointWindow;

    public Tip(int x, int y, int w, int h, TipModel tip)
    {
        super(x, y, w, h);
        this.pointWindow = new Point((x + w) / 2, (y + h) / 2);
        this.tip = tip;
    }

    public void paint(Graphics g)
    {
        paintTip(g);
    }

    /**
     * 绘制Tip面板
     */
    private void paintTip(Graphics g)
    {
        if (tip.getStartTick() < tip.getCurTick()
                && tip.getNextTick() >= tip.getCurTick())
        {
            this.pointWindow.x = tip.getPlayer().getX() + 45;
            this.pointWindow.y = tip.getPlayer().getY() + 10;
            g.drawImage(bg, pointWindow.x, pointWindow.y, pointWindow.x + bg.getWidth(null),
                    pointWindow.y + bg.getHeight(null), 0, 0, bg.getWidth(null),
                    bg.getHeight(null), null);
            drawSting(g);
        }
    }

    /**
     * 绘制文字
     */
    private void drawSting(Graphics g)
    {
        String str = this.tip.getTipString();
        int maxSize = 16;
        int posY = 32;
        int front = 0;
        int rear = maxSize;
        while (front < str.length() - 1)
        {
            if (rear >= str.length())
            {
                rear = str.length() - 1;
            }
            char[] tmp = new char[maxSize];
            str.getChars(front, rear, tmp, 0);
            String s = new String(tmp);
            g.drawString(s, pointWindow.x + 20, pointWindow.y + posY);
            front = rear;
            rear += maxSize;
            posY += 20;
        }
    }

    public void initPanel()
    {
    }
}
