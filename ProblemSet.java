package EBPSO;

import java.io.*;

/**
 *
 * @author wissem
 */

public class ProblemSet {
        
        public static final double VEL_LOW = -1;
	public static final double VEL_HIGH = 1;
	
	public static final double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result, 
	                                                  // but the number of iteration is increased
	public static float boolToInt(boolean b) {
            return b ? 1 : 0;
        }
          
        static boolean feasibility(float[][] mat, float hq, String b)
        {
            boolean test=false;
            float total_capacity=0;
            for(int i=0;i<b.length();i++)
                if(b.charAt(i)=='1')
                total_capacity+=mat[i][0];
            if(total_capacity>=hq)
                test=true;
            //System.out.println("Capacité: "+total_capacity+" ");


            return test;
        }

        public static float final_cost(float[][] mat1, float[][] mat2, float a, float b1, float b2)
        {

            int i,j;
            float final_cost,cost,icc,occ;

            final_cost=0;

            for(i=0;i<mat1.length;i++)
                {
                    icc=0;
                    occ=0;
                    if(mat1[i][1]==1)
                    {
                    for(j=0;j<mat1.length;j++)
                    {
                        if(mat1[j][1]==1 && j!=i)
                            icc+=mat2[i][j];
                        else
                            if(mat1[j][1]==0 && j!=i)
                            occ+=mat2[i][j];    

                    }

                    cost=a*mat1[i][0]+b2*icc/2+b1*occ;
                    final_cost+=cost;
                    }
                }
            return final_cost;
        }

        public static int nb_line (String file )
        {
            int nbl=0;
            //lecture du file texte	
                    try{
                            InputStream ips=new FileInputStream(file); 
                            InputStreamReader ipsr=new InputStreamReader(ips);
                            BufferedReader br=new BufferedReader(ipsr);
                            String line;
                            while ((line=br.readLine())!=null){
                                    //System.out.println(line);
                                    if(line.equalsIgnoreCase("\\begin{ServiceCapacity}"))
                                    {
                                        while((line=br.readLine())!=null && !line.equalsIgnoreCase("\\end") )
                                        {
                                            nbl++;                               
                                        }
                                        //System.out.println("Nombre de line: "+nbl);
                                    }                              
                            }
                            br.close(); 
                    }		
                    catch (Exception e){
                            System.out.println(e.toString());
                    }        
            return nbl;
        }

         public static float[][] read_capacity (String file)
        {
            int nbl=nb_line(file);
            float mat[][] =new float[nbl][2];
            int i,j;
            //System.out.println("Matrice de capacité");
            //lecture du file texte	
                    try{
                        for(i=0;i<mat.length;i++)
                            mat[i][1]=0;
                            InputStream ips=new FileInputStream(file); 
                            InputStreamReader ipsr=new InputStreamReader(ips);
                            BufferedReader br=new BufferedReader(ipsr);
                            String line;

                            while ((line=br.readLine())!=null){
                                    //System.out.println(line);
                                    if(line.equalsIgnoreCase("\\begin{ServiceCapacity}"))
                                    {
                                        //System.out.println("==>");
                                        j=0;
                                        while((line=br.readLine())!=null && !line.equalsIgnoreCase("\\end") )
                                        {
                                            String[] t = line.split(" ");
                                            //for( i = 0; i<t.length; ++i) System.out.println(t[i]);                                          
                                            mat[j][0]=Float.parseFloat(t[2]);
                                            j++;
                                        }
                                    }                            

                            }
                            br.close(); 
                    }		
                    catch (Exception e){
                            System.out.println(e.toString());
                    }
            return mat;
        }

         public static float[][] read_connection (String file)
        {
            int nbl=nb_line(file);
            float mat[][] =new float[nbl][nbl];
            int i,j;
            //System.out.println("Matrice de connexion");
            //lecture du file texte	
                    try{
                            InputStream ips=new FileInputStream(file); 
                            InputStreamReader ipsr=new InputStreamReader(ips);
                            BufferedReader br=new BufferedReader(ipsr);
                            String line;

                            while ((line=br.readLine())!=null){
                                    //System.out.println(line);
                                    if(line.equalsIgnoreCase("\\begin{Serviceconnections}"))
                                    {
                                        j=0;
                                        //System.out.println("==>");
                                        while((line=br.readLine())!=null && !line.equalsIgnoreCase("\\end") )
                                        {
                                            String[] t = line.split(" ");
                                            //for( i = 0; i<t.length; ++i) System.out.println(t[i]);
                                            for(i=0;i<nbl;i++)
                                                mat[j][i]=Float.parseFloat(t[i+1]);
                                            j++;
                                        }
                                    }
                            }
                            br.close(); 
                    }		
                    catch (Exception e){
                            System.out.println(e.toString());
                    }
            return mat;
        }
         
        public static String doubleToChar(Double x)
        {
            int i;
            i=x.intValue();
            
            return(Integer.toString(i));
        }
        
	public static double evaluate(Location location) {
		double result = 0,fc;
		double x = location.getLoc()[0]; // the "x" part of the location
		double x1 = location.getLoc()[1]; // the "y" part of the location
                double x2 = location.getLoc()[2]; // the "x2" part of the location
                double x3 = location.getLoc()[3]; // the "x3" part of the location
                double x4 = location.getLoc()[4]; // the "x4" part of the location
                double x5 = location.getLoc()[5]; // the "x5" part of the location
                double x6 = location.getLoc()[6]; // the "x6" part of the location
                double x7 = location.getLoc()[7]; // the "x7" part of the location
                double x8 = location.getLoc()[8]; // the "x8" part of the location
		double x9 = location.getLoc()[9]; // the "x9" part of the location
                double x10 = location.getLoc()[10]; // the "x" part of the location
		double x11 = location.getLoc()[11]; // the "y" part of the location
                double x12 = location.getLoc()[12]; // the "x2" part of the location
                double x13 = location.getLoc()[13]; // the "x3" part of the location
                double x14 = location.getLoc()[14]; // the "x4" part of the location
                double x15 = location.getLoc()[15]; // the "x5" part of the location
                double x16 = location.getLoc()[16]; // the "x6" part of the location
                double x17 = location.getLoc()[17]; // the "x7" part of the location
                double x18 = location.getLoc()[18]; // the "x8" part of the location
		double x19 = location.getLoc()[19]; // the "x9" part of the location
		/*result = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) + 
				Math.pow(2.25 - x + x * Math.pow(y, 2), 2) + 
				Math.pow(1.5 - x + x * y, 2)+x2+x3+x4+x5+x6+x7+x8+x9;*/
                
                //int nb_population,nb_service,i,j,k,k2,nb1,m,nb_tour;
                int i,j;
                //float hq,hq_,a,b1,b2,h=0;
                float hq,hq_,h=0;
                //float [][] mat1,mat2; 
                boolean r;
        
                //System.out.print("Entrer la valeur en pourcentage de HQ\n");
                //hq_=Clavier.lireFloat();
                //hq_=PSOConstants.HQ;
                
                                
                //a=40;
                //b1=20;
                //b2=10;
                
                //m=10;
                //String file="../ServiceGraph/graph"+m+"_50.txt",b="";
                String b="";
                //System.out.println("*********** "+m+" *************");
        
                //nb_service=nb_line(file);
                //mat1=read_capacity(file);  
                //mat2=read_connection(file);  
                
                for(i=0;i<PSOConstants.mat1.length;i++)
                h+=PSOConstants.mat1[i][0];
                hq=h*PSOConstants.HQ/100;
                
                //b=Long.toBinaryString(x);
                //b=b.substring(0, nb_service);
                //b=Double. .toString(x)+Double.toString(y);
                //b="0101010101";
                b=doubleToChar(x)+doubleToChar(x1)+doubleToChar(x2)+doubleToChar(x3)+doubleToChar(x4)+doubleToChar(x5)+doubleToChar(x6)+doubleToChar(x7)+doubleToChar(x8)+doubleToChar(x9);
                b+=doubleToChar(x10)+doubleToChar(x11)+doubleToChar(x12)+doubleToChar(x13)+doubleToChar(x14)+doubleToChar(x15)+doubleToChar(x16)+doubleToChar(x17)+doubleToChar(x18)+doubleToChar(x19);
                
                //System.out.println(doubleToChar(x));
                //System.out.println(" - "+b);
                for(j=0;j<PSOConstants.PROBLEM_DIMENSION;j++)
                PSOConstants.mat1[j][1]=Float.parseFloat(Character.toString(b.charAt(j)));
                fc=final_cost(PSOConstants.mat1,PSOConstants.mat2,PSOConstants.a,PSOConstants.b1,PSOConstants.b2);
                r=feasibility(PSOConstants.mat1,hq,b);
                if (!r)
                    fc=999999999;
                result=fc;
                
                //result=Math.pow(x, 2)+1/x;
                
		
		return result;
	}
}