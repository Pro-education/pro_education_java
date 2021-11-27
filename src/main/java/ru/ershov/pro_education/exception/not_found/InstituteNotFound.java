package ru.ershov.pro_education.exception.not_found;

public class InstituteNotFound extends NotFoundException {
    public <ID extends Number> InstituteNotFound(ID id) {
        super(NameEntity.INSTITUTE, id);
    }
}
