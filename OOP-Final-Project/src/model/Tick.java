package model;

/**
 * 计时器类
 * 模型父类
 */
public class Tick
{
    /**
     * 当前tick值
     */
    protected long curTick;
    /**
     * 事件开始tick值
     */
    protected long startTick;
    /**
     * 运动持续tick(rate*秒);
     */
    protected long lastTick;
    /**
     * 下一个tick值
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
