package application;


public class DataObject {
    String name;
    double data[]= new double[64];
    public void setDataInColumn(double data,int column){
        this.data[column]=data;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double[] getData() {
        return data;
    }
    public void setData(double[] data) {
        this.data = data;
    }

}
