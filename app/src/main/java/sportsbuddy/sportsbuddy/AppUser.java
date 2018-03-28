package sportsbuddy.sportsbuddy;

import android.graphics.Bitmap;

/**
 * Created by s165700 on 3/26/2018.
 */

public class AppUser {
    private String UID;
    private String name;
    private String age;
    private String gender;
    private String about;
    private Bitmap profilePic;

    public AppUser(String UID, String name, String age, String gender, String about, Bitmap profilePic) {
        this.UID = UID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.about = about;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }
}
