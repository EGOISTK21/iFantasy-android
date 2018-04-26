package xyz.egoistk21.iFantasy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SimplePlayer extends RealmObject implements Parcelable {

    public static final Creator<SimplePlayer> CREATOR = new Creator<SimplePlayer>() {
        @Override
        public SimplePlayer createFromParcel(Parcel in) {
            return new SimplePlayer(in);
        }

        @Override
        public SimplePlayer[] newArray(int size) {
            return new SimplePlayer[size];
        }
    };
    @PrimaryKey
    private int id;
    private String name;
    private String pos1;
    private String pos2;
    private int score;
    private int price;


    public SimplePlayer() {

    }

    private SimplePlayer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        pos1 = in.readString();
        pos2 = in.readString();
        score = in.readInt();
        price = in.readInt();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public int getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(pos1);
        parcel.writeString(pos2);
        parcel.writeInt(score);
        parcel.writeInt(price);
    }

    @Override
    public String toString() {
        return "SimplePlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pos1='" + pos1 + '\'' +
                ", pos2='" + pos2 + '\'' +
                ", score=" + score +
                ", price='" + price + '\'' +
                '}';
    }
}
