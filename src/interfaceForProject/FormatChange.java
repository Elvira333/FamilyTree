package interfaceForProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Пока не рабочий класс, требуется дополнить как выводить дерево само, а не список с данными
 */
public class FormatChange {

    public FormatChange() {
    }

    public void getFormatTxt(List<List> people, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (List str : people) {
            writer.write(str+System.getProperty("line.separator") + "\n");
        }

        writer.close();
    }


}
