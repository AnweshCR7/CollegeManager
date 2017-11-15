package com.seproject.classmate;

/**
 * Created by Anwesh on 18-06-2015.
 */
public class EventObject {
    private String eventName;
    private int eventImage;

    EventObject (String text1, int imageId){
        eventName = text1;
        eventImage = imageId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventImage() {
        return eventImage;
    }

    public void setEventImage(int eventImage) {
        this.eventImage = eventImage;
    }
}
