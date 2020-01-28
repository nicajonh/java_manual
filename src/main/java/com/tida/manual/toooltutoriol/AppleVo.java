package com.tida.manual.toooltutoriol;/**
 * Created by nicajonh on 2019/7/26.
 * Description ${TEXT}
 */

/**
 * @ClassName AppleVo
 * @Description TODO
 * @Author nicajonh
 * @Date 2019/7/26 21:18
 * @Version 1.0
 **/
public class AppleVo {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Apple{
    private String id;
    private String name;
    private String createAge;

    public Apple(String s, String red, String s1) {
        this.id=s;
        this.name=red;
        this.createAge=s1;
    }

    public Apple() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateAge() {
        return createAge;
    }

    public void setCreateAge(String createAge) {
        this.createAge = createAge;
    }
}

class AppleDto{
    private String name;
    private String create_age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_age() {
        return create_age;
    }

    public void setCreate_age(String create_age) {
        this.create_age = create_age;
    }
}
