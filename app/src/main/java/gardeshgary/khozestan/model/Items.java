package gardeshgary.khozestan.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class Items {
	@ElementList(name = "item", inline = true)
	protected List<Item> item;

	public Items() {}

	public Items(List<Item> item) {
		this.item = item;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
}