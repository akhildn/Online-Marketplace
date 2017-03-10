package com.iupui.marketplace.server;

import com.iupui.marketplace.controller.MarketplaceControllerImpl;
import com.iupui.marketplace.model.beans.Account;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by anaya on 3/5/2017.
 */
public class AuthorizationInvocationHandler implements InvocationHandler, Serializable {
    private Object impl;
    String userType;
    public AuthorizationInvocationHandler(Object impl) {
        this.impl = impl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // when invoked method has an annotation to it.
        if (method.isAnnotationPresent(RequiresRole.class)) {

            RequiresRole test = method.getAnnotation(RequiresRole.class);

           // System.out.println("test :" + test.value());
            Account account = (Account) args[0]; // gets the accountType since account type is passed as 1st argument
           // System.out.println("account type :" + account.getUserType());
            userType = account.getUserType();

            // checks if the user has access to the method by checking with role specified in the annotations
            if (userType.equals(test.value())) {
                // if annotation role and userType matches following method is called
                return method.invoke(impl, args);
            } else {
                // When use doesn't have access to method which was called.
                // Custom Exception message is displayed.
                throw new AuthorizationException(method.getName(),userType);
            }
        } else {
            // when no annotation is present for the method
            return method.invoke(impl, args);
        }
    }
}

