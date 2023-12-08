package Years.Y2015.Day_02;

public class Gift {

    int l, w, h;
    int sizeSmallestSide;
    int surfaceArea;
    int ribbonLen;
    int bowLen;

    public Gift(int pL, int pW, int pH) {
        this.l = pL;
        this.w = pW;
        this.h = pH;
        findSmallestSide();
        calcSurfaceArea();
        setRibbon();
        setBow();
    }

    private void findSmallestSide() {
        sizeSmallestSide = Math.min(l*w, Math.min(l*h, w*h));
    }

    private void calcSurfaceArea() {
        surfaceArea = 2*l*w + 2*l*h + 2*w*h;
    }

    private void setRibbon() {
        if (Math.min(l, Math.min(w, h)) == l) ribbonLen = 2*l+2*Math.min(w, h);
        else if (Math.min(l, Math.min(w, h)) == w) ribbonLen = 2*w+2*Math.min(l, h);
        else ribbonLen = 2*h+2*Math.min(l, w);
    }

    private void setBow() {
        bowLen = l*w*h;
    }
}
