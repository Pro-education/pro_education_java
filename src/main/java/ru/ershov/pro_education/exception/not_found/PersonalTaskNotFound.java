package ru.ershov.pro_education.exception.not_found;

public class PersonalTaskNotFound extends NotFoundException {
    public <ID extends Number> PersonalTaskNotFound(ID id) {
        super(NameEntity.PERSONAL_TASK, id);
    }
}
