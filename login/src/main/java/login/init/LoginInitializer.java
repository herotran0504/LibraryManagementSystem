package login.init;

import core.service.Initializer;
import core.viewmodel.UserViewModel;
import core.viewmodel.ViewModelRegistry;
import login.viewmodel.UserViewModelImpl;

public class LoginInitializer implements Initializer {

    @Override
    public void initialize() {
        UserViewModel userViewModel = UserViewModelImpl.create();
        ViewModelRegistry.getInstance().add(UserViewModel.class, userViewModel);
    }

}
