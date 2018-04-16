package xyz.egoistk21.iFantasy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class RawPlayer extends RealmObject implements Parcelable {

    private int id;
    private String name;
    private String pic;
    private String pos1;
    private String pos2;
    private int score;
    private String price;

    public RawPlayer() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public String getPos1() {
        return pos1;
    }

    public String getPos2() {
        return pos2;
    }

    public int getScore() {
        return score;
    }

    public String getPrice() {
        return price;
    }

    private RawPlayer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        pic = in.readString();
        pos1 = in.readString();
        pos2 = in.readString();
        score = in.readInt();
        price = in.readString();
    }

    public static final Creator<RawPlayer> CREATOR = new Creator<RawPlayer>() {
        @Override
        public RawPlayer createFromParcel(Parcel in) {
            return new RawPlayer(in);
        }

        @Override
        public RawPlayer[] newArray(int size) {
            return new RawPlayer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(pic);
        parcel.writeString(pos1);
        parcel.writeString(pos2);
        parcel.writeInt(score);
        parcel.writeString(price);
    }

    @Override
    public String toString() {
        return "RawPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", pos1='" + pos1 + '\'' +
                ", pos2='" + pos2 + '\'' +
                ", score=" + score +
                ", price='" + price + '\'' +
                '}';
    }
}
