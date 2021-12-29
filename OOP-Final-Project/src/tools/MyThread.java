package tools;

import control.RunController;

/**
 * ʹ��ת������˯��ָ��ʱ��������һ��״̬
 */

public class MyThread implements Runnable
{

    private RunController run;

    int seconds;

    int time;

    public MyThread(RunController run, int seconds)
    {
        this.run = run;
        this.seconds = seconds;
    }

    @Override
    public void run()
    {
        while (true)
        {
            if (time >= seconds)
            {
                run.nextState();
                break;
            }
            time++;
            try
            {
                Thread.sleep(1000);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
