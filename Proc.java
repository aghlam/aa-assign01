public class Proc {

    private String procLabel;
    private int vt;
    private Proc nextNode;

    public Proc(String procLabel, int vt) {
        this.procLabel = procLabel;
        this.vt = vt;
    }

    public Proc(String procLabel, int vt, Proc nextNode) {
        this.procLabel = procLabel;
        this.vt = vt;
        this.nextNode = nextNode;
    }

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


    // Getter method for OrderLinkedList use
    public Proc getNextNode() {
        return nextNode;
    }

    // Setter method for OrderLinkedList use
    public void setNextNode(Proc nextNode) {
        this.nextNode = nextNode;
    }
}
