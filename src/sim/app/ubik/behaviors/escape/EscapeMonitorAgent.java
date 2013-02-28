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

package sim.app.ubik.behaviors.escape;

import java.util.List;
import sim.app.ubik.Ubik;
import sim.app.ubik.batch.MonitorService;
import sim.app.ubik.people.Person;
import sim.app.ubik.utils.GenericLogger;
import sim.engine.SimState;



/**
 * Simple agent to log interesting data in EscapeSim
 * @author Emilio Serrano, Ph.d.; eserrano@gsi.dit.upm.es
 */
public class EscapeMonitorAgent implements MonitorService {
/**This agent stops simulation in this step		*/ 
    protected static long maxStepToStop=1000;    
    
    /**
     * Data structure to log data and conduct statistical operations
     */
    protected GenericLogger genericLogger;
    protected Ubik ubik;
    /**
     * Counter with people who have touch fire
     */
    protected int  peopleCloseToFire=0;
    

    
    public EscapeMonitorAgent(Ubik u) {	      
        this.ubik = u;	
        String logHeadings[]={"PeopleInBuilding","PeopleWhoReachFire"};
        genericLogger = new GenericLogger(logHeadings);
        register();
    }	
    
    /**
     * Set the number of the step to stop simulation
     * @param s 
     */
    public static void setStepToStop(long s){
        maxStepToStop=s;
    }
    /**
     * Get the genericLogger with the data of the simulation
     * @return 
     */
    public  GenericLogger  getGenericLogger(){
       return genericLogger;
    }
    
 /**
  * Register the fire in the schedule (to make the simulation call the step method in fire for each step).
  */
    
    public void register() {
        ubik.schedule.scheduleRepeating(this, 1);
    }
/**
 * Method with the actions to be performed by this agent in each step: adding data in genericLogger and checking if the simulation has to finish
 * 
 * @param ss 
 */
    public void step(SimState ss) {       
        List<Person> people = ubik.getBuilding().getFloor(0).getPersonHandler().getPersons();              
        double[] toLogInStep ={ people.size(), peopleCloseToFire};        
        genericLogger.addStep(toLogInStep);        
        
        if(ubik.schedule.getSteps()>=maxStepToStop){//end simulation from agent monitor
               ubik.kill();
        }
        
    }

    public void stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
