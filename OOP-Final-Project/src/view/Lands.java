package view;

import java.awt.Graphics;
import java.awt.Image;

import model.LandModel;


/**
 * 土地面板
 */
public class Lands extends Panel
{
    /**
     * 土地模型
     */
    private LandModel land;
    /**
     * 土地图片
     */
    private Image landsIMG;

    protected Lands(int x, int y, int w, int h, LandModel land)
    {
        super(x, y, w, h);
        this.land = land;
        this.landsIMG = this.land.getLandsIMG();
    }

    public void paint(Graphics g)
    {
        this.paintLands(g);
    }

    /**
     * 土地绘制
     */
    private void paintLands(Graphics g)
    {
        int x = 0;
        int y = 0;
        for (int i = 0; i < land.getLand().length; i++)
        {
            for (int j = 0; j < land.getLand()[i].length; j++)
            {
                if (land.getLand()[i][j] != 0)
                {
                    g.drawImage(landsIMG, x + j * 60, y + i * 60,
                            x + (j + 1) * 60, y + (i + 1) * 60,
                            (land.getLand()[i][j] - 1) * 60, 0, land.getLand()[i][j] * 60, 60, null);
                }
            }
        }
    }

    public void initPanel()
    {
    }

}
