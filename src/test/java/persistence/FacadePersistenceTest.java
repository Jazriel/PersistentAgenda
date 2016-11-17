package persistence;

import org.junit.Test;
import static org.junit.Assert.*;

import model.Contact;
import persistence.IFacadeContactPersistence;
import persistence.database.FacadeUserDataBase;

public class FacadePersistenceTest {
	
	@Test
	public void testGetContactById(){
		IFacadeContactPersistence fp = new FacadeUserDataBase();
		Contact c = fp.getContactById(1);
		assertTrue(c.getId() == 1);
	}

}
