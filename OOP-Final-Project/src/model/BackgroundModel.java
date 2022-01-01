package model;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ±³¾°Ä£ÐÍ
 */
public class BackgroundModel extends Tick implements Observer
{
    /**
     * ±³¾°Í¼Ïñ
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
