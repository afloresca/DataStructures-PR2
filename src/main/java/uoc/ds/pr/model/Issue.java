package uoc.ds.pr.model;

import java.time.LocalDateTime;

public class Issue {
    String id;
    String description;
    LocalDateTime dateTime;
    boolean resolved;
    Worker worker;

    public Issue(String iid,  String desc, LocalDateTime dt){
        this.id = iid;
        this.description = desc;
        this.dateTime = dt;
        this.resolved = false;
        this.worker = null;
    }

    public boolean isResolved() {
        return resolved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public void resolve(){
        this.resolved = true;
    }

    public Worker getWorker(){
        return worker;
    }

    public void setWorker(Worker worker){
        this.worker = worker;
    }
}
