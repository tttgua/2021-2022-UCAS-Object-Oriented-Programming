package control;

import model.PlayerModel;

import java.util.List;

/**
 * ��Ϸ���п�����
 */
public class RunController
{

    /**
     * ����б�
     */
    private List<PlayerModel> players;

    /**
     * ��ǰ�������
     */
    private PlayerModel curPlayer = null;

    /**
     * ���ӵ�ǰ����
     */
    private int point;

    /**
     * ���ʹ�ÿ�Ƭ״̬
     */
    public static int STATE_CARD = 1;
    /**
     * �������״̬
     */
    public static int STATE_THROWDICE = 2;
    /**
     * ����ƶ�״̬
     */
    public static int STATE_MOVE = 3;
    /**
     * ��Ϸ��ֹ״̬
     */
    public static int GAME_STOP = 4;
    /**
     * Ŀǰ״̬
     */
    private int curState;
    /**
     * ��Ϸ��������
     */
    public static int day = 1;
    /**
     * ��Ϸ�������� -1Ϊ������
     */
    public static int GAME_DAY = -1;
    /**
     * ��Ϸ��Ǯ���� -1Ϊ������
     */
    public static int MONEY_MAX = -1;

    /**
     * ��ҳ�ʼ��Ǯ
     */
    public static int PLAYER_CASH = 10000;

    private Controller controller;

    public RunController(Controller controller, List<PlayerModel> players)
    {
        this.controller = controller;
        this.players = players;
    }

    /**
     * ��õ�ǰ���״̬
     */
    public int getCurState()
    {
        return this.curState;
    }

    /**
     * ת�����״̬
     */
    public void nextState()
    {
        // �����ж���Ϸ�Ƿ���Ҫ����
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
     * ���˲���
     */
    private void nextPlayer()
    {
        if (this.curPlayer.equals(this.players.get(0)))
        {
            this.curPlayer = this.players.get(1);
        } else
        {
            this.curPlayer = this.players.get(0);
            // ����1Pʱ��Ϸ��������
            day++;
        }
    }

    /**
     * �ж���Ϸ�Ƿ����
     */
    public boolean isGameContinue()
    {
        PlayerModel p1 = this.curPlayer;
        PlayerModel p2 = this.curPlayer.getOtherPlayer();
        // ����
        if (GAME_DAY > 0 && day >= GAME_DAY)
        {
            this.controller.gameOver();
            return false;
        }
        // ����Ǯ
        if (MONEY_MAX > 0 && p1.getCash() >= MONEY_MAX)
        {
            this.controller.gameOver();
            return false;
        } else if (MONEY_MAX > 0 && p2.getCash() >= MONEY_MAX)
        {
            this.controller.gameOver();
            return false;
        }
        // �Ʋ�
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
     * ��ʼ��Ϸ����
     */
    public void startGame()
    {
        // �趨��ǰ��Ϸ���
        this.curPlayer = this.players.get(0);
        // �趨��ǰ���״̬Ϊ��ʹ�ÿ�Ƭ��
        this.curState = STATE_CARD;
        // ����趨����
        this.setPoint((int) (Math.random() * 6));
        // �׸����ʹ�ÿ�Ƭ
        this.controller.useCard();
    }

}
