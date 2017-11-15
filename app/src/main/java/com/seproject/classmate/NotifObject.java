//This defines a class whose object contains the info about Each TEAM MEMBER
//with lecDay giving the Time, lecTime giving the position, and photoId giving the Photo of the respective member
package com.seproject.classmate;

public class NotifObject {
    private String Date;
    private String subject;
    private String tag;
    private String description;
   // private int photoId;
   // private int imgButton1;//just a try
   // private int imgButton2;
   // private int imgButton3;//just a try... will remove if it doesn't work out as expected

    NotifObject(String text1, String text2, String text3, String text4){// int pd, int ib1, int ib2, int ib3){
        Date = text1;
        subject = text2;
        tag = text3;
        description = text4;
       // photoId = pd;
    //    imgButton1 = ib1;
     //   imgButton2 = ib2;
     //   imgButton3 = ib3;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}