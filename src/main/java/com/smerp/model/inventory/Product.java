package com.smerp.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smerp.model.master.AuditModel;
import com.smerp.model.master.HSNCode;
import com.smerp.model.master.SACCode;



@Entity
@Table(name="tbl_inventory_product")
public class Product extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id", nullable= false, unique=true)
	private Integer id;
	
	@Column(name="onventory_product")
	private String inventoryProduct;
	
	@Column(name="purchase_product")
	private String purchaseProduct;
	
	@Column(name="product_no")
	private String productNo;
	
	@Column(name="description")
	private String description;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_category_id")
	private ProductCategory productCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="uom_category_id")
	private UomCategory uomCategory;
	
	@Column(name="bar_code")
	private String barCode;
	
	@Column(name="with_old_tax_labiles")
	private String withOldTaxLiable;
	
	@Column(name="service")
	private String serviceOrProducr;
	
	@Column(name="gst")
	private String gst;
	
	@Column(name="product")
	private String product;
	
	@Column(name="service_type")
	private String serviceType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sac_id")
	private SACCode sacCode;
	
	@Column(name="tac_category")
	private String taxCategory;
	
	@Column(name="product_type")
	private String productType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hsn_id")
	private HSNCode hsnCode;
	
	@Column(name="product_tax_category")
	private String productTaxCategory;
	
	@Column(name="manage_product_by")
	private String manageProductBy;
	
	@Column(name="prefered_vendor")
	private String preferredVendor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "purchase_uom_id", referencedColumnName = "uom_id" )
	private Uom purchasingUom;
	
	@Column(name="product_per_purchase_unit")
	private String produtPerPurchaseUnit;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "packing_uom_id", referencedColumnName = "uom_id")
	private Uom packingUom;
	
	@Column(name="quantity_per_package")
	private String qualityPerPackage;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "inventory_uom_id", referencedColumnName = "uom_id")
	private Uom inventoryUom;
	
	@Column(name="minimum")
	private Integer minimun;
	
	@Column(name="maximum")
	private Integer maximim;
	
	@Column(name="valuation_method")
	private String valuationMethod;
	
	@Column(name="product_cost")
	private double productCost;
	
	@Column(name="is_delete")
	private Boolean isActive = true;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInventoryProduct() {
		return inventoryProduct;
	}

	public void setInventoryProduct(String inventoryProduct) {
		this.inventoryProduct = inventoryProduct;
	}

	public String getPurchaseProduct() {
		return purchaseProduct;
	}

	public void setPurchaseProduct(String purchaseProduct) {
		this.purchaseProduct = purchaseProduct;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public UomCategory getUomCategory() {
		return uomCategory;
	}

	public void setUomCategory(UomCategory uomCategory) {
		this.uomCategory = uomCategory;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getWithOldTaxLiable() {
		return withOldTaxLiable;
	}

	public void setWithOldTaxLiable(String withOldTaxLiable) {
		this.withOldTaxLiable = withOldTaxLiable;
	}

	

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public SACCode getSacCode() {
		return sacCode;
	}

	public void setSacCode(SACCode sacCode) {
		this.sacCode = sacCode;
	}

	public String getTaxCategory() {
		return taxCategory;
	}

	public void setTaxCategory(String taxCategory) {
		this.taxCategory = taxCategory;
	}

	

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductTaxCategory() {
		return productTaxCategory;
	}

	public void setProductTaxCategory(String productTaxCategory) {
		this.productTaxCategory = productTaxCategory;
	}

	public String getManageProductBy() {
		return manageProductBy;
	}

	public void setManageProductBy(String manageProductBy) {
		this.manageProductBy = manageProductBy;
	}

	public String getPreferredVendor() {
		return preferredVendor;
	}

	public void setPreferredVendor(String preferredVendor) {
		this.preferredVendor = preferredVendor;
	}

	

	public String getProdutPerPurchaseUnit() {
		return produtPerPurchaseUnit;
	}

	public void setProdutPerPurchaseUnit(String produtPerPurchaseUnit) {
		this.produtPerPurchaseUnit = produtPerPurchaseUnit;
	}

	

	public String getQualityPerPackage() {
		return qualityPerPackage;
	}

	public void setQualityPerPackage(String qualityPerPackage) {
		this.qualityPerPackage = qualityPerPackage;
	}

	

	public Integer getMinimun() {
		return minimun;
	}

	public void setMinimun(Integer minimun) {
		this.minimun = minimun;
	}

	public Integer getMaximim() {
		return maximim;
	}

	public void setMaximim(Integer maximim) {
		this.maximim = maximim;
	}

	public String getValuationMethod() {
		return valuationMethod;
	}

	public void setValuationMethod(String valuationMethod) {
		this.valuationMethod = valuationMethod;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public HSNCode getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(HSNCode hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Uom getPurchasingUom() {
		return purchasingUom;
	}

	public void setPurchasingUom(Uom purchasingUom) {
		this.purchasingUom = purchasingUom;
	}

	public Uom getPackingUom() {
		return packingUom;
	}

	public void setPackingUom(Uom packingUom) {
		this.packingUom = packingUom;
	}

	public Uom getInventoryUom() {
		return inventoryUom;
	}

	public void setInventoryUom(Uom inventoryUom) {
		this.inventoryUom = inventoryUom;
	}

	public String getServiceOrProducr() {
		return serviceOrProducr;
	}

	public void setServiceOrProducr(String serviceOrProducr) {
		this.serviceOrProducr = serviceOrProducr;
	}
	
	
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", inventoryProduct=" + inventoryProduct + ", purchaseProduct=" + purchaseProduct
				+ ", productNo=" + productNo + ", description=" + description + ", productCategory=" + productCategory
				+ ", uomCategory=" + uomCategory + ", barCode=" + barCode + ", withOldTaxLiable=" + withOldTaxLiable
				+ ", serviceOrProducr=" + serviceOrProducr + ", gst=" + gst + ", product=" + product + ", serviceType="
				+ serviceType + ", sacCode=" + sacCode + ", taxCategory=" + taxCategory + ", productType=" + productType
				+ ", hsnCode=" + hsnCode + ", productTaxCategory=" + productTaxCategory + ", manageProductBy="
				+ manageProductBy + ", preferredVendor=" + preferredVendor + ", purchasingUom=" + purchasingUom
				+ ", produtPerPurchaseUnit=" + produtPerPurchaseUnit + ", packingUom=" + packingUom
				+ ", qualityPerPackage=" + qualityPerPackage + ", inventoryUom=" + inventoryUom + ", minimun=" + minimun
				+ ", maximim=" + maximim + ", valuationMethod=" + valuationMethod + ", productCost=" + productCost
				+ ", isActive=" + isActive + "]";
	}

	
	
	

	
	
	
	
	
	
	

}
