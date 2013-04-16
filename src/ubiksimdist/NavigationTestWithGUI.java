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
import sim.app.ubik.UbikSimWithUI;
import sim.display.Console;

 
/**
 * @author Emilio Serrano, Ph.d.; eserrano@gsi.dit.upm.es
 */
public class NavigationTestWithGUI extends UbikSimWithUI {
    static NavigationTest escapesim;
    public NavigationTestWithGUI(Ubik ubik) {
		super(ubik);		
	}
    
    
    public static void main(String []args) {
         escapesim = new NavigationTest(System.currentTimeMillis());
        NavigationTestWithGUI vid = new NavigationTestWithGUI(escapesim);
        Console c = new Console(vid);	
        c.setIncrementSeedOnStop(false);
        c.setVisible(true);
    }
    
    
      public void start() {
        super.start();
        //myDisplay = new MyDiplay(this); 
        //controller.registerFrame(myDisplay);  
        
        
    }
    
       
}
