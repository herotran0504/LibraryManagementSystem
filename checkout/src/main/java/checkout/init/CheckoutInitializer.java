package checkout.init;

import checkout.viewmodel.CheckoutViewModelImpl;
import core.service.Initializer;
import core.viewmodel.CheckoutViewModel;
import core.viewmodel.ViewModelRegistry;

public class CheckoutInitializer implements Initializer {

    @Override
    public void initialize() {
        final CheckoutViewModel viewModel = CheckoutViewModelImpl.create();
        ViewModelRegistry.getInstance().add(CheckoutViewModel.class, viewModel);
    }

}
