package com.stegnin.jiraved.storage.model.jira;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Assignee {

    String name;
    String key;
    String emailAddress;
    String displayName;
    boolean active;

}
