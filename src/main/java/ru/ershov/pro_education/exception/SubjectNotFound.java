package ru.ershov.pro_education.exception;

public class SubjectNotFound extends RuntimeException{
    <ID extends Number> SubjectNotFound(ID id){
        super("Subject with id = " + id + " not found");
    }
}
