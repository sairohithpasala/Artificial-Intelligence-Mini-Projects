import java.util.ArrayList;

/**@author Gopi Krishna
 * UTA ID:1001677986*/

public class bnet {
	static double B=0.001; 
	static double E=0.002;
	static ArrayList<String> array1 = new ArrayList<String>();
	static ArrayList<String> array2 = new ArrayList<String>();
	static double A[]= {0.95,0.94,0.29,0.001};
	static double J[]= {0.90,0.05};
	static double M[]= {0.70,0.01};

	public static void main(String[] args) {
	
		int BCounter=0,ECounter=0,ACounter=0,JCounter=0,MCounter=0;
		if (args.length <1 || args.length > 6) { 
			System.exit(0);
		}
		int index =-1;
		for(int i=0;i<args.length;i++) { 
			if(args[i].equals("given")){
				index=0;
				continue;
			}if(index==-1) {
				array1.add(args[i]);
			}
			else
			{
				array2.add(args[i]);
			}

		}	
		if (array1.size() <1 || array1.size() > 6) { 
			System.exit(0);
		}
		if(index==0) {
			if (array2.size() <1 || array2.size() > 4) {
				System.exit(0);
			}		
		}

		System.out.println(array1 + "given" + array2);
		array1.addAll(array2); 
		
		for(int i=0;i< array1.size();i++) {  
			
			if (!array1.contains("Bt")&&!array1.contains("Bf")) {
				array1.add("Bt");
				array1.add("Bf");
				BCounter=1;
			}
			if (!array1.contains("Et")&&!array1.contains("Ef")) {
				array1.add("Et");
				array1.add("Ef");
				ECounter=1;
			}
			if (!array1.contains("At")&&!array1.contains("Af")) {
				array1.add("At");
				array1.add("Af");
				ACounter=1;}
			if (!(array1.contains("Jt"))&&!array1.contains("Jf")) {
				array1.add("Jt");
				array1.add("Jf");
				JCounter=1;
			}
			if (!array1.contains("Mt")&&!array1.contains("Mf")) {
				array1.add("Mt");
				array1.add("Mf");
				MCounter=1;}}

		double numerator = callCompute(BCounter, ECounter, ACounter, JCounter, MCounter, array1);
		if(array2.size()==0) {
			System.out.println("Computed Probability: "+numerator);
		}
		BCounter=ECounter=ACounter=JCounter=MCounter=0;
		for(int j=0;j< array2.size();j++) {
				
			if (!array2.contains("Bt")&&!array2.contains("Bf")) {
				array2.add("Bt");
				array2.add("Bf");
				BCounter=1;}
			if (!array2.contains("Et")&&!array2.contains("Ef")) {
				array2.add("Et");
				array2.add("Ef");
				ECounter=1;}
			if (!array2.contains("At")&&!array2.contains("Af")) {
				array2.add("At");
				array2.add("Af");
				ACounter=1;}
			if (!array2.contains("Jt")&&!array2.contains("Jf")) {
				array2.add("Jt");
				array2.add("Jf");
				JCounter=1;}
			if (!array2.contains("Mt")&&!array2.contains("Mf")) {
				array2.add("Mt");
				array2.add("Mf");
				MCounter=1;}}
		double denominator = callCompute(BCounter, ECounter, ACounter, JCounter, MCounter, array2);
		if(array2.size()>0) {
			System.out.println("Computed Probability: "+numerator/denominator);
		}
		
	}
	

	public static double callCompute(int bc,int ec,int ac,int jc,int mc,ArrayList<String> arrayProc) {
		double probability=0.0;
		Boolean b_bool=false,e_bool=false,a_bool=false,j_bool=false,m_bool=false;
		if(bc==0) {
			if(arrayProc.contains("Bt")) {
				b_bool=true;
			}
			else b_bool=false;
		}
		if(ec==0) {
			if(arrayProc.contains("Et")) {
				e_bool=true;
			}
			else e_bool=false;
		}
		if(ac==0) {
			if(arrayProc.contains("At")) {
				a_bool=true;
			}
			else a_bool=false;
		}
		if(jc==0) {
			if(arrayProc.contains("Jt")) {
				j_bool=true;
			}
			else j_bool=false;
		}
		if(mc==0) {
			if(arrayProc.contains("Mt")) {
				m_bool=true;
			}
			else m_bool=false;
		}
		for(int i1=0;i1<=bc;i1++) {
			for(int i2=0;i2<=ec;i2++) {
				for(int i3=0;i3<=ac;i3++) {
					for(int i4=0;i4<=jc;i4++) {
						for(int i5=0;i5<=mc;i5++) {
							probability+=computeProbability(b_bool, e_bool, a_bool, j_bool, m_bool);
							if(mc==1 && m_bool==false) m_bool=true;
							else if(mc==1 && m_bool==true) m_bool=false;
						}
						if(jc==1 && j_bool==false) j_bool=true;
						else if(jc==1 && j_bool==true) j_bool=false;
					}
					if(ac==1 && a_bool==false) a_bool=true;
					else if(ac==1 && a_bool==true) a_bool=false;
				}
				if(ec==1 && e_bool==false) e_bool=true;
				else if(ec==1 && e_bool==true) e_bool=false;
			}
			if(bc==1 && b_bool==false) b_bool=true;
			else if(bc==1 && b_bool==true) b_bool=false;
		}
		return probability;
	}
	
	public static double computeProbability(boolean b, boolean e, boolean a, boolean j, boolean m) {
		double Bval=0.0;
		if(b) {
			Bval=B;}
		else {
			Bval = 1-B;}
		double Eval;
		if(e) {
			Eval=E;}
		else {
			Eval = 1-E;}
		double Aval = 0.0;
		if(a) {
			if(b==true && e==true )
				Aval = A[0];
			else if(b==true && e==false )
				Aval = A[1];
			else if(b==false && e==true )
				Aval = A[2];
			else if(b==false && e==false )
				Aval = A[3];}
		else
		{
			if(b==true && e==true )
				Aval = 1-A[0];
			else if(b==true && e==false )
				Aval = 1-A[1];
			else if(b==false && e==true )
				Aval = 1-A[2];
			else if(b==false && e==false )
				Aval = 1-A[3];}

		double Jval = 0.0;
		if(j) {
			if(a==true)
				Jval = J[0];
			else if(a==false )
				Jval = J[1];}

		else
		{
			if(a==true)
				Jval =1-J[0];
			else if(a==false )
				Jval =1-J[1];}

		double Mval = 0.0;
		if(m) {
			if(a==true)
				Mval = M[0];
			else if(a==false )
				Mval = M[1];}

		else
		{
			if(a==true)
				Mval =1-M[0];
			else if(a==false )
				Mval =1-M[1];
		}
		return Bval*Eval*Aval*Jval*Mval; 
	}

}