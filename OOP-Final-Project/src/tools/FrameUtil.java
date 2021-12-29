package tools;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil
{
    /**
     * Ãæ°å¾ÓÖÐ
     */
    public static void setFrameCenter(JFrame jf)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - jf.getWidth()) / 2;
        int y = (screen.height - jf.getHeight()) / 2 - 32;
        jf.setLocation(x, y);
    }
}
