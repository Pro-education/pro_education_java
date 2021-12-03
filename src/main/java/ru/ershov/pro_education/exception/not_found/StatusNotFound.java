package ru.ershov.pro_education.exception.not_found;

public class StatusNotFound extends NotFoundException{
    <ID extends Number> StatusNotFound(ID id) {
        super(NameEntity.STATUS, id);
    }
}
