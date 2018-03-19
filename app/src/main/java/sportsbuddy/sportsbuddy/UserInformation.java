package sportsbuddy.sportsbuddy;

/**
 * Created by s165700 on 3/19/2018.
 */

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Holds user info information
 * Used for easier communication between classes
 */
public class UserInformation {
    private String uID;
    private String name;
    private String age;
    private String gender;
    private String about;
    private Bitmap profilePic;

    public UserInformation(String uID, String name, String age, String gender, String about) {
        this.uID = uID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.about = about;
    }

    public String getuID() { return uID; }

    public void setuID(String uID) {
        this.uID = uID;
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
