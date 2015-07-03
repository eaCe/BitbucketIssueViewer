package net.orekyuu.bitbucketissues;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import net.orekyuu.bitbucketissues.response.Issue;
import net.orekyuu.bitbucketissues.response.IssuesResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class IssueViewer {
    private JPanel panel1;
    private JTable table1;
    private JButton update;
    private JComboBox<StatusFilter> statusFilter;
    private JComboBox<UserFilter> userFilter;


    private IssueTableModel dataModel;
    public java.util.List<Issue> issues;

    public Component getRoot(Project project) {
        dataModel = new IssueTableModel();
        statusFilter.setModel(new DefaultComboBoxModel<>(StatusFilter.values()));
        userFilter.setModel(new DefaultComboBoxModel<>(UserFilter.values()));

        table1.setModel(dataModel);
        update.addActionListener(e -> updateIssues(project));
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                int row = table1.rowAtPoint(p);
                if (e.getClickCount() == 2 && issues != null && 0 <= row && row < issues.size()) {
                    Issue issue = issues.get(row);
                    String resource_uri = issue.getResource_uri();
                    //replace使うのはまずいけど面倒なので今はこれで対応
                    String substring = resource_uri.substring("/1.0/repositories/".length(), resource_uri.length()).replace("issues", "issue");
                    try {
                        Desktop.getDesktop().browse(new URL("https://bitbucket.org/" + substring).toURI());
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        return panel1;
    }

    public void updateIssues(Project project) {
        BitbucketIssueService checker = ServiceManager.getService(project, BitbucketIssueService.class);
        IssuesResponse issues = checker.getIssues((StatusFilter)statusFilter.getSelectedItem(), (UserFilter)userFilter.getSelectedItem());
        if (issues == null) {
            return;
        }
        this.issues = issues.getIssues();
        java.util.List<Issue> list = this.issues;
        if (list == null) {
            return;
        }

        dataModel.clear();
        list.forEach(dataModel::addRow);


    }
}
