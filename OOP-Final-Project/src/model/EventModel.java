package model;

import java.awt.Image;

import model.building.Ticket;

import control.Controller;

/**
 * �¼�ģ��
 */
public class EventModel extends Tick implements Port
{
    /**
     * ��ʾͼƬ
     */
    private Image img = null;
    /**
     * ͼƬ��ʾλ��
     */
    private Ticket imgPosition = null;


    public Image getImg()
    {
        return img;
    }

    public Ticket getImgPosition()
    {
        return imgPosition;
    }

    /**
     * ��ʾͼƬ
     */
    public void showImg(Image img, int time, Ticket point)
    {
        this.img = img;
        this.imgPosition = point;
        this.setStartTick(this.curTick);
        this.setNextTick(this.curTick + (long) time * Controller.rate);
    }

    public void initGame()
    {
    }
    public void update(long tick)
    {
        this.curTick = tick;
    }
}
