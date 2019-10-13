package showcase.mvc.messageconverter;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;

@RestController
@RequestMapping("/messageconverters")
public class MessageConvertersController {
	
	@GetMapping("/simple")
	public JavaBean simple() {
		return new JavaBean("bar", "apple");
	}

	// StringHttpMessageConverter

	@PostMapping("/string")
	public String readString(@RequestBody String string) {
		return "Read string '" + string + "'";
	}
	
	@GetMapping("/string")
	public String writeString() {
		return "Wrote a string";
	}

	// Form encoded data (application/x-www-form-urlencoded)

	@PostMapping("/form")
	public String readForm(@ModelAttribute JavaBean bean) {
		return "Read x-www-form-urlencoded: " + bean;
	}

	@GetMapping("/form")
	public MultiValueMap<String, String> writeForm() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("foo", "bar");
		map.add("fruit", "apple");
		return map;
	}

	// MappingJackson2HttpMessageConverter (requires Jackson2 on the classpath - particularly useful for serving JavaScript clients that expect to work with JSON)

	@PostMapping(value = "/json", consumes = "application/json")
	public String readJson(@RequestBody JavaBean bean) {
		return "Read from JSON: " + bean;
	}

	@GetMapping("/json")
	public JavaBean writeJson() {
		return new JavaBean("bar", "apple");
	}

	// Jaxb2RootElementHttpMessageConverter (requires JAXB2 on the classpath - useful for serving clients that expect to work with XML)

	@PostMapping(value = "/xml", consumes = "application/xml")
	public String readXml(@RequestBody JavaBean bean) {
		return "Read from XML: " + bean;
	}

	@GetMapping("/xml")
	public JavaBean writeXml() {
		return new JavaBean("bar", "apple");
	}

	// AtomFeedHttpMessageConverter (requires Rome on the classpath - useful for serving Atom feeds)

	@PostMapping("/atom")
	public String readFeed(HttpEntity<Feed> entity) {
		return "Read " + entity.getBody().getTitle();
	}

	@GetMapping("/atom")
	public Feed writeFeed() {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("My Atom feed");
		return feed;
	}

	// RssChannelHttpMessageConverter (requires Rome on the classpath - useful for serving RSS feeds)

	@PostMapping("/rss")
	public String readChannel(HttpEntity<Channel> entity) {
		return "Read " + entity.getBody().getTitle();
	}

	@GetMapping("/rss")
	public Channel writeChannel() {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		channel.setTitle("My RSS feed");
		channel.setDescription("Description");
		channel.setLink("http://localhost:8080/messageconverter/rss");
		return channel;
	}

}