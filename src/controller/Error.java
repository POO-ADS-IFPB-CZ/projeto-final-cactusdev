package src.controller;

public class Error extends Exception {
    public Error(String message){
        super(message);
    }
    public Error(){
        super();
    }

}
