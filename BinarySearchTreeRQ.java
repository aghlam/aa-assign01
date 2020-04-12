import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the Runqueue interface using a Binary Search Tree.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class BinarySearchTreeRQ implements Runqueue {

    Proc root;
    /**
     * Constructs empty queue
     */
    public BinarySearchTreeRQ() {
        // Implement Me
        root = null;

    }  // end of BinarySearchTreeRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
        Proc newNode = new Proc(procLabel, vt, null, null);
        Proc currentNode = null;
        Proc parentNode = null;
        boolean search = true;
        // If tree is empty, assign newNode as root
        if (root == null) {
            root = newNode;
        } else {
            currentNode = root;

            while(search) {
                parentNode = currentNode;
                // Check if heading left (smaller than current vt) or right (bigger than current vt)
                // Left path
                if (vt < currentNode.getVt()) {
                    currentNode = currentNode.getLeftNode();
                    // Set left node as new node if empty
                    if (currentNode == null) {
                        parentNode.setLeftNode(newNode);
                        search = false;
                    }
                } // End left path
                // Right Path
                else {
                    currentNode = currentNode.getRightNode();
                    // Set right node as new node if empty
                    if (currentNode == null) {
                        parentNode.setRightNode(newNode);
                        search = false;
                    }
                } // End right path
            }

        }

    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me

        return ""; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        // Implement me

        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me

        return false; // placeholder, modify this
    } // end of removeProcess()


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
        // Implement me

    } // end of printAllProcess()

} // end of class BinarySearchTreeRQ
