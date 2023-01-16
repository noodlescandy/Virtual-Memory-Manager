public class MyInvalidMemoryException extends Exception{
    public String toString(){
        return "Error: Invalid Memory ID. Please use an ID matching an allocated memory chunk.";
    }
}
