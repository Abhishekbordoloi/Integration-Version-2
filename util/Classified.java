package util;

import java.util.Date;

import until.enumeration.CategoryType;
import until.enumeration.ClassifiedStatus;

public class Classified {

	public Classified(Integer classifiedId, String title, double price, String description, CategoryType category, Date createdAt,
			String createdBy, ClassifiedStatus status) {
		//super();
		this.classifiedId = classifiedId;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.status = status;
	}
	
	public Classified(String title, double price, String description, CategoryType category, Date createdAt,
			String createdBy, ClassifiedStatus status) {
		this(null, title, price, description, category, createdAt, createdBy, status);
	}
	
	public Classified() {
	}
	
	private Integer classifiedId;
	private String title;
	private double price;
	private String description;
	//private String category;
	private CategoryType category = CategoryType.OTHERS;
	private Date createdAt;
	private String createdBy;
	private ClassifiedStatus status = ClassifiedStatus.NA;
	
	//Getter And Setter
	public int getClassifiedId() {
		return classifiedId;
	}
	public void setClassifiedId(int classifiedId) {
		this.classifiedId = classifiedId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryType getCategory() {
		return category;
	}
	public void setCategory(CategoryType category) {
		this.category = category;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public ClassifiedStatus getStatus() {
		return status;
	}
	public void setStatus(ClassifiedStatus status) {
		this.status = status;
	}
	//toString function called
	@Override
	public String toString() {
		return "{classifiedId=" + classifiedId + ", title=" + title + ", price=" + price + ", description="
				+ description + ", category=" + category + ", createdAt=" + createdAt + ", createdBy=" + createdBy
				+ ", status=" + status + "}\n";
	}

	public void setId(Integer classifiedId) {
		this.classifiedId= classifiedId;
	}
}


