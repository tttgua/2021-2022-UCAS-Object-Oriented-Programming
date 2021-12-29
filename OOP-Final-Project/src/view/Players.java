package view;

import model.PlayerModel;

import java.awt.*;
import java.util.List;

/**
 * ���λ����ͼ����
 */
public class Players extends Panel
{

    private List<PlayerModel> players;

    protected Players(int x, int y, int w, int h, List<PlayerModel> players)
    {
        super(x, y, w, h);
        this.players = players;
    }

    public void paint(Graphics g)
    {
        for (PlayerModel tmp : players)
        {
            paintPlayer(tmp, g);
        }
    }

    /**
     * ���Ƶ������
     */
    private void paintPlayer(PlayerModel player, Graphics g)
    {
        boolean show = true;
        Image tmp = player.getIMG("mini");
        if (this.x == player.getOtherPlayer().getX() && this.y == player.getOtherPlayer().getY())
        {
            show = false; // ����غ�
        }
        if (show)
            g.drawImage(tmp, player.getX() + 28, player.getY() + 28, player.getX() + 60,
                    player.getY() + 60, 0, 0, 32, 32, null);
    }


    public void initPanel()
    {
    }
}
