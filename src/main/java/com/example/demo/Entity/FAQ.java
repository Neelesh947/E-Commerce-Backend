package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.Dto.FAQdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FAQ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String question;
	private String answer;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="product_id", nullable = false) 
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Product product;
	
	public FAQdto getFaqDto() {
		FAQdto faqdto=new FAQdto();
		faqdto.setId(id);
		faqdto.setQuestion(question);
		faqdto.setAnswer(answer);
		faqdto.setProductId(product.getProductId());
		return faqdto;
	}
	
}
