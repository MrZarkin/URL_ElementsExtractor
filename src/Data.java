public class Data {
    
    float min, max, moy, total;
    int count;
    
    public Data()
    {
        this.min = 0;
        this.max = 0;
        this.moy = 0;
        this.count = 0;
        this.total = 0;
    }
    
    
    public float getMin() {
        return min;
    }
    public float getMax() {
        return max;
    }
    public float getMoy() {
        return moy;
    }
    public int getCount() {
        return count;
    }
    public float getTotal() {
        return total;
    }
    
    
    public void setMin(float min) {
        this.min = min;
    }
    public void setMax(float max) {
        this.max = max;
    }
    public void setMoy(double moy) {
        this.moy = (float) moy;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setTotal(float number) {
        this.total = number;
    }



    public void addTotal(float number) {
        this.total += number;
    }
    
}