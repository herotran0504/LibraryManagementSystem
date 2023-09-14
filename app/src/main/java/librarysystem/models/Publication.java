package librarysystem.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Publication implements Serializable {

    private String title;
    private List<Copy> copies = new ArrayList<>();

    private int maxCheckoutLength;

    public int getMaxCheckoutLength() {
        return maxCheckoutLength;
    }

    protected Publication() {
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setMaxCheckoutLength(int maxCheckoutLength) {
        this.maxCheckoutLength = maxCheckoutLength;
    }

    protected Publication(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract String getPublicationId();

    public abstract String getPrimaryKey();

    public void addCopy() {
        if (this.copies == null) {
            this.copies = new ArrayList<>();
        }
        Copy copy = new Copy(this.copies.size() + 1);
        copy.setCheckedOut(false);
        copy.setPublication(this);
        this.copies.add(copy);
    }
}