package org.example;

public class CreateNullNodeException extends Exception{
    public CreateNullNodeException(){
        super("You cannot create a null node");
    }
}
