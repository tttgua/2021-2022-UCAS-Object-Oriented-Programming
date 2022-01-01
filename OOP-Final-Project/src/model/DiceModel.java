package model;

import java.awt.Image;
import javax.swing.ImageIcon;

import control.Controller;
import control.RunController;

/**
 * ����ģ��
 */
public class DiceModel extends Tick implements Observer
{

    /**
     * �����˶�ͼ
     */
    private int point;
    private Image[] diceRunningImg = new Image[]{
            new ImageIcon("images/dice/dice_action_0.png").getImage(),
            new ImageIcon("images/dice/dice_action_1.png").getImage(),
            new ImageIcon("images/dice/dice_action_2.png").getImage(),
            new ImageIcon("images/dice/dice_action_3.png").getImage()};
    /**
     * ���ӵ���ͼ
     */
    private Image[] dicePoints = new Image[]{
            new ImageIcon("images/dice/point/1.png").getImage(),
            new ImageIcon("images/dice/point/2.png").getImage(),
            new ImageIcon("images/dice/point/3.png").getImage(),
            new ImageIcon("images/dice/point/4.png").getImage(),
            new ImageIcon("images/dice/point/5.png").getImage(),
            new ImageIcon("images/dice/point/6.png").getImage()};
    /**
     * ���Ӱ�ťͼ
     */
    public ImageIcon[] diceIMG = new ImageIcon[]{
            new ImageIcon("images/dice/dice.png"),
            new ImageIcon("images/dice/diceEnter.png"),
            new ImageIcon("images/dice/dicePress.png")
    };

    private RunController running;

    /**
     * ͼƬ������������
     */
    private int imgCount;
    /**
     * ���ӹ���״̬
     */
    public static int DICE_RUNNING = 1;
    /**
     * ���Ӳ�������״̬
     */
    public static int DICE_POINT = 2;
    /**
     * ���ӵ�ǰ״̬
     */
    private int diceState;
    /**
     * ��ť��ʾ����
     */
    boolean showButton;

    public DiceModel(RunController running)
    {
        this.running = running;
    }


    public void addImgCount(int add)
    {
        this.imgCount += add;
    }


    public ImageIcon[] getDiceIMG()
    {
        return diceIMG;
    }


    public Image[] getDicePoints()
    {
        return dicePoints;
    }


    /**
     * ��ȡ��ǰ��ʾ�������˶�ͼ
     */
    public Image getCurImg()
    {
        this.imgCount = this.imgCount % this.diceRunningImg.length;
        return this.diceRunningImg[this.imgCount];
    }


    public void setDiceState(int diceState)
    {
        this.diceState = diceState;
    }

    public int getDiceState()
    {
        return diceState;
    }


    /**
     * ȷ�ϰ�ť״̬
     */
    private void checkButton()
    {
        if (this.running.getCurState() == RunController.STATE_THROWDICE)
        {
            this.showButton = true;
        } else
        {
            this.showButton = false;
        }
    }

    public RunController getRunning()
    {
        return running;
    }


    public boolean isShowDiceButton()
    {
        return showButton;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    public void initGame()
    {
        this.diceState = DiceModel.DICE_POINT;
        this.showButton = true;
        this.lastTick = Controller.rate;
    }

    public void update(long tick)
    {
        this.curTick = tick;
        this.checkButton();
    }

}
