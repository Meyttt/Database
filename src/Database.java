import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Александра on 07.01.2017.
 */
public class Database extends HashMap<String,PersonInformation> implements Serializable{
    String name;
    public Database() {
        super();
    }
    public Database (String filename) throws IOException, ClassNotFoundException {
        super();
        this.name=filename;
        FileInputStream fileInputStream = new FileInputStream("data/"+filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Database database = (Database) objectInputStream.readObject();
        this.putAll(database);
    }
    public void endWork(String filename) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
    }
    public static void communication() throws IOException, ClassNotFoundException {
        Database database;
        System.out.println("Press 0 to create new database \nPress 1 to load database");
        Scanner scanner= new Scanner(System.in);
        while (true){
            String nextLine = scanner.nextLine();
            if (!(nextLine.equals("1")||nextLine.equals("0"))){
                continue;
            }else{
                if(nextLine.equals("0")){
                    database=createDatabase();
                }else{
                    System.out.println("Please, enter the database name");
                    String filename = scanner.nextLine();
                    File file = new File("data");
                    String[] files = file.list();
                    if (Arrays.binarySearch(files,filename)==-2){
                        System.err.println("File not found!");
                        return;
                    }
                    try{
                        database=new Database(filename);
                    }catch (FileNotFoundException e){
                        System.err.println("File not found!");
                        return;
                    }

                    System.out.println(database);
                }
            }
        }
    }
    public static Database createDatabase() throws IOException, ClassNotFoundException {
        Database database;
        Scanner scanner = new Scanner(System.in);
        mainCircle:while(true) {
            System.out.println("Please, enter the database name");
            String filename = scanner.nextLine();
            File file = new File("data");
            String[] files = file.list();
            if (Arrays.binarySearch(files, filename) == -2) {
                return new Database(filename);
            } else {
                System.out.println("Database with this name is already exists.\nDo you want to rewrite it? (y/n)");
                reading:
                while (true) {
                    String answer = scanner.nextLine();
                    if (!(answer.equals("y") || answer.equals("n"))) {
                        continue reading;
                    }
                    if (answer.equals("y")) {
                        return new Database(filename);
                    } else {
                         continue mainCircle;
                    }
                }


            }
        }

    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        communication();
//        Database database= new Database();
//        database.put("1", new PersonInformation("Thomas","Anderson","The Matrix"));
//        database.endWork("database.txt");
//
//        Database database1 = new Database("database.txt");
//        System.out.println(database1.get("1").toString("1"));
    }
}
