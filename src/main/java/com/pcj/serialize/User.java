package com.pcj.serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private Integer age;

    public User() {

    }

    public User(String name, Integer age) {
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




    private void readObject(java.io.ObjectInputStream stream)
      throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
        System.out.println("readObject");
    }

      private void writeObject(java.io.ObjectOutputStream stream)
          throws IOException{
          stream.defaultWriteObject();
          System.out.println("writeObject");
      }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
