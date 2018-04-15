package xyz.egoistk21.iFantasy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class Player extends RealmObject implements Parcelable {

    private int id;
    private String name;
    private String pos1;
    private String pos2;
    private int score;
    private String price;


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

    public String getPrice() {
        return price;
    }

    private Player(Parcel in) {
        id = in.readInt();
        name = in.readString();
        pos1 = in.readString();
        pos2 = in.readString();
        score = in.readInt();
        price = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
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
        parcel.writeString(pos1);
        parcel.writeString(pos2);
        parcel.writeInt(score);
        parcel.writeString(price);
    }
}
