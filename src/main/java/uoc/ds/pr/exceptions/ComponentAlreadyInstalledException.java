package uoc.ds.pr.exceptions;

public class ComponentAlreadyInstalledException extends DSException{
    public ComponentAlreadyInstalledException() {
        super();
    }

    public ComponentAlreadyInstalledException(String msg) {
        super(msg);
    }
}
