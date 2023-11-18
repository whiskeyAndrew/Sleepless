package com.sleepless.Sleepless.Exceptions;

public class NoObjectInDbException extends RuntimeException{
    public NoObjectInDbException(){
        System.out.println("No object by value in database");
    }
}
