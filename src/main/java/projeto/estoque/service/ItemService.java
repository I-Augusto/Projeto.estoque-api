package projeto.estoque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.estoque.model.Item;
import projeto.estoque.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public Item addItem(Item item) {
		if(itemRepository.existsByName(item.getName())) {
			throw new IllegalArgumentException("This product name already exists");
		}
		
		Optional.ofNullable(item.getName()).filter(n -> !n.trim().isEmpty())
		.orElseThrow(() -> new IllegalArgumentException("The name is empty"));
		
		Optional.ofNullable(item.getDescription()).filter(n -> !n.trim().isEmpty())
		.orElseThrow(() -> new IllegalArgumentException("The description is empty"));
		
		Optional.ofNullable(item.getPrice()).filter(n -> !n.isNaN())
		.orElseThrow(() -> new IllegalArgumentException("The price is empty"));
		
		return itemRepository.save(item);
	}
	
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	public Item getItemById(Long id) {
		return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
	}
	
	public Item updateNameItem(Long id, String name) {
		Item item = getItemById(id);
		item.setName(name); 
		return itemRepository.save(item);
	}
	
	public Item updateDescriptionItem(Long id, String descripton) {
		Item item = getItemById(id);
		item.setDescription(descripton); 
		return itemRepository.save(item);
	}
	
	public Item updatePriceItem(Long id, Double price) {
		Item item = getItemById(id);
		if (price >= 0) {
			item.setPrice(price);			
		}else {
			throw new IllegalArgumentException("price can't be negative");
		}
		return itemRepository.save(item);
	}
	
	public void deleteItem(Long id) {
		getItemById(id);
		itemRepository.deleteById(id);
	}
	
	public Item addUnit(Long id, int unit) {
		Item item = getItemById(id);
		int newUnit = item.getQuantity() + unit;
		item.setQuantity(newUnit);
		itemRepository.save(item);
		return item;
	}
	
	public Item removeUnit(Long id, int unit) {
		Item item = getItemById(id);
		int newUnit = item.getQuantity() - unit;
		if (newUnit >= 0) {
			item.setQuantity(newUnit); 
		} else {
			throw new IllegalArgumentException("insufficient quantity in stock");
		}
		itemRepository.save(item);
		return item;
	}
	
	
}
