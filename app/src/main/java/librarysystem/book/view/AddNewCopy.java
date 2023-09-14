package librarysystem.book.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import librarysystem.controller.ControllerFactory;
import librarysystem.book.controller.PublicationController;
import librarysystem.controller.UiLoader;
import business.Publication;
import librarysystem.util.Const;
import librarysystem.util.DialogUtil;

public class AddNewCopy {

    @FXML
    private PublicationLookUp publicationViewController;

    @FXML
    private TextField txtNumOfCopies;

    private final PublicationController controller = ControllerFactory.get().getPublicationController();

    @FXML
    private void addNewCopy() {
        ObservableList<Publication> selected = publicationViewController.getTableView()
                .getSelectionModel().getSelectedItems();
        if (selected.size() == 0) {
            DialogUtil.showInformationDialog("Select a publication first");
        } else if (selected.size() > 1) {
            DialogUtil.showInformationDialog("Selecting multiple items is not allowed!!");
        } else {
            try {
                Integer noOfCopies = Integer.parseInt(txtNumOfCopies.getText());
                Publication p = selected.get(0);
                for (int i = 0; i < noOfCopies; i++) {
                    p.addCopy();
                }
                // FIXME hung.tran
//                if (p instanceof Book) {
//                    controller.addNewBook((Book) p);
//                }
                publicationViewController.showCompleteList();
                showBookAddedInfo(noOfCopies);
            } catch (NumberFormatException e) {
                showInvalidBookError();
            } catch (Exception e) {
                DialogUtil.showExceptionDialog(e.getMessage());
            }
        }
    }

    private void showInvalidBookError() {
        DialogUtil.showExceptionDialog("Number of copies to be added should be a valid number");
    }

    private void showBookAddedInfo(Integer noOfCopies) {
        DialogUtil.showInformationDialog(noOfCopies + " copies added to the selected publication.");
    }

    @FXML
    protected void cancelWindow() {
        UiLoader.loadUI(Const.VIEW_DASHBOARD);
    }
}
