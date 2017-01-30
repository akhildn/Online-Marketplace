/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iupui.marketplace.client;

/**
 *
 * @author Akhil Nayabu
 */

import java.rmi.Naming;
import java.rmi.registry.*;

import com.iupui.marketplace.*;

public class MarketplaceClient{
	
	public static void main(String args[]){
	
		try {
			
            Registry registry = LocateRegistry.getRegistry("tesla.cs.iupui.edu",2005);
            MarketplaceController controller= (MarketplaceController) registry.lookup("controller");
            

/*
			String name = "//tesla.cs.iupui.edu/Marketplace";
			MarketplaceController controller= (MarketplaceController) Naming.lookup(name);
*/	
		int connectionStatus = controller.connect();
            if(connectionStatus == 1){
            	System.out.println("Connected to server....");
            	System.out.println("Message from server: " + controller.welcomeMessage());
            }
		} catch(Exception e){
			System.out.println("MarketplaceClient Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
}
