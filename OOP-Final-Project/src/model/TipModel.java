package model;

import control.Controller;

/**
 * 文字提示模型
 */
public class TipModel extends Tick implements Port
{

    private PlayerModel player = null;

    private String tipString = "我超，冰！";

    public String getTipString()
    {
        return tipString;
    }

    public void setTipString(String tipString)
    {
        this.tipString = tipString;
    }

    public PlayerModel getPlayer()
    {
        return player;
    }

    /**
     * 显示文字提示
     */
    public void showTextTip(PlayerModel player, String str, int time)
    {
        this.player = player;
        this.setTipString(str);
        this.setStartTick(this.curTick);
        this.setNextTick(this.curTick + time * Controller.rate);
    }

    public void initGame()
    {
    }
    public void update(long tick)
    {
        this.curTick = tick;
    }
}
