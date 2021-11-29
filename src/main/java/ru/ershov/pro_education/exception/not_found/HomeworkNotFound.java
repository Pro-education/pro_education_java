package ru.ershov.pro_education.exception.not_found;

public class HomeworkNotFound extends NotFoundException{
    public <ID extends Number> HomeworkNotFound(ID id) {
        super(NameEntity.HOMEWORK, id);
    }
}
