package br.com.ifood.shop.catalog.job.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Date lastDate;
	
	public Job(String name) {
		this.name = name;
	}
}
