package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * ��ͼ�������
 */
public abstract class Panel extends JPanel
{
    /**
     * �������Ͻ�x����
     */
    protected int x;
    /**
     * �������Ͻ�y����
     */
    protected int y;
    /**
     * ���ڿ��
     */
    protected int w;
    /**
     * ���ڸ߶�
     */
    protected int h;

    protected static final int SIZE = 2;
    protected static Image WINDOW_IMG = new ImageIcon("images/window/window.png").getImage();
    protected static int WINDOW_W = WINDOW_IMG.getWidth(null);
    protected static int WINDOW_H = WINDOW_IMG.getHeight(null);

    protected Panel(int x, int y, int w, int h)
    {
        this.setBounds(x, y, w, h);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getW()
    {
        return w;
    }

    public int getH()
    {
        return h;
    }

    /**
     * ���ƴ��ڱ߿�
     */
    public void createWindow(Graphics g)
    {
        g.drawImage(WINDOW_IMG, 0, 0, SIZE, SIZE, 0, 0, SIZE, SIZE,
                null);
        g.drawImage(WINDOW_IMG, SIZE, 0, w - SIZE, SIZE, SIZE, 0,
                WINDOW_W - SIZE, SIZE, null);
        g.drawImage(WINDOW_IMG, w - SIZE, 0, w, SIZE, WINDOW_W
                - SIZE, 0, WINDOW_W, SIZE, null);
        g.drawImage(WINDOW_IMG, 0, SIZE, SIZE, h - SIZE, 0, SIZE,
                SIZE, WINDOW_H - SIZE, null);
        g.drawImage(WINDOW_IMG, SIZE, SIZE, w - SIZE, h - SIZE,
                SIZE, SIZE, WINDOW_W - SIZE, WINDOW_H - SIZE, null);
        g.drawImage(WINDOW_IMG, w - SIZE, SIZE, w, h - SIZE,
                WINDOW_W - SIZE, SIZE, WINDOW_W, WINDOW_H - SIZE, null);
        g.drawImage(WINDOW_IMG, 0, h - SIZE, SIZE, h, 0, WINDOW_H
                - SIZE, SIZE, WINDOW_H, null);
        g.drawImage(WINDOW_IMG, SIZE, h - SIZE, w - SIZE, h,
                SIZE, 50 - SIZE, WINDOW_W - SIZE, WINDOW_H, null);
        g.drawImage(WINDOW_IMG, w - SIZE, h - SIZE, w, h,
                WINDOW_W - SIZE, WINDOW_H - SIZE, WINDOW_W, WINDOW_H, null);
    }

    abstract public void paint(Graphics g);

    /**
     * ��ʼ��panel
     */
    abstract public void initPanel();
}
