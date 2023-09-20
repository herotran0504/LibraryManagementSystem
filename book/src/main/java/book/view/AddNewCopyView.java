package book.view;

import business.Book;
import core.navigator.GlobalProvider;
import core.util.DialogUtil;
import core.viewmodel.BookViewModel;
import core.viewmodel.ViewModelRegistry;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddNewCopyView {

    @FXML
    private BookSearchView publicationViewController;

    @FXML
    private TextField txtNumOfCopies;

    private final BookViewModel viewModel = ViewModelRegistry.getInstance().get(BookViewModel.class);

    @FXML
    private void addNewCopy() {
        ObservableList<Book> selected = publicationViewController.getTableView().getSelectionModel().getSelectedItems();
        if (selected.isEmpty()) {
            DialogUtil.showInformationDialog("Select a book first");
        } else if (selected.size() > 1) {
            DialogUtil.showInformationDialog("Selecting multiple items is not allowed!!");
        } else {
            try {
                Integer noOfCopies = Integer.parseInt(txtNumOfCopies.getText());
                Book p = selected.get(0);
                for (int i = 0; i < noOfCopies; i++) {
                    p.addCopy();
                }
                viewModel.addNewBook(p);
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
        GlobalProvider.getInstance().loader.loadViewController("views/DashboardView.fxml");
    }
}
