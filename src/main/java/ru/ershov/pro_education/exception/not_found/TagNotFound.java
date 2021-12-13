package ru.ershov.pro_education.exception.not_found;

public class TagNotFound extends NotFoundException {
    <ID extends Number> TagNotFound(ID id) {
        super(NameEntity.TAG, id);
    }
}
