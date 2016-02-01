package award.viewarchitectures.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adamward on 1/29/16.
 */
public class GithubComment implements Parcelable {
    public final int id;
    public final String url;
    public final String html_url;
    public final String body;
    public final GithubUser user;
    public final String created_at;
    public final String updated_at;

    public GithubComment(int id, String url, String html_url, String body, GithubUser user, String created_at, String updated_at) {
        this.id = id;
        this.url = url;
        this.html_url = html_url;
        this.body = body;
        this.user = user;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GithubComment that = (GithubComment) o;

        if (id != that.id) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (html_url != null ? !html_url.equals(that.html_url) : that.html_url != null)
            return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (created_at != null ? !created_at.equals(that.created_at) : that.created_at != null)
            return false;
        return !(updated_at != null ? !updated_at.equals(that.updated_at) : that.updated_at != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (html_url != null ? html_url.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        result = 31 * result + (updated_at != null ? updated_at.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeString(this.html_url);
        dest.writeString(this.body);
        dest.writeParcelable(this.user, 0);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
    }

    protected GithubComment(Parcel in) {
        this.id = in.readInt();
        this.url = in.readString();
        this.html_url = in.readString();
        this.body = in.readString();
        this.user = in.readParcelable(GithubUser.class.getClassLoader());
        this.created_at = in.readString();
        this.updated_at = in.readString();
    }

    public static final Creator<GithubComment> CREATOR = new Creator<GithubComment>() {
        public GithubComment createFromParcel(Parcel source) {
            return new GithubComment(source);
        }

        public GithubComment[] newArray(int size) {
            return new GithubComment[size];
        }
    };
}
