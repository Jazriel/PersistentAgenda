package persistence;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import persistence.util.PrintAtDepth;

public class IAbstractPersistenceFactoryTest {
	
	@BeforeClass
	public static void start() {
		PrintAtDepth.print(0,"IAbstractPersistenceFactoryTest start");
	}

	@AfterClass
	public static void end() {
		PrintAtDepth.print(0,"IAbstractPersistenceFactoryTest end");
	}

	@Test
	public void getDBPersistenceFactory() {
		PrintAtDepth.print(1,"getDBPersistence start");
		IAbstractPersistenceFactory abstractPersistenceFactory = new AbstractPersistenceFactory();
		IFactoryPersistence factoryPersistence = abstractPersistenceFactory.getDBPersistence();
		assertTrue(factoryPersistence.getClass().equals(persistence.database.FactoryDataBase.class));
		PrintAtDepth.print(1,"getDBPersistence pass");
	}

	@Test
	public void getBinPersistenceFactory() {
		PrintAtDepth.print(1,"getDBPersistence start");
		IAbstractPersistenceFactory abstractPersistenceFactory = new AbstractPersistenceFactory();
		IFactoryPersistence factoryPersistence = abstractPersistenceFactory.getBinPersistence();
		assertTrue(factoryPersistence.getClass().equals(persistence.bin.FactoryBinFile.class));
		PrintAtDepth.print(1,"getDBPersistence pass");
	}
}
