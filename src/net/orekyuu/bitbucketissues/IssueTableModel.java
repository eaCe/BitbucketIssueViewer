package net.orekyuu.bitbucketissues;

import net.orekyuu.bitbucketissues.response.Issue;

import javax.swing.table.DefaultTableModel;

public class IssueTableModel extends DefaultTableModel {

    private static String[] TITLE = {"Issue Number", "Title", "Type", "Priority", "Status", "Responsible", "Created",
            "Updated"};

    public IssueTableModel() {
        super(TITLE, 0);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        //常に編集を許可しない
        return false;
    }

    public void addRow(Issue issue) {
        addRow(new Object[]{issue.getLocal_id(), issue.getTitle(), issue.getMetadata().getKind(), issue.getPriority()
                , issue.getStatus(),
                issue.getResponsible()
                != null ? issue.getResponsible().getDisplay_name() : "Not set", issue.getCreated_on(), issue
                .getUtc_last_updated()});
    }

    public void clear() {
        int rows = getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            removeRow(i);
        }
    }
}
