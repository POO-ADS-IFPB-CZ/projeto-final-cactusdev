package src.controller;

public class CustomError extends Exception {
    public CustomError(String message){
        super(message);
    }
    public CustomError(){
        super();
    }

}
