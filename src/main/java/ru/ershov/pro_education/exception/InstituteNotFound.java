package ru.ershov.pro_education.exception;

public class InstituteNotFound extends RuntimeException {
    public <ID extends Number> InstituteNotFound(ID id) {
        super("Institute Not Found by id = " +  id);
    }
}
