package projeto.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projeto.estoque.model.Item;
import projeto.estoque.service.ItemService;

@RestController
@RequestMapping("/")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@PostMapping()
	public ResponseEntity<Item> addItem(Item item){
		Item newItem = itemService.addItem(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
	}
	
	@GetMapping()
	public ResponseEntity<List<Item>> getAllItems(){
		List<Item> itemList = itemService.getAllItems();
		return ResponseEntity.ok(itemList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id){
		Item item = itemService.getItemById(id);
		return ResponseEntity.ok(item);
	}
	
	@PatchMapping("/{id}/update-name")
	public ResponseEntity<Item> updateName(@PathVariable Long id,@RequestParam String name){
		Item item = itemService.updateNameItem(id, name);
		return ResponseEntity.ok(item);
	}
	
	@PatchMapping("/{id}/update-description")
	public ResponseEntity<Item> updateDescription(@PathVariable Long id,@RequestParam String description){
		Item item = itemService.updateDescriptionItem(id, description);
		return ResponseEntity.ok(item);
	}
	
	@PatchMapping("/{id}/update-price")
	public ResponseEntity<Item> updatePrice(@PathVariable Long id,@RequestParam Double price){
		Item item = itemService.updatePriceItem(id, price);
		return ResponseEntity.ok(item);
	}
	
	@PatchMapping("/{id}/add-unit")
	public ResponseEntity<Item> addUnitItem(@PathVariable Long id,@RequestParam int unit){
		Item item = itemService.addUnit(id, unit);
		return ResponseEntity.ok(item);
	}
	
	@PatchMapping("/{id}/remove-unit")
	public ResponseEntity<Item> removeUnitItem(@PathVariable Long id,@RequestParam int unit){
		Item item = itemService.removeUnit(id, unit);
		return ResponseEntity.ok(item);
	}
	
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
	}
	
	
	
}
