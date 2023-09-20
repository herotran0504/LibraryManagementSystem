package member.view;

import business.Address;
import business.LibraryMember;
import core.navigator.GlobalProvider;
import core.util.DialogUtil;
import core.viewmodel.MemberViewModel;
import core.viewmodel.ViewModelRegistry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryMemberView implements Initializable {

    private final static String ACTION_CREATE = "Create";
    private final static String ACTION_UPDATE = "Update";
    private static final String VIEW_MEMBER_TABLE = "views/LibraryMemberTableView.fxml";
    private static final String VIEW_MEMBER = "views/LibraryMemberView.fxml";

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField street;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zip;
    @FXML
    private TextField phone;

    @FXML
    private TextField memberIdHdn; // Hidden Field
    @FXML
    private TextField actionHdn; // Hidden Field

    private final MemberViewModel viewModel = ViewModelRegistry.getInstance().get(MemberViewModel.class);

    @FXML
    protected void addNewMember() {
        if (!validateForm()) return;
        LibraryMember libraryMember = new LibraryMember();
        libraryMember.setFirstname(getFirstName());
        libraryMember.setLastName(getLastName());
        libraryMember.setPhone(getPhone());
        Address address = new Address();
        address.setCity(getCity());
        address.setState(getState());
        address.setStreet(getStreet());
        address.setZip(getZip());
        libraryMember.setAddress(address);
        String msg = getActionHdn();

        try {
            switch (msg) {
                case ACTION_CREATE:
                    DialogUtil.showServiceResponseMessage(viewModel.addNewMember(libraryMember));
                    back();
                    break;
                case ACTION_UPDATE:
                    libraryMember.setMemberId(getMemberIdHdn());
                    DialogUtil.showServiceResponseMessage(viewModel.updateMember(libraryMember));
                    back();
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            DialogUtil.showServiceResponseMessage(e);
        }
    }

    public void setRecordAndShow(LibraryMember librabryMember) {
        GlobalProvider.getInstance().loader.loadViewController(VIEW_MEMBER, librabryMember);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (LibraryMemberTableView.getSelectedMember() != null) {
            LibraryMember libraryMember = LibraryMemberTableView.getSelectedMember();
            firstName.textProperty().set(libraryMember.getFirstname());
            lastName.textProperty().set(libraryMember.getLastName());
            phone.textProperty().set(libraryMember.getPhone());
            city.textProperty().set(libraryMember.getAddress().getCity());
            state.textProperty().set(libraryMember.getAddress().getState());
            street.textProperty().set(libraryMember.getAddress().getStreet());
            zip.textProperty().set(libraryMember.getAddress().getZip());
            memberIdHdn.textProperty().set(libraryMember.getMemberId());
            actionHdn.textProperty().set(ACTION_UPDATE);
        } else {
            actionHdn.textProperty().set(ACTION_CREATE);
        }
    }

    private Boolean validateForm() {
        if (getFirstName().isEmpty() || getLastName().isEmpty()
                || getPhone().isEmpty() || getCity().isEmpty()
                || getState().isEmpty() || getStreet().isEmpty()
                || getZip().isEmpty()) {
            DialogUtil.showExceptionDialog("Please input all field");
            return false;
        }

        try {
            Integer.parseInt(getZip());
        } catch (Exception e) {
            DialogUtil.showExceptionDialog("Zip contains only digit");
            return false;
        }
        return true;

    }

    private String getZip() {
        return zip.textProperty().get();
    }

    private String getStreet() {
        return street.textProperty().get();
    }

    private String getState() {
        return state.textProperty().get();
    }

    private String getCity() {
        return city.textProperty().get();
    }

    private String getPhone() {
        return phone.textProperty().get();
    }

    private String getLastName() {
        return lastName.textProperty().get();
    }

    private String getFirstName() {
        return firstName.textProperty().get();
    }

    private String getMemberIdHdn() {
        return memberIdHdn.textProperty().get();
    }

    private String getActionHdn() {
        return actionHdn.textProperty().get();
    }

    public void back() {
        String actionParent = getActionHdn();
        if (actionParent.equals(ACTION_CREATE)) {
            GlobalProvider.getInstance().navigator.reloadDashBoardView();
        } else if (actionParent.equals(ACTION_UPDATE)) {
            GlobalProvider.getInstance().loader.loadViewController(VIEW_MEMBER_TABLE);
        }
    }

}
