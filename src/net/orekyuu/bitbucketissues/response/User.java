package net.orekyuu.bitbucketissues.response;

public class User {

    private String username;
    private String first_name;
    private String last_name;
    private String display_name;
    private boolean is_staff;
    private String avatar;
    private String resource_uri;
    private String is_team;

    public String getUsername() {
        return username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public boolean is_staff() {
        return is_staff;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public String getIs_team() {
        return is_team;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", first_name='").append(first_name).append('\'');
        sb.append(", last_name='").append(last_name).append('\'');
        sb.append(", display_name='").append(display_name).append('\'');
        sb.append(", is_staff=").append(is_staff);
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append(", resource_uri='").append(resource_uri).append('\'');
        sb.append(", is_team='").append(is_team).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
