
public class MinMaxHeap {
	private int currentSize;
	private int[] arr;
	
	public MinMaxHeap(int capacity){//Constructor
		arr = new int[capacity + 1];
		currentSize = 0;
	}
	public boolean isFull(){return currentSize == arr.length - 1;}
	public boolean isEmpty(){return currentSize == 0;}

	// COMPLETE THE FOLLOWING METHODS
	public void insert(int x){
		arr[++currentSize] = x;
		BubbleUp(currentSize);
	}//PRE: The heap is not full
	
	public int min(){
		return arr[1];
	}//PRE: The heap is not empty
	public int max(){
		if(arr[3]>arr[2]){
			return arr[3];
		}
		else{
			return arr[2];
		}
	}//PRE: The heap is not empty
	public int deleteMin(){
		int min = min();
		arr[1] = arr[currentSize--];
		TrickleDown(1);
		return min;
	}//PRE: The heap is not empty
	public int deleteMax(){
		int max;
		if(arr[3]>arr[2]){
			max = arr[3];
			arr[3] = arr[currentSize--];
			TrickleDown(3);
		}
		else{
			max = arr[2];
			arr[2] = arr[currentSize--];
			TrickleDown(2);
		}
		return max;
	}//PRE: The heap is not empty
	//Private methods go here.
	private void TrickleDown(int i){
		int isOdd = ((int)(Math.log(i)/Math.log(2)))%2;
		if( isOdd ==1){
			TrickleDownMax(i);
		}
		else{
			TrickleDownMin(i);
		}
	}
	
	private void BubbleUp(int i){
		int isOdd = ((int)(Math.log(i)/Math.log(2)))%2;
		if( isOdd ==1){
			if(i != 1 && arr[i] < arr[i/2]){
				int tmp = arr[i];
				arr[i] = arr[i/2];
				arr[i/2]=tmp;
				BubbleUpMin(i/2);
			}
			else{
				BubbleUpMax(i);
			}
		}
		else{
			if(i != 1 && arr[i] > arr[i/2]){
				int tmp = arr[i];
				arr[i] = arr[i/2];
				arr[i/2]=tmp;
				BubbleUpMax(i/2);
			}
			else{
				BubbleUpMin(i);
			}
		}
	}
	
	private void TrickleDownMin(int i){
		int minChild;
		int children = currentSize - 2*i + 1;
		if(children>2){children = 2;}
		int grandchildren = currentSize - 4*i + 1;
		if(grandchildren>4){grandchildren = 4;}
		if(children>0){
			minChild = 2*i;
			if(arr[minChild+1]<arr[minChild]){
				minChild = 2*i + 1;
			}
			for(int j = 0; j<grandchildren; j++){
				if(arr[4*i+j-1]<arr[minChild]){
					minChild = 4*i +j - 1;
				}
			}
			if(minChild >= 4*i){
				if(arr[minChild]<arr[i]){
					int tmp = arr[minChild];
					arr[minChild] = arr[i];
					arr[i] = tmp;
					if(arr[minChild]>arr[minChild/2]){
						tmp = arr[minChild];
						arr[minChild] = arr[minChild/2];
						arr[minChild/2] = tmp;
					}
					TrickleDownMin(minChild);
				}
			}
			else if(minChild >= 2*i){
				if(arr[minChild]<arr[i]){
					int tmp = arr[minChild];
					arr[minChild] = arr[i];
					arr[i] = tmp;
				}
			}
		}
	}
	
	private void TrickleDownMax(int i){
		int maxChild;
		int children = currentSize - 2*i + 1;
		if(children>2){children = 2;}
		int grandchildren = currentSize - 4*i + 1;
		if(grandchildren>4){grandchildren = 4;}
		if(children>0){
			maxChild = 2*i;
			if(arr[maxChild+1]>arr[maxChild]){
				maxChild = 2*i + 1;
			}
			for(int j = 0; j<grandchildren; j++){
				if(arr[4*i+j-1]>arr[maxChild]){
					maxChild = 4*i +j - 1;
				}
			}
			if(maxChild >= 4*i){
				if(arr[maxChild]>arr[i]){
					int tmp = arr[maxChild];
					arr[maxChild] = arr[i];
					arr[i] = tmp;
					if(arr[maxChild]<arr[maxChild/2]){
						tmp = arr[maxChild];
						arr[maxChild] = arr[maxChild/2];
						arr[maxChild/2] = tmp;
					}
					TrickleDownMax(maxChild);
				}
			}
			else if(maxChild >= 2*i){
				if(arr[maxChild]>arr[i]){
					int tmp = arr[maxChild];
					arr[maxChild] = arr[i];
					arr[i] = tmp;
				}
			}
		}
	}

	private void BubbleUpMin(int i){
		if(i/4 !=0){
			if(arr[i] < arr[i/4]){
				int tmp = arr[i];
				arr[i] = arr[i/4];
				arr[i/4] = tmp;
				BubbleUpMin(i/4);
			}
		}
	}
	
	private void BubbleUpMax(int i){
		if(i/4 !=0){
			if(arr[i] > arr[i/4]){
				int tmp = arr[i];
				arr[i] = arr[i/4];
				arr[i/4] = tmp;
				BubbleUpMax(i/4);
			}
		}
	}
}
