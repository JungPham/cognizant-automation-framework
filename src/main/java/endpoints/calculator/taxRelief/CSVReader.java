package endpoints.calculator.taxRelief;
import java.io. * ;
import java.util.*;

public class CSVReader {
    public static final String delimiter = ",";
    public static List<WorkingClassHero> listHeroFromCSV;
    public static List<WorkingClassHero> read(String csvFile)  throws Exception {
        try {
            listHeroFromCSV = new ArrayList<>();
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            String[] tempArr;
            br.readLine();
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);

                WorkingClassHero hero = new WorkingClassHero(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[4], tempArr[5]);
                listHeroFromCSV.add(hero);
            }
            br.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return listHeroFromCSV;
    }

    public static List<WorkingClassHero> getListHeroFromCSV() throws Exception{
        //csv file to read
        ClassLoader classLoader = CSVReader.class.getClassLoader();
        File file = new File(classLoader.getResource("insert.csv").getFile());
        String csvFile = file.getAbsolutePath();
        return CSVReader.read(csvFile);
    }
}
