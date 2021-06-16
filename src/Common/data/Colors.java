package Common.data;

import java.io.Serializable;

/**
 * Перечисление цветов глаз и волос
 */
public enum Colors implements Serializable {
    GREEN,
    BLUE,
    YELLOW,
    ORANGE;

    /**
     * @return список цветов
     */
    public static String lookColor(){
        String colorList="";
        for(Colors color:values()){
            colorList += color.name() + "\n";
        }
        System.out.println("Выберите один из предложенных вариантов");
        return colorList;
    }
}
