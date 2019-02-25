package com.smerp.model.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_docNo_auto_gen_master")
public class DocNumAutoGenerate {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id" , nullable=false, unique= true)
	private Integer id;
	
	@Column(name="doc_type")
	private String docType;
	
	@Column(name="count")
	private Integer count;

	
	public DocNumAutoGenerate() {
		super();
	}

	public DocNumAutoGenerate(Integer id, String docType, Integer count) {
		super();
		this.id = id;
		this.docType = docType;
		this.count = count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DocNoAutoGenerator [id=" + id + ", docType=" + docType + ", count=" + count + "]";
	}
	
	
}
