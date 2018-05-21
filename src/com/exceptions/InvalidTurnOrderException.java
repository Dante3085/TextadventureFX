package com.exceptions;

public class InvalidTurnOrderException extends Exception
{
    public InvalidTurnOrderException()
    {
        super();
    }

    public InvalidTurnOrderException(String message)
    {
        super(message);
    }
}
