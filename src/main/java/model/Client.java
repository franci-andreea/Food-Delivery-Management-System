package model;

public class Client
{
    private int clientID;
    private String realName;
    private String username;
    private String password;
    private String phoneNumber;
    private int numberOfOrders;

    /**
     * Constructor to initialize all the fields for the Client object
     * @param clientID
     * @param realName
     * @param username
     * @param password
     * @param phoneNumber
     */
    public Client(int clientID, String realName, String username, String password, String phoneNumber)
    {
        this.clientID = clientID;
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public int getClientID()
    {
        return clientID;
    }

    public void setClientID(int clientID)
    {
        this.clientID = clientID;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public int getNumberOfOrders()
    {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders)
    {
        this.numberOfOrders = numberOfOrders;
    }

    @Override
    public String toString()
    {
        return "Client{" +
                "clientID=" + clientID +
                ", realName='" + realName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", numberOfOrders=" + numberOfOrders + '\'' +
                '}';
    }
}
