package uoc.ds.pr.pr2;

import uoc.ds.pr.model.Component;
import uoc.ds.pr.model.System;
import uoc.ds.pr.model.Worker;

public class SystemIssuesHelperImpl implements SystemIssuesHelper {
    Worker[] workers ;
    System[] systems ;
    Component[] components;

    public SystemIssuesHelperImpl(Worker[] w, System[] s, Component[] c){
        workers = w;
        systems = s;
        components = c;

    }
    @Override
    public Worker getWorker(String id) {
        Worker worker = null;
        for (Worker w : workers){
            if (w != null && w.getId().equals(id)){
                worker = w;
            }
        }
        return worker;
    }

    @Override
    public int numWorkers() {
        int count = 0;
        for (Worker worker : workers) {
            if (worker != null)
                count++;
        }
        return count;
    }

    @Override
    public System getSystem(String id) {
        System system = null;
        for (System s : systems){
            if (s!= null && s.getId().equals(id))
                system = s;
        }
        return system;
    }

    @Override
    public int numSystems() {
        int count = 0;
        for (System system : systems) {
            if (system != null)
                count++;
        }
        return count;
    }

    @Override
    public Component getComponent(String id) {
        Component component = null;
        for (Component c : components){
            if (c!= null && c.getId().equals(id))
                component = c;
        }
        return component;
    }

    @Override
    public int numComponents() {
        int count = 0;
        for (Component component : components) {
            if (component != null)
                count++;
        }
        return count;
    }

    @Override
    public int numComponentsBySystem(String systemId) {
        return this.getSystem(systemId).getComponents().size();
    }

    @Override
    public int numIssues() {
        int numIssues = 0;
        for (Component iterCom : components){
            if (iterCom == null)
                continue;
            numIssues += iterCom.getIssues().size();
        }
        return numIssues;
    }

    @Override
    public int numIssuesByComponent(String componentId) {
        return this.getComponent(componentId).getIssues().size();
    }

    @Override
    public int numIssuesByWorker(String workerId) {
        return this.getWorker(workerId).getIssues().size();
    }
}
