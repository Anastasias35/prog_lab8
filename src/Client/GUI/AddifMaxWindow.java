package Client.GUI;

import Client.Client;
import Client.util.User;
import Common.Request;
import Common.Response;
import Common.ResponseType;
import Common.data.*;
import Common.exceptions.IncorrectVariableException;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class AddifMaxWindow extends JPanel {
        private Client client;
        private User user;
        private JPanel addJPanel;
        private JLabel newWorker;
        private JLabel newName;
        private JTextField name;
        private JLabel coordinates;
        private JLabel coordinatesx;
        private JTextField x;
        private JLabel coordinatesy;
        private JTextField y;
        private JLabel nameSalary;
        private JTextField salary;
        private JLabel nameWeight;
        private JTextField weight;
        private JLabel nameStartDate;
        private JTextField startDate;
        private JLabel nameEndDate;
        private JTextField endDate;
        private JLabel namePosition;
        private JComboBox position;
        private JLabel nameEyeColor;
        private JComboBox eyeColor;
        private JLabel nameHairColor;
        private JComboBox hairColor;
        private JLabel nameNationality;
        private JComboBox nationality;
        private JButton back;
        private JButton add;
        private WorkerWindow workerWindow;


        public AddifMaxWindow(Client client, User user) {
            this.client = client;
            this.user = user;
            loadingWindow();
            workerWindow=new WorkerWindow(client,user);
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    OpenWindow.jFrame.setContentPane(workerWindow.getJPanel());
                    OpenWindow.jFrame.validate();
                }
            });
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder stringBuilder=new StringBuilder();
                    try{
                        if (name.getText().isEmpty()) throw new NullPointerException();
                    }catch(NullPointerException exception){
                        stringBuilder.append("Имя не может быть пустым");
                    }
                    int x1=0;
                    try{
                        x1=Integer.parseInt(x.getText());
                    }catch(NumberFormatException exception){
                        stringBuilder.append("'x' должен быть целым числом");
                    }
                    double y1=0;
                    try{
                        y1=Double.parseDouble(y.getText());
                        if (y1<-256) throw new IncorrectVariableException();
                    } catch (IncorrectVariableException exception) {
                        stringBuilder.append("'y' должно быть больше -256");
                    } catch(NumberFormatException exception){
                        stringBuilder.append("'y' должно быть числом");
                    }
                    LocalDateTime startDate1=null;
                    try{
                        startDate1=LocalDateTime.parse(startDate.getText() +"T00:00:00");
                    }catch (DateTimeParseException exception){
                        stringBuilder.append("неправильный формат даты начала работы");
                    }
                    LocalDateTime endDate1=null;
                    try{
                        endDate1=LocalDateTime.parse(endDate.getText()+"T00:00:00");
                    }catch (DateTimeParseException exception){
                        stringBuilder.append("неправильный формат даты окончания работы");
                    }
                    Long salary1=null;
                    try{
                        salary1=Long.parseLong(salary.getText());
                        if (salary1<0) throw new IncorrectVariableException();
                    }catch (NumberFormatException exception){
                        stringBuilder.append("зарплата должна быть целым числом");
                    }catch (NullPointerException exception){
                    } catch (IncorrectVariableException exception) {
                        stringBuilder.append("зарплата должна быть больше 0");
                    }
                    Double weight1=null;
                    try{
                        weight1=Double.parseDouble(weight.getText());
                    }catch (NumberFormatException exception){
                        stringBuilder.append("вес должен быть числом");
                    }

                    if(stringBuilder.equals("")) {
                        try {
                            Coordinates coordinates = new Coordinates(x1, y1);
                            Instant instant = Instant.now();
                            Person person = new Person(weight1, Colors.valueOf((eyeColor.getItemAt(eyeColor.getSelectedIndex())).toString()), Colors.valueOf((hairColor.getItemAt(hairColor.getSelectedIndex())).toString()), Country.valueOf(nationality.getItemAt(nationality.getSelectedIndex()).toString()));
                            Worker worker = new Worker(0, name.getText(), coordinates, Date.from(instant),salary1 , startDate1, endDate1, Position.valueOf((position.getItemAt(position.getSelectedIndex())).toString()), person, user);
                            Request request = new Request("add_if_max", "", worker, user);
                            client.sendToServer(request);
                            Response response = client.recieveFromServer();
                            if (response.getResponseType().equals(ResponseType.OK)) {
                                JOptionPane.showMessageDialog(null, response.getInf());
                                OpenWindow.jFrame.setContentPane(workerWindow.getJPanel());
                                OpenWindow.jFrame.validate();
                            } else {
                                JOptionPane.showMessageDialog(null, response.getInf());
                            }
                        } catch (IOException | ClassNotFoundException exception) {
                            exception.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,stringBuilder);
                    }
                }
            });
        }

        public void loadingWindow(){
            addJPanel=new JPanel();
            addJPanel.setBackground(new Color(0xF6CC6E));
            addJPanel.setLayout(new MigLayout(
                    "insets 0,hidemode 3,align center center",
                    // columns
                    "[40,grow,fill]" +
                            "[20,grow,fill]" +
                            "[20,grow,fill]"+
                            "[15,grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[20,grow,fill]" +
                            "[20,grow,fill]" +
                            "[30,grow,fill]" +
                            "[grow,fill]",
                    // rows
                    "[30,fill]" +
                            "[50,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]" +
                            "[5,grow,fill]"));

            //заголовок
            newWorker=new JLabel("NEW WORKER");
            newWorker.setForeground(new Color(0x54AD54));
            newWorker.setFont(new Font("TimesRoman",Font.BOLD,60));
            addJPanel.add(newWorker,"cell 1 1 7 1,align center center,grow 0 0"); //поменять размешение

            //заголовок имени
            newName=new JLabel("Введите имя:");
            newName.setForeground(new Color(0x54AD54));
            newName.setFont(new Font("TimesRoman",Font.BOLD,20));
            addJPanel.add(newName,"cell 1 2,align center center,grow 0 0");

            //поле для ввода имени
            name=new JTextField();
            name.setFont(new Font("TimesRoman",Font.BOLD,20));
            addJPanel.add(name,"cell 2 2 2 0,align center center,grow 0 0,width 100");

            //координаты
            coordinates=new JLabel("Введите координаты:");
            coordinates.setFont(new Font("TimesRoman",Font.BOLD,20));
            coordinates.setForeground(new Color(0x54AD54));
            addJPanel.add(coordinates,"cell 1 3 1 0,align center center,grow 0 0");

            //координата x
            coordinatesx=new JLabel("x:");
            coordinatesx.setFont(new Font("TimeRoman",Font.BOLD,20));
            coordinatesx.setForeground(new Color(0x54AD54));
            addJPanel.add(coordinatesx,"cell 1 4,align center center,grow 0 0");

            //поле для ввода x
            x=new JTextField();
            x.setFont(new Font("TimesRoman",Font.BOLD,20));
            addJPanel.add(x,"cell 2 4 2 0,align center center,grow 0 0,width 100");

            //координата y
            coordinatesy=new JLabel("y:");
            coordinatesy.setFont(new Font("TimesRoman",Font.BOLD,20));
            coordinatesy.setForeground(new Color(0x54AD54));
            addJPanel.add(coordinatesy,"cell 1 5,align center center,grow 0 0");

            //поле для ввода y
            y=new JTextField();
            y.setFont(new Font("TimesRoman",Font.BOLD,20));
            addJPanel.add(y,"cell 2 5 2 0,align center center,grow  0 0,width 100");

            //название для зарплаты
            nameSalary=new JLabel("Введите зарплату:");
            nameSalary.setFont(new Font("TimesRoman",Font.BOLD,20));
            nameSalary.setForeground(new Color(0x54AD54));
            addJPanel.add(nameSalary,"cell 1 6,align center center,grow 0 0");

            //поле для ввода зарплаты
            salary=new JTextField();
            salary.setFont(new Font("TimesRoman",Font.BOLD,20));
            addJPanel.add(salary,"cell 2 6 2 0,align center center,grow 0 0,width 100");

            //названия для веса
            nameWeight=new JLabel("Введите вес:");
            nameWeight.setFont(new Font("TimesRoman",Font.BOLD,20));
            nameWeight.setForeground(new Color(0x54AD54));
            addJPanel.add(nameWeight,"cell 1 7,align center center,grow 0 0");

            //поле для ввода веса
            weight=new JTextField();
            weight.setFont(new Font("TimesRoman",Font.BOLD,20));
            addJPanel.add(weight,"cell 2 7 2 0,align center center,grow 0 0,width 100");

            //навзания для начала работы
            nameStartDate=new JLabel("Введите дату начала работы:");
            nameStartDate.setFont(new Font("TimesRoman",Font.BOLD,20));
            nameStartDate.setForeground(new Color(0x54AD54));
            addJPanel.add(nameStartDate,"cell 6 2,align center center,grow 0 0");

            //ввод для начала работы
            startDate=new JTextField();
            startDate.setFont(new Font("TimesRoman",Font.BOLD,20));
            addJPanel.add(startDate,"cell 7 2,align center center,grow 0 0,width 100");

            //заголовок окончания работы
            nameEndDate=new JLabel("Введите дату окончания работы:");
            nameEndDate.setFont(new Font("TimesRoman",Font.BOLD,20));
            nameEndDate.setForeground(new Color(0x54AD54));
            addJPanel.add(nameEndDate,"cell 6 3,align center center,grow 0 0");

            //ввод для окончания работы
            endDate=new JTextField();
            endDate.setFont(new Font("TimesRoman",Font.BOLD,20));
            addJPanel.add(endDate,"cell 7 3,align center center,grow 0 0,width 100");

            //поле для названия профессии
            namePosition=new JLabel("Выберите профессию:");
            namePosition.setFont(new Font("TimesRoman",Font.BOLD,20));
            namePosition.setForeground(new Color(0x54AD54));
            addJPanel.add(namePosition,"cell 6 4,align center center,grow 0 0");

            //выбор профессии
            String[] items = {
                    "CLEANER",
                    "ENGINEER",
                    "LEAD_DEVELOPER",
                    "DIRECTOR"
            };
            position=new JComboBox(items);
            position.setForeground(new Color(0x54AD54));
            position.setBackground(new Color(0xFFFFFF));
            position.setBorder(new OutLinesBoarder(4, new Color(0x218027)));
            position.setFont(new Font("TimesRoman", Font.BOLD, 20));
            addJPanel.add(position, "cell 7 4,align center center,grow 0 0 ,width 220,height 40");


            //заголовок для названия цвета глаз
            nameEyeColor=new JLabel("Выберите цвет глаз:");
            nameEyeColor.setFont(new Font("TimesRoman",Font.BOLD,20));
            nameEyeColor.setForeground(new Color(0x54AD54));
            addJPanel.add(nameEyeColor,"cell 6 5,align center center,grow 0 0");

            //выбор цвета глаз
            String[] items1={
                    "GREEN",
                    "BLUE",
                    "YELLOW",
                    "ORANGE"
            };
            eyeColor=new JComboBox(items1);
            eyeColor.setForeground(new Color(0x54AD54));
            eyeColor.setBackground(new Color(0xFFFFFF));
            eyeColor.setBorder(new OutLinesBoarder(4, new Color(0x218027)));
            eyeColor.setFont(new Font("TimesRoman", Font.BOLD, 20));
            addJPanel.add(eyeColor, "cell 7 5,align center center,grow 0 0 ,width 220,height 40");


            //заголовок для названия цвета волос
            nameHairColor=new JLabel("Выберите цвет волос:");
            nameHairColor.setFont(new Font("TimesRoman",Font.BOLD,20));
            nameHairColor.setForeground(new Color(0x54AD54));
            addJPanel.add(nameHairColor,"cell 6 6,align center center,grow 0 0");

            //выбор цвета
            hairColor=new JComboBox(items1);
            hairColor.setForeground(new Color(0x54AD54));
            hairColor.setBackground(new Color(0xFFFFFF));
            hairColor.setBorder(new OutLinesBoarder(4, new Color(0x218027)));
            hairColor.setFont(new Font("TimesRoman", Font.BOLD, 20));
            addJPanel.add(hairColor, "cell 7 6,align center center,grow 0 0 ,width 220,height 40");


            //заголовок для выбора страны
            nameNationality=new JLabel("Выберите страну:");
            nameNationality.setFont(new Font("TimesRoman",Font.BOLD,20));
            nameNationality.setForeground(new Color(0x54AD54));
            addJPanel.add(nameNationality,"cell 6 7,align center center,grow 0 0");


            //выбор страны
            String[] items2={
                    "RUSSIA",
                    "GERMANY",
                    "VATICAN"
            };
            nationality=new JComboBox(items2);
            nationality.setForeground(new Color(0x54AD54));
            nationality.setBackground(new Color(0xFFFFFF));
            nationality.setBorder(new OutLinesBoarder(4, new Color(0x218027)));
            nationality.setFont(new Font("TimesRoman", Font.BOLD, 20));
            addJPanel.add(nationality, "cell 7 7,align center center,grow 0 0 ,width 220,height 40");

            //кнопка назад
            back=new JButton("Назад");
            back.setForeground(new Color(0xFFFFFF));
            back.setBackground(new Color(0x54AD54));
            back.setBorder(new OutLinesBoarder(4,new Color(0x218027)));
            back.setFont(new Font("TimesRoman",Font.BOLD,40));
            addJPanel.add(back,"cell 4 8 6 8,align center center,grow 0 0");

            //кнопка добавить
            add=new JButton("Добавить");
            add.setForeground(new Color(0xFFFFFF));
            add.setBackground(new Color(0x54AD54));
            add.setBorder(new OutLinesBoarder(4,new Color(0x218027)));
            add.setFont(new Font("TimesRoman",Font.BOLD,40));
            addJPanel.add(add,"cell 2 8 4 8,align center center,grow 0 0");
        }

        public JPanel getJpanel(){
            return addJPanel;
        }
}

