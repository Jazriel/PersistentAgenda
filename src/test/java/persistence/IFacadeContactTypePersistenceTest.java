package persistence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import model.ContactType;
import persistence.util.PrintAtDepth;

public class IFacadeContactTypePersistenceTest {

	private List<IFactoryPersistence> persistences;

	@Before
	public void beforeTest() {
		PrintAtDepth.print(0, "FacadeContactTypePersistenceTest start");
		IAbstractPersistenceFactory aPersistenceFactory = new AbstractPersistenceFactory();
		persistences = new ArrayList<>();
		persistences.add(aPersistenceFactory.getDBPersistence());
		persistences.add(aPersistenceFactory.getBinPersistence());
	}

	@Test
	public void testSaveContactType() {
		PrintAtDepth.print(1, "saveContactType start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactTypePersistence facadeContactTypePersistence = persistence.createContactTypePersistence();
			int id = getIncMaxContactTypeId(facadeContactTypePersistence);
			facadeContactTypePersistence.saveContactType(new ContactType(id, "ContactTypeFalso"));
			boolean b = false;
			for (ContactType contactType : facadeContactTypePersistence.getAllContactTypes()) {
				if (contactType.getId() == id) {
					b = true;
				}
			}
			assertTrue(b);
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "saveContactType pass");
	}

	@Test
	public void testGetContactTypeById() {
		PrintAtDepth.print(1, "getContactTypeById start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactTypePersistence facadeContactTypePersistence = persistence.createContactTypePersistence();
			int id = getIncMaxContactTypeId(facadeContactTypePersistence);
			facadeContactTypePersistence.saveContactType(new ContactType(id, "CtypeFalso"));
			ContactType contactType = facadeContactTypePersistence.getContactTypeById(id);
			assertTrue(contactType != null);
			PrintAtDepth.print(3, "ContactType was not null");
			assertTrue(contactType.getId() == id);
			PrintAtDepth.print(3, "Id was correct.");
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getContactTypeById pass");
	}
	@Test
	public void testGetAllContactTypes() {
		PrintAtDepth.print(1, "getAllContactTypes start");
		for (IFactoryPersistence persistence : persistences) {
			PrintAtDepth.print(2, persistence.getClass() + " start");
			IFacadeContactTypePersistence facadeContactTypePersistence = persistence.createContactTypePersistence();

			List<ContactType> contactTypesBefore = facadeContactTypePersistence.getAllContactTypes();
			
			int id = getIncMaxContactTypeId(facadeContactTypePersistence);
			facadeContactTypePersistence.saveContactType(new ContactType(id, "Ctypename"));
			
			List<ContactType> contactTypesAfter = facadeContactTypePersistence.getAllContactTypes();
			List<Integer> contactTypesIds= contactTypesAfter.stream().mapToInt(c->c.getId()).boxed().collect(Collectors.toList());
			for (ContactType contactType : contactTypesBefore) {
				contactTypesIds.remove(Integer.valueOf(contactType.getId()));
			}
			assertTrue(contactTypesIds.remove(Integer.valueOf(id)));
			PrintAtDepth.print(2, persistence.getClass() + " pass");
		}
		PrintAtDepth.print(1, "getAllContactTypes pass");
	}

	private int getIncMaxContactTypeId(IFacadeContactTypePersistence persistence) {
		int ret = 0;
		List<ContactType> contactTypes = persistence.getAllContactTypes();
		for (ContactType contactType : contactTypes) {
			if (contactType.getId() > ret) {
				ret = contactType.getId();
			}
		}
		return ret + 1;
	}


}
