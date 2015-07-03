package net.orekyuu.bitbucketissues.response;

public class Metadata {

    private String kind;
    private String version;
    private String component;
    private String milestone;

    public String getKind() {
        return kind;
    }

    public String getVersion() {
        return version;
    }

    public String getComponent() {
        return component;
    }

    public String getMilestone() {
        return milestone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Metadata{");
        sb.append("kind='").append(kind).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", component='").append(component).append('\'');
        sb.append(", milestone='").append(milestone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
