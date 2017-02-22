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
import java.util.Scanner;

import com.iupui.marketplace.client.view.LoginView;
import com.iupui.marketplace.client.view.MarketplaceView;
import com.iupui.marketplace.controller.MarketplaceController;

// Ryan: Please include usefull comments in each file.
public class MarketplaceClient{

	public static void main(String args[]){

		try {

        /*    Registry registry = LocateRegistry.getRegistry("tesla.cs.iupui.edu",2005);
            MarketplaceController controller= (MarketplaceController) registry.lookup("controller");
         */

		String username,password;
		Scanner in = new Scanner(System.in);
		MarketplaceController controller= (MarketplaceController) Naming.lookup("//tesla.cs.iupui.edu:2010/MarketPlace");
		MarketplaceFrontController fc=new MarketplaceFrontController(controller);
		int connectionStatus = controller.connect();
            if(connectionStatus == 1){
            	System.out.println("Message from server: " + controller.welcomeMessage());
				MarketplaceView mv=new LoginView(fc);
				mv.show();
            }

		} catch(Exception e){
			System.out.println("MarketplaceClient Exception: " + e.getMessage());
			e.printStackTrace();
		}


	}
}
