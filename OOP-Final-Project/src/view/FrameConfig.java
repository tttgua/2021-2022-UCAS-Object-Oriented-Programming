package view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.PlayerModel;
import tools.FrameUtil;
import control.Controller;
import control.RunController;

/**
 * 用户配置读取
 */
public class FrameConfig extends JFrame
{

    private final JButton JBStart = new JButton("开始游戏");
    private final JButton JBCancel = new JButton("重新选择");

    private final JButton JBPlayer1 = new JButton("玩家1确认角色");
    private final JLabel JLPlayerName1 = new JLabel("名字:");
    private final JTextField JTFPlayerName1 = new JTextField(12);
    private final JButton JBPlayerName1 = new JButton("玩家1确认名字");

    private final JButton JBPlayer2 = new JButton("玩家2确认角色");
    private final JLabel JLPlayerName2 = new JLabel("名字:");
    private final JTextField JTFPlayerName2 = new JTextField(12);
    private final JButton JBPlayerName2 = new JButton("玩家2确认名字");

    /**
     * 选项卡
     */
    private JTabbedPane tabs;
    /**
     * 可选图片
     */
    private ImageIcon[] img = ConfigPicture.PLAYER_CHOOSE;
    /**
     * 人物1
     */
    private JLabel jlPlayer1Choose = null;
    private final JLabel jlPlayer1Selected = new JLabel(
            ConfigPicture.PLAYER1_SELECTED);
    private JButton leftButton1;
    private JButton rightButton1;
    /**
     * 人物2
     */
    private JLabel jlPlayer2Choose = null;
    private final JLabel jlPlayer2Selected = new JLabel(
            ConfigPicture.PLAYER2_SELECTED);
    private JButton leftButton2;
    private JButton rightButton2;
    /**
     * 1P 2P可选人物
     */
    private int[] optional = {0, 0};
    /**
     * 1P 2P已选人物
     */
    private int[] selected = {-1, -2};
    /**
     * 1P 2P已填名字
     */
    private String[] selectedName = {"", ""};

    private MyJFrame myJFrame;

    public FrameConfig(MyJFrame myJFrame)
    {
        this.myJFrame = myJFrame;
        setTitle("玩家数据设定");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setIconImage(new ImageIcon("images/icon/ucas.png").getImage());
        // 添加主面板
        this.add(this.createMainPanel(), BorderLayout.CENTER);
        // 添加按钮面板
        this.add(this.createButtonPanel(), BorderLayout.SOUTH);
        this.setResizable(false);
        this.setSize(500, 500);
        // 居中对齐
        FrameUtil.setFrameCenter(this);
        setVisible(true);
    }

    /**
     * 添加主面板
     */
    private JTabbedPane createMainPanel()
    {
        this.tabs = new JTabbedPane();
        this.tabs.setOpaque(false);
        this.tabs.add("角色设置", this.createPlayerSelectPanel());
        this.tabs.add("游戏设置", this.createGameSelectPanel());
        return tabs;
    }

    /**
     * 游戏胜利条件设置
     */
    private Component createGameSelectPanel()
    {
        JPanel jp = new JPanel(new GridLayout(0, 1));
        jp.setBackground(new Color(240, 255, 240));

        final JPanel dayPanel = new JPanel();
        dayPanel.setBorder(BorderFactory.createTitledBorder(""));
        JLabel day = new JLabel("游戏天数");
        final String[] days = {"无限制", "20", "40", "80", "120", "240", "480"};
        final Choice daysChoice = new Choice();

        for (String a : days)
        {
            daysChoice.add(a);
        }
        daysChoice.addItemListener(arg0 ->
        {
            String str = days[daysChoice.getSelectedIndex()];
            if (str.equals("无限制"))
            {
                RunController.GAME_DAY = -1;
            } else
            {
                RunController.GAME_DAY = Integer.parseInt(str);
            }
        });
        dayPanel.add(day);
        dayPanel.add(daysChoice);

        JPanel moneyPanel = new JPanel();
        moneyPanel.setBorder(BorderFactory.createTitledBorder(""));
        JLabel money = new JLabel("胜利金钱");
        final String[] money_ = {"无限制", "10000", "20000", "40000", "80000", "200000"};
        final Choice moneyChoice = new Choice();
        for (String a : money_)
        {
            moneyChoice.add(a);
        }
        moneyChoice.addItemListener(arg0 ->
        {
            String str = money_[moneyChoice.getSelectedIndex()];
            if (str.equals("无限制"))
            {
                RunController.MONEY_MAX = -1;
            } else
            {
                RunController.MONEY_MAX = Integer.parseInt(str);
            }
        });
        moneyPanel.add(money);
        moneyPanel.add(moneyChoice);

        JPanel cashPanel = new JPanel();
        cashPanel.setBorder(BorderFactory.createTitledBorder(""));
        JLabel cash = new JLabel("玩家初始金钱");
        final String[] optionalCash = {"10000", "20000", "50000", "100000"};
        final Choice cashChoice = new Choice();
        for (String a : optionalCash)
        {
            cashChoice.add(a);
        }
        cashChoice.addItemListener(arg0 ->
        {
            String str = optionalCash[cashChoice.getSelectedIndex()];
            RunController.PLAYER_CASH = Integer.parseInt(str);
        });
        cashPanel.add(cash);
        cashPanel.add(cashChoice);

        jp.add(dayPanel);
        jp.add(moneyPanel);
        jp.add(cashPanel);
        return jp;
    }


    /**
     * 人物选择面板
     */
    private JPanel createPlayerSelectPanel()
    {
        JPanel jp = new JPanel();
        jp.setLayout(null);
        jp.setBackground(new Color(240, 255, 240));
        addPlayer1Config(30, 0, jp);
        addPlayer2Config(300, 0, jp);
        addCancelButton(jp);
        return jp;
    }

    private void addCancelButton(JPanel panel)
    {
        JBCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                reLoad();
            }
            /**
             * 重新加载角色选择
             */
            private void reLoad()
            {
                leftButton1.setEnabled(true);
                rightButton1.setEnabled(true);
                JBPlayer1.setEnabled(true);
                jlPlayer1Selected.setVisible(false);
                jlPlayer1Choose.setIcon(img[0]);
                JTFPlayerName1.setText("");
                JTFPlayerName1.setEditable(true);
                JBPlayerName1.setEnabled(true);
                selected[0] = -1;
                optional[0] = 0;

                leftButton2.setEnabled(true);
                rightButton2.setEnabled(true);
                JBPlayer2.setEnabled(true);
                jlPlayer2Selected.setVisible(false);
                jlPlayer2Choose.setIcon(img[0]);
                JTFPlayerName2.setText("");
                JTFPlayerName2.setEditable(true);
                JBPlayerName2.setEnabled(true);
                selected[1] = -2;
                optional[1] = 0;
                repaint();
            }
        });
        JBCancel.setBounds(200, 280, 120, 40);
        panel.add(JBCancel);
    }

    /**
     * 增加1P面板
     */
    private void addPlayer1Config(int x, int y, JPanel jp)
    {
        jlPlayer1Choose = new JLabel(img[optional[0]]);
        jlPlayer1Choose.setBounds(x + 8, y, 128, 128);
        jlPlayer1Selected.setBounds(x + 8, y, 128, 128);
        jlPlayer1Selected.setVisible(false);
        leftButton1 = this.createButton(x, 92 + y, ConfigPicture.BUTTON_LEFT, 'a');
        leftButton1.addActionListener(e ->
        {
            if (optional[0] <= 0)
            {
                optional[0] = img.length;
            }
            jlPlayer1Choose.setIcon(img[--optional[0]]);
        });

        jp.add(leftButton1);
        rightButton1 = this.createButton(128 + x, 92 + y, ConfigPicture.BUTTON_RIGHT, 'd');
        rightButton1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if (optional[0] >= img.length - 1)
                {
                    optional[0] = -1;
                }
                jlPlayer1Choose.setIcon(img[++optional[0]]);
            }
        });
        jp.add(rightButton1);
        JBPlayer1.setBounds(12 + x, 128 + y, 120, 30);
        JBPlayer1.addActionListener(arg0 ->
        {
            if ((optional[0] != selected[1]))
            {
                leftButton1.setEnabled(false);
                rightButton1.setEnabled(false);
                JBPlayer1.setEnabled(false);
                jlPlayer1Selected.setVisible(true);
                selected[0] = optional[0];
            }
        });
        jp.add(JBPlayer1);
        jp.add(jlPlayer1Selected);
        jp.add(jlPlayer1Choose);

        JLPlayerName1.setBounds(x + 12, y + 128 + 36, 50, 30);
        JTFPlayerName1.setBounds(x + 12 + 30, y + 128 + 36, 120 - 30, 30);
        JBPlayerName1.setBounds(x + 12, y + 128 + 36 + 36, 120, 30);

        JBPlayerName1.addActionListener(e ->
        {
            if (!JTFPlayerName1.getText().equals(""))
            {
                selectedName[0] = JTFPlayerName1.getText();
                JTFPlayerName1.setEditable(false);
                JBPlayerName1.setEnabled(false);
            }
        });
        jp.add(JLPlayerName1);
        jp.add(JTFPlayerName1);
        jp.add(JBPlayerName1);
    }

    /**
     * 增加2P面板
     */
    private void addPlayer2Config(int x, int y, JPanel jp)
    {
        jlPlayer2Choose = new JLabel(img[optional[1]]);
        jlPlayer2Choose.setBounds(x + 8, y, 128, 128);
        jlPlayer2Selected.setBounds(x + 8, y, 128, 128);
        jlPlayer2Selected.setVisible(false);
        leftButton2 = this.createButton(x, 92 + y, ConfigPicture.BUTTON_LEFT, 'a');
        leftButton2.addActionListener(e ->
        {
            if (optional[1] <= 0)
            {
                optional[1] = img.length;
            }
            jlPlayer2Choose.setIcon(img[--optional[1]]);
        });

        jp.add(leftButton2);

        rightButton2 = this.createButton(128 + x, 92 + y, ConfigPicture.BUTTON_RIGHT,
                'd');
        rightButton2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if (optional[1] >= img.length - 1)
                {
                    optional[1] = -1;
                }
                jlPlayer2Choose.setIcon(img[++optional[1]]);
            }
        });

        jp.add(rightButton2);

        JBPlayer2.setBounds(12 + x, 128 + y, 120, 30);

        JBPlayer2.addActionListener(arg0 ->
        {
            if (selected[0] != optional[1])
            {
                leftButton2.setEnabled(false);
                rightButton2.setEnabled(false);
                JBPlayer2.setEnabled(false);
                jlPlayer2Selected.setVisible(true);
                selected[1] = optional[1];
            }
        });
        jp.add(JBPlayer2);
        jp.add(jlPlayer2Selected);
        jp.add(jlPlayer2Choose);

        JLPlayerName2.setBounds(x + 12, y + 128 + 36, 50, 30);
        JTFPlayerName2.setBounds(x + 12 + 30, y + 128 + 36, 120 - 30, 30);
        JBPlayerName2.setBounds(x + 12, y + 128 + 36 + 36, 120, 30);

        JBPlayerName2.addActionListener(e ->
        {
            if (!JTFPlayerName2.getText().equals(""))
            {
                selectedName[1] = JTFPlayerName2.getText();
                JTFPlayerName2.setEditable(false);
                JBPlayerName2.setEnabled(false);

            }

        });
        jp.add(JLPlayerName2);
        jp.add(JTFPlayerName2);
        jp.add(JBPlayerName2);
    }

    /**
     * 创建按钮
     */
    public JButton createButton(int x, int y, ImageIcon[] img, char keyListener)
    {
        JButton add = new JButton("", img[0]);
        add.setRolloverIcon(img[2]);
        add.setPressedIcon(img[3]);
        add.setMnemonic(keyListener);
        add.setBounds(x, y, img[0].getIconWidth(), img[0].getIconHeight());
        return add;
    }

    /**
     * 添加按钮面板
     */
    private JPanel createButtonPanel()
    {
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JBStart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (selected[0] < 0 || selected[1] < 0)
                {
                    JOptionPane.showMessageDialog(null, "请先完成人物设置!");
                } else if (selectedName[0].equals("")
                        || selectedName[1].equals(""))
                {
                    JOptionPane.showMessageDialog(null, "请先完成名字设置!");
                } else
                {
                    int choose = JOptionPane.showConfirmDialog(null, "是否正式开始游戏？");
                    if (choose == JOptionPane.OK_OPTION)
                    {
                        startGame();
                    }
                }
            }

            /**
             * 开始游戏
             */
            private void startGame()
            {
                setVisible(false);
                myJFrame.setVisible(true);
                Controller controller = myJFrame.getPanelGame().getControl();
                incomePlayers(controller);
                controller.start();
            }

            /**
             * 导入玩家数据
             */
            private void incomePlayers(Controller controller)
            {
                List<PlayerModel> tmpPlayer = controller.getPlayers();
                tmpPlayer.get(0).setName(selectedName[0]);
                tmpPlayer.get(1).setName(selectedName[1]);
                tmpPlayer.get(0).setImgID(selected[0]);
                tmpPlayer.get(1).setImgID(selected[1]);
                tmpPlayer.get(0).setOtherPlayer(tmpPlayer.get(1));
                tmpPlayer.get(1).setOtherPlayer(tmpPlayer.get(0));
            }
        });
        jp.add(JBStart);
        return jp;
    }
}
