JC = javac -cp ".:../lib/mysql-connector-java-5.0.8-bin.jar"
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	com/iupui/marketplace/dao/*.java \
	com/iupui/marketplace/model/beans/*.java \
	com/iupui/marketplace/controller/*.java \
	com/iupui/marketplace/database/*.java \
	com/iupui/marketplace/client/MarketplaceClient.java \
	com/iupui/marketplace/client/command/*.java \
	com/iupui/marketplace/client/handlers/*.java \
	com/iupui/marketplace/client/view/*.java \
	com/iupui/marketplace/server/*.java 

default:  clean classes

classes: $(CLASSES:.java=.class)
	
clean:
	$(RM) com/iupui/marketplace/controller/*.class
	$(RM) com/iupui/marketplace/*.class
	$(RM) com/iupui/marketplace/dao/*.class
	$(RM) com/iupui/marketplace/client/*.class
	$(RM) com/iupui/marketplace/server/*.class
