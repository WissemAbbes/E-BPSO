package EBPSO;

import static EBPSO.ProblemSet.*;
/**
 *
 * @author wissem
 */
// this is an interface to keep the configuration for the EBPSO
public interface PSOConstants {
	int SWARM_SIZE = 70;
	int MAX_ITERATION = 50;
	int PROBLEM_DIMENSION = 20;
	double C1 = 2.0;
	double C2 = 2.0;
	double W_UPPERBOUND = 1.0;
	double W_LOWERBOUND = 0.0;
        float HQ = 50, a=40, b1=20, b2=10;
        String file="../ServiceGraph/graph"+PROBLEM_DIMENSION+"_10_.txt";
        float[][] mat1=read_capacity(file);
        float [][] mat2=read_connection(file);     
        
}
