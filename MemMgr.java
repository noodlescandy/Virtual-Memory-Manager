// keeps track of memory using some variety of linked list, cannot use java.util.ArrayList or
// any collections that hold multiple objects

public class MemMgr implements MMInterface{
    private Chunk head;
    //private Chunk tail;
    class Chunk{
        private int id;
        private int size;
        private Chunk next;
        private Chunk previous;

        Chunk(int id, int size, Chunk previous, Chunk next){
            this.id = id;
            this.size = size;
            this.next = next;
            this.previous = previous;
        }
    }
    
    /**
	 * This method sets the initial amount of memory available.  Immediately after init is called all of the memory is available and is in a single large chunk.
	 */
    public void init(int size){
        head = new Chunk(-1, size, null, null);
        //tail = head;
    }

    /**
	 * This sets aside a chunk of memory for use.  Once malloc'd it is not available until it is freed.  The id will be used by the free call to identify which chunk of memory to free.  The call will set aside the amount of memory specified by size.  It will use the "First Fit" approach.  That is it will start at memory location zero and check until it finds an unused chunk of memory that is at least as large as the size requested.  When this chunk is found it will mark it as being in use, and associate the id with the chunk.  If there is more memory than requested, the additional memory remains available for future malloc requests.  The malloc always returns the entire amount requested, never a partial amount.  The chunk never goes past the upper limit of the memory specified in the init request.  If there is not enough memory available throw an MyOutOfMemoryException, create a subclass to Exception for this.
	 */
    public void malloc(int id, int size) throws MyOutOfMemoryException{
        // start at memory location zero
        Chunk current = head;
        while(current != null){
            if(current.id == -1 && size <= current.size){
                // split chunk, subtracting free space into new chunk
                Chunk allocatedChunk = new Chunk(id, size, current.previous, current);
                if(allocatedChunk.previous == null){
                    head = allocatedChunk;
                }
                else{
                    current.previous.next = allocatedChunk;
                }
                // resize current
                current.previous = allocatedChunk;
                current.size -= size;

                if(current.size == 0){ // shouldn´t be less than 0
                    // delete, empty chunk
                    allocatedChunk.next = current.next; // could be null (if null, tail)
                    //tail = allocatedChunk.next == null ? allocatedChunk : tail;
                }
                return;
            }
            current = current == null ? current : current.next;
        }
        throw new MyOutOfMemoryException();
    }

    /**
	 * Find the chunk of memory associated with this id and make it available for reuse.  These things should happen to make it available for reuse:  disassociate the id from this chunk of memory, mark the chunk of memory as available for use, coalesce this chunk of memory with any adjacent chunks that are available, so that they become one large chunk of available memory.  Completely coalesce all the memory every time a chunk is freed.  If a chunk of memory with the requested id can not be found throw a MyInvalidMemoryException, create a subclass to Exception for this.
	 */
    public void free(int id) throws MyInvalidMemoryException{
        Chunk current = head;
        while(current != null){
            if(current.id == id){
                current.id = -1;
                if(current.previous != null && current.previous.id == -1){
                    merge(current.previous);
                    current = current.previous;
                }
                if(current.next != null && current.next.id == -1){
                    merge(current);
                }
                return;
            }
            current = current == null ? current : current.next;
        }
        throw new MyInvalidMemoryException();
    }

    /**
     * Combines this chunk and the next chunk into this chunk, assuming that they both possess the id of -1 and current.next != null
     */
    private void merge(Chunk current){
        current.size += current.next.size;
        if(current.next.next != null){
            current.next.next.previous = current;
        }
        current.next = current.next.next;
    }

    /**
	 * Print out all the memory usage both free and allocated in memory order.  Prints to standard out one line for each memory chunk.  Both allocated and free chunks are printed.  Each line contains the status of the chunk (allocated or free).  The format for a free chunk is: free <starting memory location> <ending memory location>  The format for an allocated chunk is: in use <id> <starting memory location> <ending memory location>
	 */
    public void print(){
        Chunk current = head;
        int location = 0;
        while(current != null){
            int endingLocation = location + current.size;
            String out = current.id == -1 ? "free" : "in use " + current.id;
            out+= " " + location + " " + endingLocation;
            System.out.println(out);
            current = current == null ? current : current.next;
            location = endingLocation;
        }
    }
}
