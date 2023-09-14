package librarysystem.mappings;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import librarysystem.controller.CheckoutController;
import librarysystem.controller.ControllerFactory;
import librarysystem.controller.UiLoader;
import librarysystem.models.BookCopy;
import librarysystem.models.CheckoutCopies;
import librarysystem.models.CheckoutRecordEntry;
import librarysystem.models.Publication;
import librarysystem.util.Const;
import librarysystem.util.DialogUtil;
import librarysystem.util.Result;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OverdueCopies implements Initializable {
    @FXML
    private PublicationLookUp publicationViewController;
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

    private CheckoutCopies checkedoutCopies;
    private final CheckoutController checkoutController = ControllerFactory.get().getCheckoutController();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<CheckoutRecordEntry> checkoutEntries;
        try {
            checkoutEntries = checkoutController.getAllCheckoutRecordEntries();
            checkedoutCopies = new CheckoutCopies(checkoutEntries);
            publicationViewController.getTableView().getSelectionModel()
                    .selectedItemProperty()
                    .addListener((obs, oldSelection, newSelection) -> {
                        copiesInfo.setItems(null);
                        if (newSelection != null) {
                            showCopiesInfo();
                        }
                    });
        } catch (Result e) {
            DialogUtil.showExceptionDialog(e.getMessage());
        }
    }

    private void showCopiesInfo() {
        ObservableList<Publication> list = publicationViewController.getTableView().getSelectionModel().getSelectedItems();
        ObservableList<BookCopy> copies = FXCollections.observableArrayList();
        copies.addAll(list.get(0).getCopies());
        addCopiesColumnValueFactories();
        copiesInfo.setItems(copies);
    }

    private void addCopiesColumnValueFactories() {
        copyno.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCopyNum()));
        checkoutdate
                .setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkedoutCopies
                        .getCheckoutDate(data.getValue())));

        duedate.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkedoutCopies
                .getDueDate(data.getValue())));

        remarks.setCellValueFactory(data -> {
            try {
                return new ReadOnlyObjectWrapper<>(checkedoutCopies
                        .getStatus(data.getValue()));
            } catch (Result e) {
                DialogUtil.showExceptionDialog(e.getMessage());
                return new ReadOnlyObjectWrapper<>("");
            }
        });

        firstname.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkedoutCopies.getFirstNameOfMember(data.getValue())));
        lastname.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkedoutCopies.getLastNameOfMember(data.getValue())));
        memberid.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(checkedoutCopies.getCheckingMemberId(data.getValue())));
    }

    public void back() {
        UiLoader.loadUI(Const.VIEW_DASHBOARD);
    }
}
