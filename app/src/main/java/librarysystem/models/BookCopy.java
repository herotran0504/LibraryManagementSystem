package librarysystem.models;

import java.io.Serializable;

final public class BookCopy implements Serializable {

    private final Book book;
    private final int copyNum;
    private boolean isAvailable;

    BookCopy(Book book, int copyNum, boolean isAvailable) {
        this.book = book;
        this.copyNum = copyNum;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCopyNum() {
        return copyNum;
    }

    public Book getBook() {
        return book;
    }

    public void changeAvailability() {
        isAvailable = !isAvailable;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (!(ob instanceof BookCopy copy)) return false;
        return copy.book.getIsbn().equals(book.getIsbn()) && copy.copyNum == copyNum;
    }

    public String getPrimaryKey() {
        return book.getIsbn() + '_' + copyNum;
    }
}
