package uoc.ds.pr.exceptions;

public class IssueAlreadyResolvedException extends DSException {

    public IssueAlreadyResolvedException() {
        super();
    }

    public IssueAlreadyResolvedException(String msg) {
        super(msg);
    }
}