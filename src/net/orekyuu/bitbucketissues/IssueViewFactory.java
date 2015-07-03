package net.orekyuu.bitbucketissues;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;


public class IssueViewFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        toolWindow.setTitle("BitBucket Issue View");

        IssueViewer issueView = new IssueViewer();
        toolWindow.getComponent().add(issueView.getRoot(project));
        issueView.updateIssues(project);
    }

}
