package Client.util;


import Common.data.*;
import Common.exceptions.IncorrectVariableException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

//создание нового элемента

/**
 * Класс, работающий с вводом значений для создания элемента
 */
public class Creator {
    private int minY=-256;
    private double minWeight=0;
    private int minSalary=0;
    private Scanner scanner;

    public Creator(Scanner scanner){
        this.scanner=scanner;
    }

    /**
     * Получает имя работника
     * @return имя
     */
    public String inputName(){
         String name="";
         while(true) {
             System.out.println("Введите имя:");
             try {
                 name = scanner.nextLine().trim();
                 if (name.isEmpty()) throw new IncorrectVariableException(); //переименовать это исключение
                 break;
             } catch (IncorrectVariableException exception) {
                 System.out.println("Имя не может быть пустым");
             }
         }
         return name;
    }

    /**
     * Получает координаты работника
     * @return координаты x-y
     */
    public Coordinates inputCoordinates(){
        double y;
        int x;
        while(true){
            try {
                System.out.println("Введите координату x:");
                x = Integer.parseInt(scanner.nextLine().trim());
                break;
            }catch (NumberFormatException exception){
                System.err.println("Координата х должна быть только целым числом");
            }
        }

        while(true){
            try {
                System.out.println("Введите координату y:");
                y = Double.parseDouble(scanner.nextLine().trim());
                if (y < minY) throw new IncorrectVariableException();
                break;
            }catch (IncorrectVariableException exception){
                System.err.println("Координата y должна быть больше " + minY);
            }catch(NumberFormatException exception){
                System.err.println("Координата y должна быть только числом");
            }
        }
        return new Coordinates(x,y);
    }

    /**
     * Получает профессию работника
     * @return профессия
     */
    public Position inputPosition(){
        Position currentPosition;
        while(true) {
            try{
                System.out.println("Введите профессию");
                System.out.println("Список профессий" + "\n" + Position.lookPosition());
                String str=scanner.nextLine().trim();
                if(str.isEmpty()) return null;
                currentPosition=Position.valueOf(str.toUpperCase());
                break;
            }catch (IllegalArgumentException exception){ //переделать исключения
                System.err.println("Такой професси нет в предложенном списке");
            }
        }
        return currentPosition;
    }

    /**
     * Получает цвет глаз работника
     * @return цвет
     */
    public Colors inputEyeColor(){
        Colors currentEyeColor;
        while(true){
            try{
                System.out.println("Введите цвет глаз");
                System.out.println("Список цветов" + "\n" + Colors.lookColor() );
                String str=scanner.nextLine().trim();
                if(str.isEmpty()) return null;
                currentEyeColor= Colors.valueOf(str.toUpperCase());
                break;
            } catch (IllegalArgumentException exception){ //переделать исключения
                System.err.println("Такого цвета глаз нет в предложенном списке");
            }
        }
        return currentEyeColor;
    }

    /**
     * Получает цвет волос работника
     * @return цвет
     */
    public Colors inputHairColor(){
        Colors currentHairColor;
        while(true){
            try{
                System.out.println("Введите цвет волос");
                System.out.println("Список цветов "+ "\n" + Colors.lookColor());
                String str=scanner.nextLine().trim();
                if(str.isEmpty()) return null;
                currentHairColor= Colors.valueOf(str.toUpperCase());
                break;
            } catch (IllegalArgumentException exception){ //исключения
                System.err.println("Такого цвета волос нет в предложенном списке");
            }
        }
        return currentHairColor;
    }

    /**
     * Получает страну работника
     * @return страна
     */
    public Country inputNationality(){
        Country currentNationality;
        while(true){
            try{
                System.out.println("Введите страну");
                System.out.println("Список стран" + "\n" + Country.lookCountry());
                String str=scanner.nextLine().trim();
                if(str.isEmpty()) return null;
                currentNationality=Country.valueOf(str.toUpperCase());
                break;
            } catch (IllegalArgumentException exception){ //исключения
                System.err.println("Такой страны нет в предложенном списке ");
            }
        }
        return currentNationality;
    }

    /**
     * Получает вес работника
     * @return вес
     */
    public Double inputWeight(){
        Double currentWeight;
        while(true) {
            try {
                System.out.println("Введите вес");
                String str=scanner.nextLine().trim();
                if(str.isEmpty()) return null;
                currentWeight = Double.parseDouble(str); //добавить исключения
                if (currentWeight <= minWeight) throw new IncorrectVariableException();
                break;
            } catch (IncorrectVariableException exception) {
                System.err.println("Вес должен быть больше 0");
            } catch (NumberFormatException exception){
                System.err.println("Вес должен быть только числом");
            }
        }
        return currentWeight;
    }

    /**
     * Получает зарплату работника
     * @return зарплата
     */
    public Long inputSalary(){
        Long currentSalary;
        while(true){
            try {
                System.out.println("Введите зарплату");
                String str=scanner.nextLine().trim();
                if(str.isEmpty()) return null;
                currentSalary = Long.parseLong(str); //добавить исключения

                if (currentSalary <= minSalary) throw new IncorrectVariableException();
                break;
            }catch (IncorrectVariableException exception){
                System.err.println("Зарплата должна быть больше 0");
            }catch (NumberFormatException exception){
                System.err.println("Зарплата должна быть целым числом");
            }
        }
        return  currentSalary;
    }

    /**
     * Получает дату начала работы работника
     * @return дата
     */
    public LocalDateTime inputStartDate(){
        LocalDateTime currentStartDate;
        while (true) {
            try{
                System.out.println("Введите дату начала работы");
                currentStartDate = LocalDateTime.parse(scanner.nextLine().trim() +"T00:00:00"); //подумать над исключениями
                break;
            }catch (DateTimeParseException exception){
                System.err.println("Неверный формат ввода");
                System.err.println("Дата должна быть в формате yyyy-mm-dd");
            }
        }
        return currentStartDate;
    }

    /**
     * Получает дату окончания работы работника
     * @return дата
     */
    public LocalDateTime inputEndDate() {
        LocalDateTime currentEndDate;
        while (true) {
            try{
                System.out.println("Введите дату окончания работы");
                currentEndDate = LocalDateTime.parse(scanner.nextLine().trim()+ "T00:00:00"); //подумать над исключениями
                break;

            }catch (DateTimeParseException exception){
                System.err.println("Неверный формат ввода");
            }
        }
        return currentEndDate;
    }


    /**
     * Получает личность работника
     * @return личность
     */
    public Person inputPerson(){
        return new Person(inputWeight(), inputEyeColor(), inputHairColor(), inputNationality());
    }

    /**
     * Установка scanner для нового пользователя
     * @param scanner
     */
    public void setScanner(Scanner scanner){
        this.scanner=scanner;
    }

    /**
     * @return Scanner, который использует пользователь
     */
    public Scanner getScanner(){
        return this.scanner;
    }



}
