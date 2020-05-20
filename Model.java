import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Model {

	public static final int WIDTH = 640, HEIGHT = 480;
	private double b;
    private int i,j;
    private boolean sorted;
	private int[] num;
	private Rectangle[] rectangles;

	public Model() {
        this.sorted = false;
    }

    public boolean isSorted() {
        return this.sorted;
    }

    public void setSorted(boolean bool) {
        this.sorted = bool;
    }

	public void createRect(GraphicsContext context, int n) {

		context.setFill(Color.LIGHTBLUE);
		context.fillRect(0, 0, WIDTH, HEIGHT);

		rectangles = new Rectangle[n];
		b = 1200/(3*n-1);     //width of a rectangle

		for (int j=0; j<n; j++) {
			rectangles[j] = new Rectangle(20+3*j*b/2,b,10*(int)(37*Math.random())+10);
			rectangles[j].draw(context);
		}
	}

	public void sort(int k, int i) {

		if (k == 0)
			insertion(i);
		if (k == 1)
			selection(i);
		if (k == 2)
			bubble(i);
/*		if (k == 3)
			merge(context);
		if (k == 4)
			quick(context);
		if (k == 5)
			heap(context);*/
	}

	void insertion(int i) {

        if (i < rectangles.length - 1) {
            i++;
            for (Rectangle r : rectangles)
                r.setColor(Color.hsb(30,0.75,1));

            int keyH = rectangles[i].getHauteur();
            int keyY = rectangles[i].getY();
            rectangles[i].setColor(Color.hsb(100,0.75,1));
            j = i - 1;

    		while (j >= 0 && rectangles[j].getHauteur() > keyH) {
                rectangles[j + 1].setHauteur(rectangles[j].getHauteur());
                rectangles[j + 1].setY(rectangles[j].getY());
    			j = j - 1;
    		}
    		rectangles[j + 1].setHauteur(keyH);
            rectangles[j + 1].setY(keyY);
            rectangles[j + 1].setColor(Color.hsb(100,0.75,1));
        }
        else
            this.sorted = true;
	}

	void selection(int i) {

		if (i < rectangles.length) {
			int min_idx = i;
            for (Rectangle r : rectangles)
                r.setColor(Color.hsb(30,0.75,1));

			for (int j = i+1; j < rectangles.length; j++)
				if (rectangles[j].getHauteur() < rectangles[min_idx].getHauteur())
					min_idx = j;

			int tempH = rectangles[min_idx].getHauteur();
            int tempY = rectangles[min_idx].getY();
			rectangles[min_idx].setHauteur(rectangles[i].getHauteur());
            rectangles[min_idx].setY(rectangles[i].getY());
            rectangles[min_idx].setColor(Color.hsb(100,0.75,1));
			rectangles[i].setHauteur(tempH);
            rectangles[i].setY(tempY);
            rectangles[i].setColor(Color.hsb(100,0.75,1));
		}
        else
            this.sorted = true;
	}

	void bubble(int i) {

		if (i < rectangles.length) {
            for (Rectangle r : rectangles)
                r.setColor(Color.hsb(30,0.75,1));

			for (int j = 0; j < rectangles.length-i-1; j++)
				if (rectangles[j].getHauteur() > rectangles[j+1].getHauteur()) {
					int tempH = rectangles[j].getHauteur();
                    int tempY = rectangles[j].getY();
					rectangles[j].setHauteur(rectangles[j+1].getHauteur());
                    rectangles[j].setY(rectangles[j+1].getY());
                    rectangles[j].setColor(Color.hsb(100,0.75,1));
					rectangles[j + 1].setHauteur(tempH);
                    rectangles[j + 1].setY(tempY);
                    rectangles[j + 1].setColor(Color.hsb(100,0.75,1));
				}
        }
        else
            this.sorted = true;
	}
/*
    void combine(int arr[], int l, int m, int r) { 
        
        int n1 = m - l + 1; 
        int n2 = r - m; 

        int L[] = new int [n1]; 
        int R[] = new int [n2]; 

        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 

        int i = 0, j = 0; 

        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 

        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 

        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    
    
    void merge(int arr[], int l, int r) {
        
        if (l < r) { 

            int m = (l+r)/2; 
  
            merge(arr, l, m); 
            merge(arr , m+1, r); 

            combine(arr, l, m, r); 
        } 
    } 

	int partition(int arr[], int low, int high) {
        int pivot = arr[high];  
        int i = (low-1);
        for (int j=low; j<high; j++) { 
            
            if (arr[j] < pivot) 
            { 
                i++; 
  
                
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 

    void quick(int[] num, int low, int high) {
        
        if (low < high) {

            int pi = partition(arr, low, high); 

            quick(num, low, pi-1); 
            quick(num, pi+1, high); 
        } 
    }

    void heapify(int arr[], int n, int i) 
    { 
        int largest = i; 
        int l = 2*i + 1; 
        int r = 2*i + 2; 
  
        
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        
        if (largest != i) {
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
  
            
            heapify(arr, n, largest); 
        } 
    } 

	void heap(int arr[]) { 
        int n = arr.length; 
  
        
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr, n, i); 
  
        
        for (int i=n-1; i>0; i--) { 
            
            int temp = arr[0]; 
            arr[0] = arr[i];
            arr[i] = temp; 

            heapify(arr, i, 0); 
        } 
    }*/

    public void draw(GraphicsContext context) {
    	context.setFill(Color.LIGHTBLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        for (Rectangle rect : rectangles)
            rect.draw(context);
    }
}