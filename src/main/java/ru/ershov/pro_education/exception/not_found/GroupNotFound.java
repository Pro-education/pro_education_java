package ru.ershov.pro_education.exception.not_found;

public class GroupNotFound extends NotFoundException{
    public <ID extends Number> GroupNotFound(ID id) {
        super(NameEntity.GROUP, id);
    }
}
