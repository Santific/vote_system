package com.vs.datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dish", catalog = "public")
public class Dish implements Serializable {
	
    @EmbeddedId
    private DishKey dishKey;
	private Float price;

	public DishKey getDishKey() {
		return dishKey;
	}

	public void setDishKey(DishKey dishKey) {
		this.dishKey = dishKey;
	}
	
	@Column(name = "price", nullable = false)
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}

	@Embeddable
	public static class DishKey implements Serializable {
		
		private String restorantName;
		private String dishName;
		private java.sql.Date date;

		@Column(name = "name", nullable = false)
		public String getDishName() {
			return dishName;
		}
		
		public void setDishName(String dishName) {
			this.dishName = dishName;
		}
		
		@Column(name = "date", nullable = false)
		public java.util.Date getDate() {
			return date;
		}

		public void setDate(java.sql.Date date) {
			this.date = date;
		}

		@Column(name = "restorant_name", nullable = false)
		public String getRestorantName() {
			return restorantName;
		}

		public void setRestorantName(String restorantName) {
			this.restorantName = restorantName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result
					+ ((dishName == null) ? 0 : dishName.hashCode());
			result = prime * result
					+ ((restorantName == null) ? 0 : restorantName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DishKey other = (DishKey) obj;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (dishName == null) {
				if (other.dishName != null)
					return false;
			} else if (!dishName.equals(other.dishName))
				return false;
			if (restorantName == null) {
				if (other.restorantName != null)
					return false;
			} else if (!restorantName.equals(other.restorantName))
				return false;
			return true;
		}

	}
}
