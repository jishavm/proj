package EC.GA.crowding;

import EC.GA.*;
import EC.GA.fitness.*;

import java.util.*;

/**
 * Handles hybrid crowding method for keeping diversity in the population.
 * <p>
 * @author Severino F. Galan
 * @since June 2009
 */
public class HybridReplacement extends GACrowding
{

   /**
    * Creates a hybrid replacement with null values.
    */
   public HybridReplacement() 
   {
      S = 0;
   }
    
   /**
    * Creates a hybrid replacement with given values.
    * <p>
    * @param SParameter Parameter S (see paper 'The crowding approach to niching in genetic algorithms')
    */
   public HybridReplacement(int SParameter) 
   {
      S = SParameter;
   }

   /**
    * Peforms hybrid replacement on a population of <code>S</code> (or less) individuals, given a permutation of their children. 
    * <p>
    * @param population the population containing the children. In general, this population will be changed after several (parent, child) tournaments.
    * @param parents the parents temporarily substituted in the population by their children.
    * @param indexPool the indices of the children in the population. This variable connects in order each pair of parents with their two children.
    */
   public void performReplacement(GAPopulation population1, GAPopulation population2, ArrayList<GAIndividual> parents, ArrayList<Integer> indexPool, int[] permutation, int setpoint, int currentNumberOfClusters, double FeedbackScalingFactor)
   {
      GAIndividual currentParent = new GAIndividual(); 
      GAIndividual currentChild = new GAIndividual(); 
      double f_currentParent, f_currentChild; // Fitnesses for the current parent and the current child
      Random random = new Random();

      for(int i=0; i<parents.size(); i++)
      {
         currentParent = (GAIndividual)parents.get(i);
         currentChild = population2.individuals[((Integer)indexPool.get(permutation[i])).intValue()];
         f_currentParent = currentParent.fitness;
         f_currentChild = currentChild.fitness;
         if(currentParent.isBetterThan(currentChild))
            if(random.nextDouble() < (f_currentParent/(f_currentParent+f_currentChild)))
               population2.individuals[((Integer)indexPool.get(permutation[i])).intValue()] = currentParent;
      }
   }

}