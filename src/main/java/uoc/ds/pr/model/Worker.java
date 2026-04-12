package uoc.ds.pr.model;


import edu.uoc.ds.adt.sequential.LinkedList;
import uoc.ds.pr.pr2.adt.sequential.StackLinkedList;

public class Worker {
    String id;
    String name;
    String address;
    StackLinkedList<Issue> issues;
    LinkedList<Issue> completedIssues;

    public Worker (String id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
        this.issues = new StackLinkedList<Issue>();
        this.completedIssues = new LinkedList<Issue>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StackLinkedList<Issue> getIssues() {
        return issues;
    }

    public LinkedList<Issue> getCompletedIssues(){
        return completedIssues;
    }
}
