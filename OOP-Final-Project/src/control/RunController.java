package control;

import model.PlayerModel;

import java.util.List;

/**
 * 游戏运行控制器
 */
public class RunController
{

    /**
     * 玩家列表
     */
    private List<PlayerModel> players;

    /**
     * 当前操作玩家
     */
    private PlayerModel curPlayer = null;

    /**
     * 骰子当前点数
     */
    private int point;

    /**
     * 玩家使用卡片状态
     */
    public static int STATE_CARD = 1;
    /**
     * 玩家掷点状态
     */
    public static int STATE_THROWDICE = 2;
    /**
     * 玩家移动状态
     */
    public static int STATE_MOVE = 3;
    /**
     * 游戏终止状态
     */
    public static int GAME_STOP = 4;
    /**
     * 目前状态
     */
    private int curState;
    /**
     * 游戏进行天数
     */
    public static int day = 1;
    /**
     * 游戏上限天数 -1为无上限
     */
    public static int GAME_DAY = -1;
    /**
     * 游戏金钱上限 -1为无上限
     */
    public static int MONEY_MAX = -1;

    /**
     * 玩家初始金钱
     */
    public static int PLAYER_CASH = 10000;

    private Controller controller;

    public RunController(Controller controller, List<PlayerModel> players)
    {
        this.controller = controller;
        this.players = players;
    }

    /**
     * 获得当前玩家状态
     */
    public int getCurState()
    {
        return this.curState;
    }

    /**
     * 转换玩家状态
     */
    public void nextState()
    {
        // 首先判断游戏是否需要继续
        if (isGameContinue())
        {
            if (this.curState == STATE_CARD)
            {
                this.curState = STATE_THROWDICE;
            } else if (this.curState == STATE_THROWDICE)
            {
                this.curState = STATE_MOVE;
            } else if (this.curState == STATE_MOVE)
            {
                this.curState = STATE_CARD;
                this.nextPlayer();
                this.setPoint((int) (Math.random() * 6));
                this.controller.useCard();
            }
        }
    }

    public PlayerModel getCurPlayer()
    {
        return this.curPlayer;
    }

    public void setCurState(int curState)
    {
        this.curState = curState;
    }

    /**
     * 换人操作
     */
    private void nextPlayer()
    {
        if (this.curPlayer.equals(this.players.get(0)))
        {
            this.curPlayer = this.players.get(1);
        } else
        {
            this.curPlayer = this.players.get(0);
            // 换回1P时游戏天数增加
            day++;
        }
    }

    /**
     * 判断游戏是否继续
     */
    public boolean isGameContinue()
    {
        PlayerModel p1 = this.curPlayer;
        PlayerModel p2 = this.curPlayer.getOtherPlayer();
        // 天数
        if (GAME_DAY > 0 && day >= GAME_DAY)
        {
            this.controller.gameOver();
            return false;
        }
        // 最大金钱
        if (MONEY_MAX > 0 && p1.getCash() >= MONEY_MAX)
        {
            this.controller.gameOver();
            return false;
        } else if (MONEY_MAX > 0 && p2.getCash() >= MONEY_MAX)
        {
            this.controller.gameOver();
            return false;
        }
        // 破产
        if (p1.getCash() < 0)
        {
            if (this.controller.sellOff(p1))
            {
                this.controller.gameOver();
                return false;
            }
        } else if (p2.getCash() < 0)
        {
            if (this.controller.sellOff(p2))
            {
                this.controller.gameOver();
                return false;
            }
        }
        return true;
    }

    public void setPlayers(List<PlayerModel> players)
    {
        this.players = players;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    public int getDay()
    {
        return day;
    }

    /**
     * 开始游戏设置
     */
    public void startGame()
    {
        // 设定当前游戏玩家
        this.curPlayer = this.players.get(0);
        // 设定当前玩家状态为“使用卡片”
        this.curState = STATE_CARD;
        // 随机设定点数
        this.setPoint((int) (Math.random() * 6));
        // 首个玩家使用卡片
        this.controller.useCard();
    }

}
