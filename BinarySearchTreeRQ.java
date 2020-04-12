import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the Runqueue interface using a Binary Search Tree.
 * <p>
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
        Proc currentNode = null;
        Proc parentNode = null;
        boolean search = true;
        // If tree is empty, assign newNode as root
        if (root == null) {
            Proc newNode = new Proc(procLabel, vt, null, null, null);
            root = newNode;
        } else {

            currentNode = root;

            while (search) {
                parentNode = currentNode;
                // Check if heading left (smaller than current vt) or right (bigger than current vt)
                // Left path
                if (vt < currentNode.getVt()) {
                    currentNode = currentNode.getLeftNode();
                    // Set left node as new node if empty
                    if (currentNode == null) {
                        Proc newNode = new Proc(procLabel, vt, parentNode, null, null);
                        parentNode.setLeftNode(newNode);
                        search = false;
                    }
                } // End left path
                // Right Path
                else {
                    currentNode = currentNode.getRightNode();
                    // Set right node as new node if empty
                    if (currentNode == null) {
                        Proc newNode = new Proc(procLabel, vt, parentNode, null, null);
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

//        Proc currentNode = root;
//        Proc removed = null;
//        while (currentNode != null) {
//            traverseNodes(currentNode.getLeftNode());
//            traverseNodes(currentNode.getRightNode());
//            removed = currentNode;
//            break;
//
//        }
//        return removed.getProcLabel();

        Proc currentNode = root;
        Proc removed;
        Proc parentNode;

//        if (root != null) {
//            traverseNodes(node.getLeftNode());
//            System.out.print(node.getProcLabel() + " ");
//            traverseNodes(node.getRightNode());
//        }

        while (currentNode.getLeftNode() != null) {
            currentNode = currentNode.getLeftNode();
        }

        if (currentNode.getRightNode() == null) {
            removed = currentNode;
            currentNode.getParentNode().setLeftNode(null);
//            parentNode.setLeftNode(null);
        } else {
            parentNode = currentNode.getParentNode();
            removed = currentNode;
            parentNode.setLeftNode(currentNode.getRightNode());
            currentNode.getRightNode().setParentNode(parentNode);
        }

        return removed.getProcLabel(); // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        // Implement me

    	return findProcessRecursive(procLabel, root);

    } // end of findProcess()

    public boolean findProcessRecursive(String procLabel, Proc node)
    {
    	Proc currentNode = node;
    	boolean result = false;

    	if(currentNode == null)
    	{
    		return false;
    	}
    	if(currentNode.getProcLabel().equals(procLabel))
    	{
    		return true;
    	}
    	if(currentNode.getLeftNode() != null && !result)
    	{
    		result = findProcessRecursive(procLabel, currentNode.getLeftNode());
    	}
    	if(currentNode.getRightNode() != null && !result)
    	{
    		result = findProcessRecursive(procLabel, currentNode.getRightNode());
    	}

    	return result;
    }


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
        if (root == null) {
            os.println("Error: No processes");
        } else {
            printProcessRec(root, os);
        }

        os.println();
    } // end of printAllProcess()
    
    public void printProcessRec(Proc node, PrintWriter os)
    {
    	if(node != null)
    	{
    		if(nodeIsFarthestRight(node) && node.getLeftNode() == null)
    		{
    			os.print(node.getProcLabel());
    		}
    		else
    		{
        		printProcessRec(node.getLeftNode(), os);
            	os.print(node.getProcLabel() + " ");
            	printProcessRec(node.getRightNode(), os);
    		}
    	}
    }
    
    public boolean nodeIsFarthestRight(Proc node)
    {
    	//Maybe it is easier to save this as a variable, and only update this when enqueuing/dequeuing
    	//Hence we only need to check the variable instead of calling this method always
    	Proc current = root;
    	while(current.getRightNode() != null)
    	{
    		current = current.getRightNode();
    	}
    	
    	if(node.getProcLabel().equals(current.getProcLabel()))
    	{
    		return true; //Node we're on IS the furthest right node
    	}
    	else
    	{
    		return false;
    	}
    }

//    @Override
//    public void printAllProcesses(PrintWriter os) {
//        traverseNodes(root);
//    } // end of printAllProcess()
//
//    public void traverseNodes(Proc node) {
//        if (node != null) {
//            traverseNodes(node.getLeftNode());
//            System.out.print(node.getProcLabel() + " ");
//            traverseNodes(node.getRightNode());
//        }
//    }



} // end of class BinarySearchTreeRQ
