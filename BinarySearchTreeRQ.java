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
            } // End while loop search
        }
    } // end of enqueue()


    @Override
    public String dequeue() {

        Proc currentNode = root;
        Proc removed;
        Proc parentNode;

        if (root == null) {
            return "";
        } else {

            if (root.getLeftNode() == null && root.getRightNode() == null) {
                removed = root;
                root = null;
                return removed.getProcLabel();
            } else if (currentNode.getLeftNode() == null && currentNode.getRightNode() != null) {
                removed = currentNode;
                root = currentNode.getRightNode();
                root.setParentNode(null);
                return removed.getProcLabel();
            } else {
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
            }
        }
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {

        return findProcessRecursive(procLabel, root);

    } // end of findProcess()

    public boolean findProcessRecursive(String procLabel, Proc node) {
        Proc currentNode = node;
        boolean result = false;

        if (currentNode == null) {
            return false;
        }
        if (currentNode.getProcLabel().equals(procLabel)) {
            return true;
        }
        if (currentNode.getLeftNode() != null && !result) {
            result = findProcessRecursive(procLabel, currentNode.getLeftNode());
        }
        if (currentNode.getRightNode() != null && !result) {
            result = findProcessRecursive(procLabel, currentNode.getRightNode());
        }

        return result;
    }


    @Override
    public boolean removeProcess(String procLabel) {

        return removeProcessRecursive(procLabel, root);

    } // end of removeProcess()

    public boolean removeProcessRecursive(String procLabel, Proc node) {
        Proc currentNode = node;
        boolean removed = false;

        if (currentNode == null) {
            return false;
        }
        if (currentNode.getProcLabel().equals(procLabel)) {
            /* All the below if-statements check the various conditions the found node could be in:
             * 1.Node has no child AND node is root
             * 2.Node has a left child AND node is root
             * 3.Node has right child AND node is root
             * 4.Node has no children --> Further checks if the leaf is a right or left child of the parent node
             * 5.Node has a left child --> Further checks if the node is a right or left child of the parent node
             * 6.Node has a left child --> Further checks if the node is a right or left child of the parent node
             * 7.Node has two children --> Has further checks if the node-to-swap has a right child or not before the swap occurs
             */
            if (currentNode == root && (root.getLeftNode() == null && root.getRightNode() == null)) //1.Check if there is only one node I.E root
            {
                root = null;
                removed = true;
            } else if (currentNode == root && (root.getLeftNode() != null && root.getRightNode() == null)) //2.Check if root node has left child only
            {
                root = currentNode.getLeftNode();
                root.setParentNode(null);
                currentNode = null;
                removed = true;
            } else if (currentNode == root && (root.getLeftNode() == null && root.getRightNode() != null)) //3.Check if root node has right child only
            {
                root = currentNode.getRightNode();
                root.setParentNode(null);
                currentNode = null;
                removed = true;
            } else if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) //4.Check if node is leaf
            {
                if (currentNode.getVt() > currentNode.getParentNode().getVt()) //Check if this leaf is the right node of parent
                {
                    currentNode.getParentNode().setRightNode(null);
                    currentNode = null;
                    removed = true;
                } else //This leaf is left node of parent
                {
                    currentNode.getParentNode().setLeftNode(null);
                    currentNode = null;
                    removed = true;
                }
            } else if (currentNode.getLeftNode() != null && currentNode.getRightNode() == null) //5.Check if node has left child only
            {
                if (currentNode.getVt() > currentNode.getParentNode().getVt()) //Check if this node is the right node of parent
                {
                    currentNode.getLeftNode().setParentNode(currentNode.getParentNode());
                    currentNode.getParentNode().setRightNode(currentNode.getLeftNode());
                    currentNode = null;
                    removed = true;
                } else //This node is left node of parent
                {
                    currentNode.getLeftNode().setParentNode(currentNode.getParentNode());
                    currentNode.getParentNode().setLeftNode(currentNode.getLeftNode());
                    currentNode = null;
                    removed = true;
                }
            } else if (currentNode.getLeftNode() == null && currentNode.getRightNode() != null) //6.Check if node has right child only
            {
                if (currentNode.getVt() > currentNode.getParentNode().getVt()) //Check if this node is the right node of parent
                {
                    currentNode.getRightNode().setParentNode(currentNode.getParentNode());
                    currentNode.getParentNode().setRightNode(currentNode.getRightNode());
                    currentNode = null;
                    removed = true;
                } else //This node is left node of parent
                {
                    currentNode.getRightNode().setParentNode(currentNode.getParentNode());
                    currentNode.getParentNode().setLeftNode(currentNode.getRightNode());
                    currentNode = null;
                    removed = true;
                }
            } else //7.Node has 2 children - Only one way to do this even if node is the root node
            {
                boolean searched = false;
                Proc toSwap;
                Proc currentMovingNode = currentNode;
                while (!searched) {
                    toSwap = currentMovingNode.getRightNode();
                    if (toSwap.getLeftNode() != null) //Check if the next right node of currentMovingNode has a left node
                    {
                        toSwap = toSwap.getLeftNode();
                        boolean leftSearched = false;
                        while (!leftSearched) {
                            if (toSwap.getLeftNode() != null) {
                                toSwap = toSwap.getLeftNode();
                            } else {
                                leftSearched = true;
                            }
                        }
                        if (toSwap.getRightNode() != null) //Node to swap has a right node
                        {
                            currentNode.setProcLabel(toSwap.getProcLabel());
                            currentNode.setVt(toSwap.getVt());
                            toSwap.getRightNode().setParentNode(toSwap.getParentNode());
                            toSwap.getParentNode().setLeftNode(toSwap.getRightNode());
                            toSwap = null;
                        } else //Node to swap is a leaf
                        {
                            toSwap.getParentNode().setLeftNode(null);
                            currentNode.setProcLabel(toSwap.getProcLabel());
                            currentNode.setVt(toSwap.getVt());
                            toSwap = null;

                        }
                        searched = true;
                    } else {
                        currentMovingNode = currentMovingNode.getRightNode();
                    }
                }
                removed = true;
            }
            return removed;
        }
        if (currentNode.getLeftNode() != null && !removed) {
            removed = removeProcessRecursive(procLabel, currentNode.getLeftNode());
        }
        if (currentNode.getRightNode() != null && !removed) {
            removed = removeProcessRecursive(procLabel, currentNode.getRightNode());
        }

        return removed;
    }


    @Override
    public int precedingProcessTime(String procLabel) {

        Proc targetNode = searchNodes(root, procLabel);
        int time = 0;

        if (targetNode == null) {
            return -1;
        } else {
            time += addPreceddingTime(root, targetNode);
        }
        return time;

    } // end of precedingProcessTime()

    public int addPreceddingTime(Proc node, Proc targetNode) {
        int time = 0;
        if (node != null) {
            time += addPreceddingTime(node.getLeftNode(), targetNode);
            if (!node.getProcLabel().equals(targetNode.getProcLabel()) && node.getVt() <= targetNode.getVt()) {
                if(node != root && targetNode.getRightNode() != node) {
                        time += node.getVt();
                } else if (node.equals(root)) {
                        time += node.getVt();
                }
            }
            time += addPreceddingTime(node.getRightNode(), targetNode);
        }
        return time;
    }

    public Proc searchNodes(Proc node, String procLabel) {

        if (node != null) {
            if (node.getProcLabel().equals(procLabel)) {
                return node;
            } else {
                Proc findingNode = searchNodes(node.getLeftNode(), procLabel);
                if (findingNode == null) {
                    findingNode = searchNodes(node.getRightNode(), procLabel);
                }
                return findingNode;
            }
        } else {
            return null;
        }
    }


    @Override
    public int succeedingProcessTime(String procLabel) {

        Proc targetNode = searchNodes(root, procLabel);
        int time = 0;

        if (targetNode == null) {
            return -1;
        } else {
            time += addSucceedingTime(root, targetNode);
        }
        return time;

    } // end of precedingProcessTime()

    public int addSucceedingTime(Proc node, Proc targetNode) {
        int time = 0;
        if (node != null) {
            time += addSucceedingTime(node.getLeftNode(), targetNode);
            if (!node.getProcLabel().equals(targetNode.getProcLabel()) && node.getVt() >= targetNode.getVt()) {
                if(node != root && targetNode.getParentNode() != node) {
                    time += node.getVt();
                } else if (node.equals(root)) {
                    time += node.getVt();
                }
            }
            time += addSucceedingTime(node.getRightNode(), targetNode);
        }
        return time;
    }


    @Override
    public void printAllProcesses(PrintWriter os) {
        if (root == null) {
            os.println("Error: No processes");
        } else {
            printProcessRec(root, os);
        }

        os.println();
    } // end of printAllProcess()

    public void printProcessRec(Proc node, PrintWriter os) {
        if (node != null) {
            if (nodeIsFarthestRight(node) && node.getLeftNode() == null) {
                os.print(node.getProcLabel());
            } else {
                printProcessRec(node.getLeftNode(), os);
                os.print(node.getProcLabel() + " ");
                printProcessRec(node.getRightNode(), os);
            }
        }
    }

    public boolean nodeIsFarthestRight(Proc node) {
        //Maybe it is easier to save this as a variable, and only update this when enqueuing/dequeuing
        //Hence we only need to check the variable instead of calling this method always
        Proc current = root;
        while (current.getRightNode() != null) {
            current = current.getRightNode();
        }

        if (node.getProcLabel().equals(current.getProcLabel())) {
            return true; //Node we're on IS the furthest right node
        } else {
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
