# Summary of Scala Access modifier

Access | Default | [this] | [class]| [package]
--- | --- | --- | --- | ---
private | current class and its companion object or class, but not subclass | current instance, not other instance | accessed by all members in the scope of "class" | accessed by all members in the scope of "package" 
protected | current class and its companion object or class, also subclass | current instance, not other instance  | accessed by all members in the scope of "class" | accessed by all members in the scope of "package"




#You can use following maven commands to run your application.
##Run Java Program 
mvn exec:java -Dexec.mainClass="com.jhh.Main"  
mvn exec:java -Dexec.mainClass="akka.Main" -Dexec.args="com.jhh.HelloWorld" 

##Run Scala Program
mvn scala:run -DmainClass=com.jhh.App -DaddArgs="arg1|arg2|arg3"


