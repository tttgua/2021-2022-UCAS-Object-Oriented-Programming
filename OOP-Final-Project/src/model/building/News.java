package model.building;

import java.awt.Image;
import javax.swing.ImageIcon;

import constant.GameConstant;

/**
 * 随机事件
 */
public class News extends Building
{
    /*
     * 事件图片
     */
    private final Image[] imageEvents = {
            EVENT_BAD_HOSPITAL_3,
            EVENT_BAD_LOSE_1000,
            EVENT_BAD_LOSE_2000,
            EVENT_BAD_LOSE_3000,
            EVENT_BAD_LOSE_40S,
            EVENT_LUCK_GAIN_1000,
            EVENT_LUCK_GAIN_2000,
            EVENT_LUCK_GAIN_3999_100S};

    public static Image EVENT_BAD_LOSE_3000 = new ImageIcon(
            "images/event/bad_lose3000.jpg").getImage();

    public static Image EVENT_BAD_HOSPITAL_3 = new ImageIcon(
            "images/event/bad_hospital_3_01.jpg").getImage();

    public static Image EVENT_BAD_LOSE_1000 = new ImageIcon(
            "images/event/bad_lose1000.jpg").getImage();

    public static Image EVENT_BAD_LOSE_2000 = new ImageIcon(
            "images/event/bad_lose2000.jpg").getImage();

    public static Image EVENT_BAD_LOSE_40S = new ImageIcon(
            "images/event/bad_lose40s.jpg").getImage();

    public static Image EVENT_LUCK_GAIN_1000 = new ImageIcon(
            "images/event/luck_gain1000.jpg").getImage();

    public static Image EVENT_LUCK_GAIN_2000 = new ImageIcon(
            "images/event/luck_gain2000.jpg").getImage();

    public static Image EVENT_LUCK_GAIN_3999_100S = new ImageIcon(
            "images/event/luck_gain3999_100.jpg").getImage();


    public News(int posX, int posY)
    {
        super(posX, posY);
        this.name = "随机事件";
    }

    public Image[] getImageEvents()
    {
        return imageEvents;
    }

    public int getEvent()
    {
        return GameConstant.NEWS_EVENT;
    }
    public int passEvent()
    {
        return 0;
    }
}
