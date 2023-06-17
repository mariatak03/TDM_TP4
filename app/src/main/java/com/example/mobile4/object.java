package com.example.mobile4;

import java.io.Serializable;

public class object implements Serializable {
    public String username;
    public String desc;
    public int img;
    public object(String name, String desc, int img) {
        this.username=name;
        this.desc=desc;
        this.img=img;

    }
}
