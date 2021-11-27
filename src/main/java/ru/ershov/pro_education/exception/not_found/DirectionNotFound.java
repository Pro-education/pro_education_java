package ru.ershov.pro_education.exception.not_found;

public class DirectionNotFound extends NotFoundException {
    public <ID extends Number> DirectionNotFound(ID id) {
        super(NameEntity.DIRECTION, id);
    }
}
