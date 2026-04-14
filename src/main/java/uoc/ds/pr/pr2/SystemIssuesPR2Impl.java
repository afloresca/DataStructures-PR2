package uoc.ds.pr.pr2;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.IteratorArrayImpl;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.model.System;

import java.time.LocalDateTime;

public class SystemIssuesPR2Impl implements SystemIssues {

    Worker[] workers = new Worker[MAX_WORKERS];
    System[] systems = new System[MAX_SYSTEMS];
    Component[] components = new Component[MAX_COMPONENTS];
    int workerIndex = 0, systemIndex = 0, componentIndex = 0;

    LinkedList<Issue> issues = new LinkedList<Issue>();

    SystemIssuesHelperImpl systemIssuesHelper = new SystemIssuesHelperImpl(workers, systems, components);

    //To store workers, we use an array, since their number is known and small, around a few dozen
    //@pre True.
    //@post If the worker identifier is new, the number of workers increases by one.
    //      Otherwise, the worker’s data is updated.
    @Override
    public void addWorker(String workerId, String name, String address) {
        workers[workerIndex] = new Worker(workerId, name, address);
    }

    //To store systems, we use an array, since their number is known and relatively small, around a few hundred.
    //@pre True.
    //@post If the system identifier is new, the number of systems increases by one. Otherwise, the system’s data is updated.
    @Override
    public void addSystem(String systemId, String description, String location) {
        systems[systemIndex] = new System(systemId, description, location);
    }

    //To store components, we use an array, since their number is known and relatively small, around a few hundred.
    //@pre True.
    //@post If the component identifier is new, the number of components increases by one. Otherwise, the component’s data is updated.
    @Override
    public void addComponent(String componentId, String trademark, String model, String serial) {
        components[componentIndex] = new Component(componentId, trademark, model, serial);
    }


    //@pre The component and the system exist.
    //@post The number of components in a system increases by one, and the component is associated with that system.
    //If the component is already installed in the system, an error is reported.
    //To store the components of a system, we use a linked list, since their number is unknown and very small, just a few units.
    @Override
    public void installComponentToSystem(String componentId, String systemId) throws ComponentAlreadyInstalledException {
        System sys = systemIssuesHelper.getSystem(systemId);
        Component component = systemIssuesHelper.getComponent(componentId);
        //if it's already installed it returns exception
        if (sys.isSystemComponent(componentId)){
            throw new ComponentAlreadyInstalledException("Component Already Installed");
        }
        //add componentId to the system component id's

        sys.getComponents().insertEnd(component);

    }

    //To store issues, we use a linked list, since their number is unknown and relatively small, around a few hundred.
    // @pre The issue does not exist.
    // @post The number of issues increases by one, and the number of issues associated with a component increases by one.
    //       The component of the issue is updated.If the component does not exist, an error is reported.
    @Override
    public Issue createIssue(String issueId, String componentId, String description, LocalDateTime dateTime) throws ComponentNotFoundException {
        Component component = systemIssuesHelper.getComponent(componentId);
        if (component == null){
            throw new ComponentNotFoundException(); //if component does not exist, return an error
        }
        Issue issue = new Issue(issueId, description, dateTime);
        issues.insertEnd(issue); //add issue to issue's list
        component.getIssues().insertEnd(issue);//add issue to component's issues list.
        return issue;
    }

    //@pre True.
    //@post The issue is pushed onto the stack of issues assigned to the worker. The worker of the issue is updated.
    //If the issue does not exist, an error is reported.
    //If the issue has already been assigned, an error is reported.
    //If the issue has already been solved, an error is reported.
    //If the worker does not exist, an error is reported.
    @Override
    public void assignIssue(String issueId, String workerId) throws IssueNotFoundException, WorkerNotFoundException, IssueAlreadyAssignedException, IssueAlreadyResolvedException {
        Issue issue = getIssueById(issueId);
        if (issue == null){
            throw new IssueNotFoundException();
        }
        if (issue.getWorker() != null){
            throw new IssueAlreadyAssignedException();
        }
        if (issue.isResolved()){
            throw new IssueAlreadyResolvedException();
        }
        //Workers issues
        Worker worker = systemIssuesHelper.getWorker(workerId);
        if (worker == null){
            throw new WorkerNotFoundException();
        }
        worker.getIssues().push(issue); //issue is pushed;
        issue.setWorker(worker); //it updates issue worker;
    }

    /*
        Get an Issue from the Issue's list, if it does not exist it returns null;
     */
    public Issue getIssueById(String issueId){
        Issue issue = null;
        Iterator<Issue> iterIssue = issues.values();
        while (iterIssue.hasNext()){
            issue = iterIssue.next();
            if (issue.getId().equals(issueId)){
                break;
            }
            else {
                issue = null;
            }
        }
        return issue;
    }


    //@pre True.
    //@post The number of assigned issues of a worker decreases by one. The issue removed is the last one pushed.
    //The number of completed issues by the worker increases by one.
    //If the worker does not exist, an error is reported.
    //If the worker has no assigned issues, an error is reported.
    @Override
    public Issue solveIssue(String workerId) throws WorkerNotFoundException, NoIssuesException {
        Worker worker = systemIssuesHelper.getWorker(workerId);
        if (worker == null){
            throw new WorkerNotFoundException("Worker Id does not exist!");
        }
        if (worker.getIssues().isEmpty()){
            throw new NoIssuesException("Worker without issues yet!");
        }
        Issue workerIssue = worker.getIssues().pop();
        workerIssue.resolve(); //sets resolved to true
        worker.getCompletedIssues().insertEnd(workerIssue);
        //TODO should Issues list be updated?
        return workerIssue;
    }

    //@pre True.
    //@post Returns an iterator over all systems.
    @Override
    public Iterator<System> getSystems() throws NoSystemsException {
        if (systemIndex == 0){
            throw new NoSystemsException("There are no Systems yet!");
        }
        return new IteratorArrayImpl<System>(systems, systemIndex, 0);
    }

    //@pre The system exists.
    //@post Returns an iterator over all components of a system.
    @Override
    public Iterator<Component> getComponentsBySystem(String systemId) throws SystemHasNoComponentsException {
        if (componentIndex == 0){
            throw new SystemHasNoComponentsException("System has no components!!");
        }
        return new IteratorArrayImpl<Component>(components, componentIndex, 0);
    }

    //@pre The worker exists.
    //@post Returns an iterator over all issues completed by a worker.
    @Override
    public Iterator<Issue> getDoneIssuesByWorker(String workerId) throws NoIssuesException {
        if (issues.isEmpty()){
            throw new NoIssuesException("There are no issues yet");
        }
        Worker worker = systemIssuesHelper.getWorker(workerId);

        return worker.getCompletedIssues().values();
    }

    //@pre True.
    //@post Returns the worker with the highest number of completed issues.
    //If there are none, an error is reported.
    @Override
    public Worker getTopWorker() throws NoWorkerException {
        Worker topWorker = null;
        if (workerIndex == 0) {
            throw new NoWorkerException("There are no workers yet!!");
        }

        for (Worker auxWorker : workers){
            if (topWorker == null || auxWorker.getCompletedIssues().size() > topWorker.getCompletedIssues().size()){
                topWorker = auxWorker;
            }
        }
        return topWorker;
    }

    //@pre True.
    //@post Returns the system with the highest number of components.
    //If there are none, an error is reported.
    @Override
    public System getSystemWithMostComponents() throws NoSystemsException {
        System topSystem = null;
        if (systemIndex == 0){
            throw new NoSystemsException("There are no systems yet!!");
        }
        for (System  auxSystem : systems){
            if (topSystem == null || auxSystem.getComponents().size() > topSystem.getComponents().size()){
                topSystem = auxSystem;
            }
        }
        return topSystem;
    }

    @Override
    public SystemIssuesHelper getSystemIssuesHelper() {
        return systemIssuesHelper;
    }
}
