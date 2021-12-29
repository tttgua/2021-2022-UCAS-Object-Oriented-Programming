package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;

import model.PlayerModel;

/**
 * �����Ϣ���
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
     * �����Ϣ��ʾ������
     */
    public void paintPlayerInformation(Graphics g)
    {
        int tmpX = 30;
        for (PlayerModel tmp : players)
        {
            // �����Ϣ������
            paintPlayerInfo(tmp, g, tmpX, 15);
            tmpX += 80;
        }
    }

    /**
     * �����Ϣ������
     */
    private void paintPlayerInfo(PlayerModel player, Graphics g, int x, int y)
    {
        String[] information = {player.getName(),
                player.getCash() + " ���",
                player.getTicket() + " ��ȯ",
                player.getBuildings().size() + " ����",
                player.getCards().size() + " ��Ƭ"};
        g.drawImage(player.getIMG("mini1"), x - 26 + 15, y - 10, x - 26 + 15 + player.getIMG("mini1").getWidth(null),
                y - 10 + player
                        .getIMG("mini1").getHeight(null), 0, 0, player.getIMG("mini1").getWidth(null), player
                        .getIMG("mini1").getHeight(null), null);
        y += 48;
        g.setColor(Color.BLACK);
        g.setFont(new Font("����", Font.PLAIN, 14));
        // ��Ϣ����
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
