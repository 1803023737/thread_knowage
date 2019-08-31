package com.xianliu.atom;

import java.util.concurrent.atomic.AtomicReference;

public class UseAtomicReference {

    private static AtomicReference<UserInfo> userRef=new AtomicReference<UserInfo>();

    public static void main(String[] args) {

        UserInfo userInfo=new UserInfo("mark",15);
        userRef.set(userInfo);
        UserInfo userInfo2=new UserInfo("bill",17);
        //cas 操作
        userRef.compareAndSet(userInfo,userInfo2);
        UserInfo u = userRef.get();
        System.out.println(u.getName()+"========"+u.getAge());
        System.out.println(userInfo.getAge());
        System.out.println(userInfo.getName());

    }

    //定义一个实体类
    static class UserInfo{

        private String name;
        private Integer age;

        public UserInfo(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
