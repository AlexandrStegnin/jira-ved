package com.stegnin.jiraved.storage.model.jira;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Progress {

    int progress;
    int total;
    int percent;

}
