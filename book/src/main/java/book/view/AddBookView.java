package book.view;

import business.Address;
import business.Author;
import business.Book;
import business.exception.BookException;
import core.navigator.GlobalProvider;
import core.util.DialogUtil;
import core.viewmodel.BookViewModel;
import core.viewmodel.ViewModelRegistry;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import librarysystem.utils.Result;

import java.util.ArrayList;
import java.util.List;

public class AddBookView {

    @FXML
    private TextField isbn;

    @FXML
    private TextField title;

    @FXML
    private TextField copies;

    @FXML
    private TextField maxCheckoutLength;

    @FXML
    private TableView<Author> authorsTable;

    @FXML
    private TableColumn<Author, String> column_firstname;

    @FXML
    private TableColumn<Author, String> column_lastname;

    @FXML
    private TableColumn<Author, String> column_phoneNumber;

    @FXML
    private TableColumn<Author, String> column_bio;

    private final BookViewModel viewModel = ViewModelRegistry.getInstance().get(BookViewModel.class);

    @FXML
    protected void addNewBook() {
        if (!validateForm()) return;
        final String isbn = this.isbn.getText();
        final String title = this.title.getText();
        final int length = Integer.parseInt(maxCheckoutLength.getText());
        final int copyNum = Integer.parseInt(copies.getText());
        final List<Author> authors = new ArrayList<>(authorsTable.getItems());
        Book book = new Book(isbn, title, length, authors);
        if (copyNum > 1) book.addCopy(copyNum - 1);
        try {
            Result<Void> response = viewModel.addNewBook(book);
            if (response.getSuccess()) {
                DialogUtil.showInformationDialog(response.getMessage());
            } else {
                DialogUtil.showWarningDialog(response.getMessage());
            }
        } catch (BookException e) {
            DialogUtil.showExceptionDialog(e.getMessage());
        }
    }

    @FXML
    protected void cancelAdding() {
        isbn.getScene().getWindow().hide();
    }

    @FXML
    protected void addNewAuthor() {
        final Author author = createAuthor();
        handleFirstName();
        handleLastName();
        handlePhone();
        handleShortBio();
        authorsTable.getItems().add(author);
    }

    private void handleShortBio() {
        column_bio.setCellValueFactory(new PropertyValueFactory<>("bio"));
        column_bio.setCellFactory(TextFieldTableCell.forTableColumn());
        column_bio.setOnEditCommit((CellEditEvent<Author, String> t) -> {
            Author author = t.getTableView().getItems().get(t.getTablePosition().getRow());
            author.setBio(t.getNewValue());
        });
    }

    private void handlePhone() {
        column_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        column_phoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        column_phoneNumber.setOnEditCommit((CellEditEvent<Author, String> t) -> {
            Author author = t.getTableView().getItems().get(t.getTablePosition().getRow());
            author.setPhone(t.getNewValue());
        });
    }

    private static Author createAuthor() {
        Author author = new Author();
        author.setFirstname(" ");
        author.setLastName(" ");
        author.setPhone(" ");
        Address address = new Address(" ", " ", " ", " ");
        author.setAddress(address);
        return author;
    }

    private void handleLastName() {
        column_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column_lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        column_lastname.setOnEditCommit((CellEditEvent<Author, String> t) -> {
            Author author = t.getTableView().getItems().get(t.getTablePosition().getRow());
            author.setLastName(t.getNewValue());
        });
    }

    private void handleFirstName() {
        column_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        column_firstname.setCellFactory(TextFieldTableCell.forTableColumn());
        column_firstname.setOnEditCommit((CellEditEvent<Author, String> t) -> {
            Author author = t.getTableView().getItems().get(t.getTablePosition().getRow());
            author.setFirstname(t.getNewValue());
        });
    }

    private boolean validateForm() {
        if (isbn.getText().trim().isEmpty() ||
                title.getText().trim().isEmpty() ||
                maxCheckoutLength.getText().trim().isEmpty() ||
                copies.getText().trim().isEmpty()
        ) {
            DialogUtil.showExceptionDialog("Please input all field");
            return false;
        }

        try {
            final int length = Integer.parseInt(maxCheckoutLength.getText().trim());
            if (length != 7 && length != 21) {
                DialogUtil.showExceptionDialog("Max Checkout Length must be 7 or 21 days");
                return false;
            }
        } catch (Exception e) {
            DialogUtil.showExceptionDialog("Max Checkout Length contains only digit");
            return false;
        }

        try {
            Integer.parseInt(copies.getText().trim());
        } catch (Exception e) {
            DialogUtil.showExceptionDialog("Copies contains only digit");
            return false;
        }
        return true;

    }

    public void back() {
        GlobalProvider.getInstance().loader.loadViewController("view/DashboardView.fxml");
    }

}
