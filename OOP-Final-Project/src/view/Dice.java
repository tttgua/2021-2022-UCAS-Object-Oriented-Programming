package view;

import java.awt.*;

import control.Controller;
import model.DiceModel;

/**
 * 骰子面板
 */
public class Dice extends Panel
{

    private DiceModel dice;
    private DiceButton diceButton;


    protected Dice(int x, int y, int w, int h, Controller controller)
    {
        super(x, y, w, h);
        setLayout(null);
        this.dice = controller.getDice();
        this.diceButton = new DiceButton(controller, 105, 32);
        add(diceButton);
    }

    public void paint(Graphics g)
    {
        this.createWindow(g);
        this.paintDice(g, 0, 0);
        this.showDiceButton();
        diceButton.update(g);
    }

    /**
     * 骰子绘制
     */
    private void paintDice(Graphics g, int i, int j)
    {
        if (dice.getStartTick() < dice.getCurTick() && dice.getNextTick() >= dice.getCurTick())
        {
            dice.setDiceState(DiceModel.DICE_RUNNING);
        } else
        {
            dice.setDiceState(DiceModel.DICE_POINT);
        }

        if (dice.getDiceState() == DiceModel.DICE_POINT)
        {
            this.paintPoint(g, i, j);
        } else if (dice.getDiceState() == DiceModel.DICE_RUNNING)
        {
            this.paintRunning(g, i, j, dice.getCurTick() % 4 == 0);
        }
        g.setColor(Color.black);
        g.setFont(new Font("仿宋", Font.BOLD, 16));
        g.drawString(dice.getRunning().getCurPlayer().getName() + ":", i + 100, j + 25);
    }

    /**
     * 绘制骰子运动状态
     */
    public void paintRunning(Graphics g, int x, int y, boolean change)
    {
        if (change)
        {
            dice.addImgCount(1);
        }
        Image tmp = dice.getCurImg();
        g.drawImage(tmp, x, y, x + tmp.getWidth(null),
                y + tmp.getHeight(null), 0, 0, tmp.getWidth(null),
                tmp.getHeight(null), null);
    }

    /**
     * 绘制骰子点数
     */
    public void paintPoint(Graphics g, int x, int y)
    {
        Image temp = dice.getDicePoints()[dice.getPoint()];
        g.drawImage(temp, x, y, x + temp.getWidth(null),
                y + temp.getHeight(null), 0, 0, temp.getWidth(null),
                temp.getHeight(null), null);
    }

    /**
     * 骰子按钮显示
     */
    private void showDiceButton()
    {
        diceButton.setEnabled(dice.isShowDiceButton());
    }

    public void initPanel()
    {
    }

}
