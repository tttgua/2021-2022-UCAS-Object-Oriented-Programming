package model;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ����ģ��
 */
public class BackgroundModel extends Tick implements Observer
{
    /**
     * ����ͼ��
     */
    private Image bg = null;

    public Image getBg()
    {
        return bg;
    }

    public void initGame()
    {
        this.bg = new ImageIcon("images/background/bg.jpg").getImage();
    }

    public void update(long tick)
    {
        this.curTick = tick;
    }
}
