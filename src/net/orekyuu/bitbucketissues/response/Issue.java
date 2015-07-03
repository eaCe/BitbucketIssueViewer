package net.orekyuu.bitbucketissues.response;

public class Issue {

    private String status;
    private String priority;
    private String title;
    private User reported_by;
    private String utc_last_updated;
    private User responsible;
    private String created_on;
    private Metadata metadata;
    private String content;
    private int comment_count;
    private int local_id;
    private int follower_count;
    private String utc_created_on;
    private String resource_uri;
    private boolean is_spam;

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public User getReported_by() {
        return reported_by;
    }

    public String getUtc_last_updated() {
        return utc_last_updated;
    }

    public User getResponsible() {
        return responsible;
    }

    public String getCreated_on() {
        return created_on;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public String getContent() {
        return content;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getLocal_id() {
        return local_id;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public String getUtc_created_on() {
        return utc_created_on;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public boolean is_spam() {
        return is_spam;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Issue{");
        sb.append("status='").append(status).append('\'');
        sb.append(", priority='").append(priority).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", reported_by=").append(reported_by);
        sb.append(", utc_last_updated='").append(utc_last_updated).append('\'');
        sb.append(", responsible=").append(responsible);
        sb.append(", created_on='").append(created_on).append('\'');
        sb.append(", metadata=").append(metadata);
        sb.append(", content='").append(content).append('\'');
        sb.append(", comment_count=").append(comment_count);
        sb.append(", local_id=").append(local_id);
        sb.append(", follower_count=").append(follower_count);
        sb.append(", utc_created_on='").append(utc_created_on).append('\'');
        sb.append(", resource_uri='").append(resource_uri).append('\'');
        sb.append(", is_spam=").append(is_spam);
        sb.append('}');
        return sb.toString();
    }
}
