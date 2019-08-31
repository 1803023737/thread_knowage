package com.thread.test.sync;

public class Test {

    public static void main(String[] args) {

        A aa = new A(1, "abc");
        A aa2 = new A(1, "abcd");
        System.out.println(aa.equals(aa2));

    }

}

class A{
    private int id;
    private String name;

    public A(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) {
            return true;
        }
        if (obj!=null && obj.getClass() == A.class){
            A target=(A)obj;
            System.out.println(target.getId());
            System.out.println(id);
            return target.getId()==id;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}