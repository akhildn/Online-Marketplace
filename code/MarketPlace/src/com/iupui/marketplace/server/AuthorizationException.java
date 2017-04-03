package com.iupui.marketplace.server;

import java.io.Serializable;

/**
 * Created by anaya on 3/6/2017.
 */

//Ryan: Please include comments in each file.
//fixed: comments are provided.
 // this is custom exception which is raised when user doesn't have appropriate role for a method which is being
// executed
public class AuthorizationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 5528415690278423524L;

    // Exception which is displayed when user with invalid permission is trying to access the method
    public AuthorizationException(String methodName, String userType) {
        super("Invalid Authorization - Access Denied to " + methodName + "() function!" +"\n" + userType + ": does not"
                + " "+"have access to this method.");
    }
}
