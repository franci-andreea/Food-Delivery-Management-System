package application;

import bll.DeliveryService;

import presentation.*;

import java.io.IOException;
import java.text.ParseException;

public class Main
{
    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException, IllegalAccessException
    {
        String fileName = "deliveryServiceFile.txt";
        Controller controller = new Controller();

        //DeliveryService deliveryService = Serializator.deliveryServiceDeserialization(fileName);
        DeliveryService deliveryService = new DeliveryService();

        controller.start(deliveryService);

        //Serializator.deliveryServiceSerialization(deliveryService, fileName);

    }

}
