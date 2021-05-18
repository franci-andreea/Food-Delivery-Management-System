package data;

import bll.DeliveryService;

import java.io.*;

public class Serializator implements Serializable
{
    /**
     * method used for serializing an object
     * @param deliveryServiceToSerialize - the object we want to serialize
     * @param fileName - the name of the file in which we want to serialize
     * @throws IOException
     */
     public static void deliveryServiceSerialization(DeliveryService deliveryServiceToSerialize, String fileName) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(deliveryServiceToSerialize);

        System.out.println("object deliveryServiceToSerialize has been successfully serialized");

        objectOutputStream.close();
        fileOutputStream.close();

    }

    /**
     * method used to deserialize an object
     * @param fileName - the name of the file from which we want to read/extract the information about the object
     * @return - the object deserialized
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static DeliveryService deliveryServiceDeserialization(String fileName) throws IOException, ClassNotFoundException
    {
        DeliveryService deliveryService = null;

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        deliveryService = (DeliveryService) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        System.out.println("object has been deserialized and placed into deliveryService object");

        return deliveryService;
    }
}
