package uoc.ds.pr.pr2;

import edu.uoc.ds.adt.sequential.DictionaryArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.model.Component;
import uoc.ds.pr.model.System;
import uoc.ds.pr.model.Worker;

public class SystemIssuesHelperImpl implements SystemIssuesHelper {
    DictionaryArrayImpl workers;
    DictionaryArrayImpl systems;
    DictionaryArrayImpl components;

    public SystemIssuesHelperImpl(DictionaryArrayImpl w, DictionaryArrayImpl s, DictionaryArrayImpl c){
        workers = w;
        systems = s;
        components = c;

    }
    @Override
    public Worker getWorker(String id) {
        return (Worker) workers.get(id);
    }

    @Override
    public int numWorkers() {
        return workers.size();
    }

    @Override
    public System getSystem(String id) {
        return (System) systems.get(id);
    }

    @Override
    public int numSystems() {
        return systems.size();
    }

    @Override
    public Component getComponent(String id) {
        return (Component) components.get(id);
    }

    @Override
    public int numComponents() {
        return components.size();
    }

    @Override
    public int numComponentsBySystem(String systemId) {
        return this.getSystem(systemId).getComponents().size();
    }

    @Override
    public int numIssues() {
        int numIssues = 0;
        Iterator<Component> iterCom = components.values();
        while (iterCom.hasNext()){
            numIssues += iterCom.next().getIssues().size();
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
