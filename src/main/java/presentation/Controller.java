package presentation;

import bll.DeliveryService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller
{
    private AppMainWindow appMainWindow;
    private LogInWindow logInWindow;

    /**
     * methods that initialize the buttons for the main window of the application
     * @param deliveryService
     */
    public void start(DeliveryService deliveryService)
    {
        appMainWindow = new AppMainWindow();

        initializeButtons(deliveryService);
    }

    public void initializeButtons(DeliveryService deliveryService)
    {
        appMainWindow.addClientButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                logInWindow = new LogInWindow();

                    logInWindow.initializeClientLogInButton(deliveryService);
                    logInWindow.initializeRegisterButton(deliveryService);
            }
        });

        appMainWindow.addAdminButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                logInWindow = new LogInWindow();

                logInWindow.initializeAdminLogInButton(deliveryService);
            }
        });

        appMainWindow.addEmployeeButtonPressedActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                logInWindow = new LogInWindow();

                logInWindow.initializeEmployeeLogInButton(deliveryService);
            }
        });
    }

}
