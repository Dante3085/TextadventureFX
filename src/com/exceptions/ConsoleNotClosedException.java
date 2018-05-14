package com.exceptions;

public class ConsoleNotClosedException extends Exception
{
    public ConsoleNotClosedException()
    {

    }

    public ConsoleNotClosedException(String message)
    {
        super(message);
    }
}
