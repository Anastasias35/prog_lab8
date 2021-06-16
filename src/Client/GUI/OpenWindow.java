package Client.GUI;

import Client.Client;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Client.GUI.LoginWindow;

import static Client.Main1.loginWindow;
import static Client.Main1.registrationWindow;


public class OpenWindow  extends JPanel{
    Client client;
    private JButton login;
    private JButton registration;
    private JComboBox language;
    private JButton exit;
    private JLabel name;
    static JFrame jFrame;
    private JPanel mainMenuPanel;

    public OpenWindow(Client client) {
        this.client = client;
        loading();
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(loginWindow.getJPanel());
                jFrame.validate();
            }
        });
        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(registrationWindow.getJPanel());
                jFrame.validate();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

    }

    public void loading(){
        jFrame = new JFrame("WORKER");
        jFrame.setSize(1200, 800);
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBackground(new Color(0xC8DFAEE7, true));
        mainMenuPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[grow,fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]",
                // rows
                "[300,grow,fill]" +
                        "[40]" +
                        "[10]" +
                        "[40]" +
                        "[10]" +
                        "[40]" +
                        "[10]" +
                        "[40]" +
                        "[200,grow,fill]"
        ));


        //название коллекции
        name = new JLabel();
        name.setText("worker");
        name.setForeground(new Color(0xF6664D));
        name.setFont(new Font("TimesRoman", Font.BOLD, 70));
        mainMenuPanel.add(name, "cell 4 0,align center center,grow 0 0");

        //кнопка входа
        login = new JButton("Вход");
        login.setForeground(new Color(0xD73B3B));
        login.setBackground(new Color(0xF89380));
        login.setFont(new Font("TimesRoman", Font.BOLD, 30));
        login.setBorder(new OutLinesBoarder(7, new Color(0xF6664D)));
        mainMenuPanel.add(login, "cell 4 1,align center center,grow 0 0,width 219,height 40");

        //кнопка регистрации
        registration = new JButton("Регистрация");
        registration.setForeground(new Color(0xD73B3B));
        registration.setBackground(new Color(0xF89380));
        registration.setFont(new Font("TimesRoman", Font.BOLD, 30));
        registration.setBorder(new OutLinesBoarder(7, new Color(0xF6664D)));
        mainMenuPanel.add(registration, "cell 4 3,align center center,grow 0 0,width 220, height 40");

        //кнопка закрытия
        exit = new JButton("Закрыть");
        exit.setForeground(new Color(0xD73B3B));
        exit.setBackground(new Color(0xF89380));
        exit.setFont(new Font("TimesRoman", Font.BOLD, 30));
        exit.setBorder(new OutLinesBoarder(7, new Color(0xF6664D)));
        mainMenuPanel.add(exit, "cell 4 5,align center center,grow 0 0 ,width 220,height 40");

        //кнопка выбора языка
        String[] items = {
                "Русский",
                "Deutsch",
                "Español",
                "Dansk"
        };
        language = new JComboBox(items);
        language.setForeground(new Color(0xD73B3B));
        language.setBackground(new Color(0xF89380));
        language.setBorder(new OutLinesBoarder(7, new Color(0xF6664D)));
        language.setFont(new Font("TimesRoman", Font.BOLD, 30));
        mainMenuPanel.add(language, "cell 4 7,align center center,grow 0 0 ,width 220,height 40");

        jFrame.add(mainMenuPanel);
        jFrame.setVisible(true);
    }

    public JPanel getJPanel(){
        return mainMenuPanel;
    }

}

