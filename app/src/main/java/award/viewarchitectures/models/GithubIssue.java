package award.viewarchitectures.models;

import android.os.Parcel;
import android.os.Parcelable;

final public class GithubIssue  implements Parcelable {
    public final int id;
    public final String url;
    public final int comments;
    public final String comments_url;
    public final int number;
    public final String title;
    public final String body;
    public final GithubUser user;

    public GithubIssue(int id, String url, int comments, String comments_url, int number, String title, String body, GithubUser user) {
        this.id = id;
        this.url = url;
        this.comments = comments;
        this.comments_url = comments_url;
        this.number = number;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GithubIssue that = (GithubIssue) o;

        if (id != that.id) return false;
        if (comments != that.comments) return false;
        if (number != that.number) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (comments_url != null ? !comments_url.equals(that.comments_url) : that.comments_url != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + comments;
        result = 31 * result + (comments_url != null ? comments_url.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
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
        dest.writeInt(this.comments);
        dest.writeString(this.comments_url);
        dest.writeInt(this.number);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeParcelable(this.user, flags);
    }

    protected GithubIssue(Parcel in) {
        this.id = in.readInt();
        this.url = in.readString();
        this.comments = in.readInt();
        this.comments_url = in.readString();
        this.number = in.readInt();
        this.title = in.readString();
        this.body = in.readString();
        this.user = in.readParcelable(GithubUser.class.getClassLoader());
    }

    public static final Creator<GithubIssue> CREATOR = new Creator<GithubIssue>() {
        public GithubIssue createFromParcel(Parcel source) {
            return new GithubIssue(source);
        }

        public GithubIssue[] newArray(int size) {
            return new GithubIssue[size];
        }
    };
}
