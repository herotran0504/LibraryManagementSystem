package librarysystem.member.view;

import business.Auth;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import librarysystem.book.controller.CheckoutController;
import librarysystem.controller.ControllerFactory;
import librarysystem.controller.UiLoader;
import librarysystem.member.controller.LibraryMemberController;
import librarysystem.user.controller.UserData;
import librarysystem.util.Const;
import librarysystem.util.DialogUtil;
import librarysystem.util.Functors;
import librarysystem.utils.Result;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryMemberTableView implements Initializable {

    @FXML
    private TableView<LibraryMember> tableView;
    @FXML
    private TableColumn<LibraryMember, String> colId;
    @FXML
    private TableColumn<LibraryMember, String> colFirstName;
    @FXML
    private TableColumn<LibraryMember, String> colLastName;
    @FXML
    private TableColumn<LibraryMember, String> colStreet;
    @FXML
    private TableColumn<LibraryMember, String> colCity;
    @FXML
    private TableColumn<LibraryMember, String> colState;
    @FXML
    private TableColumn<LibraryMember, String> colZip;
    @FXML
    private TableColumn<LibraryMember, String> colPhone;
    @FXML
    private TextField searchMemberId;
    @FXML
    private TextField searchMemberName;
    @FXML
    private Button editBTN;
    @FXML
    private Button deleteBTN;

    private static LibraryMember libraryMember;

    private final ObservableList<LibraryMember> masterData = FXCollections.observableArrayList();
    private final ObservableList<LibraryMember> filteredData = FXCollections.observableArrayList();

    private final LibraryMemberController controller = ControllerFactory.get().getLibraryMemberController();
    private final CheckoutController checkoutController = ControllerFactory.get().getCheckoutController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateGrid();

        if (UserData.getAuth().toString().equals(Auth.LIBRARIAN.toString())) {
            editBTN.setDisable(true);
            deleteBTN.setDisable(true);
        }
    }

    public void populateGrid() {
        showList();

        filteredData.addAll(masterData);
        tableView.setItems(filteredData);

        searchMemberName.textProperty().addListener((observable, oldValue, newValue) -> updateFilteredData());
        searchMemberId.textProperty().addListener((observable, oldValue, newValue) -> updateFilteredData());
    }

    private void showList() {
        try {
            Result<List<LibraryMember>> serviceResponse = controller.getMembers();
            List<LibraryMember> memberList = serviceResponse.getData();
            for (LibraryMember libraryMember : memberList) {
                colId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
                colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
                colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

                colCity.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAddress().getCity()));
                colStreet.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAddress().getStreet()));
                colState.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAddress().getState()));
                colZip.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAddress().getZip()));

                masterData.add(libraryMember);
            }
        } catch (Exception e) {
            DialogUtil.showServiceResponseMessage(e);
        }
    }

    public void addEditAction() {
        try {
            ObservableList<LibraryMember> list = tableView.getSelectionModel().getSelectedItems();

            if (list.isEmpty()) {
                DialogUtil.showInformationDialog("Select Member First");
            } else {
                libraryMember = list.get(0);
                new LibraryMemberView().setRecordAndShow(list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord() {
        try {
            ObservableList<LibraryMember> list = tableView.getSelectionModel().getSelectedItems();
            if (list.isEmpty()) {
                DialogUtil.showInformationDialog("Select Member First");
            } else {
                if (DialogUtil.showConfirmDialog("Are you sure to delete?")) {
                    libraryMember = list.get(0);
                    Result<Void> serviceResponse = controller.deleteMember(libraryMember.getMemberId());
                    DialogUtil.showServiceResponseMessage(serviceResponse);

                    filteredData.clear();
                    masterData.clear();
                    populateGrid();
                }
            }
        } catch (Exception e) {
            DialogUtil.showServiceResponseMessage(e);
        }
    }

    @FXML
    protected void printCheckoutInfo() {
        try {
            ObservableList<LibraryMember> list = tableView.getSelectionModel().getSelectedItems();
            if (list.isEmpty()) {
                DialogUtil.showInformationDialog("Select Member First");
            } else {
                libraryMember = list.get(0);
                List<CheckoutRecordEntry> checkoutEntries = checkoutController.getCheckoutDetail(libraryMember.getMemberId()).getData();
                if (!checkoutEntries.isEmpty()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(String.format("%-5s", "SNO")
                            + String.format("%-15s", "ISBN/Issue")
                            + String.format("%-30s", "Title")
                            + String.format("%-12s", "Publication")
                            + String.format("%-15s", "Checkout Date")
                            + String.format("%-15s", "Due Date"));

                    stringBuffer.append("\n========================================================================================");
                    int count = 0;
                    for (CheckoutRecordEntry checkoutRecordEntry : checkoutEntries) {
                        stringBuffer.append('\n');
                        stringBuffer.append(String.format("%-5s", ++count)
                                + String.format("%-15s", checkoutRecordEntry
                                .getCopy().getBook()
                                .getIsbn())
                                + String.format("%-30s", checkoutRecordEntry
                                .getCopy().getBook().getTitle())
                                + String.format("%-12s", checkoutRecordEntry
                                .getCopy().getBook().getClass()
                                .getSimpleName())
                                + String.format("%-15s",
                                checkoutRecordEntry.getCheckoutDate())
                                + String.format("%-15s",
                                checkoutRecordEntry.getDueDate()));
                    }
                    stringBuffer
                            .append("\n========================================================================================");

                    System.out.println(stringBuffer);
                    DialogUtil.showInformationDialog("Look in console");
                } else {
                    DialogUtil.showInformationDialog("User doesn't have any checkout records");
                }

            }
        } catch (Exception e) {
            DialogUtil.showServiceResponseMessage(e);
        }
    }

    public static LibraryMember getSelectedMember() {
        return libraryMember;
    }

    private void updateFilteredData() {
        filteredData.clear();
        final String memberIdText = searchMemberId.getText();
        String idSubString = memberIdText == null ? "" : memberIdText;
        final String memberNameText = searchMemberName.getText();
        String nameSubString = memberNameText == null ? "" : memberNameText;
        List<LibraryMember> members = Functors.MEMBER_FILTER.apply(masterData, idSubString, nameSubString);
        filteredData.addAll(members);
        reapplyTableSortOrder();
    }

    private void reapplyTableSortOrder() {
        List<TableColumn<LibraryMember, ?>> sortOrder = new ArrayList<>(tableView.getSortOrder());
        tableView.getSortOrder().clear();
        tableView.getSortOrder().addAll(sortOrder);
    }

    public void back() {
        libraryMember = null;
        UiLoader.loadUI(Const.VIEW_DASHBOARD);
    }
}
