package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.Dto.ReviewDto;

import lombok.Data;

@Entity
@Data
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	
	private Long rating;
	
	private String description;
	
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] img;
	
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id", nullable =false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name="product_id", nullable =false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Product product;
	
	public ReviewDto getDto()
	{
		ReviewDto reviewDto=new ReviewDto();
		
		reviewDto.setReviewId(reviewId);
		reviewDto.setRating(rating);
		reviewDto.setDescription(description);
		reviewDto.setReturnedImg(img);
		reviewDto.setProductId(product.getProductId());
		reviewDto.setUserId(user.getId());
		reviewDto.setUsername(user.getUsername());
		
		return reviewDto;
	}
	
}
