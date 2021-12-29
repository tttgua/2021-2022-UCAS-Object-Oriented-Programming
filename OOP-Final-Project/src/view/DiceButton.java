package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import control.Controller;
import model.DiceModel;

/**
 * ÷»×Ó°´Å¥ÊÓÍ¼
 */
public class DiceButton extends JPanel implements MouseListener
{

    private Controller controller;
    private DiceModel dice;

    private Image normalImage;
    private Image rolloverImage;
    private Image pressedImage;

    private Image currentImage;

    private boolean enabled = true;

    public DiceButton(Controller controller, int x, int y)
    {
        this.controller = controller;
        this.dice = controller.getDice();
        this.normalImage = dice.getDiceIMG()[0].getImage();
        this.rolloverImage = dice.getDiceIMG()[1].getImage();
        this.pressedImage = dice.getDiceIMG()[2].getImage();

        this.currentImage = normalImage;
        this.setBounds(x, y, 50, 50);
        this.addMouseListener(this);
        repaint();
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
            controller.pressDiceButton();
        }
    }

    public void mouseReleased(MouseEvent e)
    {
        currentImage = rolloverImage;
    }

    public void mouseEntered(MouseEvent e)
    {
        currentImage = rolloverImage;
    }

    public void mouseExited(MouseEvent e)
    {
        currentImage = normalImage;
    }
}