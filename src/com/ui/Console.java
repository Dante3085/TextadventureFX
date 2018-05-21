package com.ui;

import com.Main.Main;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Console Display for Debugging.
 * @author mjsch
 */
public class Console extends OutputStream
{
    private TextArea textArea;

    public Console(TextArea ta)
    {
        this.textArea = ta;
        ta.setOpacity(0.2);
        ta.setPrefWidth(Main.mainWindow.widthProperty().doubleValue());
        //ta.setPrefHeight(Main.mainWindow.heightProperty().doubleValue() + 1000);
        ta.setTranslateY(Main.mainWindow.heightProperty().doubleValue() - 178);

        textArea.setOnKeyPressed(event ->
        {
            switch (event.getCode())
            {
                case CONTROL:
                {
                    readConsoleCommand();
                    break;
                }
            }
        });
    }

    public void readConsoleCommand()
    {
        String command = textArea.getText();

        if (command.indexOf("flush") != -1)
        {
            textArea.clear();
            return;
        }

        switch (command)
        {
            case "print":
            {
                try
                {
                    writeToConsole("Hello from command!");
                } catch (NullPointerException e)
                {
                    System.out.println("CONSOLE_NOT_INITIALIZED");
                    e.printStackTrace();
                }
                break;
            }

            default:
            {
                try
                {
                    writeToConsole("Hello from command!");
                } catch (NullPointerException e)
                {
                    System.out.println("CONSOLE_NOT_INITIALIZED");
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void write(final int i) throws IOException
    {
        Platform.runLater(new Runnable() {
            public void run() {
                textArea.appendText(String.valueOf((char) i));
            }
        });
    }

    /**
     * Sends text to a TTextArea(Console) that is displayed on the application's window.
     * Messages can be directly seen in the application.
     * @param input
     */
    public void writeToConsole(String input) throws NullPointerException
    {
        input += "\n";

        PrintStream ps = new PrintStream(this, true);
        System.setOut(ps);
        System.setErr(ps);

        try
        {
            for (char c : input.toCharArray())
                this.write(c);
        } catch (IOException e)
        {
            System.out.println("Writing to console didn't work.");
            e.printStackTrace();
        }

        ps.close();
    }

    public TextArea getTextArea()
    {
        return textArea;
    }
}