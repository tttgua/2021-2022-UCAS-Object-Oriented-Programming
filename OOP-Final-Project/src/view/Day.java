package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import control.RunController;
/**
 * 游戏天数面板
 */
public class Day extends Panel
{
    private Image info = new ImageIcon("images/days/info.png").getImage();
    private Image[] numberIMG = {
            new ImageIcon("images/days/0.png").getImage(),
            new ImageIcon("images/days/1.png").getImage(),
            new ImageIcon("images/days/2.png").getImage(),
            new ImageIcon("images/days/3.png").getImage(),
            new ImageIcon("images/days/4.png").getImage(),
            new ImageIcon("images/days/5.png").getImage(),
            new ImageIcon("images/days/6.png").getImage(),
            new ImageIcon("images/days/7.png").getImage(),
            new ImageIcon("images/days/8.png").getImage(),
            new ImageIcon("images/days/9.png").getImage()
    };

    private RunController running;

    protected Day(int x, int y, int w, int h, RunController running)
    {
        super(x, y, w, h);
        this.running = running;
    }

    public void paint(Graphics g)
    {

        g.drawImage(info, 0, 0, info.getWidth(null), info.getHeight(null),
                0, 0, info.getWidth(null), info.getHeight(null), null);
        // 绘制天数
        int day = running.getDay();
        int posX = 220;
        int posY = 10;
        while (day > 0)
        {
            int num = day % 10;
            g.drawImage(numberIMG[num], posX, posY, posX + numberIMG[num].getWidth(null), posY + numberIMG[num].getHeight(null) + 6,
                    0, 0, numberIMG[num].getWidth(null), numberIMG[num].getHeight(null), null);
            day /= 10;
            posX -= 20;
        }
    }

    public void initPanel()
    {

    }

}
