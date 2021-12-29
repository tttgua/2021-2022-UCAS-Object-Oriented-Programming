package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;

import model.PlayerModel;

/**
 * 玩家信息面板
 */
public class PlayersInfo extends Panel
{

    private List<PlayerModel> players;

    protected PlayersInfo(int x, int y, int w, int h, List<PlayerModel> players)
    {
        super(x, y, w, h);
        this.players = players;
    }

    /**
     * 玩家信息显示面板绘制
     */
    public void paintPlayerInformation(Graphics g)
    {
        int tmpX = 30;
        for (PlayerModel tmp : players)
        {
            // 玩家信息面板绘制
            paintPlayerInfo(tmp, g, tmpX, 15);
            tmpX += 80;
        }
    }

    /**
     * 玩家信息面板绘制
     */
    private void paintPlayerInfo(PlayerModel player, Graphics g, int x, int y)
    {
        String[] information = {player.getName(),
                player.getCash() + " 金币",
                player.getTicket() + " 点券",
                player.getBuildings().size() + " 房屋",
                player.getCards().size() + " 卡片"};
        g.drawImage(player.getIMG("mini1"), x - 26 + 15, y - 10, x - 26 + 15 + player.getIMG("mini1").getWidth(null),
                y - 10 + player
                        .getIMG("mini1").getHeight(null), 0, 0, player.getIMG("mini1").getWidth(null), player
                        .getIMG("mini1").getHeight(null), null);
        y += 48;
        g.setColor(Color.BLACK);
        g.setFont(new Font("黑体", Font.PLAIN, 14));
        // 信息绘制
        FontMetrics fm = g.getFontMetrics();
        for (int k = 0; k < information.length; g.drawString(information[k], x
                + (45 - fm.stringWidth(information[k])), y += 30), k++)
            ;

    }

    public void paint(Graphics g)
    {
        this.createWindow(g);
        this.paintPlayerInformation(g);
    }

    public void initPanel()
    {
    }

}
