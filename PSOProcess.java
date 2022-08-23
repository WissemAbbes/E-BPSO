package EBPSO;
/**
 *
 * @author wissem
 */
// this is the heart of the EBPSO program
// the code is for 2-dimensional space problem

import static EBPSO.ProblemSet.feasibility;
import java.util.Random;
import java.util.Vector;

public class PSOProcess implements PSOConstants {
	private Vector<Particle> swarm = new Vector<Particle>();
	private double[] pBest = new double[SWARM_SIZE];
	private Vector<Location> pBestLocation = new Vector<Location>();
	private double gBest;
	private Location gBestLocation;
	private double[] fitnessValueList = new double[SWARM_SIZE];
	
	Random generator = new Random();
	
	public void execute() {
                
		initializeSwarm();
                long startTime = System.nanoTime();
		updateFitnessList();
                
		
		for(int i=0; i<SWARM_SIZE; i++) {
			pBest[i] = fitnessValueList[i];
                        pBestLocation.add(swarm.get(i).getLocation());
		}
		
		int t = 0;
		double w;
		double err = 9999;
		
		while(t < MAX_ITERATION && err > ProblemSet.ERR_TOLERANCE) {
                    
                    
                    
			// step 1 - update pBest
			for(int i=0; i<SWARM_SIZE; i++) {
				if(fitnessValueList[i] < pBest[i]) {
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getLocation());
				}
			}
				
			// step 2 - update gBest
			int bestParticleIndex = PSOUtility.getMinPos(fitnessValueList);
			if(t == 0 || fitnessValueList[bestParticleIndex] < gBest) {
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getLocation();
			}
			
			w = W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);
			
			for(int i=0; i<SWARM_SIZE; i++) {
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();
				
				Particle p = swarm.get(i);
				
				// step 3 - update velocity
				double[] newVel = new double[PROBLEM_DIMENSION];
                                for(int j=0;j<PROBLEM_DIMENSION;j++)
                                {
                                   newVel[j] = (w * p.getVelocity().getPos()[j]) + 
							(r1 * C1) * (pBestLocation.get(i).getLoc()[j] - p.getLocation().getLoc()[j]) +
							(r2 * C2) * (gBestLocation.getLoc()[j] - p.getLocation().getLoc()[j]);
                                if(newVel[j]<ProblemSet.VEL_LOW)
                                    newVel[j]=ProblemSet.VEL_LOW;
                                else if(newVel[j]>ProblemSet.VEL_HIGH)
                                    newVel[j]=ProblemSet.VEL_HIGH; 
                                }
                                
                                Velocity vel = new Velocity(newVel);
				p.setVelocity(vel);
				
				// step 4 - update location
				double[] newLoc = new double[PROBLEM_DIMENSION];
                                for(int j=0;j<PROBLEM_DIMENSION;j++)
                                {
                                   newLoc[j]=0;
                                if(p.getLocation().getLoc()[j] + newVel[j]>0.5)
                                    newLoc[j]=1;
                                }
                                
				Location loc = new Location(newLoc);
				p.setLocation(loc);
			}
			
			err = ProblemSet.evaluate(gBestLocation) - 0; // minimizing the functions means it's getting closer to 0
			
                        
			System.out.println("     Value: " + Math.round(ProblemSet.evaluate(gBestLocation)));
                    long endTime = System.nanoTime();
                        System.out.println("Total elapsed time in execution of method callMethod() is :"+ (float)(endTime-startTime)/1000000);    
			
			
                        t++;
			updateFitnessList();
		}
                
	}
        public int nb_1(String ch)
        {
         int i,nb=0;
         String c;
         for(i=0;i<ch.length();i++)
             if((ch.charAt(i))=='1')
                 nb++;
         return nb;
        }
	
	public void initializeSwarm() {
            
		Particle p;
		for(int i=0; i<SWARM_SIZE; i++) {
			p = new Particle();
			
			// randomize location inside a space defined in Problem Set
			double[] loc = new double[PROBLEM_DIMENSION];
		
                                               
                        float h=0,hq_;
                        for(int k=0;k<mat1.length;k++)
                            h+=mat1[k][0];
                        hq_=h*HQ/100;
                        int nb1=Math.round(PROBLEM_DIMENSION*HQ/100);
                        
                        
                        boolean test;
                        int j;
                        String b,c;
                        //ProblemSet.ma
                         do
                        {
                            b="";
                            test=true;
                            //nb1=0;
                            for(j=0;j<PROBLEM_DIMENSION;j++)
                            {
                                c=Integer.toString((int)Math.round(Math.random()));
                                b=b+c;     
                            }
                            if(!feasibility(mat1,hq_,b))
                            {test=false;}   
                            if(nb_1(b)>nb1)
                                test=false;

                        }while(!test); 
                         //System.out.println(i+": "+b);
                         for(j=0;j<PROBLEM_DIMENSION;j++)
                             loc[j]=Double.valueOf(String.valueOf(b.charAt(j)));
                        
                        
                        
                        Location location = new Location(loc);
			
			// randomize velocity in the range defined in Problem Set
			double[] vel = new double[PROBLEM_DIMENSION];
                        for(j=0;j<PROBLEM_DIMENSION;j++)
                         vel[j] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);   
                        Velocity velocity = new Velocity(vel);
			
			p.setLocation(location);
			p.setVelocity(velocity);
			swarm.add(p);
                        
		}   
			
	}
	
	public void updateFitnessList() {
		for(int i=0; i<SWARM_SIZE; i++) {
			fitnessValueList[i] = swarm.get(i).getFitnessValue();
                        
		}
	}
}