import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MMTest {
    public static void main(String[] args){
        String file;
        try{
            file = args[0];
        }
        catch(Exception e){
            System.err.println("Usage MMTest.java <file.txt>");
            return;
        }
        BufferedReader b;
        try{
            File f = new File(file);
            FileReader reader = new FileReader(f);
            b = new BufferedReader(reader);
        }
        catch(Exception e){
            System.err.println("Invalid File: " + file + " could not be found or opened.\nUsage MMTest.java <file.txt>");
            return;
        }
        String command;
        MMInterface mem = new MemMgr();
        try{
            while((command = b.readLine()) != null){
                int indx = command.indexOf(" ");
                String cmd = indx != -1 ? command.substring(0, indx) : command;
                String arg = indx != -1 ? command.substring(indx).trim() : "";
                switch (cmd) {
                    case "init":
                        // init <size>
                        try {
                            int size = Integer.parseInt(arg);
                            mem.init(size);
                        } catch (Exception e) {
                            System.err.println("Usage: init <size>");
                        }
                        break;
                    case "malloc":
                        // malloc <id> <size>
                        int ind = arg.indexOf(" ");
                        try {
                            int id = Integer.parseInt(arg.substring(0, ind));
                            int size = Integer.parseInt(arg.substring(ind+1));
                            mem.malloc(id, size);
                        } catch (Exception e) {
                            System.err.println("Invalid. Usage: malloc <id> <size> where size does not exceed total space free");
                        }
                        break;
                    case "free":
                        // free <id>
                        try {
                            int id = Integer.parseInt(arg);
                            mem.free(id);
                        } catch (Exception e) {
                            System.err.println("Invalid. Usage: free <id> where id matches a used id.");
                        }
                        break;
                    case "print":
                        mem.print();
                        break;
                    default:
                        break;
                }
            }
        }
        catch(Exception e){
            System.err.println("An unexpected error has occurred.");
            System.err.println(e);
        }
    }
}
