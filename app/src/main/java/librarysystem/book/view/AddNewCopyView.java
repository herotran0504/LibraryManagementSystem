package librarysystem.book.view;

import business.Book;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import librarysystem.book.controller.BookController;
import librarysystem.controller.ControllerFactory;
import librarysystem.controller.UiLoader;
import librarysystem.util.Const;
import librarysystem.util.DialogUtil;

public class AddNewCopyView {

    @FXML
    private BookSearchView publicationViewController;

    @FXML
    private TextField txtNumOfCopies;

    private final BookController controller = ControllerFactory.get().getBookController();

    @FXML
    private void addNewCopy() {
        ObservableList<Book> selected = publicationViewController.getTableView()
                .getSelectionModel().getSelectedItems();
        if (selected.isEmpty()) {
            DialogUtil.showInformationDialog("Select a publication first");
        } else if (selected.size() > 1) {
            DialogUtil.showInformationDialog("Selecting multiple items is not allowed!!");
        } else {
            try {
                Integer noOfCopies = Integer.parseInt(txtNumOfCopies.getText());
                Book p = selected.get(0);
                for (int i = 0; i < noOfCopies; i++) {
                    p.addCopy();
                }
                controller.addNewBook(p);
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
