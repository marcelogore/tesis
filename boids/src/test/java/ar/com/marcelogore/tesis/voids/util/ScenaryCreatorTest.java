package ar.com.marcelogore.tesis.voids.util;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import ar.com.marcelogore.tesis.voids.CircularVoid;
import ar.com.marcelogore.tesis.voids.util.ScenaryCreator;
import ar.com.marcelogore.tesis.voids.util.Vector;

public class ScenaryCreatorTest {

	private static final Log log = LogFactory.getLog(ScenaryCreatorTest.class);
	
	@Test
	public void LineFrom0x0To20x20() {
		
		List<CircularVoid> voids = new LinkedList<CircularVoid>();
		ScenaryCreator.drawLine(voids, new Vector(0,0), new Vector(20,20));
		log.info(voids);
	}

	@Test
	public void LineFrom20x20To0x0() {
		
		List<CircularVoid> voids = new LinkedList<CircularVoid>();
		ScenaryCreator.drawLine(voids, new Vector(20,20), new Vector(0,0));
		log.info(voids);
	}
	
	@Test
	public void LineFrom0x0To20x0() {
		
		List<CircularVoid> voids = new LinkedList<CircularVoid>();
		ScenaryCreator.drawLine(voids, new Vector(0,0), new Vector(20,0));
		log.info(voids);
	}

}
