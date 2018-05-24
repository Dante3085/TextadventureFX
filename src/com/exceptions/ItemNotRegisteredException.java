package com.exceptions;

public class ItemNotRegisteredException extends Exception
{
    public ItemNotRegisteredException()
    {
        super();
    }

    public ItemNotRegisteredException(String message)
    {
        super(message);
    }
}
