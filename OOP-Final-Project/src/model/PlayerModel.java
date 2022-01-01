package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import control.RunController;
import model.building.Building;
import model.card.Card;
import control.Controller;

/**
 * 玩家模型
 */
public class PlayerModel extends Tick implements Observer
{
    /**
     * 姓名
     */
    private String name;
    /**
     * 玩家使用人物
     */
    private int imgID = 0;
    /**
     * 现金
     */
    private int cash;
    /**
     * 点券
     */
    private int ticket;
    /**
     * 当前坐标x
     */
    private int x;
    /**
     * 当前坐标y
     */
    private int y;
    /**
     * 剩余住院天数
     */
    private int hospitalRemain;
    /**
     * 剩余监狱天数
     */
    private int prisonRemain;

    /**
     * 玩家编号
     */
    private int playerID;

    /**
     * 玩家拥有房屋链表
     */
    private List<Building> buildings = new ArrayList<>();

    /**
     * 拥有卡片
     */
    private List<Card> cards = new ArrayList<>();

    /**
     * 最大可持有卡片
     */
    public static int MAX_CAN_HOLD_CARDS = 3;

    private Image[] playerIMG = new Image[100];

    /**
     * 敌对玩家
     */
    private PlayerModel otherPlayer = null;
    /**
     * 游戏控制器
     */
    private Controller controller;

    public PlayerModel(int playerID, Controller controller)
    {
        this.name = "";
        this.playerID = playerID;
        this.controller = controller;
    }

    public List<Card> getCards()
    {
        return cards;
    }

    public List<Building> getBuildings()
    {
        return buildings;
    }

    public int getPrisonRemain()
    {
        return prisonRemain;
    }

    public void setPrisonRemain(int prisonRemain)
    {
        this.prisonRemain = prisonRemain;
    }

    /**
     * 初始化玩家人物图像
     */
    private void initPlayerIMG()
    {
        this.playerIMG[0] = new ImageIcon("images/player/" + this.getImgID()
                + "/normal.png").getImage();
        this.playerIMG[1] = new ImageIcon("images/player/" + this.getImgID()
                + "/mini.png").getImage();
        this.playerIMG[2] = new ImageIcon("images/player/" + this.getImgID()
                + "/mini1.png").getImage();
        this.playerIMG[3] = new ImageIcon("images/player/" + this.getImgID()
                + "/mini2.png").getImage();
    }

    /**
     * 获取玩家人物图像
     */
    public Image getIMG(String str)
    {
        if (str.equals("normal"))
            return this.playerIMG[0];
        else if (str.equals("mini"))
            return this.playerIMG[1];
        else if (str.equals("mini1"))
            return this.playerIMG[2];
        else if (str.equals("mini2"))
            return this.playerIMG[3];
        else
            return null;
    }

    public PlayerModel getOtherPlayer()
    {
        return otherPlayer;
    }

    public void setOtherPlayer(PlayerModel otherPlayer)
    {
        this.otherPlayer = otherPlayer;
    }

    public int getPlayerID()
    {
        return playerID;
    }

    public int getHospitalRemain()
    {
        return hospitalRemain;
    }

    public void setHospitalRemain(int hospitalRemain)
    {
        this.hospitalRemain = hospitalRemain;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getImgID()
    {
        return imgID;
    }

    public void setImgID(int imgID)
    {
        this.imgID = imgID;
    }

    public int getCash()
    {
        return cash;
    }

    public void setCash(int cash)
    {
        this.cash = cash;
    }

    public int getTicket()
    {
        return ticket;
    }

    public void setTicket(int ticket)
    {
        this.ticket = ticket;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void initGame()
    {
        this.initPlayerIMG();
        this.lastTick = Controller.rate / 5;
        this.cash = RunController.PLAYER_CASH;
        this.ticket = 100;
    }

    public void update(long tick)
    {
        this.curTick = tick;
        if (this.startTick < this.curTick && this.nextTick >= this.curTick)
        {
            this.controller.movePlayer();
            if (this.nextTick != this.curTick)
            {
                this.controller.passBuilding();
            }
            if (this.nextTick == this.curTick)
            {
                this.controller.playerStopJudge();
            }
        }
    }
}
