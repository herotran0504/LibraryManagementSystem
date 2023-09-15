package core.navigator;

import java.io.IOException;

public interface Navigator {
    void reloadDashBoardView();

    void openMemberView();

    void openMemberListView();

    void openCheckoutView();

    void openDashboardView() throws IOException;
}
