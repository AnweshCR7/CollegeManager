//This defines a class whose object contains the info about Each TEAM MEMBER
//with lecDay giving the Time, lecTime giving the position, and photoId giving the Photo of the respective member
package com.seproject.classmate;

public class LectObject {
    private String lecDay;
    private String lecTime;
    private String lecVenue;
   // private int photoId;
   // private int imgButton1;//just a try
   // private int imgButton2;
   // private int imgButton3;//just a try... will remove if it doesn't work out as expected

    LectObject(String text1, String text2, String text3){// int pd, int ib1, int ib2, int ib3){
        lecDay = text1;
        lecTime = text2;
        lecVenue = text3;
       // photoId = pd;
    //    imgButton1 = ib1;
     //   imgButton2 = ib2;
     //   imgButton3 = ib3;
    }

    public String getlecDay() {
        return lecDay;
    }

    public void setlecDay(String lecDay) {
        this.lecDay = lecDay;
    }

    public String getlecTime() {
        return lecTime;
    }

    public void setlecTime(String lecTime) {
        this.lecTime = lecTime;
    }

    public String getLecVenue() {
        return lecVenue;
    }

    public void setLecVenue(String lecVenue) {
        this.lecVenue = lecVenue;
    }
    /* public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getImgButton1() {
        return imgButton1;
    }

    public void setImgButton1(int imgButton1) {
        this.imgButton1 = imgButton1;
    }

    public int getImgButton2() {
        return imgButton2;
    }

    public void setImgButton2(int imgButton2) {
        this.imgButton2 = imgButton2;
    }

    public int getImgButton3() {
        return imgButton3;
    }

    public void setImgButton3(int imgButton3) {
        this.imgButton3 = imgButton3;
    }*/
}