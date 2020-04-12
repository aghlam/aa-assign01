public class Proc {

    // General Variables
    private String procLabel;
    private int vt;
    // OrderedLinkedList variables
    private Proc nextNode;
    //BinarySearchTree variables
    private Proc leftNode, rightNode; // Child Nodes
    private Proc parentNode;

    // General Constructor
    public Proc(String procLabel, int vt) {
        this.procLabel = procLabel;
        this.vt = vt;
    }

    // Constructor for OrderLinkedList
    public Proc(String procLabel, int vt, Proc nextNode) {
        this.procLabel = procLabel;
        this.vt = vt;
        this.nextNode = nextNode;
    }

    // Constructor for BinarySearchTree
    public Proc(String procLabel, int vt,Proc parentNode, Proc leftNode, Proc rightNode) {
        this.procLabel = procLabel;
        this.vt = vt;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.parentNode = parentNode;
    }

    // General getter and setter methods
    public String getProcLabel() {
        return procLabel;
    }

    public void setProcLabel(String procLabel) {
        this.procLabel = procLabel;
    }

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }


    // Getter and setter methods for OrderLinkedList use
    public Proc getNextNode() {
        return nextNode;
    }

    public void setNextNode(Proc nextNode) {
        this.nextNode = nextNode;
    }


    //Getter and setter methods for BinaryTreeSearch
    public Proc getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Proc leftNode) {
        this.leftNode = leftNode;
    }

    public Proc getRightNode() {
        return rightNode;
    }

    public void setRightNode(Proc rightNode) {
        this.rightNode = rightNode;
    }

    public Proc getParentNode() {
        return parentNode;
    }

    public void setParentNode(Proc parentNode) {
        this.parentNode = parentNode;
    }
}