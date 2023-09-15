package librarysystem.book.view;

import business.Address;
import business.Author;
import business.Book;
import business.exception.BookException;
import core.util.DialogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import librarysystem.book.controller.BookController;
import librarysystem.controller.ControllerFactory;
import librarysystem.controller.UiLoader;
import librarysystem.utils.Result;

import java.util.ArrayList;
import java.util.List;

import static librarysystem.util.Const.VIEW_DASHBOARD;

public class AddBookView {

    @FXML
    private TextField isbn;

    @FXML
    private TextField title;

    @FXML
    private TextField Copies;

    @FXML
    private TextField maxcheckoutlength;

    @FXML
    private TableView<Author> authorstable;

    @FXML
    private TableColumn<Author, String> column_firstname;

    @FXML
    private TableColumn<Author, String> column_lastname;

    @FXML
    private TableColumn<Author, String> column_credentials;

    @FXML
    private TableColumn<Author, String> column_phoneNumber;

    @FXML
    private TableColumn<Author, String> column_shortBio;

    private final BookController controller = ControllerFactory.get().getBookController();

    @FXML
    protected void addNewMember() {
        final String isbn = this.isbn.getText();
        final String title = this.title.getText();
        final int maxCheckoutLength = Integer.parseInt(maxcheckoutlength.getText());
        final int copyNum = Integer.parseInt(Copies.getText());
        final List<Author> authors = new ArrayList<>(authorstable.getItems());
        Book book = new Book(isbn, title, maxCheckoutLength, authors);
        if (copyNum > 1) book.addCopy(copyNum - 1);
        try {
            Result<Void> response = controller.addNewBook(book);
            if (response.getSuccess()) {
                DialogUtil.showInformationDialog(response.getMessage());
                back();
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
        if (!validateForm()) return;
        final Author author = createAuthor();
        handleFirstName();
        handleLastName();
        handleCredentials();
        handlePhone();
        handleShortBio();
        authorstable.getItems().add(author);
    }

    private void handleShortBio() {
        column_shortBio.setCellValueFactory(new PropertyValueFactory<>("shortBio"));
        column_shortBio.setCellFactory(TextFieldTableCell.forTableColumn());
        column_shortBio.setOnEditCommit((CellEditEvent<Author, String> t) -> {
            Author author = t.getTableView().getItems().get(t.getTablePosition().getRow());
            author.setPhone(t.getNewValue());
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
        author.setFirstname("First Name");
        author.setLastName("Last Name");
        author.setCredentials("Credentials");
        author.setPhone("Phone Number");
        author.setShortBio("Short Bio");
        Address address = new Address("Street", "City", "State", "Zip");
        author.setAddress(address);
        return author;
    }

    private void handleCredentials() {
        column_credentials.setCellValueFactory(new PropertyValueFactory<>("credentials"));
        column_credentials.setCellFactory(TextFieldTableCell.forTableColumn());
        column_credentials.setOnEditCommit((CellEditEvent<Author, String> t) -> {
            Author author = t.getTableView().getItems().get(t.getTablePosition().getRow());
            author.setCredentials(t.getNewValue());
        });
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
        if (
                isbn.getText().trim().isEmpty() ||
                        title.getText().trim().isEmpty() ||
                        maxcheckoutlength.getText().trim().isEmpty() ||
                        Copies.getText().trim().isEmpty()
        ) {
            DialogUtil.showExceptionDialog("Please input all field");
            return false;
        }

        try {
            Integer.parseInt(maxcheckoutlength.getText().trim());
        } catch (Exception e) {
            DialogUtil.showExceptionDialog("Max Checkout Length contains only digit");
            return false;
        }

        try {
            Integer.parseInt(Copies.getText().trim());
        } catch (Exception e) {
            DialogUtil.showExceptionDialog("Copies contains only digit");
            return false;
        }
        return true;

    }

    public void back() {
        UiLoader.loadUI(VIEW_DASHBOARD);
    }
}
