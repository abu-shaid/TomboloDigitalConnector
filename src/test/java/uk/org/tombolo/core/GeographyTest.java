package uk.org.tombolo.core;

import static org.junit.Assert.assertEquals;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import uk.org.tombolo.core.utils.HibernateUtil;

public class GeographyTest {
	
	@Test
	public void testLsoaLoad(){
		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Geography.class);
		Geography cityOfLondon1A = (Geography)criteria.add(Restrictions.eq("label", "E01000001")).uniqueResult();
		assertEquals("lsoa", cityOfLondon1A.getGeographyType().getLabel());
		assertEquals("Lower Layer Super Output Area", cityOfLondon1A.getGeographyType().getName());
		assertEquals("City of London 001A", cityOfLondon1A.getName());
		
		assertEquals("MultiPolygon",cityOfLondon1A.shape.getGeometryType());
		assertEquals(133320d, cityOfLondon1A.shape.getArea(), 1d);
		session.close();
	}
	
}