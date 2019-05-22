package com.smerp.util;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.krysalis.barcode4j.impl.postnet.POSTNET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smerp.model.admin.Department;
import com.smerp.model.admin.Plant;
import com.smerp.model.inventory.CreditMemo;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReceiptLineItems;
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.LineItemsBean;
import com.smerp.model.inventory.ProductType;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.service.admin.DepartmentService;
import com.smerp.service.inventory.ProductTypeService;
import com.smerp.service.inventorytransactions.InventoryGoodsIssueService;
import com.smerp.service.inventorytransactions.InventoryGoodsReceiptService;
import com.smerp.service.inventorytransactions.InventoryGoodsTransferService;
import com.smerp.service.master.PlantService;
import com.smerp.service.purchase.CreditMemoService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.service.purchase.InVoiceService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.serviceImpl.purchase.RequestForQuotationServiceImpl;

@Component
public class DownloadReportsXLS {
	
	private static final Logger logger = LogManager.getLogger(DownloadReportsXLS.class);
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private GoodsReceiptService goodsReceiptService;
	
	@Autowired
	private GoodsReturnService goodsReturnService;
	
	@Autowired
	private InVoiceService inVoiceService;
	
	@Autowired 
	private CreditMemoService creditMemoService;

	@Autowired
	private XLSXDownload xLSXDownload;
	
	@Autowired
	private PlantService plantService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	InventoryGoodsReceiptService inventoryGoodsReceiptService;
	
	@Autowired
	InventoryGoodsIssueService inventoryGoodsIssueService;
	
	@Autowired
	InventoryGoodsTransferService inventoryGoodsTransferService;
	
	@PersistenceContext    
	private EntityManager entityManager;
	
	public static final String SNO = "S.No.";
	public static final String VENDORCODE = "Vendor Code";
	public static final String VENDOR = "Vendor Name";
	public static final String USER = "User Name";
	public static final String REQUESTORNAME = "Requestor Name";
	public static final String REQUESTEDDATE = "Requested Date";
	public static final String REQUIREDDATE = "Required Date";
	public static final String EMAIL = "Email Id";
	public static final String DOC = "Document Number";
	public static final String PRDOC = "PR Doc#";
	public static final String STATUS = "Status";
	public static final String PRODUCTNUMBER = "Product Number";
	public static final String PRODUCTCODE = "Product Code";
	public static final String PRODUCTDESCRIPTION = "Product Description";
	public static final String UOM = "UOM";
	public static final String PRODUCTGROUP = "Product Group";
	public static final String QUANTITY = "Quantity";
    public static final String ORDERQUANTITY = "Ordered Quantity";
    public static final String PENDINGQUANTITY = "Pending Quantity";
    public static final String RECEIVEDQUANTITY = " Received Quantity";
    public static final String RETURNEDQUANTITY = "Returned Quantity";
    public static final String INVOICEDQUANTITY = "Invoiced  Quantity";
    public static final String PLANTNAME = "Plant Name";
    public static final String WAREHOUSE = "Warehouse";
    public static final String UNITPRICE = "Unit Price";
    public static final String TAXDESCRIPTION = "Tax Description";
    public static final String TAXAMOUNT = "Tax Amount";
    public static final String LINETOTAL = "Line Total";
    public static final String DISCOUNT = "Discount";
    public static final String FREIGHT = "Freight";
    public static final String PAYTO = "Pay To";
    public static final String SHIPFROM = "Ship From";
    public static final String SHIPTO = "Ship To";
    public static final String TOTALPAYMENT = "Document Total";
    public static final String REMARKS = "Remarks";
	public static final String NEWLINE = "\n";
	public static final String SEMICOLUMN = ";";
	public static final String BLANK ="";
	public static final String ZERO ="0";
	public static final String COMMA =", ";
	public static final String DOCDATE = "Document Date";
	public static final String DEPARTMENT = "Department";
	public static final String PO = "Purchase Order";

	private static final String DOCNUMBER = "Doc#";

	private static final String FROM = "From";
	private static final String TO = "To";
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private String getGRHeader() {
		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN  + VENDOR + SEMICOLUMN  + DOC +  SEMICOLUMN + DOCDATE + SEMICOLUMN   + PRODUCTCODE
			    + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN  + PRODUCTGROUP + SEMICOLUMN +  UOM + SEMICOLUMN  + WAREHOUSE + SEMICOLUMN + RECEIVEDQUANTITY 
			    + SEMICOLUMN + UNITPRICE + SEMICOLUMN + TAXDESCRIPTION + SEMICOLUMN + TAXAMOUNT + SEMICOLUMN + LINETOTAL + SEMICOLUMN + DISCOUNT + SEMICOLUMN 
				+ FREIGHT + SEMICOLUMN + PAYTO + SEMICOLUMN + SHIPFROM + SEMICOLUMN + SHIPTO + SEMICOLUMN + REMARKS + SEMICOLUMN + STATUS + SEMICOLUMN +  NEWLINE;
		return heading;
	}
	
	private String getPOHeader() {
		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN  + VENDOR + SEMICOLUMN  + DOC +  SEMICOLUMN + DOCDATE + SEMICOLUMN   + PRODUCTCODE
			    + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN  + PRODUCTGROUP + SEMICOLUMN +  UOM + SEMICOLUMN  + WAREHOUSE + SEMICOLUMN + ORDERQUANTITY + SEMICOLUMN
			    + RECEIVEDQUANTITY + SEMICOLUMN + PENDINGQUANTITY+ SEMICOLUMN + UNITPRICE + SEMICOLUMN + TAXDESCRIPTION + SEMICOLUMN + TAXAMOUNT + SEMICOLUMN + LINETOTAL + SEMICOLUMN + DISCOUNT + SEMICOLUMN 
				+ FREIGHT + SEMICOLUMN + PAYTO + SEMICOLUMN + SHIPFROM + SEMICOLUMN + SHIPTO + SEMICOLUMN + REMARKS + SEMICOLUMN + STATUS + SEMICOLUMN + NEWLINE;
		return heading;
	}
	
	private String getGREHeader() {
		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN  + VENDOR + SEMICOLUMN  + DOC +  SEMICOLUMN + DOCDATE + SEMICOLUMN   + PRODUCTCODE
			    + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN  + PRODUCTGROUP + SEMICOLUMN +  UOM + SEMICOLUMN  + WAREHOUSE + SEMICOLUMN + RETURNEDQUANTITY 
			    + SEMICOLUMN + UNITPRICE + SEMICOLUMN + TAXDESCRIPTION + SEMICOLUMN + TAXAMOUNT + SEMICOLUMN + LINETOTAL + SEMICOLUMN + DISCOUNT + SEMICOLUMN 
				+ FREIGHT + SEMICOLUMN + PAYTO + SEMICOLUMN + SHIPFROM + SEMICOLUMN + SHIPTO + SEMICOLUMN + REMARKS + SEMICOLUMN + STATUS + SEMICOLUMN +  NEWLINE;
		return heading;
	}
	
	private String getInvHeader() {
		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN  + VENDOR + SEMICOLUMN  + DOC +  SEMICOLUMN + DOCDATE + SEMICOLUMN   + PRODUCTCODE
			    + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN  + PRODUCTGROUP + SEMICOLUMN +  UOM + SEMICOLUMN  + WAREHOUSE + SEMICOLUMN + INVOICEDQUANTITY 
			    + SEMICOLUMN + UNITPRICE + SEMICOLUMN + TAXDESCRIPTION + SEMICOLUMN + TAXAMOUNT + SEMICOLUMN + LINETOTAL + SEMICOLUMN + DISCOUNT + SEMICOLUMN 
				+ FREIGHT + SEMICOLUMN + PAYTO + SEMICOLUMN + SHIPFROM + SEMICOLUMN + SHIPTO + SEMICOLUMN + REMARKS + SEMICOLUMN + STATUS + SEMICOLUMN +  NEWLINE;
		return heading;
	}
	
	private String getCMHeader() {
		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN  + VENDOR + SEMICOLUMN  + DOC +  SEMICOLUMN + DOCDATE + SEMICOLUMN   + PRODUCTCODE
			    + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN  + PRODUCTGROUP + SEMICOLUMN +  UOM + SEMICOLUMN  + WAREHOUSE + SEMICOLUMN + RETURNEDQUANTITY 
			    + SEMICOLUMN + UNITPRICE + SEMICOLUMN + TAXDESCRIPTION + SEMICOLUMN + TAXAMOUNT + SEMICOLUMN + LINETOTAL + SEMICOLUMN + DISCOUNT + SEMICOLUMN 
				+ FREIGHT + SEMICOLUMN + PAYTO + SEMICOLUMN + SHIPFROM + SEMICOLUMN + SHIPTO + SEMICOLUMN + REMARKS + SEMICOLUMN + STATUS + SEMICOLUMN +  NEWLINE;
		return heading;
	}
	
	private String getPRHeader() {
		String heading = SNO + SEMICOLUMN + DOC + SEMICOLUMN + DOCDATE + SEMICOLUMN  + REQUESTEDDATE + SEMICOLUMN  + REQUIREDDATE  + SEMICOLUMN  + REQUESTORNAME + SEMICOLUMN  + PRODUCTCODE
			    + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN  + PRODUCTGROUP + SEMICOLUMN   + UOM + SEMICOLUMN + QUANTITY 
				+ SEMICOLUMN + WAREHOUSE +  SEMICOLUMN + REMARKS + SEMICOLUMN + STATUS +  NEWLINE;
		return heading;
	}
	
	private String getRFQHeader() {
		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN  + VENDOR + SEMICOLUMN  +  DOC +  SEMICOLUMN +  DOCDATE + SEMICOLUMN  + REQUIREDDATE  + SEMICOLUMN  + PRODUCTCODE
			    + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN  + PRODUCTGROUP + SEMICOLUMN   +  UOM + SEMICOLUMN +  QUANTITY
				+ SEMICOLUMN + WAREHOUSE +  SEMICOLUMN + REMARKS + SEMICOLUMN + STATUS + NEWLINE;
		return heading;
	}
	
	public ByteArrayOutputStream PRReport(List<PurchaseRequest> prList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		
		int index = 1;
		if (!prList.isEmpty() && prList != null) {
			for (PurchaseRequest list : prList) {
				for(int i=0; i< list.getPurchaseRequestLists().size(); i++) {
				excelData = index + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
						(sdf.format(list.getPostingDate())==null ? BLANK : sdf.format(list.getPostingDate())) + SEMICOLUMN +
						(sdf.format(list.getRequiredDate())==null ? BLANK : sdf.format(list.getRequiredDate())) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getReferenceUser().getFirstname()) ? BLANK : list.getReferenceUser().getFirstname()+" "+list.getReferenceUser().getLastname()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getPurchaseRequestLists().get(i).getProdouctNumber())? BLANK : list.getPurchaseRequestLists().get(i).getProdouctNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getPurchaseRequestLists().get(i).getDescription())? BLANK : list.getPurchaseRequestLists().get(i).getDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(getPGDescription(list.getPurchaseRequestLists().get(i).getProductGroup()))? BLANK : getPGDescription(list.getPurchaseRequestLists().get(i).getProductGroup())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getPurchaseRequestLists().get(i).getUom())? BLANK : list.getPurchaseRequestLists().get(i).getUom()) + SEMICOLUMN +
						(list.getPurchaseRequestLists().get(i).getRequiredQuantity()==null? BLANK : list.getPurchaseRequestLists().get(i).getRequiredQuantity()) + SEMICOLUMN + 
						(getWarehouseName(list.getPurchaseRequestLists().get(i).getWarehouse())==null? BLANK : getWarehouseName(list.getPurchaseRequestLists().get(i).getWarehouse())) + SEMICOLUMN +
						//(list.getPurchaseRequestLists().get(i).getUnitPrice()==null? BLANK : list.getPurchaseRequestLists().get(i).getUnitPrice()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getRemarks().toString())? BLANK : list.getRemarks()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
						NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
				
		}
		 
		excelReport =  getPRHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
	public ByteArrayOutputStream RFQReport(List<RequestForQuotation> rfqList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		
		int index = 1;
		if (!rfqList.isEmpty() && rfqList != null) {
			for (RequestForQuotation list : rfqList) {
				for(int i=0; i< list.getLineItems().size(); i++) {
					
				excelData = index + SEMICOLUMN + 
						(list.getVendor() == null ? BLANK : list.getVendor().getVendorCode()) + SEMICOLUMN +
						(list.getVendor() == null ? BLANK : list.getVendor().getName()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(list.getRequiredDate()))? BLANK : sdf.format(list.getRequiredDate())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getLineItems().get(i).getProdouctNumber())? BLANK : list.getLineItems().get(i).getProdouctNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getLineItems().get(i).getDescription())? BLANK : list.getLineItems().get(i).getDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(getPGDescription(list.getLineItems().get(i).getProductGroup()))? BLANK : getPGDescription(list.getLineItems().get(i).getProductGroup())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getLineItems().get(i).getUom())? BLANK : list.getLineItems().get(i).getUom()) + SEMICOLUMN +
						(list.getLineItems().get(i).getRequiredQuantity()==null? BLANK : list.getLineItems().get(i).getRequiredQuantity()) + SEMICOLUMN + 
						//(list.getLineItems().get(i).getUnitPrice()==null? BLANK : list.getLineItems().get(i).getUnitPrice()) + SEMICOLUMN + 
						(getWarehouseName(list.getLineItems().get(i).getWarehouse())==null? BLANK : getWarehouseName(list.getLineItems().get(i).getWarehouse())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getRemark().toString())? BLANK : list.getRemark()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
						NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
				
		}
		 
		excelReport =  getRFQHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
	private List<Integer> getPendingQty(int poId,String product_number){
		String sql= "select id,product_number,pending_quantity,available_quantity from vw_purchase_order_pending_qty  where id =" + poId + " and product_number= " + "'" + product_number + "'";
		logger.info("Sql ----> " + sql);
		List<Integer> listQuantity = new ArrayList<Integer>();
		 Integer pendingQty = 0;
		 Integer availableQty = 0; 
		   Query query = entityManager.createNativeQuery(sql);
		   List<Object[]>	pendingQtyList = query.getResultList();
		    
		    for(Object[] tuple : pendingQtyList) {
		    	pendingQty = ((Integer)(tuple[2] == null ? 0 : (Integer.parseInt((tuple[2].toString())))));
		    	availableQty = ((Integer)(tuple[3] == null ? 0 : (Integer.parseInt((tuple[3].toString())))));
		    	logger.info("pendingQty ----> " + pendingQty);
		    	listQuantity.add(pendingQty);
		    	listQuantity.add(availableQty);
		    }
		
		return listQuantity;
		
	}
	
	public ByteArrayOutputStream POReport(List<PurchaseOrder> poList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		
		int index = 1;
		if (!poList.isEmpty() && poList != null) {
			for (PurchaseOrder list : poList) {
				for(int i=0; i< list.getPurchaseOrderlineItems().size(); i++) {
					list = purchaseOrderService.getListAmount(list);
					
					List<Integer> listQuantity = 	getPendingQty(list.getId(),list.getPurchaseOrderlineItems().get(i).getProdouctNumber());
					
					
				excelData = index + SEMICOLUMN + 
						(list.getVendor() == null ? BLANK : list.getVendor().getVendorCode()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getVendor().getName()) ? BLANK : list.getVendor().getName()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getPurchaseOrderlineItems().get(i).getProdouctNumber())? BLANK : list.getPurchaseOrderlineItems().get(i).getProdouctNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getPurchaseOrderlineItems().get(i).getDescription())? BLANK : list.getPurchaseOrderlineItems().get(i).getDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(getPGDescription(list.getPurchaseOrderlineItems().get(i).getProductGroup()))? BLANK : getPGDescription(list.getPurchaseOrderlineItems().get(i).getProductGroup())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getPurchaseOrderlineItems().get(i).getUom())? BLANK : list.getPurchaseOrderlineItems().get(i).getUom()) + SEMICOLUMN +
						(getWarehouseName(list.getPurchaseOrderlineItems().get(i).getWarehouse())==null? BLANK : getWarehouseName(list.getPurchaseOrderlineItems().get(i).getWarehouse())) + SEMICOLUMN +
						(list.getPurchaseOrderlineItems().get(i).getRequiredQuantity()==null? BLANK : list.getPurchaseOrderlineItems().get(i).getRequiredQuantity()) + SEMICOLUMN + 
						(listQuantity==null? BLANK : listQuantity.get(1)) + SEMICOLUMN + //1 = available quantity
						(listQuantity==null? BLANK : listQuantity.get(0)) + SEMICOLUMN + //0 = pending quantity
						(list.getPurchaseOrderlineItems().get(i).getUnitPrice()==null? BLANK : list.getPurchaseOrderlineItems().get(i).getUnitPrice()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getPurchaseOrderlineItems().get(i).getTaxDescription())? BLANK : list.getPurchaseOrderlineItems().get(i).getTaxDescription()) + SEMICOLUMN +
						(list.getPurchaseOrderlineItems().get(i).getTaxTotal()==null? ZERO : list.getPurchaseOrderlineItems().get(i).getTaxTotal()) + SEMICOLUMN + 
						(list.getPurchaseOrderlineItems().get(i).getTotal()==null? ZERO : list.getPurchaseOrderlineItems().get(i).getTotal()) + SEMICOLUMN + 
						(list.getTotalDiscount()==null? "0" : list.getTotalDiscount()) + SEMICOLUMN +
						(list.getFreight()==null? ZERO : list.getFreight()) + SEMICOLUMN +
						
						/* Vendor Pay To Address */
						(list.getVendorPayTypeAddress() == null ? BLANK : list.getVendorPayTypeAddress().getAddressName() +COMMA+ list.getVendorPayTypeAddress().getStreet() + COMMA 
								+list.getVendorPayTypeAddress().getCity() +COMMA+list.getVendorPayTypeAddress().getZipCode() ) + SEMICOLUMN +
						
						/* Vendor Shipping From Address */
						(list.getVendorShippingAddress() == null ? BLANK : list.getVendorShippingAddress().getAddressName() +COMMA+ list.getVendorShippingAddress().getStreet() + COMMA 
								+list.getVendorShippingAddress().getCity() +COMMA+list.getVendorShippingAddress().getZipCode() ) + SEMICOLUMN +
						
						/* Vendor Ship To Address */
						(list.getDeliverTo() == null ? BLANK : list.getDeliverTo()) + SEMICOLUMN +
												
					//	(list.getTotalPayment()==null? BLANK : list.getTotalPayment()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getRemark().toString())? BLANK : list.getRemark()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
						NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
				
		}
		 
		excelReport =  getPOHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
	public ByteArrayOutputStream GRReport(List<GoodsReceipt> grList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		
		int index = 1;
		if (!grList.isEmpty() && grList != null) {
			for (GoodsReceipt grItem : grList) {
				for(int i=0; i< grItem.getGoodsReceiptLineItems().size(); i++) {
					grItem = goodsReceiptService.getListAmount(grItem);
					GoodsReceipt gr = null;
					
					
				excelData = index + SEMICOLUMN + 
						(grItem.getVendor() == null ? BLANK : grItem.getVendor().getVendorCode()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(grItem.getVendor().getName()) ? BLANK : grItem.getVendor().getName()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(grItem.getDocNumber())? BLANK : grItem.getDocNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(grItem.getDocumentDate()))? BLANK : sdf.format(grItem.getDocumentDate())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(grItem.getGoodsReceiptLineItems().get(i).getProdouctNumber())? BLANK : grItem.getGoodsReceiptLineItems().get(i).getProdouctNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(grItem.getGoodsReceiptLineItems().get(i).getDescription())? BLANK : grItem.getGoodsReceiptLineItems().get(i).getDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(getPGDescription(grItem.getGoodsReceiptLineItems().get(i).getProductGroup()))? BLANK : getPGDescription(grItem.getGoodsReceiptLineItems().get(i).getProductGroup())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(grItem.getGoodsReceiptLineItems().get(i).getUom())? BLANK : grItem.getGoodsReceiptLineItems().get(i).getUom()) + SEMICOLUMN +
						(getWarehouseName(grItem.getGoodsReceiptLineItems().get(i).getWarehouse())==null? BLANK : getWarehouseName(grItem.getGoodsReceiptLineItems().get(i).getWarehouse())) + SEMICOLUMN +
						(grItem.getGoodsReceiptLineItems().get(i).getRequiredQuantity()==null? BLANK : grItem.getGoodsReceiptLineItems().get(i).getRequiredQuantity()) + SEMICOLUMN + 
						//(gr ==null? ZERO : gr.getGoodsReceiptLineItems().get(i).getTempRequiredQuantity()) + SEMICOLUMN +
						(grItem.getGoodsReceiptLineItems().get(i).getUnitPrice()==null? BLANK : grItem.getGoodsReceiptLineItems().get(i).getUnitPrice()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(grItem.getGoodsReceiptLineItems().get(i).getTaxDescription())? BLANK : grItem.getGoodsReceiptLineItems().get(i).getTaxDescription()) + SEMICOLUMN +
						(grItem.getGoodsReceiptLineItems().get(i).getTaxTotal()==null? ZERO : grItem.getGoodsReceiptLineItems().get(i).getTaxTotal()) + SEMICOLUMN + 
						(grItem.getGoodsReceiptLineItems().get(i).getTotal()==null? ZERO : grItem.getGoodsReceiptLineItems().get(i).getTotal()) + SEMICOLUMN + 
						(grItem.getTotalDiscount()==null? ZERO : grItem.getTotalDiscount()) + SEMICOLUMN +
						(grItem.getFreight()==null? ZERO : grItem.getFreight()) + SEMICOLUMN +
						
						/* Vendor Pay To Address */
						(grItem.getVendorPayTypeAddress() == null ? BLANK : grItem.getVendorPayTypeAddress().getAddressName() +COMMA+ grItem.getVendorPayTypeAddress().getStreet() + COMMA 
								+grItem.getVendorPayTypeAddress().getCity() +COMMA+grItem.getVendorPayTypeAddress().getZipCode() ) + SEMICOLUMN +
						
						/* Vendor Shipping From Address */
						(grItem.getVendorShippingAddress() == null ? BLANK : grItem.getVendorShippingAddress().getAddressName() +COMMA+ grItem.getVendorShippingAddress().getStreet() + COMMA 
								+grItem.getVendorShippingAddress().getCity() +COMMA+grItem.getVendorShippingAddress().getZipCode() ) + SEMICOLUMN +
						
						/* Vendor Ship To Address */
						(grItem.getDeliverTo() == null ? BLANK : grItem.getDeliverTo()) + SEMICOLUMN +
						
					//	(grItem.getTotalPayment()==null? BLANK : grItem.getTotalPayment()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(grItem.getRemark().toString())? BLANK : grItem.getRemark()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(grItem.getStatus())? BLANK : grItem.getStatus()) + SEMICOLUMN + NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
				
		}
		 
		excelReport =  getGRHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
	public ByteArrayOutputStream GREReport(List<GoodsReturn> greList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		
		int index = 1;
		if (!greList.isEmpty() && greList != null) {
			for (GoodsReturn list : greList) {
				for(int i=0; i< list.getGoodsReturnLineItems().size(); i++) {
					list = goodsReturnService.getListAmount(list);
				//	GoodsReturn gre = goodsReturnService.getGoodsReturnById(Integer.valueOf(list.getId()));
				excelData = index + SEMICOLUMN + 
						(list.getVendor() == null ? BLANK : list.getVendor().getVendorCode()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getVendor().getName()) ? BLANK : list.getVendor().getName()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getGoodsReturnLineItems().get(i).getProdouctNumber())? BLANK : list.getGoodsReturnLineItems().get(i).getProdouctNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getGoodsReturnLineItems().get(i).getDescription())? BLANK : list.getGoodsReturnLineItems().get(i).getDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(getPGDescription(list.getGoodsReturnLineItems().get(i).getProductGroup()))? BLANK : getPGDescription(list.getGoodsReturnLineItems().get(i).getProductGroup())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getGoodsReturnLineItems().get(i).getUom())? BLANK : list.getGoodsReturnLineItems().get(i).getUom()) + SEMICOLUMN +
						(getWarehouseName(list.getGoodsReturnLineItems().get(i).getWarehouse())==null? BLANK : getWarehouseName(list.getGoodsReturnLineItems().get(i).getWarehouse())) + SEMICOLUMN +
						(list.getGoodsReturnLineItems().get(i).getRequiredQuantity()==null? BLANK : list.getGoodsReturnLineItems().get(i).getRequiredQuantity()) + SEMICOLUMN + 
						//(gre.getGoodsReturnLineItems().get(i).getTempRequiredQuantity() ==null? ZERO : gre.getGoodsReturnLineItems().get(i).getTempRequiredQuantity()) + SEMICOLUMN +
						(list.getGoodsReturnLineItems().get(i).getUnitPrice()==null? BLANK : list.getGoodsReturnLineItems().get(i).getUnitPrice()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getGoodsReturnLineItems().get(i).getTaxDescription())? BLANK : list.getGoodsReturnLineItems().get(i).getTaxDescription()) + SEMICOLUMN +
						(list.getGoodsReturnLineItems().get(i).getTaxTotal()==null? ZERO : list.getGoodsReturnLineItems().get(i).getTaxTotal()) + SEMICOLUMN + 
						(list.getGoodsReturnLineItems().get(i).getTotal()==null? ZERO : list.getGoodsReturnLineItems().get(i).getTotal()) + SEMICOLUMN + 
						(list.getTotalDiscount()==null? ZERO : list.getTotalDiscount()) + SEMICOLUMN +
						(list.getFreight()==null? ZERO : list.getFreight()) + SEMICOLUMN +
						
						/* Vendor Pay To Address */
						(list.getVendorPayTypeAddress() == null ? BLANK : list.getVendorPayTypeAddress().getAddressName() +COMMA+ list.getVendorPayTypeAddress().getStreet() + COMMA 
								+list.getVendorPayTypeAddress().getCity() +COMMA+list.getVendorPayTypeAddress().getZipCode() ) + SEMICOLUMN +
						
						/* Vendor Shipping From Address */
						(list.getVendorShippingAddress() == null ? BLANK : list.getVendorShippingAddress().getAddressName() +COMMA+ list.getVendorShippingAddress().getStreet() + COMMA 
								+list.getVendorShippingAddress().getCity() +COMMA+list.getVendorShippingAddress().getZipCode() ) + SEMICOLUMN +
						
						/* Vendor Ship To Address */
						(list.getDeliverTo() == null ? BLANK : list.getDeliverTo()) + SEMICOLUMN +
						
					//	(list.getTotalPayment()==null? BLANK : list.getTotalPayment()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getRemark().toString())? BLANK : list.getRemark()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
						NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
		}
		excelReport =  getGREHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;	
		}
		
		public ByteArrayOutputStream invReport(List<InVoice> invList) throws Exception {
			String excelReport = "";
			String excelData = "";
			String concat = "";
			
			int index = 1;
			if (!invList.isEmpty() && invList != null) {
				for (InVoice list : invList) {
					for(int i=0; i< list.getInVoiceLineItems().size(); i++) {
						list = inVoiceService.getListAmount(list);
					excelData = index + SEMICOLUMN + 
							(list.getVendor() == null ? BLANK : list.getVendor().getVendorCode()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getVendor().getName()) ? BLANK : list.getVendor().getName()) + SEMICOLUMN + 
							(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getInVoiceLineItems().get(i).getProdouctNumber())? BLANK : list.getInVoiceLineItems().get(i).getProdouctNumber()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getInVoiceLineItems().get(i).getDescription())? BLANK : list.getInVoiceLineItems().get(i).getDescription()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(getPGDescription(list.getInVoiceLineItems().get(i).getProductGroup()))? BLANK : getPGDescription(list.getInVoiceLineItems().get(i).getProductGroup())) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getInVoiceLineItems().get(i).getUom())? BLANK : list.getInVoiceLineItems().get(i).getUom()) + SEMICOLUMN +
							(getWarehouseName(list.getInVoiceLineItems().get(i).getWarehouse())==null? BLANK : getWarehouseName(list.getInVoiceLineItems().get(i).getWarehouse())) + SEMICOLUMN +
							(list.getInVoiceLineItems().get(i).getRequiredQuantity()==null? BLANK : list.getInVoiceLineItems().get(i).getRequiredQuantity()) + SEMICOLUMN + 
							(list.getInVoiceLineItems().get(i).getUnitPrice()==null? BLANK : list.getInVoiceLineItems().get(i).getUnitPrice()) + SEMICOLUMN + 
							(StringUtil.isEmptyTrim(list.getInVoiceLineItems().get(i).getTaxDescription())? BLANK : list.getInVoiceLineItems().get(i).getTaxDescription()) + SEMICOLUMN +
							(list.getInVoiceLineItems().get(i).getTaxTotal()==null? ZERO : list.getInVoiceLineItems().get(i).getTaxTotal()) + SEMICOLUMN + 
							(list.getInVoiceLineItems().get(i).getTotal()==null? ZERO : list.getInVoiceLineItems().get(i).getTotal()) + SEMICOLUMN + 
							(list.getTotalDiscount()==null? ZERO : list.getTotalDiscount()) + SEMICOLUMN +
							(list.getFreight()==null? ZERO : list.getFreight()) + SEMICOLUMN +
							
							/* Vendor Pay To Address */
							(list.getVendorPayTypeAddress() == null ? BLANK : list.getVendorPayTypeAddress().getAddressName() +COMMA+ list.getVendorPayTypeAddress().getStreet() + COMMA 
									+list.getVendorPayTypeAddress().getCity() +COMMA+list.getVendorPayTypeAddress().getZipCode() ) + SEMICOLUMN +
							
							/* Vendor Shipping From Address */
							(list.getVendorShippingAddress() == null ? BLANK : list.getVendorShippingAddress().getAddressName() +COMMA+ list.getVendorShippingAddress().getStreet() + COMMA 
									+list.getVendorShippingAddress().getCity() +COMMA+list.getVendorShippingAddress().getZipCode() ) + SEMICOLUMN +
							
							/* Vendor Ship To Address */
							(list.getDeliverTo() == null ? BLANK : list.getDeliverTo()) + SEMICOLUMN +
							
							//(list.getTotalPayment()==null? BLANK : list.getTotalPayment()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getRemark().toString())? BLANK : list.getRemark()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
							NEWLINE ;
							
					concat = concat + excelData;
					index = index + 1;
					}
				}
					
			}
		 
		excelReport =  getInvHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
		
		public ByteArrayOutputStream creditMemoReport(List<CreditMemo> creList) throws Exception {
			String excelReport = "";
			String excelData = "";
			String concat = "";
			
			int index = 1;
			if (!creList.isEmpty() && creList != null) {
				for (CreditMemo list : creList) {
					for(int i=0; i< list.getCreditMemoLineItems().size(); i++) {
						list = creditMemoService.getListAmount(list);
					//	CreditMemo cre = creditMemoService.getCreditMemoById(Integer.valueOf(list.getId()));
					excelData = index + SEMICOLUMN + 
							(list.getVendor() == null ? BLANK : list.getVendor().getVendorCode()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getVendor().getName()) ? BLANK : list.getVendor().getName()) + SEMICOLUMN + 
							(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getCreditMemoLineItems().get(i).getProdouctNumber())? BLANK : list.getCreditMemoLineItems().get(i).getProdouctNumber()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getCreditMemoLineItems().get(i).getDescription())? BLANK : list.getCreditMemoLineItems().get(i).getDescription()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(getPGDescription(list.getCreditMemoLineItems().get(i).getProductGroup()))? BLANK : getPGDescription(list.getCreditMemoLineItems().get(i).getProductGroup())) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getCreditMemoLineItems().get(i).getUom())? BLANK : list.getCreditMemoLineItems().get(i).getUom()) + SEMICOLUMN +
							(getWarehouseName(list.getCreditMemoLineItems().get(i).getWarehouse())==null? BLANK : getWarehouseName(list.getCreditMemoLineItems().get(i).getWarehouse())) + SEMICOLUMN +
							(list.getCreditMemoLineItems().get(i).getRequiredQuantity()==null? BLANK : list.getCreditMemoLineItems().get(i).getRequiredQuantity()) + SEMICOLUMN + 
							//(cre.getCreditMemoLineItems().get(i).getTempRequiredQuantity() ==null? ZERO : cre.getCreditMemoLineItems().get(i).getTempRequiredQuantity()) + SEMICOLUMN +
							(list.getCreditMemoLineItems().get(i).getUnitPrice()==null? BLANK : list.getCreditMemoLineItems().get(i).getUnitPrice()) + SEMICOLUMN + 
							(StringUtil.isEmptyTrim(list.getCreditMemoLineItems().get(i).getTaxDescription())? BLANK : list.getCreditMemoLineItems().get(i).getTaxDescription()) + SEMICOLUMN +
							(list.getCreditMemoLineItems().get(i).getTaxTotal()==null? ZERO : list.getCreditMemoLineItems().get(i).getTaxTotal()) + SEMICOLUMN + 
							(list.getCreditMemoLineItems().get(i).getTotal()==null? ZERO : list.getCreditMemoLineItems().get(i).getTotal()) + SEMICOLUMN + 
							(list.getTotalDiscount()==null? ZERO : list.getTotalDiscount()) + SEMICOLUMN +
							(list.getFreight()==null? ZERO : list.getFreight()) + SEMICOLUMN +
							
							/* Vendor Pay To Address */
							(list.getVendorPayTypeAddress() == null ? BLANK : list.getVendorPayTypeAddress().getAddressName() +COMMA+ list.getVendorPayTypeAddress().getStreet() + COMMA 
									+list.getVendorPayTypeAddress().getCity() +COMMA+list.getVendorPayTypeAddress().getZipCode() ) + SEMICOLUMN +
							
							/* Vendor Shipping From Address */
							(list.getVendorShippingAddress() == null ? BLANK : list.getVendorShippingAddress().getAddressName() +COMMA+ list.getVendorShippingAddress().getStreet() + COMMA 
									+list.getVendorShippingAddress().getCity() +COMMA+list.getVendorShippingAddress().getZipCode() ) + SEMICOLUMN +
							
							/* Vendor Ship To Address */
							(list.getDeliverTo() == null ? BLANK : list.getDeliverTo()) + SEMICOLUMN +
							
							//(list.getTotalPayment()==null? BLANK : list.getTotalPayment()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getRemark().toString())? BLANK : list.getRemark()) + SEMICOLUMN +
							(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
							NEWLINE ;
							
					concat = concat + excelData;
					index = index + 1;
					}
				}
					
			}
		 
		excelReport =  getCMHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
		
		
	/*
	 public ByteArrayOutputStream InventoryGoodsIssueReport(List<InventoryGoodsIssue> productList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		String heading = SNO + SEMICOLUMN + DOCNUMBER + SEMICOLUMN  + DOCDATE + SEMICOLUMN + PRODUCTNUMBER + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN + PRODUCTGROUP
				+ SEMICOLUMN  + UOM + SEMICOLUMN  + QUANTITY +  SEMICOLUMN + DEPARTMENT + SEMICOLUMN + REMARKS + SEMICOLUMN +  NEWLINE;

		int index = 1;
		if (!productList.isEmpty() && productList != null) {
			for (InventoryGoodsIssue list : productList) {
				for(int i=0; i< list.getInventoryGoodsIssueList().size(); i++) {
				excelData = index + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber()) ? BLANK : list.getDocNumber()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getProductNumber())? BLANK : list.getProductNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getProductDescription())? BLANK : list.getProductDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getProductGroup())? BLANK : list.getProductGroup()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getUomName())? BLANK : list.getUomName()) + SEMICOLUMN +
						(list.getRequiredQty()==null? BLANK : list.getRequiredQty()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getDepartmentName())? BLANK : list.getDepartmentName()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getRemarks())? BLANK : list.getRemarks()) + NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
		}
		excelReport = heading + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}	
	 */
	
	
	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	private String getWarehouseName(int id) {
		String plant = (String) plantMap().get(id);
		return plant;
	}
	
	public Map<String, Object> productGroupMap() {
		return productTypeService.findAll().stream().collect(Collectors.toMap(ProductType::getProductName, ProductType::getDescription));
	}
	
	private String getPGDescription(String group) {
		String plant = (String) productGroupMap().get(group);
		return plant;
	}
	
	public Map<Integer, Object> departmentMap() {
		return departmentService.findAll().stream().collect(Collectors.toMap(Department::getId, Department::getName));
	}
	
	private String getDepartment(int id) {
		String departmentName = (String) departmentMap().get(id);
		return departmentName;
	}
	
	public ByteArrayOutputStream INVGRReport(List<InventoryGoodsReceipt> invgrList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		
		String heading = SNO + SEMICOLUMN + DOCNUMBER + SEMICOLUMN + DOCDATE+ SEMICOLUMN  + PRODUCTNUMBER + SEMICOLUMN  + PRODUCTDESCRIPTION + SEMICOLUMN + PRODUCTGROUP
				        + SEMICOLUMN  + UOM + SEMICOLUMN + WAREHOUSE +  SEMICOLUMN + QUANTITY  +  SEMICOLUMN + UNITPRICE +  SEMICOLUMN + TAXDESCRIPTION + SEMICOLUMN +
				       TAXAMOUNT + SEMICOLUMN + TOTALPAYMENT + SEMICOLUMN + DISCOUNT + SEMICOLUMN + FREIGHT + SEMICOLUMN +  REMARKS + SEMICOLUMN + STATUS  +NEWLINE;

		
		int index = 1;
		if (!invgrList.isEmpty() && invgrList != null) {
			
			for (InventoryGoodsReceipt list : invgrList) {
				for(int i=0; i< list.getInventoryGoodsReceiptList().size(); i++) {
					list = inventoryGoodsReceiptService.getListAmount(list);
				excelData = index + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsReceiptList().get(i).getProductNumber())? BLANK : list.getInventoryGoodsReceiptList().get(i).getProductNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsReceiptList().get(i).getDescription())? BLANK : list.getInventoryGoodsReceiptList().get(i).getDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(getPGDescription(list.getInventoryGoodsReceiptList().get(i).getProductGroup()))? BLANK : getPGDescription(list.getInventoryGoodsReceiptList().get(i).getProductGroup())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsReceiptList().get(i).getUom())? BLANK : list.getInventoryGoodsReceiptList().get(i).getUom()) + SEMICOLUMN +
						(getWarehouseName(list.getInventoryGoodsReceiptList().get(i).getWarehouse())==null? BLANK : getWarehouseName(list.getInventoryGoodsReceiptList().get(i).getWarehouse())) + SEMICOLUMN +
						(list.getInventoryGoodsReceiptList().get(i).getRequiredQuantity()==null? BLANK : list.getInventoryGoodsReceiptList().get(i).getRequiredQuantity()) + SEMICOLUMN + 
						(list.getInventoryGoodsReceiptList().get(i).getUnitPrice()==null? BLANK : list.getInventoryGoodsReceiptList().get(i).getUnitPrice()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getInventoryGoodsReceiptList().get(i).getTaxDescription())? BLANK : list.getInventoryGoodsReceiptList().get(i).getTaxDescription()) + SEMICOLUMN +
						(list.getInventoryGoodsReceiptList().get(i).getTaxTotal()==null? ZERO : list.getInventoryGoodsReceiptList().get(i).getTaxTotal()) + SEMICOLUMN + 
						(list.getInventoryGoodsReceiptList().get(i).getTotal()==null? ZERO : list.getInventoryGoodsReceiptList().get(i).getTotal()) + SEMICOLUMN + 
						(list.getTotalDiscount()==null? "0" : list.getTotalDiscount()) + SEMICOLUMN +
						(list.getFreight()==null? ZERO : list.getFreight()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getRemarks().toString())? BLANK : list.getRemarks()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
						NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
				
		}
		 
		excelReport =  heading + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
	public ByteArrayOutputStream INVGIReport(List<InventoryGoodsIssue> invgrList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		
		String heading = SNO + SEMICOLUMN + DOCNUMBER + SEMICOLUMN + DOCDATE+ SEMICOLUMN  + PRODUCTNUMBER + SEMICOLUMN  + PRODUCTDESCRIPTION + SEMICOLUMN + PRODUCTGROUP
				        + SEMICOLUMN  + UOM + SEMICOLUMN + WAREHOUSE + SEMICOLUMN + DEPARTMENT +  SEMICOLUMN + QUANTITY  +  SEMICOLUMN + UNITPRICE +  SEMICOLUMN + TAXDESCRIPTION + SEMICOLUMN +
				       TAXAMOUNT + SEMICOLUMN + TOTALPAYMENT + SEMICOLUMN + DISCOUNT + SEMICOLUMN + FREIGHT + SEMICOLUMN +  REMARKS + SEMICOLUMN + STATUS  +NEWLINE;

		
		int index = 1;
		if (!invgrList.isEmpty() && invgrList != null) {
			
			for (InventoryGoodsIssue list : invgrList) {
				for(int i=0; i< list.getInventoryGoodsIssueList().size(); i++) {
					list = inventoryGoodsIssueService.getListAmount(list);
				excelData = index + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsIssueList().get(i).getProductNumber())? BLANK : list.getInventoryGoodsIssueList().get(i).getProductNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsIssueList().get(i).getDescription())? BLANK : list.getInventoryGoodsIssueList().get(i).getDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(getPGDescription(list.getInventoryGoodsIssueList().get(i).getProductGroup()))? BLANK : getPGDescription(list.getInventoryGoodsIssueList().get(i).getProductGroup())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsIssueList().get(i).getUom())? BLANK : list.getInventoryGoodsIssueList().get(i).getUom()) + SEMICOLUMN +
						(getWarehouseName(list.getInventoryGoodsIssueList().get(i).getWarehouse())==null? BLANK : getWarehouseName(list.getInventoryGoodsIssueList().get(i).getWarehouse())) + SEMICOLUMN +
						(getDepartment(list.getInventoryGoodsIssueList().get(i).getDepartment())==null? BLANK : getDepartment(list.getInventoryGoodsIssueList().get(i).getDepartment())) + SEMICOLUMN +
						(list.getInventoryGoodsIssueList().get(i).getRequiredQuantity()==null? BLANK : list.getInventoryGoodsIssueList().get(i).getRequiredQuantity()) + SEMICOLUMN + 
						(list.getInventoryGoodsIssueList().get(i).getUnitPrice()==null? BLANK : list.getInventoryGoodsIssueList().get(i).getUnitPrice()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getInventoryGoodsIssueList().get(i).getTaxDescription())? BLANK : list.getInventoryGoodsIssueList().get(i).getTaxDescription()) + SEMICOLUMN +
						(list.getInventoryGoodsIssueList().get(i).getTaxTotal()==null? ZERO : list.getInventoryGoodsIssueList().get(i).getTaxTotal()) + SEMICOLUMN + 
						(list.getInventoryGoodsIssueList().get(i).getTotal()==null? ZERO : list.getInventoryGoodsIssueList().get(i).getTotal()) + SEMICOLUMN + 
						(list.getTotalDiscount()==null? "0" : list.getTotalDiscount()) + SEMICOLUMN +
						(list.getFreight()==null? ZERO : list.getFreight()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getRemarks().toString())? BLANK : list.getRemarks()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
						NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
				
		}
		 
		excelReport =  heading + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
	public ByteArrayOutputStream INVGTReport(List<InventoryGoodsTransfer> invgrList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		
		String heading = SNO + SEMICOLUMN + DOCNUMBER + SEMICOLUMN + DOCDATE+ SEMICOLUMN  + PRODUCTNUMBER + SEMICOLUMN  + PRODUCTDESCRIPTION + SEMICOLUMN + 
				         UOM + SEMICOLUMN + FROM +WAREHOUSE + SEMICOLUMN + TO + WAREHOUSE +  SEMICOLUMN + QUANTITY  +  SEMICOLUMN + UNITPRICE +  SEMICOLUMN + TAXDESCRIPTION + SEMICOLUMN +
				       TAXAMOUNT + SEMICOLUMN + TOTALPAYMENT + SEMICOLUMN + DISCOUNT + SEMICOLUMN + FREIGHT + SEMICOLUMN +  REMARKS + SEMICOLUMN + STATUS  +NEWLINE;

		
		int index = 1;
		if (!invgrList.isEmpty() && invgrList != null) {
			
			for (InventoryGoodsTransfer list : invgrList) {
				for(int i=0; i< list.getInventoryGoodsTransferList().size(); i++) {
					list = inventoryGoodsTransferService.getListAmount(list);
				excelData = index + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber())? BLANK : list.getDocNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(sdf.format(list.getDocumentDate()))? BLANK : sdf.format(list.getDocumentDate())) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsTransferList().get(i).getProductNumber())? BLANK : list.getInventoryGoodsTransferList().get(i).getProductNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsTransferList().get(i).getDescription())? BLANK : list.getInventoryGoodsTransferList().get(i).getDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getInventoryGoodsTransferList().get(i).getUom())? BLANK : list.getInventoryGoodsTransferList().get(i).getUom()) + SEMICOLUMN +
						(getWarehouseName(list.getInventoryGoodsTransferList().get(i).getFromWarehouse())==null? BLANK : getWarehouseName(list.getInventoryGoodsTransferList().get(i).getFromWarehouse())) + SEMICOLUMN +
						(getWarehouseName(list.getInventoryGoodsTransferList().get(i).getToWarehouse())==null? BLANK : getWarehouseName(list.getInventoryGoodsTransferList().get(i).getToWarehouse())) + SEMICOLUMN +
						(list.getInventoryGoodsTransferList().get(i).getRequiredQuantity()==null? BLANK : list.getInventoryGoodsTransferList().get(i).getRequiredQuantity()) + SEMICOLUMN + 
						(list.getInventoryGoodsTransferList().get(i).getUnitPrice()==null? BLANK : list.getInventoryGoodsTransferList().get(i).getUnitPrice()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getInventoryGoodsTransferList().get(i).getTaxDescription())? BLANK : list.getInventoryGoodsTransferList().get(i).getTaxDescription()) + SEMICOLUMN +
						(list.getInventoryGoodsTransferList().get(i).getTaxTotal()==null? ZERO : list.getInventoryGoodsTransferList().get(i).getTaxTotal()) + SEMICOLUMN + 
						(list.getInventoryGoodsTransferList().get(i).getTotal()==null? ZERO : list.getInventoryGoodsTransferList().get(i).getTotal()) + SEMICOLUMN + 
						(list.getTotalDiscount()==null? "0" : list.getTotalDiscount()) + SEMICOLUMN +
						(list.getFreight()==null? ZERO : list.getFreight()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getRemarks().toString())? BLANK : list.getRemarks()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getStatus())? BLANK : list.getStatus()) + SEMICOLUMN +
						NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
				}
			}
				
		}
		 
		excelReport =  heading + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
}
