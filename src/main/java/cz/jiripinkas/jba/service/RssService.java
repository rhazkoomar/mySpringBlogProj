package cz.jiripinkas.jba.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;

import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.exception.RssException;
import cz.jiripinkas.jba.rss.ObjectFactory;
import cz.jiripinkas.jba.rss.TRss;
import cz.jiripinkas.jba.rss.TRssChannel;
import cz.jiripinkas.jba.rss.TRssItem;

@Service
public class RssService {

	public List<Item> getItems(File file) throws RssException{
		return getItems(new StreamSource(file));
	}
	public List<Item> getItems(String url) throws RssException{
		return getItems(new StreamSource(url));
	}
	
/*	private List<Item> getItems(Source source) throws RssException{
		ArrayList<Item> itemList= new ArrayList<Item>();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source, TRss.class);
			TRss trss = jaxbElement.getValue();
			List<TRssChannel> channel = trss.getChannel();
			for (TRssChannel tRssChannel : channel) {
				List<TRssItem> items = tRssChannel.getItem();
				for (TRssItem tRssItem : items) {
					Item item=new Item();
					item.setTitle(tRssItem.getTitle());
					item.setDescription(tRssItem.getDescription());
					item.setGuid(tRssItem.getGuid().toString());
					item.setLink(tRssItem.getLink());
					Date pubDate = new SimpleDateFormat("EEE,dd MM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(tRssItem.getPubDate());
					item.setPublishedDate(pubDate);
					itemList.add(item);
					
				}
			} 
			
		} catch (JAXBException | ParseException e) {
			throw new RssException(e);
		}
		return itemList;
	}
*/
	private List<Item> getItems(Source source) throws RssException {
		ArrayList<Item> list = new ArrayList<Item>();
		
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source, TRss.class);
			TRss rss = jaxbElement.getValue();

			List<TRssChannel> channels = rss.getChannel();
			for (TRssChannel channel : channels) {
				List<TRssItem> items = channel.getItem();
				for (TRssItem rssItem : items) {
					Item item = new Item();
					item.setTitle(rssItem.getTitle());
					item.setDescription(rssItem.getDescription());
					item.setLink(rssItem.getLink());
					Date pubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(rssItem.getPubDate());
					item.setPublishedDate(pubDate);
					list.add(item);
				}
			}
		} catch (JAXBException e) {
			throw new RssException(e);
		} catch (ParseException e) {
			throw new RssException(e);
		}
		return list;
	}
}
