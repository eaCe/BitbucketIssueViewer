package net.orekyuu.bitbucketissues;

import com.google.gson.Gson;
import com.intellij.openapi.components.*;
import com.intellij.util.net.IOExceptionDialog;
import net.orekyuu.bitbucketissues.response.IssuesResponse;
import org.apache.commons.net.util.Base64;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

@State(name = "BitbucketIssueService", storages = {
        @Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
        @Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/bitbucket-issue.xml", scheme = StorageScheme.DIRECTORY_BASED)
})
public class BitbucketIssueService implements PersistentStateComponent<AuthState> {
    private AuthState state;

    @Nullable
    @Override
    public AuthState getState() {
        return state;
    }

    @Override
    public void loadState(AuthState state) {
        this.state = state;
    }

    public IssuesResponse getIssues(StatusFilter statusFilter, UserFilter userFilter) {

        if (state == null || state.team == null) {
            SetupDialog setupDialog = new SetupDialog();
            setupDialog.setSize(400, 240);
            setupDialog.setVisible(true);
            state = new AuthState();
            state.team = setupDialog.getTeam();
            state.repository = setupDialog.getRepo();
            state.userId = setupDialog.getUserId();
            state.password = setupDialog.getPassword();
        }

        String statusQuery = "";
        if (statusFilter == StatusFilter.OPEN) {
            statusQuery = "status=open&status=new&status=wontfix";
        } else if (statusFilter == StatusFilter.CLOSE) {
            statusQuery = "status=close&status=resolved";
        }
        String userQuery = UserFilter.ALL == userFilter ? "" : "responsible=" + state.userId;

        String query = "";
        if (userQuery.isEmpty() && statusQuery.isEmpty()) {
            //query is empty
        } else {
            query += "?";
            //どちらかが空ならそのまま繋ぐ
            if (userQuery.isEmpty() ^ statusQuery.isEmpty()) {
                query += userQuery + statusQuery;
            } else {
                query += userQuery + "&" + statusQuery;
            }
        }

        try {
            byte[] authEncBytes = Base64.encodeBase64((state.userId + ":" + state.password).getBytes());
            String base64login = new String(authEncBytes);
            Connection.Response execute = Jsoup.connect(String
                    .format("https://api.bitbucket.org/1.0/repositories/%s/%s/issues/", state.team, state.repository) + query)
                    .ignoreContentType(true)
                    .header("Authorization", "Basic " + base64login)
                    .execute();

            String body = execute.body();
            Gson gson = new Gson();
            return gson.fromJson(body, IssuesResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            IOExceptionDialog.showErrorDialog("Error", e.getMessage());
            state.team = null;
            state.repository = null;
            state.userId = null;
            state.password = null;
        }

        return null;
    }
}
