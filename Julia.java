
public class Julia extends PanelEx implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double minComplex,maxComplex,scale;
	private int iterations,start,end;

	public Julia(double complex1, double complex2, int count,int threadstate, int threadCount) {//constructor
		this.minComplex = complex1;
		this.maxComplex = complex2;
		this.iterations = count;
		this.scale = (double) 400.0;
		this.start = (800/threadCount)*(threadstate-1);//statrting real axis value of thread
		this.end = (800/threadCount)*(threadstate);//end value in real axis of thread
	}

	public void run() {
		for(int x = start; x < end; x++){//looping through real axis
			for(int y=0; y < 800; y++) {//looping through imaginary axis
				double a1 = (x - 400)/scale;//real part
				double a2 = (y - 400)/scale;//complex part
				double cx = (double) minComplex;
				double cy = (double) maxComplex ;	
				int i ;
				for(i = 0;i<iterations;i++) {
					double nx = (double) (a1*a1 - a2*a2 +cx);
					double ny = (double) (2*a1*a2 - cy);
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
