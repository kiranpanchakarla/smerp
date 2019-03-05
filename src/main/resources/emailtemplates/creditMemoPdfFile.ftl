<#ftl output_format="XML"  auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
<link rel="stylesheet" media="screen,print" type="text/css" href="${contextPath}/resources/css/print.css"/>
 
</head>

<body>
 
 <div id="background">
  <p id="watermark"> ${user.company.name}</p>
	</div>
   <div style="-fs-page-sequence: start;">
      <div style="position: running(current);">
      	<div id="page-header" class="head">
 		
 		<div style="width:100%; margin-top: 50px;">
 			<table>
		   <tr>
            
	         <td style="width: 30%; border: 1px solid #fff !important;">
                 <img style="height:70px;width:auto;" src="${contextPath}/${user.company.logo}" />
            </td>
            <td style="width: 25%; border: 1px solid #fff !important; text-align:center;">
                <strong style="font-size:20px;">${moduleName}</strong><br/>
            </td>
        </tr>
	</table>
 		</div>
		 <div class="line" style="margin-top:-15px;">&#160;</div>
		</div>
		</div>
		</div>
	 
	<br></br>


    <div style="z-index:1; width:100%; position: absolute;">
    <#if credit??>
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>:<#if credit.vendor.name??>&nbsp;${credit.vendor.name}<#else>--</#if></td>
                <td><strong >Email Id</strong></td>
                <td>:<#if credit.vendor.emailId??>&nbsp;${credit.vendor.emailId}<#else>-- </#if></td>
                 <td><strong >Contact Person</strong></td>
                <td>:<#if credit.vendorContactDetails.contactName??>&nbsp;${credit.vendorContactDetails.contactName}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>:<#if credit.vendorPayTypeAddress.city??>&nbsp;${credit.vendorPayTypeAddress.city}<#else>--</#if></td>
                <td><strong >Ship From</strong></td>
                <td>:<#if credit.vendorShippingAddress.city??>&nbsp;${credit.vendorShippingAddress.city}<#else>--</#if></td>
                <td><strong >Posting Date</strong></td>
                <td>:<#if credit.postingDate??>&nbsp;${credit.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>CRE Doc#</strong></td>
                <td>:<#if credit.docNumber??>&nbsp;${credit.docNumber}<#else>--</#if></td>
                <td><strong>INV Doc#</strong></td>
                <td>:<#if credit.referenceDocNumber??>&nbsp;${credit.referenceDocNumber}<#else>--</#if></td>
                <td><strong>GR Doc#</strong></td>
                <td>:<#if credit.invId?? && credit.invId.grId??>&nbsp;${credit.invId.grId.docNumber}<#else>--</#if></td>
                </tr>
                
                <tr>
                 <td><strong>PO Doc#</strong></td>
                <td>:<#if credit.invId?? && credit.invId.grId?? && credit.invId.grId.poId??>&nbsp;${credit.invId.grId.poId.docNumber}<#else>--</#if></td>
                <td><strong>RFQ Doc#</strong></td>
                <td>:<#if credit.invId?? && credit.invId.grId?? && credit.invId.grId.poId?? && credit.invId.grId.poId.rfqId??>&nbsp;${credit.invId.grId.poId.rfqId.docNumber}<#else>--</#if></td>
                <td><strong>PR Doc#</strong></td>
                <td>:<#if credit.invId?? && credit.invId.grId?? && credit.invId.grId.poId?? && credit.invId.grId.poId.rfqId?? && credit.invId.grId.poId.rfqId.purchaseReqId??>&nbsp;${credit.invId.grId.poId.rfqId.purchaseReqId.docNumber}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong >Doc Date</strong></td>
                <td>:<#if credit.documentDate??>&nbsp;${credit.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong >Require Date</strong></td>
                <td>:<#if credit.requiredDate??>${credit.requiredDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Status</strong></td>
				<td>: <#if credit.status??> ${credit.status}</#if></td>
				<td><strong>Remarks</strong></td>
				<td>: <#if credit.remark??> ${credit.remark}</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if credit.category??>
                 <#assign sno = 1/>
               <#if credit.category = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.No</strong></td>
                <td style="border: solid 1px ;"><strong >Product#</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >SKU</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Unit Price</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Code</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Total</strong></td>
                <td style="border: solid 1px ;"><strong >Total</strong></td>
                <td style="border: solid 1px ;"><strong >Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse	</strong></td>
                <td style="border: solid 1px ;"><strong >HSN Code</strong></td>
                </tr>
                <#list credit.creditMemoLineItems as creditoiceList>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if creditoiceList.prodouctNumber??>&nbsp;${creditoiceList.prodouctNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.description??>&nbsp;${creditoiceList.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.uom??>&nbsp;${creditoiceList.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.sku??>&nbsp;${creditoiceList.sku}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.requiredQuantity??>&nbsp;${creditoiceList.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.unitPrice??>&nbsp;${creditoiceList.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.taxDescription??>&nbsp;${creditoiceList.taxDescription}<#else>--</#if>
                 
                </td>
                <td style="border: solid 1px ;"><#if creditoiceList.taxTotal??>&nbsp;${creditoiceList.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.total??>&nbsp;${creditoiceList.total}<#else>--</#if></td>
                <td style="border: solid 1px ;"> <#if creditoiceList.productGroup??>&nbsp;${creditoiceList.productGroup}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (creditoiceList.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;"><#if creditoiceList.hsn??>&nbsp;${creditoiceList.hsn}<#else>--</#if></td>
                </tr>
                </#list>
                </table>
                <#else>
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.No</strong></td>
                <td style="border: solid 1px ;"><strong >SAC Code</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Unit Price</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Code</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Total</strong></td>
                <td style="border: solid 1px ;"><strong >Total</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                </tr>
                <#list credit.creditMemoLineItems as creditoiceList>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if creditoiceList.sacCode??>&nbsp;${creditoiceList.sacCode}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.description??>&nbsp;${creditoiceList.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.requiredQuantity??>&nbsp;${creditoiceList.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.unitPrice??>&nbsp;${creditoiceList.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
               <#if creditoiceList.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (creditoiceList.taxCode) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                </#if>
                </td>
                <td style="border: solid 1px ;"><#if creditoiceList.taxTotal??>&nbsp;${creditoiceList.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.total??>&nbsp;${creditoiceList.total}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (creditoiceList.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                </tr>
                </#list>
                </table>
                </#if></#if>
                <br></br><br></br>
                <table style="width:100%">
                <tr>
                <td><strong>Shipping From :</strong></td>
                <td><strong>Pay To :</strong></td>
                <td><strong>Discount(%)</strong></td>
                <td>:<#if credit.totalDiscount??>${credit.totalDiscount}<#else>--</#if></td>
                </tr>
                                                                                   
                <tr>
                <td><#if credit.vendorShippingAddress.addressName??>${credit.vendorShippingAddress.addressName},</#if></td>
                <td><#if credit.vendorPayTypeAddress.addressName??>${credit.vendorPayTypeAddress.addressName}</#if></td>
                <td><strong>Total Invoice Amount</strong></td>
                <td>:<#if credit.totalBeforeDisAmt??> ${credit.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                <td><#if credit.vendorShippingAddress.street??>${credit.vendorShippingAddress.street}, </#if></td>
                 <td> <#if credit.vendorPayTypeAddress.street??>${credit.vendorPayTypeAddress.street},</#if></td>
                 <td><strong>Freight</strong></td>
                <td>:<#if credit.freight??> ${credit.freight} <#else>--</#if></td>
                </tr>
                <tr>
                <td><#if credit.vendorShippingAddress.zipCode??>${credit.vendorShippingAddress.zipCode}, </#if></td>
                <td> <#if credit.vendorPayTypeAddress.zipCode??>${credit.vendorPayTypeAddress.zipCode},</#if></td>
                <td><strong>Tax Amount</strong></td>
                <td>:<#if credit.taxAmt??> ${credit.taxAmt}<#else>--</#if></td>
                
               
                </tr>
                <tr>
                <td><#if credit.vendorShippingAddress.country.name??>${credit.vendorShippingAddress.country.name}.</#if></td>
                <td><#if credit.vendorPayTypeAddress.country.name??>${credit.vendorPayTypeAddress.country.name}.</#if></td>
                <td><strong>Total</strong></td>
                <td>:<#if credit.amtRounding??> ${credit.amtRounding}<#else>-- </#if></td>
                </tr>
                
                <tr>
                <td></td>
                <td></td>
                 <td><strong>Rounded Off</strong></td>
                <td>:<#if credit.roundedOff??> ${credit.roundedOff}<#else>--</#if></td>
                </tr>
                
                 <tr>
                <td></td>
                <td></td>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if credit.totalPayment??> ${credit.totalPayment}<#else>--</#if></td>
                </tr>
            </table>
             <table style="width:100%">
                <tr>
                <td><strong>Deliver To :</strong></td>
                </tr>
                <tr>
                <td><#if credit.deliverTo??>${credit.deliverTo}</#if></td>
                </tr>
              </table>       
     
     </#if>
     </div>
     <!-- <div style="position: fixed; left: 0;  bottom: 0; width: 100%; color: black;  text-align: center;">
     <p>Created by: ${user.username}</p>
     <p>Contact information: <a href="">help@manuhindia.com</a>.</p>
     </div> -->
    </body>
</html>