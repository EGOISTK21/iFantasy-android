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
    private int bag_id;
    private int score;
    private int price;
    private int salary;
    private String name;
    private String pos1;
    private String pos2;

    public SimplePlayer() {

    }


    protected SimplePlayer(Parcel in) {
        id = in.readInt();
        bag_id = in.readInt();
        score = in.readInt();
        price = in.readInt();
        salary = in.readInt();
        name = in.readString();
        pos1 = in.readString();
        pos2 = in.readString();
    }

    public int getId() {
        return id;
    }

    public int getBag_id() {
        return bag_id;
    }

    public int getScore() {
        return score;
    }

    public int getPrice() {
        return price;
    }

    public int getSalary() {
        return salary;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(bag_id);
        dest.writeInt(score);
        dest.writeInt(price);
        dest.writeInt(salary);
        dest.writeString(name);
        dest.writeString(pos1);
        dest.writeString(pos2);
    }

    @Override
    public String toString() {
        return "SimplePlayer{" +
                "id=" + id +
                ", bag_id=" + bag_id +
                ", score=" + score +
                ", price=" + price +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", pos1='" + pos1 + '\'' +
                ", pos2='" + pos2 + '\'' +
                '}';
    }
}
