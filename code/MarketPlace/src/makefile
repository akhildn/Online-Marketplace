JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	com/iupui/marketplace/dao/AccountDAO.java \
	com/iupui/marketplace/dao/AdministratorDAO.java \
	com/iupui/marketplace/dao/CustomerDAO.java \
	com/iupui/marketplace/dao/ItemDAO.java \
	com/iupui/marketplace/dao/OrderDAO.java \
	com/iupui/marketplace/dao/ProductCategoryDAO.java \
	com/iupui/marketplace/dao/ProductDAO.java \
	com/iupui/marketplace/model/beans/ProductCategory.java \
	com/iupui/marketplace/MarketplaceController.java \
	com/iupui/marketplace/controller/MarketplaceControllerImpl.java \
	com/iupui/marketplace/client/MarketplaceClient.java \
	com/iupui/marketplace/server/MarketplaceServer.java 

default:  clean classes

classes: $(CLASSES:.java=.class)
	
clean:
	$(RM) com/iupui/marketplace/controller/*.class
	$(RM) com/iupui/marketplace/*.class
	$(RM) com/iupui/marketplace/dao/*.class
	$(RM) com/iupui/marketplace/client/*.class
	$(RM) com/iupui/marketplace/server/*.class
