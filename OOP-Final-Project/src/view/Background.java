package view;

import java.awt.Graphics;
import java.awt.Image;

import model.BackgroundModel;


/**
 * �������
 */
public class Background extends Panel
{

    /**
     * ����ͼƬ
     */
    private Image bg = null;
    /**
     * ����ģ��
     */
    private BackgroundModel background;
    private MyJPanel panel;

    protected Background(int x, int y, int w, int h,
                         BackgroundModel background, MyJPanel panel)
    {
        super(x, y, w, h);
        this.background = background;
        this.panel = panel;
    }

    public void paint(Graphics g)
    {
        this.paintBg(g);
    }

    /**
     * �������Ʒ���
     */
    public void paintBg(Graphics g)
    {
        g.drawImage(this.bg, 0, 0, this.bg.getWidth(null),
                this.bg.getHeight(null), 0, 0, this.bg.getWidth(null),
                this.bg.getHeight(null), null);
    }

    public void initPanel()
    {
        this.bg = background.getBg();
    }

}
