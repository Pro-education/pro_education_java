package ru.ershov.pro_education.exception.not_found;

public class SubjectNotFound extends NotFoundException{
    <ID extends Number> SubjectNotFound(ID id){
        super(NameEntity.SUBJECT, id);
    }
}
