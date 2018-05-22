package com.pcj.serialize;

import java.io.*;

public class ReadAndWriteObjectDomain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File(ReadAndWriteObjectDomain.class.getClassLoader().getResource("").getPath()+"user.txt");
        file.createNewFile();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        User user = new User("pcj",28);
        User user2 = new User("pcj",29);
        objectOutputStream.writeObject(user);
        objectOutputStream.writeObject(user2);

        System.out.println("写入完毕");
        objectOutputStream.close();

        ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
        Object o = os.readObject();
        Object o2 = os.readObject();
        os.close();
        System.out.println(o); System.out.println(o2);
    }


    public byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public Object toObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

}
