
public class Mandelbrot extends PanelEx implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double minReal, maxComplex,scale1,scale2;
	private int iterations,start,end;
	
	public Mandelbrot(double real1, double real2, double real3, double real4, int count,int threadstate, int threadCount) {//constructor
		this.minReal = real1;
		this.maxComplex = real4;
		this.iterations = count;
		this.scale1 = (double) Math.abs(real2 - real1)/800.0 ;
		this.scale2 = (double) Math.abs(real4 - real3)/800.0 ;
		this.start = (800/threadCount)*(threadstate-1);//starting real axis value of thread
		this.end = (800/threadCount)*(threadstate);//end value in real axis of thread
	}
	
	public void run() {
		for(int x = start; x < end; x++){//looping through real axis
			for(int y=0; y < 800; y++) {//looping through imaginary axis
				double a1 = minReal + x*scale1;//real part in complex plane
				double a2 = maxComplex - y*scale2;//complex part in complex plane
				double cx = minReal + x*scale1;
				double cy = maxComplex - y*scale2;;	
				int i ;
				for(i = 0;i<iterations;i++) {
					double nx = (double) (a1*a1 - a2*a2 +cx);
					double ny = (double) (2*a1*a2 +cy);
					a1 = nx;
					a2 = ny;
					if(a1*a1 + a2*a2 > 4) break;
				}
				if(i == iterations){//points in fractal set
					Set(x,y);
				}
				else{//points not in fractal set
					NotSet(x,y,i);	
				}	 
			}
		}
	}
}
