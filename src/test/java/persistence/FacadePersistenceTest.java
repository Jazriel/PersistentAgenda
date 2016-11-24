package persistence;

import org.junit.Test;
import static org.junit.Assert.*;

import model.Contact;
import persistence.IFacadeContactPersistence;
import persistence.database.FacadeContactDataBase;

public class FacadePersistenceTest {
	
	@Test
	public void testGetContactById(){
		IFacadeContactPersistence fp = new FacadeContactDataBase();
		Contact c = fp.getContactById(1);
		assertTrue(c.getId() == 1);
	}

}
