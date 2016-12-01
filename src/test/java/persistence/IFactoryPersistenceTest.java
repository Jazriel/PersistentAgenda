package persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.util.PrintAtDepth;

public class IFactoryPersistenceTest {
	
	@BeforeClass
	public static void before(){
		PrintAtDepth.print(0, "IFactoryPersistenceTest start");
		IAbstractPersistenceFactory aPersistenceFactory = new AbstractPersistenceFactory();
		persistences = new ArrayList<>();
		persistences.add(aPersistenceFactory.getBinPersistence());
		persistences.add(aPersistenceFactory.getDBPersistence());
	}

	private static List<IFactoryPersistence> persistences;
	
	@AfterClass
	public static void after(){
		PrintAtDepth.print(0, "IFactoryPersistenceTest end");
	}
	
	@Test
	public void createContactPersistenceTest(){
		PrintAtDepth.print(1, "createContactPersistenceTest start");
		List<Class> classes = new ArrayList<>();
		classes.add(persistence.database.FacadeContactDataBase.class);
		classes.add(persistence.bin.FacadeContactBinFile.class);
		for(IFactoryPersistence persistence: persistences){
			PrintAtDepth.print(2, persistence.getClass()+" start");
			IFacadeContactPersistence contactPersistence = persistence.createContactPersistence();
			PrintAtDepth.print(3, contactPersistence.getClass()+" start");
			assertTrue(classes.contains(contactPersistence.getClass()));
			classes.remove(contactPersistence.getClass());
			PrintAtDepth.print(3, contactPersistence.getClass()+" pass");
			PrintAtDepth.print(2, persistence.getClass()+" pass");
		}
		PrintAtDepth.print(1, "createContactPersistenceTest pass");
	}
	
	@Test
	public void createCallPersistenceTest(){
		PrintAtDepth.print(1, "createCallPersistenceTest start");
		List<Class> classes = new ArrayList<>();
		classes.add(persistence.database.FacadeCallDataBase.class);
		classes.add(persistence.bin.FacadeCallBinFile.class);
		for(IFactoryPersistence persistence: persistences){
			PrintAtDepth.print(2, persistence.getClass()+" start");
			IFacadeCallPersistence callPersistence = persistence.createCallPersistence();
			PrintAtDepth.print(3, callPersistence.getClass()+" start");
			assertTrue(classes.contains(callPersistence.getClass()));
			classes.remove(callPersistence.getClass());
			PrintAtDepth.print(3, callPersistence.getClass()+" pass");
			PrintAtDepth.print(2, persistence.getClass()+" pass");
		}
		PrintAtDepth.print(1, "createCallPersistenceTest pass");
	}
	
	@Test
	public void createContactTypePersistenceTest(){
		PrintAtDepth.print(1, "createContactTypePersistenceTest start");
		List<Class> classes = new ArrayList<>();
		classes.add(persistence.database.FacadeContactTypeDataBase.class);
		classes.add(persistence.bin.FacadeContactTypeBinFile.class);
		for(IFactoryPersistence persistence: persistences){
			PrintAtDepth.print(2, persistence.getClass()+" start");
			IFacadeContactTypePersistence contactTypePersistence = persistence.createContactTypePersistence();
			PrintAtDepth.print(3, contactTypePersistence.getClass()+" start");
			assertTrue(classes.contains(contactTypePersistence.getClass()));
			classes.remove(contactTypePersistence.getClass());
			PrintAtDepth.print(3, contactTypePersistence.getClass()+" pass");
			PrintAtDepth.print(2, persistence.getClass()+" pass");
		}
		PrintAtDepth.print(1, "createContactTypePersistenceTest pass");
	}
}
