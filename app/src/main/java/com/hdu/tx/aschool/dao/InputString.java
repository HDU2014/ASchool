package com.hdu.tx.aschool.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "INPUT_STRING".
 */
public class InputString implements java.io.Serializable {

    private Long id;
    private Integer type;
    private String text;

    public InputString() {
    }

    public InputString(Long id) {
        this.id = id;
    }

    public InputString(Long id, Integer type, String text) {
        this.id = id;
        this.type = type;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
