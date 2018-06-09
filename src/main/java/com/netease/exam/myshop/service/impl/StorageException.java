package com.netease.exam.myshop.service.impl;

import java.io.IOException;

public class StorageException extends RuntimeException {
    public StorageException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public StorageException(String message)
    {
        super(message);
    }
}
