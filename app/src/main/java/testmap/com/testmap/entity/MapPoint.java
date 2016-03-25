package testmap.com.testmap.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Igor on 25.03.2016.
 */
public class MapPoint implements Parcelable {

    public String title;
    public String description;
    public LatLng coordinates;

    public MapPoint() {
    }

    public MapPoint(String title, String description, LatLng coordinates) {
        this.title = title;
        this.description = description;
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapPoint)) return false;

        MapPoint mapPoint = (MapPoint) o;

        if (!title.equals(mapPoint.title)) return false;
        if (!description.equals(mapPoint.description)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + coordinates.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeParcelable(this.coordinates, 0);
    }

    protected MapPoint(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.coordinates = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Parcelable.Creator<MapPoint> CREATOR = new Parcelable.Creator<MapPoint>() {
        public MapPoint createFromParcel(Parcel source) {
            return new MapPoint(source);
        }

        public MapPoint[] newArray(int size) {
            return new MapPoint[size];
        }
    };
}
