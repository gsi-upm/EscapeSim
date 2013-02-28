/*
 * Copyright (C) 2013 esfupm
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ubiksimdist;

import sim.app.ubik.Ubik;
import sim.app.ubik.behaviors.Automaton;
import sim.app.ubik.behaviors.escape.EscapeMonitorAgent;
import sim.app.ubik.behaviors.escape.Fire;
import sim.app.ubik.people.PersonHandler;


/**
 * @author Emilio Serrano, Ph.d.; eserrano@gsi.dit.upm.es
 */
public class EscapeSim extends Ubik {
    
     static int maxTimeForExecution=1500;
     
     /**
      * Object with information about execution and, if needed,
      * to finish the execution
      */     
     EscapeMonitorAgent ema ;
     Fire fire;
    
    /**
     * Passing a random seed
     * @param seed 
     */
    public EscapeSim(long seed)   {
        super(seed);
        
    }
    
      /**
     * Passing a random seed and time to make EscapeMonitorAgent to finish simulation
     * This time must be less than maxTimeForExecution
     * @param seed 
     */
    public EscapeSim(long seed, int timeForSim)   {
        super(seed);
        EscapeMonitorAgent.setStepToStop(timeForSim);
        
    }
    

    /**
     * Using seed from config.pros file
     */
     public EscapeSim() {         
           super();
           setSeed(getSeedFromFile());         
    }
     
     /**
      * 
     * Adding things before running simulation.   
     * Method called after pressing pause (the building variables are instantiated) but before executing simulation.
 
      */
   public void start() {               
        super.start();      
        ema= new EscapeMonitorAgent(this); 
        fire= new Fire(this);
        Automaton.setEcho(false);
        //add more people
        PersonHandler ph=  getBuilding().getFloor(0).getPersonHandler();
        //ph.addPersons(20, true, ph.getPersons().get(0));
        //change their name
        ph.changeNameOfAgents("a");
   }
    
   
   /**
 * Default execution without GUI. It executed the simulation for maxTimeForExecution steps.
 * @param args 
 */
    public static void main(String []args) {
       
       EscapeSim state = new EscapeSim(System.currentTimeMillis());
       state.start();
        do{
                if (!state.schedule.step(state)) break;
        }while(state.schedule.getSteps() < maxTimeForExecution);//
        state.finish();     
      
     
    }
    
    /**
     * Get the Fire object from the simulation object
     * @return 
     */
        
    public Fire getFire(){
        return fire;
    }
    /**
     * Get the monitor agent (agent logging data) from the simulation object
     * @return 
     */
  public EscapeMonitorAgent getMonitorAgent(){
        return ema;
    }
    
    


}
