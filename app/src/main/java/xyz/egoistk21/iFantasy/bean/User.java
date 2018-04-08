package xyz.egoistk21.iFantasy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    private String nickname;
    private String phone;
    private int level;
    private int money;
    private int viplevel;
    private String logintoken;
    private String accesstoken;

    public User() {
    }

    private User(Parcel in) {
        id = in.readInt();
        nickname = in.readString();
        phone = in.readString();
        level = in.readInt();
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

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public int getLevel() {
        return level;
    }


    public int getMoney() {
        return money;
    }

    public int getViplevel() {
        return viplevel;
    }

    public void setLogintoken(String logintoken) {
        this.logintoken = logintoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
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
        dest.writeInt(id);
        dest.writeString(nickname);
        dest.writeString(phone);
        dest.writeInt(level);
        dest.writeInt(money);
        dest.writeInt(viplevel);
        dest.writeString(logintoken);
        dest.writeString(accesstoken);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                ", money=" + money +
                ", viplevel=" + viplevel +
                ", logintoken='" + logintoken + '\'' +
                ", accesstoken='" + accesstoken + '\'' +
                '}';
    }
}
