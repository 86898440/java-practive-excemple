package com.pcj.clone;

import com.pcj.po.User;

import java.io.*;

public class Clone {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {

        depthClone();
    }

    /**
     * 深度复制，用序列化和反序列化实现 ,实体类需继承Serializable接口
     */
    public static void  depthClone() throws IOException, ClassNotFoundException {
       User u = new User("pcj",30);
       byte[] bytes = toByteArray(u);
        User o = (User) toObject(bytes);
        System.out.println(o);
    }

    public static byte[] toByteArray(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        baos.close();
        oos.close();
        return baos.toByteArray();
    }

    public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream stream = new ObjectInputStream(bais);
        bais.close();
        stream.close();
        return stream.readObject();
    }
    /**
     * 浅度复制 实体类只需继承cloneable接口 重写clone方法。
     * @throws CloneNotSupportedException
     */
    public static void clone1() throws CloneNotSupportedException {
        User u = new User("pcj",18);
        User u2 = (User) u.clone();
        System.out.println(u2);
    }
}
