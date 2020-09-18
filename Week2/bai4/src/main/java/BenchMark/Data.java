package BenchMark;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private int res, ref;
    private double time, max, min = Double.MAX_VALUE;
    private final List<Double> timeReq;

    public Data(){

        timeReq = new ArrayList<>();
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
    public void addResult(double tpr) {
        timeReq.add(tpr);
    }

    public List<Double> getTimeRequests() {
        return timeReq;
    }
}
