package librarysystem.book.view;

import business.Book;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import librarysystem.book.controller.BookController;
import librarysystem.controller.ControllerFactory;
import librarysystem.util.DialogUtil;
import librarysystem.util.Functors;
import librarysystem.utils.Result;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookSearchView implements Initializable {

    private List<Book> allPublications;

    @FXML
    private TextField searchPublicationId;
    @FXML
    private TextField searchPublicationTitle;
    @FXML
    private TableView<Book> tableView;

    public TableView<Book> getTableView() {
        return tableView;
    }

    @FXML
    private TableColumn<Book, String> collPublicationId;
    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, Integer> colAvailableCopies;
    @FXML
    private TableColumn<Book, Integer> colTotalCopies;
    @FXML
    private TableColumn<Book, Integer> colMaxCheckoutLength;
    @FXML
    private TableColumn<Book, String> colPublicationType;

    private final BookController controller = ControllerFactory.get().getBookController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCompleteList();
        addSearchCapabilities();
    }

    private void addSearchCapabilities() {
        searchPublicationId.textProperty().addListener((observable, oldValue, newValue) -> filterTableData());

        searchPublicationTitle.textProperty().addListener((observable, oldValue, newValue) -> filterTableData());
    }

    public void showCompleteList() {
        try {
            Result<List<Book>> result = controller.getAllBooks();
            colPublicationType.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getClass().getSimpleName()));
            collPublicationId.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getIsbn()));
            colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colAvailableCopies.setCellValueFactory(data -> {
                int numOfAvailableCopies = Functors.AVAILABLE_COPIES_COUNTER.apply(data.getValue());
                return new ReadOnlyObjectWrapper<>(numOfAvailableCopies);
            });
            colTotalCopies.setCellValueFactory(data -> {
                int total = 0;
                if (data.getValue().getCopies() != null) {
                    total = data.getValue().getCopies().length;
                }
                return new ReadOnlyObjectWrapper<>(total);
            });
            colMaxCheckoutLength.setCellValueFactory(new PropertyValueFactory<>("maxCheckoutLength"));

            allPublications = result.getData();
            searchPublicationId.setText("");
            searchPublicationTitle.setText("");
            tableView.getItems().clear();
            allPublications.forEach(p -> tableView.getItems().add(p));
        } catch (Exception e) {
            DialogUtil.showServiceResponseMessage(e);
        }
    }

    private void filterTableData() {
        String newId = searchPublicationId.getText() != null ? searchPublicationId.getText().toLowerCase() : "";
        String newTitle = searchPublicationTitle.getText() != null ? searchPublicationTitle.getText().toLowerCase() : "";
        List<Book> result = Functors.BOOK_FILTER.apply(allPublications, newId, newTitle);
        tableView.getItems().clear();
        result.forEach(p -> tableView.getItems().add(p));
    }
}
