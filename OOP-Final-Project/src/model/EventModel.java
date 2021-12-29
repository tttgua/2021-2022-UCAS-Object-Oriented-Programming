package model;

import java.awt.Image;

import model.building.Ticket;

import control.Controller;

/**
 * 事件模型
 */
public class EventModel extends Tick implements Port
{
    /**
     * 显示图片
     */
    private Image img = null;
    /**
     * 图片显示位置
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
     * 显示图片
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
