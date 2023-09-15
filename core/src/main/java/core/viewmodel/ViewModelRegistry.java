package core.viewmodel;

import java.util.HashMap;
import java.util.Map;

public final class ViewModelRegistry {

    private final Map<Class<? extends ViewModel>, ViewModel> data = new HashMap<>();

    private ViewModelRegistry() {
    }

    private static class RegistryHolder {
        private static final ViewModelRegistry INSTANCE = new ViewModelRegistry();
    }

    public static ViewModelRegistry getInstance() {
        return RegistryHolder.INSTANCE;
    }

    public <T extends ViewModel> void add(Class<? extends T> cls, T viewModel) {
        data.put(cls, viewModel);
    }

    public <T extends ViewModel> T get(Class<? extends T> cls) {
        final ViewModel viewModel = data.get(cls);
        if (viewModel == null) {
            throw new IllegalArgumentException(cls.getSimpleName() + " must be initialized before use");
        }
        return cls.cast(viewModel);
    }
}
