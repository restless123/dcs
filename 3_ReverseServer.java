import ReverseModule.Reverse;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

class ReverseServer
{
    public static void main(String[] args)
    {
        try
        {
            // initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

            // initialize the BOA/POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // creating the calculator object
            ReverseImpl rvr = new ReverseImpl();
            
            // get the object reference from the servant class
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(rvr);

            System.out.println("Step1");
            Reverse h_ref = ReverseModule.ReverseHelper.narrow(ref);
            System.out.println("Step2");

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            System.out.println("Step3");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            System.out.println("Step4");

            String name = "Reverse";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path,h_ref);

            System.out.println("Reverse Server reading and waiting....");
            orb.run();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

/*
 String Reverse using corba , idl and java implementation : 

1. Create the all ReverseServer.java , ReverseClient.java , ReverseImpl.java & ReverseModule.idl  files.

2. Run the IDL-to-Java compiler idlj, on the IDL file to create stubs and skeletons. This step assumes that you have included the path to the java/bin directory in your path.

  idlj -fall  ReverseModule.idl
The idlj compiler generates a number of files.
3. Compile the .java files, including the stubs and skeletons (which are in the directory newly created directory). This step assumes the java/bin directory is included in your path.

   javac *.java  ReverseModule/*.java
4. Start orbd. To start orbd from a UNIX command shell, enter :

  	orbd -ORBInitialPort 1050&
5. Start the server. To start the  server from a UNIX command shell, enter :
  java ReverseServer -ORBInitialPort 1050& -ORBInitialHost localhost&

6. Run the client application :
  java ReverseClient -ORBInitialPort 1050 -ORBInitialHost localhost
*/
