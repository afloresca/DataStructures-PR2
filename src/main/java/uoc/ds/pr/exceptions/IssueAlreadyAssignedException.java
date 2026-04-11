package uoc.ds.pr.exceptions;

public class IssueAlreadyAssignedException extends DSException{
    public IssueAlreadyAssignedException() {
        super();
    }

    public IssueAlreadyAssignedException(String msg) {
        super(msg);
    }
}
