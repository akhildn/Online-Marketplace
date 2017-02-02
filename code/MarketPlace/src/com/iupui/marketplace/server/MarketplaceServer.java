/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iupui.marketplace.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

import com.iupui.marketplace.*;
import com.iupui.marketplace.controller.MarketplaceControllerImpl;

public class MarketplaceServer  {

	private static final long serialVersionUID = 1L;

    

	public static void main(String args[]) {
		try{
			
            System.out.println("Creating a Marketplace Server!");
                       MarketplaceController controller = new MarketplaceControllerImpl();
            Naming.rebind("//tesla.cs.iupui.edu:2010/MarketPlace", controller);         
            
	/*	Registry registry= LocateRegistry.createRegistry(2005);
            MarketplaceController controller= new MarketplaceControllerImpl();
            registry.rebind("controller", controller);
	*/		
            System.out.println("Marketplace Server Ready!");
		} catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
