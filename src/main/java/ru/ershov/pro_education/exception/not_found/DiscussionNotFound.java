package ru.ershov.pro_education.exception.not_found;

public class DiscussionNotFound extends NotFoundException {
    <ID extends Number> DiscussionNotFound( ID id) {
        super(NameEntity.DISCUSSION, id);
    }
}
