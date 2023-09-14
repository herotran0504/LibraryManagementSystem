package librarysystem.models;

import java.io.Serializable;

public class Copy implements Serializable {

    private Integer copyNum;
    private Publication publication;
    private boolean isCheckedOut;

    public Copy() {
    }

    public Copy(Integer noOfCopy) {
        this.copyNum = noOfCopy;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean isCheckedout) {
        this.isCheckedOut = isCheckedout;
    }

    public Integer getCopyNum() {
        return copyNum;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getPrimaryKey() {
        return publication.getPrimaryKey() + '_' + copyNum.toString();
    }

}