package view;

import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import control.Controller;

/**
 * ø®≈∆…ÃµÍ∞¥≈•
 */
public class ShopButton extends JPanel implements MouseListener
{

    private ShopUI shopUI;

    private Image[] img;

    private Image normalImage;
    private Image mouseoverImage;
    private Image pressedImage;
    private Image disabledImage;

    private Image currentImage;

    private boolean enabled = true;

    private String name;

    private Controller controller;

    public ShopButton(ShopUI shopUI, String name, int x, int y, Controller controller)
    {
        this.shopUI = shopUI;
        this.name = name;
        this.controller = controller;
        this.img = this.shopUI.createCardImg(name);
        this.normalImage = this.img[0];
        this.mouseoverImage = this.img[1];
        this.pressedImage = this.img[2];
        this.disabledImage = this.img[3];
        this.currentImage = normalImage;
        this.setBounds(x, y, this.img[0].getWidth(null), this.img[0].getHeight(null));
        this.addMouseListener(this);
    }


    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public void paint(Graphics g)
    {
        this.setOpaque(false);
        if (enabled)
        {
            g.drawImage(currentImage, this.getX(), this.getY(), this.getWidth(),
                    this.getHeight(), this);
        } else if (!(name.equals("buy") || name.equals("cancel")))
        {
            g.drawImage(disabledImage, this.getX(), this.getY(), this.getWidth(),
                    this.getHeight(), this);
        }
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        currentImage = pressedImage;
        if (enabled)
        {
            if (this.name.equals("close"))
            {
                this.shopUI.moveToBack();
                this.controller.exitShop();
            } else if (this.name.equals("cancel"))
            {
                this.shopUI.setChooseCard(null);
            } else if (this.name.equals("buy"))
            {
                this.controller.buyCard(this.shopUI.getShop());
            } else
            {
                this.shopUI.setChooseCard(this);
            }
        }
    }

    public void mouseReleased(MouseEvent e)
    {
        currentImage = mouseoverImage;
    }

    public void mouseEntered(MouseEvent e)
    {
        currentImage = mouseoverImage;
    }

    public void mouseExited(MouseEvent e)
    {
        currentImage = normalImage;
    }
}