package com.hdu.tx.aschool.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lugeek on 2015/8/9.
 */
public class Events {
    private static Events instance;
    private List<Event> events;

    public static Events getInstance(){
        if (instance == null){
            instance = new Events();
            instance.events = new ArrayList<Event>();
        }
        return instance;
    }

    public List<Event> getEvents(){
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> setTestEvents(){
        events.removeAll(events);
        Event event1 = new Event();
        event1.setImageUrl("http://7xiq48.com1.z0.glb.clouddn.com/article/drjh.jpg");
        event1.setTitle("�1");
        event1.setTime("ʱ�䣺8��10��13:00");
        event1.setAddress("�ص㣺���ݵ��ӿƼ���ѧ");
        event1.setParticipants(10);
        event1.setNeedSignup(true);
        events.add(event1);
        Event event2 = new Event();
        event2.setImageUrl("http://7xiq48.com1.z0.glb.clouddn.com/article/drjh.jpg");
        event2.setTitle("�2");
        event2.setTime("ʱ�䣺8��15��13:00");
        event2.setAddress("�ص㣺����");
        event2.setParticipants(16);
        event2.setNeedSignup(false);
        events.add(event2);
        events.add(event1);
        events.add(event2);
        events.add(event1);
        events.add(event2);
        events.add(event1);
        events.add(event2);
        return events;
    }

    public List<Event> addList(List<Event> newEvents){
        events.addAll(0, newEvents);
        return events;
    }
}
