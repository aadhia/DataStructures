public class TapeSorter {

    private int memorySize;
    private int tapeSize;
    public int[] memory;

    public TapeSorter(int memorySize, int tapeSize) {
        this.memorySize = memorySize;
        this.tapeSize = tapeSize;
        this.memory = new int[memorySize];
    }

    public void quicksort(int size) {
    	quicksort(memory, 0, size-1);
    }
    
    private void quicksort(int[] array, int first, int last){
    	if(first >= last){return;}
    	int pivot;
    	
    	int middle = (first + last)/2;
    	int i = first-1;
    	int j = last;
    	if((array[first] <= array[middle] && array[middle] < array[last])||(array[last] < array[middle] && array[middle] <= array[first])){
    		pivot = array[middle];
    		swapReferences(array, middle, last);
    	}
    	else if ((array[middle] < array[first] && array[first] < array[last])||(array[last] < array[first] && array[first] < array[middle])){
    		pivot = array[first];
    		swapReferences(array, first,last);
    	}
    	else {
    		pivot = array[last];
    	}
    	
    	while(true){
    		while( array[++i] < pivot && i < memorySize){}
    		while( array[--j] > pivot && j > 0){}
    		if(i < j){
    			swapReferences(array, i,j);
    		}
    		else{
    			break;
    		}
    	}
    	swapReferences(array, i, last);
    	quicksort(array, i+1, last);
    	quicksort(array, first, i-1);
    }
    
    public void swapReferences(int[] array, int a, int b){
    	int temp = array[a];
    	array[a] = array[b];
    	array[b] = temp;
    }
    public void initialPass(TapeDrive in, TapeDrive out1, TapeDrive out2) {
    	boolean tape1 = true;
    	int passes = tapeSize/memorySize;
    	int remains = tapeSize%memorySize;
    	for (int i = 0; i < passes; i ++){
    		for(int j = 0; j< memorySize; j++){
    			memory[j] = in.read();
    		}
    		quicksort(memorySize);
    		if(tape1){
    			for( int j = 0; j < memorySize; j++){
    				out1.write(memory[j]);
    			}
    			tape1 = !tape1;
    		}
    		else{
    			for( int j = 0; j < memorySize; j++){
    				out2.write(memory[j]);
    			}
    			tape1 = !tape1;
    		}
    	}
    	for( int j = 0; j < remains; j++){
    		memory[j] = in.read();
    	}
    	quicksort(remains);
    	if(tape1){
			for( int j = 0; j < remains; j++){
				out1.write(memory[j]);
			}
			tape1 = !tape1;
		}
		else{
			for( int j = 0; j < remains; j++){
				out2.write(memory[j]);
			}
			tape1 = !tape1;
		}
    }

    public void mergeChunks(TapeDrive in1, TapeDrive in2, TapeDrive out, int size1, int size2) {
    	int pointer1 = 0;
        int pointer2 = 0;
        int value1 = in1.read();
        int value2 = in2.read();
        while(pointer2 < size2 || pointer1 <size1){
	        if (value2 < value1 ){
	        	if(pointer2 < size2){
	            	out.write(value2);
	            	pointer2++;
	            	if(pointer2 < size2){value2=in2.read();	}
	        	}
	        	else if(pointer1 < size1){
	        		out.write(value1);
	            	pointer1++;
	            	if(pointer1 < size1){value1=in1.read();}
	        	}
	        }
	        else{
	        	if(pointer1< size1){
	        		out.write(value1);
	            	pointer1++;
	            	if(pointer1 < size1){value1=in1.read();	}
	        	}
	        	else if(pointer2< size2){
	        		out.write(value2);
	            	pointer2++;
	            	if(pointer2 < size2){value2=in2.read();	}
	        	}
	        }
        }
    }

    public void doRun(TapeDrive in1, TapeDrive in2, TapeDrive out1, TapeDrive out2, int runNumber) {
    	in1.reset();
    	in2.reset();
    	int chunkSize = (int) (memorySize * Math.pow(2, runNumber));
        int fullChunk = tapeSize/(chunkSize*2);
        int leftoverSize = tapeSize - 2*chunkSize*fullChunk;
        int chunkSize1;
        int chunkSize2;
        boolean tape1 = true;
        for( int i = 0; i < fullChunk; i ++){
        	if(tape1){
        		mergeChunks(in1, in2, out1, chunkSize, chunkSize);	
        		tape1 = !tape1;
        	}
        	else{
        		mergeChunks(in1, in2, out2, chunkSize, chunkSize);
        		tape1 = !tape1;
        	}
        }
        if(leftoverSize > 0){
	        if(leftoverSize <= chunkSize){
	        	chunkSize1 = leftoverSize;
	        	chunkSize2 = 0;
	        }
	        else{
	        	chunkSize1 = chunkSize;
	        	chunkSize2 = leftoverSize-chunkSize;
	        }
	        if(tape1){
	    		mergeChunks(in1, in2, out1, chunkSize1, chunkSize2);	
	    		tape1 = !tape1;
	    	}
	    	else{
	    		mergeChunks(in1, in2, out2, chunkSize1, chunkSize2);
	    		tape1 = !tape1;
	    	}
        }
        
    }

    public void sort(TapeDrive t1, TapeDrive t2, TapeDrive t3, TapeDrive t4) {
        initialPass(t1, t2, t3);
        t1.reset();
        int run;
        for (run = 0; memorySize*Math.pow(2, run) < tapeSize; run++){
        	if(run % 2 == 0){
        		doRun(t2,t3,t1,t4,run);
        		t2.reset();
        		t3.reset();
        	}
        	else{
        		doRun(t1,t4,t2,t3,run);
        		t1.reset();
        		t4.reset();
        	}
        }
        if(run % 2 == 0){
        	for(int k = 0; k < tapeSize; k ++){
        		t1.write(t2.read());
        	}
        }
    }

    public static void main(String[] args) {
        // Example of how to test
        TapeSorter tapeSorter = new TapeSorter(10, 80);
        TapeDrive t1 = TapeDrive.generateRandomTape(80);
        TapeDrive t2 = new TapeDrive(80);
        TapeDrive t3 = new TapeDrive(80);
        TapeDrive t4 = new TapeDrive(80);

        tapeSorter.sort(t1, t2, t3, t4);
        int last = Integer.MIN_VALUE;
        boolean sorted = true;
        for (int i = 0; i < 12; i++) {
            int val = t1.read();
            sorted &= last <= val;
            last = val;
        }
        if (sorted)
            System.out.println("Sorted!");
        else
            System.out.println("Not sorted!");
    }

}