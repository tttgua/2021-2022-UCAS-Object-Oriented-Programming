package view;

import javax.swing.ImageIcon;

/**
 * �趨����ͼƬ��
 */

public class ConfigPicture
{
    public static ImageIcon PLAYER1_SELECTED = new ImageIcon("images/config/playerChoose/selected_01.png");
    public static ImageIcon PLAYER2_SELECTED = new ImageIcon("images/config/playerChoose/selected_02.png");
    public static ImageIcon[] BUTTON_LEFT = {
            new ImageIcon("images/config/LeftButton/normal.png"),
            new ImageIcon("images/config/LeftButton/disabled.png"),
            new ImageIcon("images/config/LeftButton/mouseOver.png"),
            new ImageIcon("images/config/LeftButton/pressed.png")
    };
    public static ImageIcon[] BUTTON_RIGHT = {
            new ImageIcon("images/config/RightButton/normal.png"),
            new ImageIcon("images/config/RightButton/disabled.png"),
            new ImageIcon("images/config/RightButton/mouseOver.png"),
            new ImageIcon("images/config/RightButton/pressed.png")
    };
    /**
     * ��ѡ��ɫͼ
     */
    public static ImageIcon[] PLAYER_CHOOSE = {
            new ImageIcon("images/player/0/mini2.png"),
            new ImageIcon("images/player/1/mini2.png"),
            new ImageIcon("images/player/2/mini2.png"),
            new ImageIcon("images/player/3/mini2.png")
    };
}
