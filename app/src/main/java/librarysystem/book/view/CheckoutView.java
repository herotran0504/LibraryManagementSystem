package librarysystem.book.view;

import book.view.BookSearchView;
import business.*;
import core.navigator.GlobalProvider;
import core.util.DialogUtil;
import core.util.Functors;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import librarysystem.book.controller.CheckoutController;
import librarysystem.controller.ControllerFactory;
import librarysystem.member.controller.LibraryMemberController;
import librarysystem.utils.DateUtil;
import librarysystem.utils.Result;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CheckoutView implements Initializable {

    @FXML
    private BookSearchView publicationViewController;
    @FXML
    private TextField firstName;
    @FXML
    private TextField memberId;
    @FXML
    private TextField lastName;
    @FXML
    private TextField checkoutDate;
    @FXML
    private TextField dueDate;
    @FXML
    private Button checkMemberBtn;
    @FXML
    private Button checkoutBtn;

    private final LibraryMemberController libraryMemberController = ControllerFactory.get().getLibraryMemberController();
    private final CheckoutController checkoutController = ControllerFactory.get().getCheckoutController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkoutBtn.setDisable(true);
        setCheckoutDate(DateUtil.format(new Date()));

        publicationViewController.getTableView().getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        rowListenerValidate();
                    }
                });

    }

    private void rowListenerValidate() {
        ObservableList<Book> list = publicationViewController.getTableView().getSelectionModel().getSelectedItems();
        Book pub = list.get(0);

        int available = Functors.AVAILABLE_COPIES_COUNTER.apply(pub);

        if (available > 0 && !getMemberId().isEmpty()
                && !getFirstName().isEmpty() && !getLastName().isEmpty()) {
            setDueDate(addDayToCurrentDate(pub.getMaxCheckoutLength()));
            checkoutBtn.setDisable(false);
        } else {
            checkoutBtn.setDisable(true);
        }
    }

    @FXML
    protected void checkout() {
        ObservableList<Book> list = publicationViewController.getTableView().getSelectionModel().getSelectedItems();
        if (list.isEmpty()) {
            DialogUtil.showInformationDialog("Select publication first");
        } else {
            if (DialogUtil.showConfirmDialog("Are you sure to checkout?")) {
                // Get Data
                String member = getMemberId();
                String checkoutDate = getCheckoutDate();
                String dueDate = getDueDate();
                Book publication = list.get(0);

                CheckoutRecord checkoutRecord;
                LibraryMember libraryMember;
                try {
                    libraryMember = libraryMemberController.getMember(member).getData();
                    BookCopy copy = Functors.AVAILABLE_COPIES_FINDER.apply(publication).get(0);
                    checkoutRecord = checkoutController.getCheckoutRecord(libraryMember);
                    List<CheckoutRecordEntry> checkoutEntries = checkoutRecord.getCheckoutEntries();
                    checkoutEntries.add(new CheckoutRecordEntry(checkoutDate, dueDate, copy, checkoutRecord));
                    Result<Void> serviceResponse = checkoutController.save(checkoutRecord);
                    DialogUtil.showServiceResponseMessage(serviceResponse);
                    if (serviceResponse.getSuccess()) {
                        back();
                    }
                } catch (Exception e) {
                    DialogUtil.showServiceResponseMessage(e);
                }
            }
        }

    }

    @FXML
    protected void cancelWindow() {
        back();
    }

    @FXML
    protected void checkMember() {
        if (getMemberId().isEmpty()) {
            DialogUtil.showExceptionDialog("Please input member id");
        } else {
            try {
                Result result = libraryMemberController.getMember(getMemberId());
                if (result.getSuccess()) {
                    LibraryMember libraryMember = (LibraryMember) result.getData();
                    setFirstName(libraryMember.getFirstname());
                    setLastName(libraryMember.getLastName());
                    memberId.setDisable(true);
                    checkMemberBtn.setDisable(true);
                } else {
                    DialogUtil.showServiceResponseMessage(result);
                    memberId.requestFocus();
                }
            } catch (Exception e) {
                DialogUtil.showServiceResponseMessage(e);
            }

        }
    }

    private String getFirstName() {
        return firstName.textProperty().get();
    }

    private void setFirstName(String firstName) {
        this.firstName.textProperty().set(firstName);
    }

    private void setLastName(String lastName) {
        this.lastName.textProperty().set(lastName);
    }

    private String getMemberId() {
        return memberId.textProperty().get();
    }

    private String getLastName() {
        return lastName.textProperty().get();
    }

    private String getCheckoutDate() {
        return checkoutDate.textProperty().get();
    }

    private void setCheckoutDate(String date) {
        this.checkoutDate.textProperty().set(date);
    }

    private String getDueDate() {
        return dueDate.textProperty().get();
    }

    private void setDueDate(String date) {
        this.dueDate.textProperty().set(date);
    }

    private String addDayToCurrentDate(int day) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return DateUtil.format(c.getTime());
    }

    public void back() {
        GlobalProvider.getInstance().navigator.reloadDashBoardView();
    }

}
