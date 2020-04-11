import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan.
 */
public class OrderedLinkedListRQ implements Runqueue {

    Proc nodeHead;

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
         * If list is not empty, find first node bigger than vt and assign as current
         * Set new node as previous node's next node
         * Set new node's next node as current node
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
        // Set node head as removed, and node head as next node
        Proc removed = nodeHead;
        nodeHead = nodeHead.getNextNode();

        // Print label of removed node
        return removed.getProcLabel();
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {

        Proc currentNode = nodeHead;

        // Iterate through list until node with matching procLabel
        while (currentNode != null && !currentNode.getProcLabel().equals(procLabel)) {
            currentNode = currentNode.getNextNode();
        }

        // If node is not null and equal to procLabel return true
        if (currentNode != null && currentNode.getProcLabel().equals(procLabel)) {
            return true;
        }

        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
        Proc currentNode = nodeHead;
        Proc previousNode = null;

        // Find if node with procLable exists
        while (currentNode == null || !currentNode.getProcLabel().equals(procLabel)) {
            if (currentNode == null) { // Return false if it does not exist
                return false;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }
        }
        // Is the node the head node, set next node as head node
        if (currentNode == nodeHead) {
            nodeHead = nodeHead.getNextNode();
        }
        // Set previous node's next node as current node's next node
        else {
            previousNode.setNextNode(currentNode.getNextNode());
        }
        return true;

    } // End of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {

        int processTime = 0;
        Proc currentNode = nodeHead;
        Proc previousNode = null;

        // Iterate through list until until procLabel and add up all previous node's vt
        // EXCEPT for last node's
        while (!currentNode.getProcLabel().equals(procLabel)) {
            if (previousNode != null) {
                processTime += previousNode.getVt();
            }
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }

        // Add previous node's vt of current node
        if (previousNode != null) {
            processTime += previousNode.getVt();
        }

        return processTime;
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
