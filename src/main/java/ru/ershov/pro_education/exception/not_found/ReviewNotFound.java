package ru.ershov.pro_education.exception.not_found;

public class ReviewNotFound extends NotFoundException {
    <ID extends Number> ReviewNotFound(ID id) {
        super(NameEntity.REVIEW, id);
    }
}
