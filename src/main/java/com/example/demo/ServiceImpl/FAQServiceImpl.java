package com.example.demo.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.FAQdto;
import com.example.demo.Entity.FAQ;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.FAQRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.FAQService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService{

	@Autowired
	private FAQRepository faqRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	//Post FAQ for specific product
	public FAQdto postFAQ(String productId, FAQdto faQdto)
	{
		Optional<Product> optionalProduct=this.productRepository.findById(productId);
		if(optionalProduct.isPresent())
		{
			FAQ faq=new FAQ();
			faq.setQuestion(faQdto.getQuestion());
			faq.setAnswer(faQdto.getAnswer());
			
			faq.setProduct(optionalProduct.get());
			
			return faqRepository.save(faq).getFaqDto();
		}
		return null;
	}
}
