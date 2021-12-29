package model;

/**
 * 接口
 * 提取所有模型的共性
 */
public interface Port
{
    void update(long tick);
    void initGame();
}
