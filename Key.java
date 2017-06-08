import java.util.*;
import java.io.*;
public class Key
{  

   
   public static double log2(double f)
   {
      return Math.log(f)/Math.log(2.0);
   }
   
   public static void main(String[] args)
   {
             int MAX_POPULATION = 200;
      int shtylla = 192;
      int x;
      int[][] initPop = new int[MAX_POPULATION][shtylla];
      int[][] finalPop = new int[MAX_POPULATION][shtylla];
      double summation_X_2 = 0;
      double mutation_rate = 0.5;
      double number_of_mutation = (shtylla*MAX_POPULATION*mutation_rate)/MAX_POPULATION; 
      int[] c2 = new int[shtylla];
      int[] c1 = new int[shtylla];
        double max_rank = 0;
         int rank_index = 0;
     
      for(int i=0; i<MAX_POPULATION; i++)
      {
         for(int j=0; j<initPop[0].length; j++)
         {
            x = (int)(Math.random()*100);
            if(x<=50)
            {
               initPop[i][j] = 0;
            }
            else
            {
               initPop[i][j] = 1;
            }
         }
      }
      

         
      for(int i=0; i<MAX_POPULATION; i++)
      {
         for(int m=0; m<shtylla; m++)
         {
            //crossover
            int[] p1 = new int[shtylla];
            for(int j=0; j<shtylla; j++)
            {
               p1[j] = initPop[(int)Math.random()*20][j];
            }
         
            int[] p2 = new int[shtylla];
            for(int j=0; j<shtylla; j++)
            {
               p2[j] = initPop[(int)Math.random()*200][j];
            }
         
            int[] pp = new int[p1.length+p2.length];
            for(int j=0; j<p1.length; j++)
            {
               pp[j] = p1[j];
            }
            for(int j=p1.length; j<pp.length; j++)
            {
               for(int k=0; k<p2.length; k++)
               {
                  pp[j] = p2[k];
               }
            }
         
            int cutpoint =(int) Math.random()*(pp.length);                                                                                                                                                   
         
            
            for(int j=0; j<shtylla; j++)
            {
               c1[j] = pp[cutpoint+j];
            }
         
            
            for(int j=0; j<pp.length-(cutpoint+shtylla); j++)
            {
               c2[j] = pp[cutpoint+shtylla+j];
            }
            for(int j = pp.length-(cutpoint+shtylla); j<shtylla; j++)
            {
               for(int k=0; k<cutpoint; k++)
               {
                  c2[j] = pp[k];
               }
            }
            if(i%2==0)
            {
            finalPop[i][m] = c1[m];
            }
            else{finalPop[i][m] = c2[m];}
            
            //mutation
            for(int s = 0; s<number_of_mutation; s++)
            {
            int z = (int) Math.random()*192;
            if(finalPop[i][z]==0){finalPop[i][z] = 0;}
            else{finalPop[i][z] = 1;}
            }
         
         
         }
      }
       
      //fitness
      for(int i=0; i<MAX_POPULATION;i++)
      {
         double d1=0; 
         double d2=0;
         for(int j=0; j<shtylla;j++)
         {
            int DM = Math.abs(finalPop[i][j]-initPop[i][j]);
            d1 += j*DM;
            d2 += j*initPop[i][j];
         }
      
      //percantage of randomness observed
         double p = ((double)Math.abs(d1-d2))/d2;
      
      //expected_H calculation
         double expected_H = (0.5*(log2(0.5)))-((1-0.5)*(log2(1-0.5)));
      
      //H(x) calculation
         double observed_H = -(p*(log2(p)))-((1-p)*(log2(1-p)));
      
      //X^2 calculation
         
          double X_2 = ((observed_H-expected_H)*(observed_H-expected_H))/(expected_H);
         summation_X_2 = summation_X_2 + X_2;
      
      //PHI calculation
      //PHI correspondinf value against total 200 keys
       
         double PHI = Math.sqrt(X_2/200);
         if(max_rank<PHI)
         {
            max_rank = PHI;
            rank_index = i;
         }
      }
   
   //find chi square value avg for summationX_2
      double chi_square = summation_X_2/200;
         
         for(int l=0; l<192; l++)
         {
         System.out.print(finalPop[(int)rank_index][l]);
         
         }
         System.out.println();
                  for(int l=0; l<192; l++)
         {
         System.out.print(initPop[(int)rank_index][l]);
         
         }
         System.out.println();
         
         //Dalja
         try
         {
      BufferedWriter out = new BufferedWriter(new FileWriter("Celesi.txt"));
   
      String dalja = "";
      for(int i=0; i<shtylla; i++)
      { 
         if(finalPop[(int)rank_index][i]==1)
         { dalja = dalja  + "1" + " ";}
         else {dalja = dalja + "0" + " " ;}
      }
       out.write(dalja);
         out.close();    
         }
         catch(Exception e){}   
   }
}