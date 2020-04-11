import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan.
 */
public class OrderedLinkedListRQ implements Runqueue {

    Proc nodeHead;
    int listLength;

    /**
     * Constructs empty linked list
     */
    public OrderedLinkedListRQ() {
        // Implement me
        nodeHead = null;

    }  // end of OrderedLinkedList()


    @Override
    public void enqueue(String procLabel, int vt) {

        Proc newNode = new Proc(procLabel, vt, null);
        Proc currentNode = nodeHead;
        Proc previousNode = null;

        /**
         * If list is not empty, find a node that is smaller than vt
         *
         */
        while (currentNode != null && vt >= currentNode.getVt()) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        // If list is empty, set newNode as nodeHead
        if (previousNode == null) {
            nodeHead = newNode;
        } else {
            previousNode.setNextNode(newNode);
        }

        newNode.setNextNode(currentNode);

    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
        Proc removed = nodeHead;
        nodeHead = nodeHead.getNextNode();

        return removed.getProcLabel();
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        // Implement me

        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
        Proc currentNode = nodeHead;
        Proc previousNode = null;

        while (currentNode.getProcLabel() != procLabel) {
            if (currentNode == null) {
                return false;
            } else {
                currentNode = currentNode.getNextNode();
                return true;
            }
        } // End while loop
        if (currentNode == nodeHead) {
            nodeHead = nodeHead.getNextNode();
        }

        return false; // placeholder, modify this
    } // End of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        Proc currentNode = nodeHead;
        StringBuilder list = new StringBuilder();
        while (currentNode != null) {
            list.append(currentNode.getProcLabel() + " ");
            currentNode = currentNode.getNextNode();
        }
        System.out.println(list);

    } // end of printAllProcess()

} // end of class OrderedLinkedListRQ
