package entities;

import javax.persistence.*;


@Entity
@Table
public class Book {
    @Id
    @GeneratedValue
    private int id;
    
    private String title;
    

    private int year;

    private int price;
    

  
    private Publisher publisher;

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public Publisher getPublisher() {
		return publisher;
	}


	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}


}
