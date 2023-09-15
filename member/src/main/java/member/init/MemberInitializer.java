package member.init;

import core.service.Initializer;
import core.viewmodel.MemberViewModel;
import core.viewmodel.ViewModelRegistry;
import member.viewmodel.MemberViewModelImpl;

public class MemberInitializer implements Initializer {

    @Override
    public void initialize() {
        final MemberViewModel viewModel = MemberViewModelImpl.create();
        ViewModelRegistry.getInstance().add(MemberViewModel.class, viewModel);
    }

}
