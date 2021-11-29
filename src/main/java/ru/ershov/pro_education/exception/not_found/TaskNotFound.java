package ru.ershov.pro_education.exception.not_found;

public class TaskNotFound extends NotFoundException {
    public <ID extends Number> TaskNotFound(ID id) {
        super(NameEntity.TASK, id);
    }
}
