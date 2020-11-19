package entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
public class Product {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id; 

	@Column(name="name", nullable=false, unique = true)
	private String name; 

	@Column(name="price", nullable=false)
	private String price; 

	@Column(name="description", nullable=false)
	private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "image1")
    private String image1;

    @Column(name = "image2")
    private String image2;

    @Column(name = "image3")
    private String image3;

    @Column(name = "date")
    private Date date;

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price= price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
