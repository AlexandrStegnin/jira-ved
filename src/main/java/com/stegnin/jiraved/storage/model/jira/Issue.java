package com.stegnin.jiraved.storage.model.jira;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Issue {

    String key;

    @JsonProperty("fields")
    Fields fields;

}
