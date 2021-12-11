package ru.ershov.pro_education.exception.not_found;

public class TeamPersonException extends NotFoundException{
    <ID extends Number> TeamPersonException(ID id) {
        super(NameEntity.TEAM_PERSON, id);
    }
}
