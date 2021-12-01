package ru.ershov.pro_education.exception.not_found;

public class NotFoundException extends RuntimeException {
    <ID extends Number> NotFoundException(NameEntity name, ID id) {
        super(name.getName() + " with id = " + id + " not found");
    }

    enum NameEntity {
        INSTITUTE("Institute"),
        DIRECTION("Direction"),
        SUBJECT("Subject"),
        GROUP("Group"),
        HOMEWORK("Homework"),
        TASK("Task"),
        PERSONAL_TASK("Personal task"),
        TEAM__PERSON("Team/Person");

        private String name;

        public String getName() {
            return name;
        }

        NameEntity(String name) {
            this.name = name;
        }
    }
}
