public class MyOutOfMemoryException extends Exception{
    public String toString(){
        return "Error: Out of Memory. Initialize more memory or free up used memory.";
    }
}
