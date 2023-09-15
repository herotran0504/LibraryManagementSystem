package core.loader;

import core.viewmodel.AppController;

public interface ViewControllerLoader {

    void attachAppController(AppController controller);

    void loadViewController(String fxml, Object userData);

    void loadViewController(String fxml);
}
