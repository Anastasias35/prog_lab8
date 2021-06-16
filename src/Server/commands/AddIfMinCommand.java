package Server.commands;

import Client.util.User;
import Common.data.Worker;
import Common.exceptions.EmptyCollection;
import Common.exceptions.IncorrectArgumentException;
import Server.utilitka.CollectionManager;
import Server.utilitka.DataBaseCollectionManager;
import Server.utilitka.StringResponse;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;


/**
 * Команда "add_if_min" добавляет элемент в коллекцию,если он меньше минимального в коллекции
 */
public class AddIfMinCommand extends AbstractCommand {

    private String name;
    private String description;
    private CollectionManager collectionManager;
    private RemoveByIdCommand removeByIdCommand;
    private DataBaseCollectionManager dataBaseCollectionManager;


    public AddIfMinCommand(CollectionManager collectionManager,DataBaseCollectionManager dataBaseCollectionManager){
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n");
        this.collectionManager=collectionManager;
        this.dataBaseCollectionManager=dataBaseCollectionManager;
    }

    /**
     * Выполнение команды
     * @param argument
     * @return состояние выполнения команды
     */
    @Override
    public boolean execute(String argument, Worker worker, User user) {
        try{
            if(collectionManager.sizeCollection()==0) throw new EmptyCollection();
            if(!argument.isEmpty()) throw new IncorrectArgumentException();

            if(collectionManager.minSalary(worker)) {
                    dataBaseCollectionManager.addWorker(worker,user);
                    StringResponse.appendln("Worker добавлен в базу данных");
                    collectionManager.addToCollection(worker);
                    //System.out.println("Worker добавлен в коллекцию");
            }
            return true;
        }catch (IncorrectArgumentException exception){
            StringResponse.appendError("Команда "+ getName() + " не должна иметь аргументы");
            return false;
        }catch (EmptyCollection exception){
            StringResponse.appendError("Коллекция пуста");
            return false;
        }catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }
}
