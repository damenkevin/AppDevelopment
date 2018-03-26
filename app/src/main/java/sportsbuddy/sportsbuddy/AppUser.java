package sportsbuddy.sportsbuddy;

import android.graphics.Bitmap;

/**
 * Created by s165700 on 3/26/2018.
 */

public class AppUser {
    private String UID;
    private String name;
    private Bitmap profilePic;

    public AppUser(String UID, String name, Bitmap profilePic) {
        this.UID = UID;
        this.name = name;
        this.profilePic = profilePic;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }
}
