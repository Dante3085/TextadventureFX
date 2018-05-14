package com.exceptions;

public class ConsoleNotInitializedException extends NullPointerException
{
    public ConsoleNotInitializedException(String message)
    {
        super(message);
    }
}
