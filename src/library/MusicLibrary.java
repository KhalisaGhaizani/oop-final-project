package library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MusicLibrary<T> {
	 private List<T> items;
	 
	 public MusicLibrary() {
	     this.items = new ArrayList<>();
	 }
	 
	 public void add(T item) {
	     items.add(item);
	 }
	 
	 public List<T> getAll() {
	     return items;
	 }
	 
	 public void update(int index, T item) {
	     if (index >= 0 && index < items.size()) {
	         items.set(index, item);
	     }
	 }
	 
	 public void remove(int index) {
	     if (index >= 0 && index < items.size()) {
	         items.remove(index);
	     }
	 }
	 
	 public void sort(Comparator<T> comparator) {
	     items.sort(comparator);
	 }
}
