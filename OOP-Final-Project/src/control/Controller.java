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
 * ��Ϸ�ܿ�����
 */
public class Controller
{
    /**
     * ��Ϸ��tickֵ
     */
    public static long tick;
    /**
     * ֡��
     */
    public static int rate = 60;
    /**
     * ��Ϸ�����
     */
    private MyJPanel panel;
    /**
     * ��Ϸ����
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
     * ��ʼ����Ϸ����
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
     * ��Ϸ��ʱ��
     */
    private void createGameTimer()
    {
        /**
         * ��Ϸ��ʱ��
         */
        Timer gameTimer = new Timer();
        gameTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                tick++;
                // ���¸�����
                for (Port temp : models)
                {
                    temp.update(tick);
                }
                // UI����
                panel.repaint();
            }
        }, 0, (1000 / rate));
    }

    /**
     * ����������
     */
    public void start()
    {
        // ����һ����ʱ��
        this.createGameTimer();
        for (Port temp : this.models)
        {
            temp.initGame();
        }
        this.run.startGame();
        // panel ��ʼ��
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
     * ����������ť
     */
    public void pressDiceButton()
    {
        PlayerModel p = this.run.getCurPlayer();
        if (p.getHospitalRemain() > 0 || p.getPrisonRemain() > 0)
        {
            this.run.nextState();
            if (p.getHospitalRemain() > 0)
            {
                this.tip.showTextTip(p, p.getName() + "סԺ��\r\nʣ��"
                        + p.getHospitalRemain() + "��.", 3);
            } else if (p.getPrisonRemain() > 0)
            {
                this.tip.showTextTip(p, p.getName() + "������\r\nʣ��"
                        + p.getPrisonRemain() + "��.", 3);
            }
            this.run.nextState();
        } else
        {
            this.dice.setStartTick(Controller.tick);
            this.dice.setNextTick(this.dice.getStartTick() + this.dice.getLastTick());
            // �����ж��������������ģ�Ͷ���
            this.dice.setPoint(this.run.getPoint());
            this.run.nextState();
            // ����ת����Ϻ�����ƶ�
            this.run.getCurPlayer().setStartTick(this.dice.getNextTick() + 10);
            this.run.getCurPlayer().setNextTick(this.run.getCurPlayer().getStartTick()
                            + this.run.getCurPlayer().getLastTick() * (this.run.getPoint() + 1));
        }
    }

    /**
     * ����ƶ�
     */
    public void movePlayer()
    {
        for (int i = 0; i < (60 / this.run.getCurPlayer().getLastTick()); i++)
        {
            this.move();
        }
    }
    /**
     * ����ƶ�����
     */
    private void move()
    {
        PlayerModel p = this.run.getCurPlayer();
        if (p.getX() < 12 * 60 && p.getY() == 0)
        {
            // ��
            p.setX(p.getX() + 1);
        } else if (p.getX() == 12 * 60 && p.getY() < 7 * 60)
        {
            // ��
            p.setY(p.getY() + 1);
        } else if (p.getX() > 0 && p.getY() == 7 * 60)
        {
            // ��
            p.setX(p.getX() - 1);
        } else if (p.getX() == 0 && p.getY() > 0)
        {
            // ��
            p.setY(p.getY() - 1);
        } else if (p.getX() > 0 && p.getY() == 2 * 60 && p.getX() != 12 * 60)
        {
            // hospital ��
            p.setX(p.getX() - 1);
        } else if (p.getX() > 0 && p.getY() == 5 * 60 && p.getX() != 12 * 60)
        {
            // prison ��
            p.setX(p.getX() + 1);
        }
    }

    /**
     * �����;·������
     */
    public void passBuilding()
    {
        PlayerModel player = this.run.getCurPlayer();
        Building building = this.building.getBuilding(player.getY() / 60, player.getX() / 60);
        if (building != null && player.getX() % 60 == 0 && player.getY() % 60 == 0)
        {
            // �������ݷ����¼�
            int event = building.passEvent();
            // ���뾭�������¼�����
            handlePassEvent(building, event, player);
        }
    }

    /**
     * ���������¼�����
     */
    private void handlePassEvent(Building b, int event, PlayerModel player)
    {
        switch (event)
        {
            case GameConstant.ORIGIN_PASS_EVENT:
                // ��;����ԭ��
                passOrigin(b, player);
                break;
            default:
                break;
        }
    }

    /**
     * ��;����ԭ��
     */
    private void passOrigin(Building b, PlayerModel player)
    {
        this.tip.showTextTip(player, player.getName() + " ·��ԭ�㣬���� "
                + ((Origin) b).getPassReward() + "���.", 3);
        player.setCash(player.getCash() + ((Origin) b).getPassReward());
    }


    /**
     * ����ƶ���ϣ�ͣ���ж�
     */
    public void playerStopJudge()
    {
        // ��ǰ���
        PlayerModel player = this.run.getCurPlayer();
        if (player.getHospitalRemain() > 0)
        {
            this.tip.showTextTip(player, player.getName() + "��ǰ��ҽԺ,�����ƶ�.",
                    2);
            // �������״̬
            this.run.nextState();
        } else if (player.getPrisonRemain() > 0)
        {
            this.tip.showTextTip(player, player.getName() + "��ǰ�ڼ���,�����ƶ�.",
                    2);
            // �������״̬
            this.run.nextState();
        } else
        {
            // ������Ҳ������� �¼��ȣ�
            this.playerStop();
        }
    }

    /**
     * ����ƶ���ϣ�ͣ�²���
     */
    public void playerStop()
    {
        // ��ǰ���
        PlayerModel player = this.run.getCurPlayer();
        // �õص㷿��
        Building building = this.building.getBuilding(player.getY() / 60, player.getX() / 60);
        if (building != null)
        {
            int event = building.getEvent();
            handleStopEvent(building, event, player);
        }
    }

    /**
     * ͣ����������
     */
    private void handleStopEvent(Building b, int event, PlayerModel player)
    {
        switch (event)
        {
            case GameConstant.HOUSE_EVENT:
                // ͣ���ڿɲ�������
                stopInBuilding(b, player);
                break;
            case GameConstant.NEWS_EVENT:
                // ͣ�������ŵ���
                stopInNews(b, player);
                break;
            case GameConstant.ORIGIN_EVENT:
                // ͣ����ԭ��
                stopInOrigin(b, player);
                break;
            case GameConstant.TICKET_EVENT:
                // ͣ���ڵ�ȯλ
                stopInPoint(b, player);
                break;
            case GameConstant.SHOP_EVENT:
                // ͣ�����̵�
                stopInShop(b, player);
                break;
        }

    }

    /**
     * ͣ�����̵�
     */
    private void stopInShop(Building b, PlayerModel player)
    {
        if (player.getTicket() > 0)
        {
            // Ϊ�̵�Ļ��ܴ���������Ʒ
            ((Shop) b).createCards();
            // Ϊ�̵��������µĿ�Ƭ��Ʒ
            this.panel.getShop().addCards((Shop) b);
            this.panel.getShop().moveToFront();
        } else
        {
            this.run.nextState();
        }
    }

    /**
     * ͣ���ڵ��λ
     */
    private void stopInPoint(Building b, PlayerModel player)
    {
        player.setTicket(((Ticket) b).getTicketPoint() + player.getTicket());
        this.tip.showTextTip(player, player.getName() + " \r\n��� "
                + ((Ticket) b).getTicketPoint() + "��ȯ.", 3);
        new Thread(new MyThread(run, 1)).start();
    }


    /**
     * ͣ����ԭ��
     */
    private void stopInOrigin(Building b, PlayerModel player)
    {
        this.tip.showTextTip(player, player.getName() + " �����ͣ����\r\n���� "
                + ((Origin) b).getReward() + "���.", 3);
        player.setCash(player.getCash() + ((Origin) b).getReward());
        new Thread(new MyThread(run, 1)).start();
    }

    /**
     * ͣ�����ʺŵ���
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
        // ���¼�����ʾ�¼�
        this.events.showImg(((News) b).getImageEvents()[random], 3, new Ticket(
                350, 160, 0));
        new Thread(new MyThread(run, 3)).start();
    }

    /**
     * ͣ���ڿɲ�������
     */
    private void stopInBuilding(Building b, PlayerModel player)
    {
        if (b.isPurchasable())
        {// ��ҷ���
            if (b.getOwner() == null)
            { // ���˷���
                // ִ���򷿲���
                this.buyHouse(b, player);
            } else
            {// ���˷���
                if (b.getOwner().equals(player))
                {// �Լ�����
                    // ִ���������ݲ���
                    this.upHouseLevel(b, player);
                } else
                {// ���˷���
                    // ִ�н�˰����
                    this.giveTax(b, player);
                }
            }
        }
    }

    /**
     * ִ�н�˰����
     */
    private void giveTax(Building b, PlayerModel player)
    {
        if (b.getOwner().getHospitalRemain() > 0)
        {
            // �����ı���ʾ
            this.tip.showTextTip(player, b.getOwner().getName()
                    + "����סԺ,�⽻��·��.", 3);
        } else if (b.getOwner().getPrisonRemain() > 0)
        {
            // �����ı���ʾ
            this.tip.showTextTip(player, b.getOwner().getName()
                    + "���ڼ���,�⽻��·��.", 3);
        } else
        {
            int revenue = b.getRevenue();
            // ����Ҽ��ٽ��
            player.setCash(player.getCash() - revenue);
            // ҵ���õ����
            b.getOwner().setCash(b.getOwner().getCash() + revenue);
            // �����ı���ʾ
            this.tip.showTextTip(player, player.getName() + "����"
                    + b.getOwner().getName() + "�ķ��ݣ�\r\n��·��:" + revenue + "���.", 3);

        }
        new Thread(new MyThread(run, 1)).start();
    }

    /**
     * ִ���򷿲���
     */
    private void buyHouse(Building b, PlayerModel player)
    {
        int price = b.getPrice();
        int choose = JOptionPane.showConfirmDialog(
                null,
                "���ã�" + player.getName() + "\r\n" + "�Ƿ������أ�\r\n"
                        + b.getName() + "��" + b.getUpName() + "\r\n" + "�۸�"
                        + price + " ���.");

        if (choose == JOptionPane.OK_OPTION)
        {
            // ����
            if (player.getCash() >= price)
            {
                b.setOwner(player);
                b.setLevel(1);
                player.getBuildings().add(b);
                player.setCash(player.getCash() - price);
                this.tip.showTextTip(player, player.getName()
                        + " ������һ��յأ�\r\n������: " + price + "���. ", 3);
            } else
            {
                this.tip.showTextTip(player, player.getName()
                        + " ��Ҳ���,����ʧ��. ", 3);
            }
        }
        new Thread(new MyThread(run, 1)).start();
    }

    /**
     * ִ���������ݲ���
     */
    private void upHouseLevel(Building b, PlayerModel player)
    {
        if (b.canUpLevel())
        {
            // ��������
            int price = b.getPrice();
            String name = b.getName();
            String upName = b.getUpName();
            int choose = JOptionPane.showConfirmDialog(null,
                    "���ã�" + player.getName() + "\r\n" + "�Ƿ��������أ�\r\n" + name
                            + "��" + upName + "\r\n" + "�۸�" + price + " ���.");
            if (choose == JOptionPane.OK_OPTION)
            {
                if (player.getCash() >= price)
                {
                    b.setLevel(b.getLevel() + 1);
                    player.setCash(player.getCash() - price);
                    this.tip.showTextTip(player, player.getName() + " �� "
                            + name + " ������ " + upName + "��\r\n������ " + price
                            + "���. ", 3);
                } else
                {
                    // �����ı���ʾ
                    this.tip.showTextTip(player, player.getName()
                            + " ��Ҳ���,����ʧ��. ", 3);
                }
            }
        }
        new Thread(new MyThread(run, 1)).start();
    }


    /**
     * ʹ�ÿ�Ƭ
     */
    public void useCards()
    {
        PlayerModel p = this.run.getCurPlayer();
        while (true)
        {
            if (p.getCards().size() == 0)
            {
                // �޿�Ƭ�������׶�
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
                options[i] = "��ʹ��";
                int response = JOptionPane.showOptionDialog(null,
                        " " + p.getName() + "����ѡ����Ҫʹ�õĿ�Ƭ", "��Ƭʹ�ý׶�",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response != i && response != -1)
                {
                    int id = p.getCards().get(response).getCardID();
                    useCard(p.getCards().get(response), id);
                } else
                {
                    // ��ʹ�ã������׶�.
                    this.run.nextState();
                    break;
                }
            }
        }
    }

    /**
     * ʹ�ÿ�Ƭ
     */
    private void useCard(Card card, int id)
    {
        switch (id)
        {
            case GameConstant.CARD_CONTROLDICE:
                // ʹ�ÿ�����
                useControlDiceCard(card);
                break;
            case GameConstant.CARD_PURCHASE:
                // ʹ�ù��ؿ�
                useHaveCard(card);
                break;
            case GameConstant.CARD_TAX:
                // ʹ�ò�˰��
                useTallageCard(card);
                break;
            case GameConstant.CARD_TRAP:
                // ʹ���ݺ���
                useTrapCard(card);
                break;
        }
    }

    /**
     * ʹ���ݺ���
     */
    private void useTrapCard(Card card)
    {
        Object[] options = {"ȷ��ʹ��", "����ѡ��"};
        int response = JOptionPane.showOptionDialog(null, "ȷ��ʹ��\"�ݺ���\"�� \""
                        + card.getOwner().getOtherPlayer().getName() + "\"����3��?",
                "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0)
        {
            // ʹ��
            PlayerModel cPlayer = card.getOwner().getOtherPlayer();
            // ��������
            cPlayer.setPrisonRemain(cPlayer.getPrisonRemain() + 3);
            // ���λ���л�������λ��
            if (LandModel.prison != null)
            {
                cPlayer.setX(LandModel.prison.x);
                cPlayer.setY(LandModel.prison.y);
            }
            // �����ı���ʾ
            this.tip.showTextTip(card.getOwner(), card.getOwner().getName()
                    + " ʹ���� \"�ݺ���\"���� \""
                    + card.getOwner().getOtherPlayer().getName()
                    + "\"����3��.", 3);
            // ��ȥ��Ƭ
            card.getOwner().getCards().remove(card);
        }
    }

    /**
     * ʹ�ò�˰��
     */
    private void useTallageCard(Card card)
    {
        Object[] options = {"ȷ��ʹ��", "����ѡ��"};
        int response = JOptionPane.showOptionDialog(null, "ȷ��ʹ��\"��˰��\"�� \""
                        + card.getOwner().getOtherPlayer().getName() + "\"���л�� 10%˰��?",
                "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        if (response == 0)
        {
            // ʹ��
            int money = (int) (card.getOwner().getOtherPlayer().getCash() / 10);
            card.getOwner().setCash(card.getOwner().getCash() + money);
            card.getOwner()
                    .getOtherPlayer()
                    .setCash(card.getOwner().getOtherPlayer().getCash() - money);
            // �����ı���ʾ
            this.tip.showTextTip(card.getOwner(), card.getOwner().getName()
                    + " ʹ���� \"��˰��\"���� \""
                    + card.getOwner().getOtherPlayer().getName()
                    + "\"���л�� 10%˰��", 2);
            // ����ȥ��Ƭ
            card.getOwner().getCards().remove(card);
        }
    }

    /**
     * ʹ�ù��ؿ�
     */
    private void useHaveCard(Card card)
    {
        // �õص㷿��
        Building building = this.building.getBuilding(
                card.getOwner().getY() / 60, card.getOwner().getX() / 60);
        if (building.getOwner() != null
                && building.getOwner().equals(card.getOwner().getOtherPlayer()))
        {// �ǶԷ��ķ���
            Object[] options = {"ȷ��ʹ��", "����ѡ��"};
            int response = JOptionPane.showOptionDialog(null,
                    "ȷ��ʹ��\"���ؿ�\"���˵��չ�����Ҫ���ѣ�" + building.getAllPrice() + " ���.",
                    "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (response == 0)
            {
                if (card.getOwner().getCash() >= building.getAllPrice())
                {
                    // ��ҽ���
                    building.getOwner().setCash(
                            building.getOwner().getCash()
                                    + building.getAllPrice());
                    card.getOwner().setCash(
                            card.getOwner().getCash() - building.getAllPrice());
                    building.setOwner(card.getOwner());
                    // �����ı���ʾ
                    this.tip.showTextTip(card.getOwner(), card.getOwner()
                            .getName() + " ʹ���� \"���ؿ�\"���չ�����˸�����. ", 2);
                    // ����ȥ��Ƭ
                    card.getOwner().getCards().remove(card);
                } else
                {
                    Object[] options1 = {"����ѡ��"};
                    JOptionPane.showOptionDialog(null, " ��Ҳ��㣬�޷�������!",
                            "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, options1,
                            options1[0]);
                }
            }
        } else
        {
            Object[] options1 = {"����ѡ��"};
            JOptionPane.showOptionDialog(null, "�˷����޷�ʹ�øÿ�Ƭ.", "��Ƭʹ�ý׶�.",
                    JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options1, options1[0]);
        }
    }

    /**
     * ʹ�ÿ�����
     */
    private void useControlDiceCard(Card card)
    {
        Object[] options = {"1��", "2��", "3��", "4��", "5��", "6��", "����ѡ��"};
        int response = JOptionPane.showOptionDialog(null,
                "ȷ��ʹ��\"ң�����ӿ�\"ң�����ӵ���?", "��Ƭʹ�ý׶�.", JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (response == -1 || response == 6)
        {
            return;
        } else
        {
            // ʹ��
            this.run.setPoint(response);
            // �����ı���ʾ
            this.tip.showTextTip(card.getOwner(), card.getOwner().getName()
                    + " ʹ���� \"ң�����ӿ�\".", 2);
            // ��ȥ��Ƭ
            card.getOwner().getCards().remove(card);
        }
    }

    /**
     * �˳��̵�
     */
    public void exitShop()
    {
        new Thread(new MyThread(run, 1)).start();
    }

    /**
     * �̵�����Ƭ����
     */
    public void buyCard(Shop shop)
    {
        int chooseCard = this.panel.getShop().getChooseCard();
        if (chooseCard >= 0 && this.panel.getShop().getCard().get(chooseCard) != null)
        {
            // ����Ƭ �������ɹ�
            if (this.buyCard(shop, chooseCard))
            {
                this.panel.getShop().getCard().get(chooseCard).setEnabled(false);
                this.panel.getShop().setChooseCard(-1);
            }
        }
    }

    /**
     * ����Ƭ
     */
    public boolean buyCard(Shop shop, int p)
    {
        if (this.panel.getShop().getCard().get(p) != null)
        {
            if (this.run.getCurPlayer().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS)
            {
                JOptionPane.showMessageDialog(null, "�����ɳ���:"
                        + PlayerModel.MAX_CAN_HOLD_CARDS + "�ſ�Ƭ,Ŀǰ�Ѿ������ٹ�����!");
                return false;
            }
            if (this.run.getCurPlayer().getTicket() < shop.getCards().get(p)
                    .getPrice())
            {
                JOptionPane.showMessageDialog(null, "��ǰ��Ƭ��Ҫ:"
                        + shop.getCards().get(p).getPrice() + "��ȯ,���ĵ�ȯ����.");
                return false;
            }
            // ���ÿ�Ƭӵ����
            shop.getCards().get(p).setOwner(this.run.getCurPlayer());
            // ����ҿ�Ƭ������ӿ�Ƭ
            this.run.getCurPlayer().getCards().add(shop.getCards().get(p));
            // ��ȥ��Ӧ��ȯ
            this.run.getCurPlayer().setTicket(
                    this.run.getCurPlayer().getTicket()
                            - shop.getCards().get(p).getPrice());
        }
        return true;
    }

    /**
     * �����ز�(���ɹ�����true)
     */
    public boolean sellOff(PlayerModel p)
    {
        for (int i = p.getBuildings().size() - 1; i >= 0; i--)
        {
            Building tmp = p.getBuildings().get(i);
            p.setCash(p.getCash() + tmp.getAllPrice());
            this.tip.showTextTip(p, p.getName()
                    + ":\r\nʱ�˲��ã�����һ���ز�\r\n���" + tmp.getAllPrice() + "���. ", 2);
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
     * ��Ϸ����
     */
    public void gameOver()
    {
        this.run.setCurState(RunController.GAME_STOP);
        this.panel.getEndPlayerInfo().moveToFront();
    }
}
