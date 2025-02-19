package io.jenkins.plugins.maplelabs.Events.Collector;

import io.jenkins.plugins.maplelabs.Events.Data.AbstractEvent;
import io.jenkins.plugins.maplelabs.Events.interfaces.IComputerEvent;

import hudson.model.Computer;
import hudson.model.TaskListener;
import hudson.slaves.OfflineCause;

public class ComputerEventCollectorImpl extends AbstractEvent implements IComputerEvent {

    /**
     * This function will called when temp offline node is backs online
     */
    @Override
    public void collectEventData(Computer computer) {
        setEventType(EVENT);
        String nodeName = getNodeName(computer);
        String title = "Jenkins node " + nodeName + " back online";
        setText(title);
        setTitle(title);
        setPriority(Priority.LOW);
        setAlert(AlertType.SUCCESS);
    }

    /**
     * This function will called when node is offline/ temp offline
     */
    @Override
    public void collectEventData(Computer computer, OfflineCause cause, Type type) {
        setEventType(EVENT);
        String nodeName = getNodeName(computer);
        String title = "Jenkins node " + nodeName + " is ";
        switch (type) {
            case TEMPORARILYOFFLINE:
                title += "offlineTemporarily";
                setText(title);
                setTitle(title);
                setPriority(Priority.NORMAL);
                setAlert(AlertType.WARNING);
                break;
            case OFFLINE:
                title += " offline";
                setText(title);
                setTitle(title);
                setPriority(Priority.NORMAL);
                setAlert(AlertType.WARNING);
            default:
                title = "Jenkins node " + nodeName + " had some actions";
                setText(title);
                setTitle(title);
                setPriority(Priority.HIGH);
                setAlert(AlertType.WARNING);
        }
    }

    /**
     * This function will called when node is failed to launch
     */
    @Override
    public void collectEventData(Computer computer, TaskListener taskListener) {
        setEventType(EVENT);
        String nodeName = getNodeName(computer);
        String title = "Jenkins node " + nodeName + " is" + " failed to launch";
        setTitle(title);
        setTitle(title);
        setPriority(Priority.HIGH);
        setAlert(AlertType.ERROR);
    }

}
