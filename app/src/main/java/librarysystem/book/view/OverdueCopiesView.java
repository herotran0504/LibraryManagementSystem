package librarysystem.book.view;

import business.Book;
import business.BookCopy;
import business.CheckoutCopies;
import business.CheckoutRecordEntry;
import business.exception.CheckoutException;
import business.exception.MemberException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import librarysystem.book.controller.CheckoutController;
import librarysystem.controller.ControllerFactory;
import librarysystem.controller.UiLoader;
import librarysystem.util.Const;
import librarysystem.util.DialogUtil;
import librarysystem.utils.DateUtil;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class OverdueCopiesView implements Initializable {
    @FXML
    private BookSearchView publicationViewController;
    @FXML
    private TableView<BookCopy> copiesInfo;
    @FXML
    private TableColumn<BookCopy, Integer> copyno;
    @FXML
    private TableColumn<BookCopy, String> checkoutdate;
    @FXML
    private TableColumn<BookCopy, String> duedate;
    @FXML
    private TableColumn<BookCopy, String> remarks;
    @FXML
    private TableColumn<BookCopy, String> firstname;
    @FXML
    private TableColumn<BookCopy, String> lastname;
    @FXML
    private TableColumn<BookCopy, String> memberid;

    private CheckoutCopies checkoutCopies;
    private final CheckoutController checkoutController = ControllerFactory.get().getCheckoutController();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<CheckoutRecordEntry> checkoutEntries;
        try {
            checkoutEntries = checkoutController.getAllCheckoutRecordEntries();
            checkoutCopies = new CheckoutCopies(checkoutEntries);
            publicationViewController.getTableView().getSelectionModel()
                    .selectedItemProperty()
                    .addListener((obs, oldSelection, newSelection) -> {
                        copiesInfo.setItems(null);
                        if (newSelection != null) {
                            showCopiesInfo();
                        }
                    });
        } catch (CheckoutException e) {
            DialogUtil.showExceptionDialog(e.getMessage());
        }
    }

    private void showCopiesInfo() {
        ObservableList<Book> list = publicationViewController.getTableView().getSelectionModel().getSelectedItems();
        ObservableList<BookCopy> copies = FXCollections.observableArrayList();
        copies.addAll(list.get(0).getCopies());
        addCopiesColumnValueFactories();
        copiesInfo.setItems(copies);
    }

    private void addCopiesColumnValueFactories() {
        copyno.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCopyNum()));

        checkoutdate.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkoutCopies.getCheckoutDate(data.getValue())));

        duedate.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkoutCopies.getDueDate(data.getValue())));

        remarks.setCellValueFactory(data -> {
            try {
                return new ReadOnlyObjectWrapper<>(getStatus(checkoutCopies, data.getValue()));
            } catch (MemberException e) {
                DialogUtil.showExceptionDialog(e.getMessage());
                return new ReadOnlyObjectWrapper<>("");
            }
        });

        firstname.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkoutCopies.getFirstNameOfMember(data.getValue())));
        lastname.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkoutCopies.getLastNameOfMember(data.getValue())));
        memberid.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkoutCopies.getCheckingMemberId(data.getValue())));
    }

    private static String getStatus(CheckoutCopies checkoutCopy, BookCopy copy) throws MemberException {
        final Map<String, CheckoutRecordEntry> checkedOutCopies = checkoutCopy.getCheckedOutCopies();
        if (checkedOutCopies.containsKey(copy.getPrimaryKey())) {
            try {
                Date dueDate = DateUtil.parse(checkedOutCopies.get(copy.getPrimaryKey()).getDueDate());
                Date currentDate = new Date();
                if (dueDate.compareTo(currentDate) > 0) {
                    return "CHECKED OUT";
                } else {
                    return "OVERDUE";
                }
            } catch (ParseException e) {
                throw new MemberException(e.getMessage());
            }
        } else {
            return "AVAILABLE";
        }
    }

    public void back() {
        UiLoader.loadUI(Const.VIEW_DASHBOARD);
    }
}
