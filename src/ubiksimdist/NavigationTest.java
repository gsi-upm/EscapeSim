/*
* 
* 
* This file is part of EscapeSim. EscapeSim is a UbikSim library. 
* 
* EscapeSim has been developed by members of the research Group on 
* Intelligent Systems [GSI] (Grupo de Sistemas Inteligentes), 
* acknowledged group by the  Technical University of Madrid [UPM] 
* (Universidad Politécnica de Madrid) 
* 
* Authors:
* Mercedes Garijo
* Geovanny Poveda
* Emilio Serrano
* 
* 
* Contact: 
* http://www.gsi.dit.upm.es/;
* 
* 
* 
* EscapeSim, as UbikSim, is free software: 
* you can redistribute it and/or modify it under the terms of the GNU 
* General Public License as published by the Free Software Foundation, 
* either version 3 of the License, or (at your option) any later version. 
*
* 
* EscapeSim is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with VoteSim. If not, see <http://www.gnu.org/licenses/>
 */

package ubiksimdist;


import sim.app.ubik.behaviors.Automaton;

import sim.app.ubik.people.PersonHandler;
import sim.app.ubik.Ubik;

public class NavigationTest extends Ubik {

      public NavigationTest(long seed)   {
        super(seed);
        
    }
      
    public void start() {               
        super.start();              
        Automaton.setEcho(false);
        //add more people
        PersonHandler ph=  getBuilding().getFloor(0).getPersonHandler();
        //ph.addPersons(20, true, ph.getPersons().get(0));
        //change their name
        ph.changeNameOfAgents("a");
   }
    
      public static void main(String []args) {
       
       NavigationTest state = new NavigationTest(System.currentTimeMillis());
       state.start();
        do{
                PersonHandler ph=  state.getBuilding().getFloor(0).getPersonHandler();
                if(ph.getPersons().isEmpty()) System.exit(0);
                if (!state.schedule.step(state)) break;
        }while(true);//
        state.finish();     
      
     
    }
}
