public class Proc {

    private String procLabel;
    private int vt;

    public Proc(String procLabel, int vt) {
        this.procLabel = procLabel;
        this.vt = vt;
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
}
