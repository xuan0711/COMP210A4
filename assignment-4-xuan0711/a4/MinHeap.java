package a4;

public class MinHeap implements Heap {
  
  private int size = 0; // number of elements currently in the heap
  private int[] elts;   // heap array
  private int max;      // array declared size
  
  // ================================================
  // constructors
  // ================================================
  
  public MinHeap(int umax) { // user defined heap size
    this.max = umax;
    this.elts = new int[umax];
  }
  public MinHeap( ) { // default heap size is 100
    this.max = 100;
    this.elts = new int[100];
  }

  //==================================================
  // methods we need to grade
  //==================================================
  
  public int[] getArray() { // do not change this method
    return this.elts;
  }
  
  //=========================================================
  // public methods -- Implement these for the assignment.
  // Note that we want a Min Heap... so the operations
  // getFront and delFront and insert have to compare 
  // for min being at the root  
  //========================================================= 


  /*public void insert(int p) {
    //Hint: remember to update size.  Also, remember that we skip index 0 in the array.
    if (this.size >= this.max){return;}
    for(int i=this.size+1;i>0;i=i/2){
      if(this.elts[i/2]>p) {
        this.elts[i]=this.elts[i/2];
        this.elts[i/2]=p;

      }else{
        this.elts[i]=p;
        this.size++;
        return;

      }

    }
  }
  */

  public void insert(int p) {
    //Hint: remember to update size.  Also, remember that we skip index 0 in the array.
    if (this.size >= this.max){return;}
    size+=1;
    elts[size]=p;
    for(int i=size;i>0;i=i/2){
      if(elts[i]<elts[i/2]){
        elts[i]=elts[i/2];
        elts[i/2]=p;
      }
    }

  }

  public void delFront(){
    /*Your code here */
    int smallC=0;
    int ind=0;
    if(this.size == 0) {return;}
    for (int i=1; this.elts[2*i]!=0&&this.elts[2*i+1]!=0; i=ind){
      if(this.elts[2*i]>=this.elts[2*i+1]){
        smallC = this.elts[2*i+1];
        ind=2*i+1;
      }else{
        smallC = this.elts[2*i];
        ind=2*i;
      }
      this.elts[i] = smallC;
    }

    if(this.elts[2*ind]!=0){
      this.elts[ind]=this.elts[2*ind];
    }
    if(this.elts[2*ind]==0 && ind<this.size-1){
      this.elts[ind]=this.elts[this.size];
    }
    this.elts[this.size]=0;
    this.size--;
    return;
  }


  public int getFront() throws IllegalStateException {
    //Return the element at the front (i.e., the smallest) element in the min-heap.
    //If the min-heap has no elements, throw an IllegalStateException.
    /*Your code here */
    if(this.size==0){
      throw new IllegalStateException();
    }else{
      return this.elts[1];
    }
  }

  public boolean empty( ) {
    /*Your code here */
    if(this.size == 0){
      return true;
    }else{
      return false;
    }
  }

  public int size() {
    /*Your code here */
    int count = 0;
    for(int i=1; this.elts[i]!=0; i++){
      count=count+1;
    }
    return count;
  }

  public void clear() {
    /*Your code here */
    int s=this.size;
    for(int i=1; i<=s;i++){
      this.delFront();
    }
  }

  public void build (int[] e, int ne) {
    //Hint: remember to skip slot 0 in the heap array.
    /* Your code here */
    this.clear();
    this.size = ne;
    for(int i=1; i<=ne; i++){
      this.elts[i]=e[i-1];
    }
    int chi=0;
    int par=0;
    int ind=0;
    int ind2=0;
    for(int i=ne; i>=1 ;i=ind2){
      par=this.elts[i/2];
      if(i%2==0){
        chi=this.elts[i];
        ind=i;
        ind2=i-1;
      }else{
        if(this.elts[(i/2)*2+1]>=this.elts[(i/2)*2]){
          chi=this.elts[(i/2)*2];
          ind=(i/2)*2;
          ind2=ind-1;
        }else{
          chi=this.elts[(i/2)*2+1];
          ind=(i/2)*2+1;
          ind2=ind-2;
        }
      }
      if(chi<par){
        this.elts[ind]=par;
        this.elts[i/2]=chi;
      }
      int leaf=0;
      if(this.elts[2*ind+1]!=0&&this.elts[2*ind]!=0){
        if(this.elts[ind]>this.elts[2*ind+1]||this.elts[ind]>this.elts[2*ind]){
          if(this.elts[2*ind+1]>=this.elts[2*ind]){
            leaf=this.elts[2*ind];
            this.elts[2*ind]= this.elts[ind];
            this.elts[ind]=leaf;
          }else{
            leaf=this.elts[2*ind+1];
            this.elts[2*ind+1]= this.elts[ind];
            this.elts[ind+1]=leaf;
          }
        }
      }else if(this.elts[2*ind]!=0){
        if(this.elts[2*ind]<this.elts[ind]){
          leaf=this.elts[2*ind];
          this.elts[2*ind]= this.elts[ind];
          this.elts[ind]=leaf;
        }
      }
    }
  }

  public int[] sort() {
    // Hint: the smallest element will go in slot 0
    /*Your code here */
    int[] sorted = new int[size];
    for(int i=0; i<size;i++){
      sorted[i]=elts[i+1];
    }
    int temp;
    for(int i=0; i<=size;i++){
      for(int j=i+1;j<size;j++){
        if(sorted[i]>sorted[j]){
          temp = sorted[i];
          sorted[i]=sorted[j];
          sorted[j]=temp;
        }
      }
    }
    return sorted;
  }
}
