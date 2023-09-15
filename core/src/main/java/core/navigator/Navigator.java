package core.navigator;

import java.io.IOException;

@FunctionalInterface
public interface Navigator {
    void openDashboardView() throws IOException;
}
