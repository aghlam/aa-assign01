import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan.
 * @studentAuthor Alan Lam s3436174, Matthew Duong s3784450
 *
 */
public class OrderedLinkedListRQ implements Runqueue {

    Proc nodeHead;

    /**
     * Constructs empty linked list
     */
    public OrderedLinkedListRQ() {

        nodeHead = null;

    }  // end of OrderedLinkedList()


    @Override
    public void enqueue(String procLabel, int vt) {

        Proc newNode = new Proc(procLabel, vt, null);
        Proc currentNode = nodeHead;
        Proc previousNode = null;

        // If list is not empty, find first node bigger than vt and assign as current Node
        while (currentNode != null && vt >= currentNode.getVt()) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        // If list is empty, set newNode as nodeHead
        if (previousNode == null) {
            nodeHead = newNode;
        } else {
            // Set new node as previous node's next node
            previousNode.setNextNode(newNode);
        }
        // Set new node's next node as current node
        newNode.setNextNode(currentNode);

    } // end of enqueue()


    @Override
    public String dequeue() {
        // Set node head as removed, and node head as next node
        Proc removed = nodeHead;

        if (removed == null) {
            // If empty list, return empty string
            return "";
        } else {
            nodeHead = nodeHead.getNextNode();
        }
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
        // Return false if procLabel does not exist
        return false;

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

        // Iterate through list until until procLabel
        // (Note: will not add last node's previous node value
        while (currentNode == null || !currentNode.getProcLabel().equals(procLabel)) {
            if (currentNode == null) {
                // Returns -1 if node does not exist
                return -1;
            }
            // Add all previous node's value
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

        int processTime = 0;
        Proc currentNode = nodeHead;

        // Iterate through list until procLabel
        while (currentNode == null || !currentNode.getProcLabel().equals(procLabel)) {
            if (currentNode == null) {
                // Return -1 if node does not exist
                return -1;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }

        // While there exists a next node, add up all the following node's vt
        while (currentNode.getNextNode() != null) {
            processTime += currentNode.getNextNode().getVt();
            currentNode = currentNode.getNextNode();
        }

        return processTime;

    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        Proc currentNode = nodeHead;
        String list = "";
        while (currentNode != null) {
            list += (currentNode.getProcLabel() + " ");
            currentNode = currentNode.getNextNode();
        }

        os.println(list.trim());

    } // end of printAllProcess()

} // end of class OrderedLinkedListRQ
