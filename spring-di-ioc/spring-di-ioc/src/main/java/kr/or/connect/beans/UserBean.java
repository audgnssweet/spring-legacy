package kr.or.connect.beans;

/*
    Bean 제약조건 1. 기본 생성자 가짐
    2. 필드는 private
    3. 모든 필드에대해 getter, setter 즉 property를 가진다.
 */
public class UserBean {

    private String name;
    private int age;
    private boolean male;

    public UserBean() {
    }

    public UserBean(String name, int age, boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }
}
