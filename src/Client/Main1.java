package Client;

import Client.GUI.LoginWindow;
import Client.GUI.OpenWindow;
import Client.GUI.RegistrationWindow;
import Client.GUI.WorkerWindow;
import Client.util.Creator;
import Client.util.Entrance;
import Client.util.NewConsole;

import javax.swing.*;
import java.util.Scanner;

public class Main1 {
    public static OpenWindow openWindow;
    public static LoginWindow loginWindow;
    public static RegistrationWindow registrationWindow;
 //   public static WorkerWindow workerWindow;



    public static void main(String[] args){
        System.out.println("Клиент запущен");
        Scanner scanner=new Scanner(System.in);
        Creator creator=new Creator(scanner);
        NewConsole console=new NewConsole(scanner,creator);
        Entrance entrance=new Entrance(scanner);
        Client client=new Client("localhost", 5234,console,entrance);
        client.connect();
        openWindow=new OpenWindow(client);
        loginWindow=new LoginWindow(client);
        registrationWindow=new RegistrationWindow(client);
    //    workerWindow=new WorkerWindow(client);

       // client.work();
       // scanner.close();
    }
}
