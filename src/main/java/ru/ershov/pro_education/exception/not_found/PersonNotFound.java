package ru.ershov.pro_education.exception.not_found;

public class PersonNotFound extends NotFoundException {
    public <ID extends Number> PersonNotFound(ID id) {
        super(NameEntity.PERSON, id);
    }
}
