package com.ut.user.entity;

/**
 * Created by yuxiaoyu on 2017-1-17.
 */

public class MainTemplate {
    private boolean checked = false;
    private int type;
    private String title;
    private String content;
    private long id;

    public MainTemplate(){}

    public MainTemplate(long id, String title, String content, int type) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
