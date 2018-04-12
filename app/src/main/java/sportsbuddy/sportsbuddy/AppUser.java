package sportsbuddy.sportsbuddy;

/**
 * Created by s165700 on 3/26/2018.
 */

public class AppUser {
    private String UID;
    private String name;
    private String age;
    private String gender;
    private String about;
    private String profilePicture;

    public AppUser(String UID, String name, String age, String gender, String about, String profilePicture) {
        this.UID = UID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.about = about;
        this.profilePicture = profilePicture;
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

    public String getprofilePicture() {
        return profilePicture;
    }

    public void setprofilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

}
