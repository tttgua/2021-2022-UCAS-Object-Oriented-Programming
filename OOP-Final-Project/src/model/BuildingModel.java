package model;

import java.util.ArrayList;
import java.util.List;

import model.building.Building;
import model.building.Hospital;
import model.building.House;
import model.building.News;
import model.building.Origin;
import model.building.Ticket;
import model.building.Prison;
import model.building.Shop;

/**
 * 全局建筑模型
 */
public class BuildingModel extends Tick implements Observer
{
    /**
     * 建筑链表
     */
    private List<Building> buildings = null;

    private LandModel land;


    public BuildingModel(LandModel land)
    {
        this.land = land;
    }


    /**
     * 初始化建筑
     */
    private void initBuilding()
    {
        buildings = new ArrayList<>();
        int[][] temp = this.land.getLand();
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[i].length; j++)
            {
                switch (temp[i][j])
                {
                    case LandModel.SPACE:
                        Building tempBuidling = new House(i, j);
                        tempBuidling.setPurchasable(true);
                        buildings.add(tempBuidling);
                        break;

                    case LandModel.NEWS:
                        buildings.add(new News(i, j));
                        break;
                    case LandModel.ORIGIN:
                        buildings.add(new Origin(i, j));
                        break;
                    case LandModel.TICKET_10:
                        buildings.add(new Ticket(i, j, 10));
                        break;
                    case LandModel.TICKET_30:
                        buildings.add(new Ticket(i, j, 30));
                        break;
                    case LandModel.TICKET_50:
                        buildings.add(new Ticket(i, j, 50));
                        break;
                    case LandModel.SHOP:
                        buildings.add(new Shop(i, j));
                        break;
                    case LandModel.HOSPITAL:
                        buildings.add(new Hospital(i, j));
                        LandModel.hospital = new java.awt.Point(j * 60, i * 60);
                        break;
                    case LandModel.PRISON:
                        buildings.add(new Prison(i, j));
                        LandModel.prison = new java.awt.Point(j * 60, i * 60);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 获得建筑链表
     */
    public List<Building> getBuilding()
    {
        return buildings;
    }

    /**
     * 获得当前位置房屋
     * ——方法重载
     */
    public Building getBuilding(int x, int y)
    {
        for (Building temp : this.buildings)
        {
            if (temp.getPosX() == x && temp.getPosY() == y)
            {
                return temp;
            }
        }
        return null;
    }


    public void initGame()
    {
        initBuilding();
    }

    public void update(long tick)
    {
        this.curTick = tick;
    }
}