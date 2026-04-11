package uoc.ds.pr.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Issue {
    String issueId;
    String componentId;
    String description;
    LocalDateTime dateTime;
}
