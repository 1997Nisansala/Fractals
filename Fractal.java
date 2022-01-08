/*
	CO225 - Software Constructions
	Project 1 : Fractals
	E/17/212 - Morais K.W.G.A.N.D.
	Date : 06/12/2020
*/


public class Fractal {
	private static double minReal, maxReal,minComplex,maxComplex,realj,complexj;
	private static int iterations;
	private static int threadCount =  10;
	
	public static void main(String[] args) {
		int argCount = args.length;	//argument count
		PanelEx j2 = new PanelEx();
		Thread [] t1 = new Thread[threadCount];
		Mandelbrot[] m1 = new Mandelbrot[threadCount];
		Julia [] j1 = new Julia[threadCount];

		if(argCount<1) { //error message for not giving fractal set name
			System.out.println("Please specify the set");
			return;
		}
		else {
			//Mandelbrot Set
			if(args[0].equalsIgnoreCase("mandelbrot")) {
				if(argCount == 1) {//default
					minReal = -1;
					maxReal = 1;
					minComplex = -1;
					maxComplex = 1;
					iterations = 1000;	
				}
				else if(argCount == 2) {//default + user input iterations
					minReal = -1;
					maxReal = 1;
					minComplex = -1;
					maxComplex = 1;
					try{
						iterations = Integer.parseInt(args[1]);
					}catch(NumberFormatException e){
						System.out.println("Invalid Input");
						return;
					}
				}
				else if(argCount == 5) {
					try{
						minReal = Double.parseDouble(args[1]);
						maxReal = Double.parseDouble(args[2]);
						minComplex = Double.parseDouble(args[3]);
						maxComplex = Double.parseDouble(args[4]);
					}catch(NumberFormatException e){
						System.out.println("Invalid Input");
						return;
					}
					iterations = 1000;
				}
				else if(argCount >= 6) {//if there are more than 6 arguments, it takes only 6 arguments(extra arguments are not considered)
					try{
						minReal = Double.parseDouble(args[1]);
						maxReal = Double.parseDouble(args[2]);
						minComplex = Double.parseDouble(args[3]);
						maxComplex = Double.parseDouble(args[4]);
						iterations = Integer.parseInt(args[5]);
					}catch(NumberFormatException e){
						System.out.println("Invalid Input");
						return;
					}
				}
				else {//error message for not sufficient arguments
					System.out.println("Not sufficient arguments for Mandelbrot");
					return;
				}
				for(int i = 0; i<threadCount;i++){//execute thread by thread
					m1[i] = new Mandelbrot(minReal, maxReal, minComplex, maxComplex, iterations, i+1, threadCount);//creating object from MandelbrotSet
					t1[i] = new Thread(m1[i]);
					t1[i].start();
					try{
						t1[i].join();//waiting until finished
					}catch(InterruptedException f){


					}
				}
				
				j2.plotFractal("Mandelbrot Set");//plot the set
			}
			//Julia Set
			else if(args[0].equalsIgnoreCase("julia")) {
				if(argCount == 4) {
					try{
						realj = Double.parseDouble(args[1]);
						complexj = Double.parseDouble(args[2]);
						iterations = Integer.parseInt(args[3]);
					}catch(NumberFormatException e){
						System.out.println("Invalid Input");
						return;
					}

					
				}
				else if(argCount >= 3) {
					try{
						realj = Double.parseDouble(args[1]);
						complexj = Double.parseDouble(args[2]);
					}catch(NumberFormatException e){
						System.out.println("Invalid Input");
						return;
					}
					iterations = 1000;
					
				}
				else if(argCount == 2) {//default + (user input) iterations
					realj = -0.4;
					complexj = 0.6;
					try{
						iterations = Integer.parseInt(args[1]);
					}catch(NumberFormatException e){
						System.out.println("Invalid Input for Iterations");
						return;
					}	
				}
				
				else if (argCount == 1){//default
					realj = -0.4;
					complexj = 0.6;
					iterations = 1000;
					
				}
				else{	//error message for not sufficient arguments
					System.out.println("Not sufficient arguments for Julia Set");
					return ;
				}
				for(int i = 0; i<threadCount;i++){//execute thread by thread
					j1[i] = new Julia(realj,complexj,iterations,i+1,threadCount);//creating object from Julia 
					t1[i] = new Thread(j1[i]);
					t1[i].start();
					try{
						t1[i].join();//waiting until finished
					}catch(InterruptedException f){


					}
				}
				
				j2.plotFractal("Julia Set");//plot the set
			}
			else {
				System.out.println("Enter correct fractal name");
				return;
			}
		}

	}
}

