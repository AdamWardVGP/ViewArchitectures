package award.viewarchitectures.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GithubUser implements Parcelable{
    public final String login;
    public final int id;
    public final String avatar_url;
    public final String gravatar_id;
    public final String url;
    public final String html_url;
    public final String followers_url;
    public final String following_url;
    public final String gists_url;
    public final String starred_url;
    public final String subscriptions_url;
    public final String organizations_url;
    public final String repos_url;
    public final String events_url;
    public final String received_events_url;
    public final String type;
    public final boolean site_admin;

    public GithubUser(String login, int id, String avatar_url, String gravatar_id, String url,
                      String html_url, String followers_url, String following_url, String gists_url,
                      String starred_url, String subscriptions_url, String organizations_url,
                      String repos_url, String events_url, String received_events_url,
                      String type, boolean site_admin) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
        this.gravatar_id = gravatar_id;
        this.url = url;
        this.html_url = html_url;
        this.followers_url = followers_url;
        this.following_url = following_url;
        this.gists_url = gists_url;
        this.starred_url = starred_url;
        this.subscriptions_url = subscriptions_url;
        this.organizations_url = organizations_url;
        this.repos_url = repos_url;
        this.events_url = events_url;
        this.received_events_url = received_events_url;
        this.type = type;
        this.site_admin = site_admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GithubUser that = (GithubUser) o;

        if (id != that.id) return false;
        if (site_admin != that.site_admin) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (avatar_url != null ? !avatar_url.equals(that.avatar_url) : that.avatar_url != null)
            return false;
        if (gravatar_id != null ? !gravatar_id.equals(that.gravatar_id) : that.gravatar_id != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (html_url != null ? !html_url.equals(that.html_url) : that.html_url != null)
            return false;
        if (followers_url != null ? !followers_url.equals(that.followers_url) : that.followers_url != null)
            return false;
        if (following_url != null ? !following_url.equals(that.following_url) : that.following_url != null)
            return false;
        if (gists_url != null ? !gists_url.equals(that.gists_url) : that.gists_url != null)
            return false;
        if (starred_url != null ? !starred_url.equals(that.starred_url) : that.starred_url != null)
            return false;
        if (subscriptions_url != null ? !subscriptions_url.equals(that.subscriptions_url) : that.subscriptions_url != null)
            return false;
        if (organizations_url != null ? !organizations_url.equals(that.organizations_url) : that.organizations_url != null)
            return false;
        if (repos_url != null ? !repos_url.equals(that.repos_url) : that.repos_url != null)
            return false;
        if (events_url != null ? !events_url.equals(that.events_url) : that.events_url != null)
            return false;
        if (received_events_url != null ? !received_events_url.equals(that.received_events_url) : that.received_events_url != null)
            return false;
        return !(type != null ? !type.equals(that.type) : that.type != null);

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (avatar_url != null ? avatar_url.hashCode() : 0);
        result = 31 * result + (gravatar_id != null ? gravatar_id.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (html_url != null ? html_url.hashCode() : 0);
        result = 31 * result + (followers_url != null ? followers_url.hashCode() : 0);
        result = 31 * result + (following_url != null ? following_url.hashCode() : 0);
        result = 31 * result + (gists_url != null ? gists_url.hashCode() : 0);
        result = 31 * result + (starred_url != null ? starred_url.hashCode() : 0);
        result = 31 * result + (subscriptions_url != null ? subscriptions_url.hashCode() : 0);
        result = 31 * result + (organizations_url != null ? organizations_url.hashCode() : 0);
        result = 31 * result + (repos_url != null ? repos_url.hashCode() : 0);
        result = 31 * result + (events_url != null ? events_url.hashCode() : 0);
        result = 31 * result + (received_events_url != null ? received_events_url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (site_admin ? 1 : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeInt(this.id);
        dest.writeString(this.avatar_url);
        dest.writeString(this.gravatar_id);
        dest.writeString(this.url);
        dest.writeString(this.html_url);
        dest.writeString(this.followers_url);
        dest.writeString(this.following_url);
        dest.writeString(this.gists_url);
        dest.writeString(this.starred_url);
        dest.writeString(this.subscriptions_url);
        dest.writeString(this.organizations_url);
        dest.writeString(this.repos_url);
        dest.writeString(this.events_url);
        dest.writeString(this.received_events_url);
        dest.writeString(this.type);
        dest.writeByte(site_admin ? (byte) 1 : (byte) 0);
    }

    protected GithubUser(Parcel in) {
        this.login = in.readString();
        this.id = in.readInt();
        this.avatar_url = in.readString();
        this.gravatar_id = in.readString();
        this.url = in.readString();
        this.html_url = in.readString();
        this.followers_url = in.readString();
        this.following_url = in.readString();
        this.gists_url = in.readString();
        this.starred_url = in.readString();
        this.subscriptions_url = in.readString();
        this.organizations_url = in.readString();
        this.repos_url = in.readString();
        this.events_url = in.readString();
        this.received_events_url = in.readString();
        this.type = in.readString();
        this.site_admin = in.readByte() != 0;
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
        public GithubUser createFromParcel(Parcel source) {
            return new GithubUser(source);
        }

        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };
}
