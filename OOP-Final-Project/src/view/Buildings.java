package view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import model.BuildingModel;
import model.building.Building;


/**
 * �������
 */
public class Buildings extends Panel
{

    /**
     * ���ؽ�����Ϣ
     */
    private BuildingModel informationBuilding;
    /**
     * ��������
     */
    private List<Building> building;

    private Image HOUSE_01 = new ImageIcon("images/building/house01.png").getImage();
    private Image HOUSE_02 = new ImageIcon("images/building/house02.png").getImage();

    protected Buildings(int x, int y, int w, int h, BuildingModel informationBuilding)
    {
        super(x, y, w, h);
        this.informationBuilding = informationBuilding;
    }

    public void paint(Graphics g)
    {
        paintBuildings(g);
    }

    /**
     * ���ƽ�����
     */
    private void paintBuildings(Graphics g)
    {
        for (Building temp : this.building)
        {
            paintBuilding(temp, g);
        }
    }

    private void paintBuilding(Building building, Graphics g)
    {
        int x = 0;
        int y = 0;
        if (building.getOwner() != null)
        {
            int level = building.getLevel();
            int i = building.getPosX();
            int j = building.getPosY();
            Image temp = building.getOwner().getPlayerID() == 1 ? this.HOUSE_01 : this.HOUSE_02;
            if (level > 0)
            {
                g.drawImage(temp, x + j * 60,
                        y + i * 60 - (temp.getHeight(null) - 60), x + (j + 1)
                                * 60, y + (i + 1) * 60, 60 * (level - 1), 0,
                        60 * level, temp.getHeight(null), null);
            }
            // ��ʾ�ز�ӵ����
            g.drawString("" + building.getOwner().getName(), x + j * 60 + 4, y + i * 60 + 14);
        }
    }

    public void initPanel()
    {
        this.building = this.informationBuilding.getBuilding();
    }

}
