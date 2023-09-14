package librarysystem.mappings;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import librarysystem.controller.ControllerFactory;
import librarysystem.controller.PublicationController;
import librarysystem.models.Publication;
import librarysystem.util.DialogUtil;
import librarysystem.util.Functors;
import librarysystem.util.Result;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PublicationLookUp implements Initializable {

    private List<Publication> allPublications;

    @FXML
    private TextField searchPublicationId;
    @FXML
    private TextField searchPublicationTitle;
    @FXML
    private TableView<Publication> tableView;

    public TableView<Publication> getTableView() {
        return tableView;
    }

    @FXML
    private TableColumn<Publication, String> collPublicationId;
    @FXML
    private TableColumn<Publication, String> colTitle;
    @FXML
    private TableColumn<Publication, Integer> colAvailableCopies;
    @FXML
    private TableColumn<Publication, Integer> colTotalCopies;
    @FXML
    private TableColumn<Publication, Integer> colMaxCheckoutLength;
    @FXML
    private TableColumn<Publication, String> colPublicationType;

    private final PublicationController controller = ControllerFactory.get().getPublicationController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCompleteList();
        addSearchCapabilities();
    }

    private void addSearchCapabilities() {
        searchPublicationId.textProperty().addListener((observable, oldValue, newValue) -> filterTableData());

        searchPublicationTitle.textProperty().addListener((observable, oldValue, newValue) -> filterTableData());
    }

    @SuppressWarnings("unchecked")
    public void showCompleteList() {
        try {

            Result result = controller.getAllPublications();

            colPublicationType.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getClass().getSimpleName()));


            collPublicationId.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPublicationId()));
            colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colAvailableCopies.setCellValueFactory(data -> {
                int numOfAvailableCopies = Functors.AVAILABLE_COPIES_COUNTER.apply(data.getValue());
                return new ReadOnlyObjectWrapper<>(numOfAvailableCopies);
            });
            colTotalCopies.setCellValueFactory(data -> {
                int total = 0;
                if (data.getValue().getCopies() != null) {
                    total = data.getValue().getCopies().size();
                }
                return new ReadOnlyObjectWrapper<>(total);
            });
            colMaxCheckoutLength.setCellValueFactory(new PropertyValueFactory<>("maxCheckoutLength"));

            allPublications = (List<Publication>) result.getData();
            searchPublicationId.setText("");
            searchPublicationTitle.setText("");
            tableView.getItems().clear();
            allPublications.forEach(p -> tableView.getItems().add(p));

        } catch (Exception e) {
            DialogUtil.showServiceResponseMessage();
        }
    }

    private void filterTableData() {
        String newId = searchPublicationId.getText() != null ? searchPublicationId.getText().toLowerCase() : "";
        String newTitle = searchPublicationTitle.getText() != null ? searchPublicationTitle.getText().toLowerCase() : "";
        List<Publication> result = Functors.PUBLICATION_FILTER.apply(allPublications, newId, newTitle);
        tableView.getItems().clear();
        result.forEach(p -> tableView.getItems().add(p));
    }
}
