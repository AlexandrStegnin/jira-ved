package com.stegnin.jiraved.storage.model.jira;

import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Fields {

    Date lastViewed;
    List<String> labels;
    Assignee assignee;
    Progress progress;
    IssueType issueType;
    long updated;
    TimeTracking timeTracking;
    String summary;
    Priority priority;
    Status status;
    AggregateProgress aggregateProgress;
    int timeSpent;
    int aggregateTimeSpent;
    long created;

}
