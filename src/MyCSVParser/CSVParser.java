package MyCSVParser;

import recources.DataEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {

    public static List<DataEntity> parseCSV(File fin) throws IOException {
        List<DataEntity> res = new ArrayList<>();

        FileReader freader = new FileReader(fin);
        BufferedReader reader = new BufferedReader(freader);
        String line = reader.readLine();

        while(line != null){
            String[] str_arr = line.split(",");
            List<String> str_list;
            str_list = Arrays.asList(str_arr);
            res.add(new DataEntity(str_list));
            line = reader.readLine();
        }

        return res;
    }
}
