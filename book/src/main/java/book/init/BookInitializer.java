package book.init;

import book.viewmodel.BookViewModelImpl;
import core.service.Initializer;
import core.viewmodel.BookViewModel;
import core.viewmodel.ViewModelRegistry;

public final class BookInitializer implements Initializer {

    @Override
    public void initialize() {
        final BookViewModel viewModel = BookViewModelImpl.create();
        ViewModelRegistry.getInstance().add(BookViewModel.class, viewModel);
    }

}
