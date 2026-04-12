package uoc.ds.pr.model;


import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;


public class System {
    String id;
    String description;
    String location;
    LinkedList<Component> components;

    public System (String systemId, String description, String location){
        this.id = systemId;
        this.description = description;
        this.location = location;
        components = new LinkedList<Component>();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LinkedList<Component> getComponents(){
        return components;
    }
    /*
        returns true if componentId is a system's component.
     */
    public boolean isSystemComponent(String componentId){
        boolean isSystemComponent = false;
        Iterator<Component> iterCom = components.values();
        while (iterCom.hasNext()){
            isSystemComponent = iterCom.next().getId().equals(componentId);
            if (isSystemComponent){
                break;
            }
        }
        return isSystemComponent;
    }

}

