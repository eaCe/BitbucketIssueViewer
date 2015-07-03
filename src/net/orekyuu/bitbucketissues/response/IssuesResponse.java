package net.orekyuu.bitbucketissues.response;

import java.util.List;

public class IssuesResponse {

    private int count;
    private List<Issue> issues;

    public int getCount() {
        return count;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IssuesResponse{");
        sb.append("count=").append(count);
        sb.append(", issues=").append(issues);
        sb.append('}');
        return sb.toString();
    }
}
