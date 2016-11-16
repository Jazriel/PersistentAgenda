package persistence;

import org.junit.Test;
import static org.junit.Assert.*;

import model.Contact;
import persistence.FacadePersistence;
import persistence.database.FacadeDataBase;

public class FacadePersistenceTest {
	
	@Test
	public void testGetContactById(){
		FacadePersistence fp = new FacadeDataBase();
		Contact c = fp.getContactById(1);
		assertTrue(c.getId() == 1);
	}

}
