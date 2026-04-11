package uoc.ds.pr;


import uoc.ds.pr.exceptions.DSException;
import uoc.ds.pr.pr2.SystemIssues;
import uoc.ds.pr.pr2.SystemIssuesPR2Impl;
import uoc.ds.pr.util.CSVUtil;

public class FactorySystemIssues {

    public static SystemIssues getComputerProjects() throws DSException {
        SystemIssues systemIssues;
        systemIssues = new SystemIssuesPR2Impl();


        return systemIssues;
    }



}