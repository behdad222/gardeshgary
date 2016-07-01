package gardeshgary.khozestan.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "item")
public class Item implements Serializable {
	@Element(name = "id", required = false)
	private int id;

	@Element(name = "title", required = false)
	private String title;

	@Element(name = "link", required = false)
	private String link;

	//	@Attribute(required = false)
	@Element(name = "description", required = false)
	private String description;

	@Element(name = "pubDate", required = false)
	private String pubDate;

	@Element(name = "image", required = false)
	private String image;

	@Element(name = "category", required = false)
	private String category;

	@Element(name = "full_description", required = false)
	private String full_description;

	public Item() {
	}

	public Item(int id, String title, String link, String description, String pubDate, String image, String category, String full_description) {
		this.id = id;
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
		this.image = image;
		this.category = category;
		this.full_description = full_description;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFull_description() {
		return full_description;
	}

	public void setFull_description(String full_description) {
		this.full_description = full_description;
	}
}