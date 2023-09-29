package app.organicmaps.bookmarks.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

public class FeatureId implements Parcelable
{
  public static final Creator<FeatureId> CREATOR = new Creator<FeatureId>()
  {
    @Override
    public FeatureId createFromParcel(Parcel in)
    {
      return new FeatureId(in);
    }

    @Override
    public FeatureId[] newArray(int size)
    {
      return new FeatureId[size];
    }
  };

  @NonNull
  public static final FeatureId EMPTY = new FeatureId("", 0L, 0);

  @NonNull
  private final String mMwmName;
  private final long mMwmVersion;
  private final int mFeatureIndex;

  // Called from JNI.
  @Keep
  @SuppressWarnings("unused")
  public FeatureId(@NonNull String mwmName, long mwmVersion, int featureIndex)
  {
    mMwmName = mwmName;
    mMwmVersion = mwmVersion;
    mFeatureIndex = featureIndex;
  }

  private FeatureId(Parcel in)
  {
    mMwmName = in.readString();
    mMwmVersion = in.readLong();
    mFeatureIndex = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags)
  {
    dest.writeString(mMwmName);
    dest.writeLong(mMwmVersion);
    dest.writeInt(mFeatureIndex);
  }

  @Override
  public int describeContents()
  {
    return 0;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FeatureId featureId = (FeatureId) o;

    if (mMwmVersion != featureId.mMwmVersion) return false;
    if (mFeatureIndex != featureId.mFeatureIndex) return false;
    return mMwmName.equals(featureId.mMwmName);
  }

  @Override
  public int hashCode()
  {
    int result = mMwmName.hashCode();
    result = 31 * result + (int) (mMwmVersion ^ (mMwmVersion >>> 32));
    result = 31 * result + mFeatureIndex;
    return result;
  }

  @Override
  public String toString()
  {
    return "FeatureId{" +
           "mMwmName='" + mMwmName + '\'' +
           ", mMwmVersion=" + mMwmVersion +
           ", mFeatureIndex=" + mFeatureIndex +
           '}';
  }
}
