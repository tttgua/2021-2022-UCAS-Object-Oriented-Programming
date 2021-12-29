package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import model.BackgroundModel;
import model.BuildingModel;
import model.DiceModel;
import model.EventModel;
import model.LandModel;
import model.PlayerModel;
import model.Port;
import model.TipModel;
import model.building.*;
import model.card.Card;
import view.MyJPanel;

import constant.GameConstant;
import tools.MyThread;

/**
 * 游戏总控制器
 */
public class Controller
{
    /**
     * 游戏总tick值
     */
    public static long tick;
    /**
     * 帧数
     */
    public static int rate = 60;
    /**
     * 游戏主面板
     */
    private MyJPanel panel;
    /**
     * 游戏对象
     */
    private RunController run;

    private List<Port> models = new ArrayList<>();
    private List<PlayerModel> players = null;
    private BuildingModel building = null;
    private BackgroundModel background = null;
    private LandModel land = null;
    private TipModel tip = null;
    private DiceModel dice = null;
    private EventModel events = null;


    public Controller()
    {
        this.run = new RunController(this, null);
        this.initObject();
        this.run.setPlayers(players);
    }

    public void setPanel(MyJPanel panel)
    {
        this.panel = panel;
    }

    /**
     * 初始化游戏对象
     */
    private void initObject()
    {
        this.events = new EventModel();
        this.models.add(events);
        this.background = new BackgroundModel();
        this.models.add(background);
        this.land = new LandModel();
        this.models.add(land);
        this.tip = new TipModel();
        this.models.add(tip);
        this.building = new BuildingModel(land);
        this.models.add(building);
        this.players = new ArrayList<>();
        this.players.add(new PlayerModel(1, this));
        this.players.add(new PlayerModel(2, this));
        this.models.add(players.get(0));
        this.models.add(players.get(1));
        this.dice = new DiceModel(run);
        this.models.add(dice);
    }

    /**
     * 游戏计时器
     */
    private void createGameTimer()
    {
        /**
         * 游戏计时器
         */
        Timer gameTimer = new Timer();
        gameTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                tick++;
                // 更新各对象
                for (Port temp : models)
                {
                    temp.update(tick);
                }
                // UI更新
                panel.repaint();
            }
        }, 0, (1000 / rate));
    }

    /**
     * 控制器启动
     */
    public void start()
    {
        // 创建一个计时器
        this.createGameTimer();
        for (Port temp : this.models)
        {
            temp.initGame();
        }
        this.run.startGame();
        // panel 初始化
        this.panel.initGamePanel();
    }


    public List<PlayerModel> getPlayers()
    {
        return players;
    }

    public BuildingModel getBuilding()
    {
        return building;
    }

    public BackgroundModel getBackground()
    {
        return background;
    }

    public LandModel getLand()
    {
        return land;
    }

    public TipModel getTip()
    {
        return tip;
    }

    public RunController getRunning()
    {
        return run;
    }

    public DiceModel getDice()
    {
        return dice;
    }

    public EventModel getEvents()
    {
        return events;
    }

    /**
     * 按下掷骰按钮
     */
    public void pressDiceButton()
    {
        PlayerModel p = this.run.getCurPlayer();
        if (p.getHospitalRemain() > 0 || p.getPrisonRemain() > 0)
        {
            this.run.nextState();
            if (p.getHospitalRemain() > 0)
            {
                this.tip.showTextTip(p, p.getName() + "住院中\r\n剩余"
                        + p.getHospitalRemain() + "天.", 3);
            } else if (p.getPrisonRemain() > 0)
            {
                this.tip.showTextTip(p, p.getName() + "坐牢中\r\n剩余"
                        + p.getPrisonRemain() + "天.", 3);
            }
            this.run.nextState();
        } else
        {
            this.dice.setStartTick(Controller.tick);
            this.dice.setNextTick(this.dice.getStartTick() + this.dice.getLastTick());
            // 将运行对象点数传入骰子模型对象
            this.dice.setPoint(this.run.getPoint());
            this.run.nextState();
            // 骰子转动完毕后玩家移动
            this.run.getCurPlayer().setStartTick(this.dice.getNextTick() + 10);
            this.run.getCurPlayer().setNextTick(this.run.getCurPlayer().getStartTick()
                            + this.run.getCurPlayer().getLastTick() * (this.run.getPoint() + 1));
        }
    }

    /**
     * 玩家移动
     */
    public void movePlayer()
    {
        for (int i = 0; i < (60 / this.run.getCurPlayer().getLastTick()); i++)
        {
            this.move();
        }
    }
    /**
     * 玩家移动方法
     */
    private void move()
    {
        PlayerModel p = this.run.getCurPlayer();
        if (p.getX() < 12 * 60 && p.getY() == 0)
        {
            // →
            p.setX(p.getX() + 1);
        } else if (p.getX() == 12 * 60 && p.getY() < 7 * 60)
        {
            // ↓
            p.setY(p.getY() + 1);
        } else if (p.getX() > 0 && p.getY() == 7 * 60)
        {
            // ←
            p.setX(p.getX() - 1);
        } else if (p.getX() == 0 && p.getY() > 0)
        {
            // ↑
            p.setY(p.getY() - 1);
        } else if (p.getX() > 0 && p.getY() == 2 * 60 && p.getX() != 12 * 60)
        {
            // hospital ←
            p.setX(p.getX() - 1);
        } else if (p.getX() > 0 && p.getY() == 5 * 60 && p.getX() != 12 * 60)
        {
            // prison →
            p.setX(p.getX() + 1);
        }
    }

    /**
     * 玩家中途路过建筑
     */
    public void passBuilding()
    {
        PlayerModel player = this.run.getCurPlayer();
        Building building = this.building.getBuilding(player.getY() / 60, player.getX() / 60);
        if (building != null && player.getX() % 60 == 0 && player.getY() % 60 == 0)
        {
            // 经过房屋发生事件
            int event = building.passEvent();
            // 进入经过房屋事件处理
            handlePassEvent(building, event, player);
        }
    }

    /**
     * 经过房屋事件处理
     */
    private void handlePassEvent(Building b, int event, PlayerModel player)
    {
        switch (event)
        {
            case GameConstant.ORIGIN_PASS_EVENT:
                // 中途经过原点
                passOrigin(b, player);
                break;
            default:
                break;
        }
    }

    /**
     * 中途经过原点
     */
    private void passOrigin(Building b, PlayerModel player)
    {
        this.tip.showTextTip(player, player.getName() + " 路过原点，奖励 "
                + ((Origin) b).getPassReward() + "金币.", 3);
        player.setCash(player.getCash() + ((Origin) b).getPassReward());
    }


    /**
     * 玩家移动完毕，停下判断
     */
    public void playerStopJudge()
    {
        // 当前玩家
        PlayerModel player = this.run.getCurPlayer();
        if (player.getHospitalRemain() > 0)
        {
            this.tip.showTextTip(player, player.getName() + "当前在医院,不能移动.",
                    2);
            // 更换玩家状态
            this.run.nextState();
        } else if (player.getPrisonRemain() > 0)
        {
            this.tip.showTextTip(player, player.getName() + "当前在监狱,不能移动.",
                    2);
            // 更换玩家状态
            this.run.nextState();
        } else
        {
            // 进行玩家操作（买房 事件等）
            this.playerStop();
        }
    }

    /**
     * 玩家移动完毕，停下操作
     */
    public void playerStop()
    {
        // 当前玩家
        PlayerModel player = this.run.getCurPlayer();
        // 该地点房屋
        Building building = this.building.getBuilding(player.getY() / 60, player.getX() / 60);
        if (building != null)
        {
            int event = building.getEvent();
            handleStopEvent(building, event, player);
        }
    }

    /**
     * 停留建筑处理
     */
    private void handleStopEvent(Building b, int event, PlayerModel player)
    {
        switch (event)
        {
            case GameConstant.HOUSE_EVENT:
                // 停留在可操作土地
                stopInBuilding(b, player);
                break;
            case GameConstant.NEWS_EVENT:
                // 停留在新闻点上
                stopInNews(b, player);
                break;
            case GameConstant.ORIGIN_EVENT:
                // 停留在原点
                stopInOrigin(b, player);
                break;
            case GameConstant.TICKET_EVENT:
                // 停留在点券位
                stopInPoint(b, player);
                break;
            case GameConstant.SHOP_EVENT:
                // 停留在商店
                stopInShop(b, player);
                break;
        }

    }

    /**
     * 停留在商店
     */
    private void stopInShop(Building b, PlayerModel player)
    {
        if (player.getTicket() > 0)
        {
            // 为商店的货架从新生成商品
            ((Shop) b).createCards();
            // 为商店面板更新新的卡片商品
            this.panel.getShop().addCards((Shop) b);
            this.panel.getShop().moveToFront();
        } else
        {
            this.run.nextState();
        }
    }

    /**
     * 停留在点卷位
     */
    private void stopInPoint(Building b, PlayerModel player)
    {
        player.setTicket(((Ticket) b).getTicketPoint() + player.getTicket());
        this.tip.showTextTip(player, player.getName() + " \r\n获得 "
                + ((Ticket) b).getTicketPoint() + "点券.", 3);
        new Thread(new MyThread(run, 1)).start();
    }


    /**
     * 停留在原点
     */
    private void stopInOrigin(Building b, PlayerModel player)
    {
        this.tip.showTextTip(player, player.getName() + " 在起点停留，\r\n奖励 "
                + ((Origin) b).getReward() + "金币.", 3);
        player.setCash(player.getCash() + ((Origin) b).getReward());
        new Thread(new MyThread(run, 1)).start();
    }

    /**
     * 停留在问号点上
     */
    private void stopInNews(Building b, PlayerModel player)
    {
        int random = (int) (Math.random() * ((News) b).getImageEvents().length);
        switch (random)
        {
            case 0:
                player.setHospitalRemain(player.getHospitalRemain() + 3);
                if (LandModel.hospital != null)
                {
                    player.setX(LandModel.hospital.x);
                    player.setY(LandModel.hospital.y);
                }
                break;
            case 1:
                player.setCash(player.getCash() - 1000);
                break;
            case 2:
                player.setCash(player.getCash() - 2000);
                break;
            case 3:
                player.setCash(player.getCash() - 3000);
                break;
            case 4:
                if (player.getTicket() < 40)
                {
                    stopInNews(b, player);
                    return;
                }
                player.setTicket(player.getTicket() - 40);
                break;
            case 5:
                player.setCash(player.getCash() + 1000);
                break;
            case 6:
                player.setCash(player.getCash() + 2000);
                break;
            case 7:
                player.setCash(player.getCash() + 3999);
                player.setTicket(player.getTicket() + 100);
                break;
            default:
                break;
        }
        // 在事件层显示事件
        this.events.showImg(((News) b).getImageEvents()[random], 3, new Ticket(
                350, 160, 0));
        new Thread(new MyThread(run, 3)).start();
    }

    /**
     * 停留在可操作土地
     */
    private void stopInBuilding(Building b, PlayerModel player)
    {
        if (b.isPurchasable())
        {// 玩家房屋
            if (b.getOwner() == null)
            { // 无人房屋
                // 执行买房操作
                this.buyHouse(b, player);
            } else
            {// 有人房屋
                if (b.getOwner().equals(player))
                {// 自己房屋
                    // 执行升级房屋操作
                    this.upHouseLevel(b, player);
                } else
                {// 别人房屋
                    // 执行交税操作
                    this.giveTax(b, player);
                }
            }
        }
    }

    /**
     * 执行交税操作
     */
    private void giveTax(Building b, PlayerModel player)
    {
        if (b.getOwner().getHospitalRemain() > 0)
        {
            // 增加文本提示
            this.tip.showTextTip(player, b.getOwner().getName()
                    + "正在住院,免交过路费.", 3);
        } else if (b.getOwner().getPrisonRemain() > 0)
        {
            // 增加文本提示
            this.tip.showTextTip(player, b.getOwner().getName()
                    + "正在监狱,免交过路费.", 3);
        } else
        {
            int revenue = b.getRevenue();
            // 该玩家减少金币
            player.setCash(player.getCash() - revenue);
            // 业主得到金币
            b.getOwner().setCash(b.getOwner().getCash() + revenue);
            // 增加文本提示
            this.tip.showTextTip(player, player.getName() + "到达"
                    + b.getOwner().getName() + "的房屋，\r\n过路费:" + revenue + "金币.", 3);

        }
        new Thread(new MyThread(run, 1)).start();
    }

    /**
     * 执行买房操作
     */
    private void buyHouse(Building b, PlayerModel player)
    {
        int price = b.getPrice();
        int choose = JOptionPane.showConfirmDialog(
                null,
                "您好，" + player.getName() + "\r\n" + "是否购买这块地？\r\n"
                        + b.getName() + "→" + b.getUpName() + "\r\n" + "价格："
                        + price + " 金币.");

        if (choose == JOptionPane.OK_OPTION)
        {
            // 购买
            if (player.getCash() >= price)
            {
                b.setOwner(player);
                b.setLevel(1);
                player.getBuildings().add(b);
                player.setCash(player.getCash() - price);
                this.tip.showTextTip(player, player.getName()
                        + " 买下了一块空地，\r\n花费了: " + price + "金币. ", 3);
            } else
            {
                this.tip.showTextTip(player, player.getName()
                        + " 金币不足,操作失败. ", 3);
            }
        }
        new Thread(new MyThread(run, 1)).start();
    }

    /**
     * 执行升级房屋操作
     */
    private void upHouseLevel(Building b, PlayerModel player)
    {
        if (b.canUpLevel())
        {
            // 升级房屋
            int price = b.getPrice();
            String name = b.getName();
            String upName = b.getUpName();
            int choose = JOptionPane.showConfirmDialog(null,
                    "您好，" + player.getName() + "\r\n" + "是否升级这块地？\r\n" + name
                            + "→" + upName + "\r\n" + "价格：" + price + " 金币.");
            if (choose == JOptionPane.OK_OPTION)
            {
                if (player.getCash() >= price)
                {
                    b.setLevel(b.getLevel() + 1);
                    player.setCash(player.getCash() - price);
                    this.tip.showTextTip(player, player.getName() + " 从 "
                            + name + " 升级成 " + upName + "，\r\n花费了 " + price
                            + "金币. ", 3);
                } else
                {
                    // 增加文本提示
                    this.tip.showTextTip(player, player.getName()
                            + " 金币不足,操作失败. ", 3);
                }
            }
        }
        new Thread(new MyThread(run, 1)).start();
    }


    /**
     * 使用卡片
     */
    public void useCards()
    {
        PlayerModel p = this.run.getCurPlayer();
        while (true)
        {
            if (p.getCards().size() == 0)
            {
                // 无卡片，跳过阶段
                this.run.nextState();
                break;
            } else
            {
                Object[] options = new Object[p.getCards().size() + 1];
                int i;
                for (i = 0; i < p.getCards().size(); i++)
                {
                    options[i] = p.getCards().get(i).getChName() + "\r\n";
                }
                options[i] = "不使用";
                int response = JOptionPane.showOptionDialog(null,
                        " " + p.getName() + "，请选择需要使用的卡片", "卡片使用阶段",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response != i && response != -1)
                {
                    int id = p.getCards().get(response).getCardID();
                    useCard(p.getCards().get(response), id);
                } else
                {
                    // 不使用，跳过阶段.
                    this.run.nextState();
                    break;
                }
            }
        }
    }

    /**
     * 使用卡片
     */
    private void useCard(Card card, int id)
    {
        switch (id)
        {
            case GameConstant.CARD_CONTROLDICE:
                // 使用控骰卡
                useControlDiceCard(card);
                break;
            case GameConstant.CARD_PURCHASE:
                // 使用购地卡
                useHaveCard(card);
                break;
            case GameConstant.CARD_TAX:
                // 使用查税卡
                useTallageCard(card);
                break;
            case GameConstant.CARD_TRAP:
                // 使用陷害卡
                useTrapCard(card);
                break;
        }
    }

    /**
     * 使用陷害卡
     */
    private void useTrapCard(Card card)
    {
        Object[] options = {"确认使用", "重新选择"};
        int response = JOptionPane.showOptionDialog(null, "确认使用\"陷害卡\"将 \""
                        + card.getOwner().getOtherPlayer().getName() + "\"入狱3天?",
                "卡片使用阶段.", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0)
        {
            // 使用
            PlayerModel cPlayer = card.getOwner().getOtherPlayer();
            // 设置天数
            cPlayer.setPrisonRemain(cPlayer.getPrisonRemain() + 3);
            // 玩家位置切换到监狱位置
            if (LandModel.prison != null)
            {
                cPlayer.setX(LandModel.prison.x);
                cPlayer.setY(LandModel.prison.y);
            }
            // 增加文本提示
            this.tip.showTextTip(card.getOwner(), card.getOwner().getName()
                    + " 使用了 \"陷害卡\"，将 \""
                    + card.getOwner().getOtherPlayer().getName()
                    + "\"入狱3天.", 3);
            // 减去卡片
            card.getOwner().getCards().remove(card);
        }
    }

    /**
     * 使用查税卡
     */
    private void useTallageCard(Card card)
    {
        Object[] options = {"确认使用", "重新选择"};
        int response = JOptionPane.showOptionDialog(null, "确认使用\"查税卡\"从 \""
                        + card.getOwner().getOtherPlayer().getName() + "\"手中获得 10%税款?",
                "卡片使用阶段.", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0)
        {
            // 使用
            int money = (int) (card.getOwner().getOtherPlayer().getCash() / 10);
            card.getOwner().setCash(card.getOwner().getCash() + money);
            card.getOwner()
                    .getOtherPlayer()
                    .setCash(card.getOwner().getOtherPlayer().getCash() - money);
            // 增加文本提示
            this.tip.showTextTip(card.getOwner(), card.getOwner().getName()
                    + " 使用了 \"查税卡\"，从 \""
                    + card.getOwner().getOtherPlayer().getName()
                    + "\"手中获得 10%税款", 2);
            // 　减去卡片
            card.getOwner().getCards().remove(card);
        }
    }

    /**
     * 使用购地卡
     */
    private void useHaveCard(Card card)
    {
        // 该地点房屋
        Building building = this.building.getBuilding(
                card.getOwner().getY() / 60, card.getOwner().getX() / 60);
        if (building.getOwner() != null
                && building.getOwner().equals(card.getOwner().getOtherPlayer()))
        {// 是对方的房屋
            Object[] options = {"确认使用", "重新选择"};
            int response = JOptionPane.showOptionDialog(null,
                    "确认使用\"购地卡\"将此地收购？需要花费：" + building.getAllPrice() + " 金币.",
                    "卡片使用阶段.", JOptionPane.YES_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (response == 0)
            {
                if (card.getOwner().getCash() >= building.getAllPrice())
                {
                    // 金币交换
                    building.getOwner().setCash(
                            building.getOwner().getCash()
                                    + building.getAllPrice());
                    card.getOwner().setCash(
                            card.getOwner().getCash() - building.getAllPrice());
                    building.setOwner(card.getOwner());
                    // 增加文本提示
                    this.tip.showTextTip(card.getOwner(), card.getOwner()
                            .getName() + " 使用了 \"购地卡\"，收购获得了该土地. ", 2);
                    // 　减去卡片
                    card.getOwner().getCards().remove(card);
                } else
                {
                    Object[] options1 = {"重新选择"};
                    JOptionPane.showOptionDialog(null, " 金币不足，无法购买房屋!",
                            "卡片使用阶段.", JOptionPane.YES_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, options1,
                            options1[0]);
                }
            }
        } else
        {
            Object[] options1 = {"重新选择"};
            JOptionPane.showOptionDialog(null, "此房屋无法使用该卡片.", "卡片使用阶段.",
                    JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options1, options1[0]);
        }
    }

    /**
     * 使用控骰卡
     */
    private void useControlDiceCard(Card card)
    {
        Object[] options = {"1点", "2点", "3点", "4点", "5点", "6点", "重新选择"};
        int response = JOptionPane.showOptionDialog(null,
                "确认使用\"遥控骰子卡\"遥控骰子点数?", "卡片使用阶段.", JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (response == -1 || response == 6)
        {
            return;
        } else
        {
            // 使用
            this.run.setPoint(response);
            // 增加文本提示
            this.tip.showTextTip(card.getOwner(), card.getOwner().getName()
                    + " 使用了 \"遥控骰子卡\".", 2);
            // 减去卡片
            card.getOwner().getCards().remove(card);
        }
    }

    /**
     * 退出商店
     */
    public void exitShop()
    {
        new Thread(new MyThread(run, 1)).start();
    }

    /**
     * 商店里买卡片操作
     */
    public void buyCard(Shop shop)
    {
        int chooseCard = this.panel.getShop().getChooseCard();
        if (chooseCard >= 0 && this.panel.getShop().getCard().get(chooseCard) != null)
        {
            // 购买卡片 如果购买成功
            if (this.buyCard(shop, chooseCard))
            {
                this.panel.getShop().getCard().get(chooseCard).setEnabled(false);
                this.panel.getShop().setChooseCard(-1);
            }
        }
    }

    /**
     * 购买卡片
     */
    public boolean buyCard(Shop shop, int p)
    {
        if (this.panel.getShop().getCard().get(p) != null)
        {
            if (this.run.getCurPlayer().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS)
            {
                JOptionPane.showMessageDialog(null, "您最大可持有:"
                        + PlayerModel.MAX_CAN_HOLD_CARDS + "张卡片,目前已经不能再购买了!");
                return false;
            }
            if (this.run.getCurPlayer().getTicket() < shop.getCards().get(p)
                    .getPrice())
            {
                JOptionPane.showMessageDialog(null, "当前卡片需要:"
                        + shop.getCards().get(p).getPrice() + "点券,您的点券不足.");
                return false;
            }
            // 设置卡片拥有者
            shop.getCards().get(p).setOwner(this.run.getCurPlayer());
            // 向玩家卡片库中添加卡片
            this.run.getCurPlayer().getCards().add(shop.getCards().get(p));
            // 减去对应点券
            this.run.getCurPlayer().setTicket(
                    this.run.getCurPlayer().getTicket()
                            - shop.getCards().get(p).getPrice());
        }
        return true;
    }

    /**
     * 变卖地产(不成功返回true)
     */
    public boolean sellOff(PlayerModel p)
    {
        for (int i = p.getBuildings().size() - 1; i >= 0; i--)
        {
            Building tmp = p.getBuildings().get(i);
            p.setCash(p.getCash() + tmp.getAllPrice());
            this.tip.showTextTip(p, p.getName()
                    + ":\r\n时运不济，变卖一处地产\r\n获得" + tmp.getAllPrice() + "金币. ", 2);
            try
            {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            tmp.setOwner(null);
            tmp.setLevel(0);
            p.getBuildings().remove(tmp);
            if (p.getCash() >= 0)
                return false;
        }
        return true;
    }

    /**
     * 游戏结束
     */
    public void gameOver()
    {
        this.run.setCurState(RunController.GAME_STOP);
        this.panel.getEndPlayerInfo().moveToFront();
    }
}
