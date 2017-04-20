/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iupui.marketplace.client;

/*
 * @author Akhil Nayabu
 */

import java.rmi.Naming;
import java.util.Scanner;
import com.iupui.marketplace.client.view.LoginView;
import com.iupui.marketplace.client.view.MarketplaceView;
import com.iupui.marketplace.controller.MarketplaceController;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.

public class MarketplaceClient{

	public static void main(String args[]){
		try {
		// To locate the Marketplace server.
		MarketplaceController controller= (MarketplaceController) Naming.lookup("//10.234.136.55:2011/MarketPlace");
		MarketplaceFrontController fc=new MarketplaceFrontController(controller);
		int connectionStatus = controller.connect(); 	// to check if connection to the server has been made.
            if(connectionStatus == 1){
            	MarketplaceView mv=new LoginView(fc);	// calls the initial view if connection is successful
				mv.show();
            }
		} catch(Exception e){
			System.out.println("MarketplaceClient Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
