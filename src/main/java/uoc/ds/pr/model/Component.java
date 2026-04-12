package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;

public class Component {
    String id;
    String trademark;
    String model;
    String serial;
    LinkedList<Issue> issues;

    public Component(String componentId, String trademark, String model, String serial){
        this.id = componentId;
        this.trademark = trademark;
        this.model = model;
        this.serial = serial;
        this.issues = new LinkedList<Issue>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public LinkedList<Issue> getIssues() {
        return issues;
    }

}
