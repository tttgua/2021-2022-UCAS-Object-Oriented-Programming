package constant;

/**
 * 一些游戏状态常量
 *
 *
 */
public class GameConstant
{
    // 停留在建筑返回状态
    public final static int HOSPITAL_EVENT = 1;
    public final static int HOUSE_EVENT = 2;
    public final static int NEWS_EVENT = 3;
    public final static int ORIGIN_EVENT = 4;
    public final static int TICKET_EVENT = 5;
    public final static int PRISON_EVENT = 6;
    public final static int SHOP_EVENT = 7;

    // 路过建筑返回状态（暂且仅有路过原点状态）
    public final static int ORIGIN_PASS_EVENT = 1;

    // 使用卡片返回状态
    public final static int CARD_CONTROLDICE = 1;
    public final static int CARD_PURCHASE = 2;
    public final static int CARD_TAX = 3;
    public final static int CARD_TRAP = 4;
}
