package view;

import javax.swing.*;

import tools.FrameUtil;
/**
 * 游戏主窗口
 */
public class MyJFrame extends JFrame
{

    /**
     * 主panel
     */
    private MyJPanel panelGame;

    public MyJFrame()
    {
        this.setTitle("UCAS-MiniMonopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("images/icon/ucas.png").getImage());
        this.setSize(950, 650);
        this.setResizable(false);
        FrameUtil.setFrameCenter(this);
        this.panelGame = new MyJPanel();
        add(this.panelGame);
        this.setUndecorated(false);
    }

    public MyJPanel getPanelGame()
    {
        return panelGame;
    }
}
