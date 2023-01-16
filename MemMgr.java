// keeps track of memory using some variety of linked list, cannot use java.util.ArrayList or
// any collections that hold multiple objects

public class MemMgr implements MMInterface{
    /**
	 * This method sets the initial amount of memory available.  Immediately after init is called all of the memory is available and is in a single large chunk.
	 */
    public void init(int size){
        // set initial amount of memory available

        // all of the memory should be available in a single large chunk
    }

    /**
	 * This sets aside a chunk of memory for use.  Once malloc'd it is not available until it is freed.  The id will be used by the free call to identify which chunk of memory to free.  The call will set aside the amount of memory specified by size.  It will use the "First Fit" approach.  That is it will start at memory location zero and check until it finds an unused chunk of memory that is at least as large as the size requested.  When this chunk is found it will mark it as being in use, and associate the id with the chunk.  If there is more memory than requested, the additional memory remains available for future malloc requests.  The malloc always returns the entire amount requested, never a partial amount.  The chunk never goes past the upper limit of the memory specified in the init request.  If there is not enough memory available throw an MyOutOfMemoryException, create a subclass to Exception for this.
	 */
    public void malloc(int id, int size) throws MyOutOfMemoryException{
        // start at memory location zero
        // check until find an unused chunk of memory at least as large as the size requested
            // mark as being in use, associate id with chunk

        // if not enough memory available, throw MyOutOfMemoryException
        // store id for use by free call?
    }

    /**
	 * Find the chunk of memory associated with this id and make it available for reuse.  These things should happen to make it available for reuse:  disassociate the id from this chunk of memory, mark the chunk of memory as available for use, coalesce this chunk of memory with any adjacent chunks that are available, so that they become one large chunk of available memory.  Completely coalesce all the memory every time a chunk is freed.  If a chunk of memory with the requested id can not be found throw a MyInvalidMemoryException, create a subclass to Exception for this.
	 */
    public void free(int id) throws MyInvalidMemoryException{
        // find chunk of memory associated with id
            // dissociate id from chunk
            // mark as available for use
            // coalesce chunk with any adjacent available chunks
        // if cannot find chunk w/ id, throw MyInvalidMemoryException
    }

    /**
	 * Print out all the memory usage both free and allocated in memory order.  Prints to standard out one line for each memory chunk.  Both allocated and free chunks are printed.  Each line contains the status of the chunk (allocated or free).  The format for a free chunk is: free <starting memory location> <ending memory location>  The format for an allocated chunk is: in use <id> <starting memory location> <ending memory location>
	 */
    public void print(){
        // go through linked list of chunks
            // if free, print using free chunk format
            // if allocated, print using allocated chunk format
    }
}
