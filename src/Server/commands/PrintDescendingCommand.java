package Server.commands;

import Client.util.User;
import Common.data.Worker;
import Common.exceptions.EmptyCollection;
import Common.exceptions.IncorrectArgumentException;
import Server.utilitka.CollectionManager;
import Server.utilitka.DataBaseCollectionManager;
import Server.utilitka.StringResponse;


/**
 * Команда "print_descending" выводит элементы коллекции в порядке убывания
 */
public class PrintDescendingCommand extends AbstractCommand {

    private String name;
    private String description;
    private CollectionManager collectionManager;
    private DataBaseCollectionManager dataBaseCollectionManager;

    public PrintDescendingCommand(CollectionManager collectionManager,DataBaseCollectionManager dataBaseCollectionManager){
        super("print_descending", "вывести элементы коллекции в порядке убывания");
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
            collectionManager.reverseSort();
            return true;
        } catch (IncorrectArgumentException exception){
            StringResponse.appendError("Команда "+ getName() + " не имеет параметров");
            return false;
        } catch (EmptyCollection exception){
            StringResponse.appendError("Коллекция пуста");
            return false;
        }
    }
}
