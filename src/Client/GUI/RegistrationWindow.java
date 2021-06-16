package Client.GUI;

import Client.Client;
import Client.util.User;
import Common.Request;
import Common.Response;
import Common.ResponseType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Client.Main1.openWindow;

public class RegistrationWindow extends JPanel {
    private JPanel loginJPanel;
    private JLabel name;
    private JLabel loginJLabel;
    private JTextField loginJTextField;
    private JLabel passwordJLabel;
    private JTextField passwordJTextField;
    private JButton back;
    private JButton enter;
    private Client client;

    public RegistrationWindow(Client client){
        this.client=client;
        loadingWindow();
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenWindow.jFrame.setContentPane(openWindow.getJPanel());
                OpenWindow.jFrame.validate();
            }
        });
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User user = new User(loginJTextField.getText(), passwordJTextField.getText());
                    Request request = new Request("registration", "", user);
                    client.sendToServer(request);
                    Response response=client.recieveFromServer();
                    WorkerWindow workerWindow=new WorkerWindow(client,user);
                    if(response.getResponseType().equals(ResponseType.OK)){
                        loginJTextField.setText("");
                        passwordJTextField.setText("");
                        OpenWindow.jFrame.setContentPane(workerWindow.getJPanel());
                        OpenWindow.jFrame.validate();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,response.getInf());
                    }
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void loadingWindow(){
        loginJPanel=new JPanel();
        loginJPanel.setBackground(new Color(0xFF99CDAB, true));
        loginJPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[fill]" +
                        "[50,grow,fill]" +
                        "[300,grow,fill]"+
                        "[50,grow,fill]"+
                        "[fill]",
                // rows
                "[200,grow,fill]" +
                        "[40]" +
                        "[10]" +
                        "[30]" +
                        "[20]" +
                        "[40]" +
                        "[10]" +
                        "[30]" +
                        "[30]" +
                        "[40]"+
                        "[20]"+
                        "[40]"+
                        "[100,grow,fill]"));
        //Название
        name = new JLabel();
        name.setText("worker");
        name.setForeground(new Color(0x3AA78F));
        name.setFont(new Font("TimesRoman", Font.BOLD, 70));
        loginJPanel.add(name, "cell 2 0,align center center,grow 0 0");

        //Надпись ввода логина
        loginJLabel=new JLabel();
        loginJLabel.setText("Придумайте логин:");
        loginJLabel.setForeground(new Color(0x8A8FF3));
        loginJLabel.setFont(new Font("TimesRoman",Font.BOLD,35));
        loginJPanel.add(loginJLabel,"cell 2 1 , align center center, grow 0 0" );

        //стровка для ввода логина
        loginJTextField=new JTextField(16);
        loginJTextField.setFont(new Font("TimesRoman",Font.BOLD,18));
        loginJPanel.add(loginJTextField,"cell 2 3 , align center center, grow 0 0");

        //Надпись ввода пароля
        passwordJLabel = new JLabel("Придумайте пароль:");
        passwordJLabel.setForeground(new Color(0x8A8FF3));
        passwordJLabel.setFont(new Font("TimesRoman",Font.BOLD,35));
        loginJPanel.add(passwordJLabel,"cell 2 5, align center center,grow 0 0");

        //строка для ввода пароля
        passwordJTextField=new JTextField(16);
        passwordJTextField.setFont(new Font("TimesRoman",Font.BOLD,18));
        loginJPanel.add(passwordJTextField,"cell 2 7, align center center,grow 0 0 ");

        //кнопка войти
        enter =new JButton("Зарегестрироваться");
        enter.setForeground(new Color(0xFFFFFF));
        enter.setBackground(new Color(0x8A8FF3));
        enter.setBorder(new OutLinesBoarder(7, new Color(0x565BC6)));
        enter.setFont(new Font("TimesRoman", Font.BOLD, 35));
        loginJPanel.add(enter,"cell 2 9,align center center,grow 0 0");

        //назад
        back=new JButton("Назад");
        back.setForeground(new Color(0xFFFFFF));
        back.setBackground(new Color(0x8A8FF3));
        back.setBorder(new OutLinesBoarder(7, new Color(0x565BC6)));
        back.setFont(new Font("TimesRoman", Font.BOLD, 35));
        loginJPanel.add(back,"cell 2 11,align center center,grow 0 0");


    }

    public JPanel getJPanel(){
        return loginJPanel;
    }
}

