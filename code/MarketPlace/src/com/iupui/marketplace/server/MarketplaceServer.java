/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iupui.marketplace.server;

import java.rmi.Naming;
import java.lang.reflect.Proxy;

import com.iupui.marketplace.controller.MarketplaceController;
import com.iupui.marketplace.controller.MarketplaceControllerImpl;

// Ryan: Make sure to include useful comments in each file.
// Fixed: Comments are included in each file.
public class MarketplaceServer  {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		try{
			
            System.out.println("Creating a Marketplace Server!");

            // Refection and proxy
            MarketplaceController controller = (MarketplaceController) Proxy.newProxyInstance(MarketplaceController.class.getClassLoader(),
				    new Class<?>[] {MarketplaceController.class},
                    new AuthorizationInvocationHandler(new MarketplaceControllerImpl()));

			// Binds the Server to the RMI Service.
            Naming.rebind("//10.234.136.55:2011/MarketPlace", controller);
            
            System.out.println("Marketplace Server Ready!");
		} catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}


