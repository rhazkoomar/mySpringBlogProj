package cz.jiripinkas.jba.service;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.exception.RssException;

public class RssServ {

	private RssService rssService;

	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService
				.getItems(new File("test-rss/javavids.xml"));
		assertEquals(10, items.size());
		Item firstItem = items.get(0);
		assertEquals("How to generate web.xml in Eclipse", firstItem.getTitle());
		// System.out.println(new
		// SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
		assertEquals("23 03 2014 13:46:34", new SimpleDateFormat(
				"dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));

	}
}
