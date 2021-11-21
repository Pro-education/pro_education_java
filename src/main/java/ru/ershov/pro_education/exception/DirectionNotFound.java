package ru.ershov.pro_education.exception;

public class DirectionNotFound extends RuntimeException{
    public <ID extends Number> DirectionNotFound(ID id) {
        super("Direction Not Found by id = " +  id);
    }
}
