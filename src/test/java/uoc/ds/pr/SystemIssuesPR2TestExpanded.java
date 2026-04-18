package uoc.ds.pr;

import edu.uoc.ds.traversal.Iterator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.Issue;
import uoc.ds.pr.model.System;
import uoc.ds.pr.model.Worker;
import uoc.ds.pr.pr2.SystemIssues;
import uoc.ds.pr.pr2.SystemIssuesHelper;
import uoc.ds.pr.util.CSVUtil;

/**
 * New class with 5 new tests
 */

public class SystemIssuesPR2TestExpanded {

    protected SystemIssues systemIssues;
    protected SystemIssuesHelper helper;

    @Before
    public void setUp() throws Exception {
        this.systemIssues = FactorySystemIssues.getComputerProjects();
        this.helper = this.systemIssues.getSystemIssuesHelper();
    }


    @After
    public void tearDown() {
        this.systemIssues = null;
    }


    /*
       Try to get Done Issues from a worker without solved issues, returns empty iterator
     */
    @Test
    public void getDoneIssuesByWorkerWithoutSolvedIssuesTest() throws DSException {

        CSVUtil.addWorkers(systemIssues);
        CSVUtil.addSystems(systemIssues);
        CSVUtil.addComponents(systemIssues);
        CSVUtil.installComponent2Systems(systemIssues);
        CSVUtil.addIssues(systemIssues);
        Iterator<Issue> it = systemIssues.getDoneIssuesByWorker("WKR19");
        Assert.assertFalse(it.hasNext());

    }
    /*
        creates a user, it assigns issues, updates user, the user data has been updated, the issues still remain
     */
    @Test
    public void updateWorkerDataTest() throws DSException {
        CSVUtil.addWorkers(systemIssues);
        CSVUtil.addSystems(systemIssues);
        CSVUtil.addComponents(systemIssues);
        CSVUtil.installComponent2Systems(systemIssues);
        CSVUtil.addIssues(systemIssues);
        systemIssues.addWorker("WKR36", "Worker 36",  "Address 36766");
        Assert.assertEquals(29, helper.numWorkers());
        systemIssues.assignIssue("ISS-005","WKR36");
        int numIssues = helper.numIssuesByWorker("WKR36");


        systemIssues.addWorker("WKR36", "Best Worker Ever 36",  "Address 666");

        Worker worker = helper.getWorker("WKR36");
        Assert.assertEquals("Best Worker Ever 36", worker.getName());
        Assert.assertEquals("Address 666", worker.getAddress());

        Assert.assertEquals(numIssues, helper.numIssuesByWorker("WKR36"));

    }

    /*
        creates a system, it assigns components, updates system, the system data has been updated, the components still remain
     */
    @Test
    public void updateSystemDataTest() throws DSException {
        CSVUtil.addSystems(systemIssues);
        CSVUtil.addComponents(systemIssues);
        CSVUtil.installComponent2Systems(systemIssues);
        System system = helper.getSystem("SYS01");
        systemIssues.addSystem("SYS01", "Testing", "MyHome");
        Assert.assertEquals(helper.numComponentsBySystem("SYS01"), system.getComponents().size());
    }
    /*
        Tries to get the top Worker when there are no solved issues at all
     */
    @Test
    public void noTopWokerYet() throws DSException{
        CSVUtil.addWorkers(systemIssues);
        CSVUtil.addSystems(systemIssues);
        CSVUtil.addComponents(systemIssues);
        CSVUtil.installComponent2Systems(systemIssues);
        CSVUtil.addIssues(systemIssues);
        Assert.assertThrows(NoWorkerException.class, () ->
                systemIssues.getTopWorker());
    }

    /*
        Tries to get the most complete System when there is no component installed yet
     */
    @Test
    public void noMostCompleteSystemYet() throws DSException{
        CSVUtil.addSystems(systemIssues);
        Assert.assertThrows(NoSystemsException.class, () ->
                systemIssues.getSystemWithMostComponents());
    }

}
