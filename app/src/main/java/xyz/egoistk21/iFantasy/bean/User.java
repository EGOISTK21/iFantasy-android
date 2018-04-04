package xyz.egoistk21.iFantasy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class User extends RealmObject implements Parcelable {

    private int userid;
    private String nickname;
    private int level;
    private int score;
    private int money;
    private int viplevel;
    private String logintoken;
    private String accesstoken;

    public User() {
    }

    private User(Parcel in) {
        userid = in.readInt();
        nickname = in.readString();
        level = in.readInt();
        score = in.readInt();
        money = in.readInt();
        viplevel = in.readInt();
        logintoken = in.readString();
        accesstoken = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getUserid() {
        return userid;
    }

    public String getNickname() {
        return nickname;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public int getMoney() {
        return money;
    }

    public int getViplevel() {
        return viplevel;
    }

    public String getLogintoken() {
        return logintoken;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userid);
        dest.writeString(nickname);
        dest.writeInt(level);
        dest.writeInt(score);
        dest.writeInt(money);
        dest.writeInt(viplevel);
        dest.writeString(logintoken);
        dest.writeString(accesstoken);
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", nickname='" + nickname + '\'' +
                ", level=" + level +
                ", score=" + score +
                ", money=" + money +
                ", viplevel=" + viplevel +
                ", logintoken='" + logintoken + '\'' +
                ", accesstoken='" + accesstoken + '\'' +
                '}';
    }
}
