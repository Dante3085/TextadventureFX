package com.thread;

public class CustomThread extends Thread
{
    private String name;

    public CustomThread(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        System.out.println("STARTING THREAD '" + name + "' ...");

        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("STOPPING THREAD '" + name + "'");
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
