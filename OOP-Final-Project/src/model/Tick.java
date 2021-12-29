package model;

/**
 * ��ʱ����
 * ģ�͸���
 */
public class Tick
{
    /**
     * ��ǰtickֵ
     */
    protected long curTick;
    /**
     * �¼���ʼtickֵ
     */
    protected long startTick;
    /**
     * �˶�����tick(rate*��);
     */
    protected long lastTick;
    /**
     * ��һ��tickֵ
     */
    protected long nextTick;

    public long getLastTick()
    {
        return lastTick;
    }

    public long getCurTick()
    {
        return curTick;
    }

    public long getStartTick()
    {
        return startTick;
    }

    public long getNextTick()
    {
        return nextTick;
    }

    public void setStartTick(long startTick)
    {
        this.startTick = startTick;
    }

    public void setNextTick(long nextTick)
    {
        this.nextTick = nextTick;
    }

}
