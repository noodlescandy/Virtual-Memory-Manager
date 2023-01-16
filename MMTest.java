public class MMTest {
    public static void main(String[] args) {
        // catch error: no file and print proper usage
        
        // read in file from command line
        
    }

    public MMTest(String filename) {
        // open file (sep. method?)

        // go through each line and parse it using parseCommand method
    }

    private void parseCommand(String command){
        // if line starts with //, skip line as a comment

        // init <size> -- call init method of MMInterface with size
        // size must be positive integer less than or equal to Integer.MAX_Value

        // malloc <id> <size> -- calls malloc method of MMInterface
        // assume id is unique for given input file
        // Id must be a positive integer that will be less than or equal to Integer.MAX_Value 
        // size must be positive integer less than or equal to Integer.MAX_Value

        // free <id> -- calls free method of MMInterface
        // Id must be a positive integer that will be less than or equal to Integer.MAX_Value
    }
}
