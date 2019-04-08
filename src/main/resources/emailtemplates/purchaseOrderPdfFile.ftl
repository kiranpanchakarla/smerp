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
    <#if po??>
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>:<#if po.vendor.name??>&nbsp;${po.vendor.name}<#else>--</#if></td>
                <td><strong >Email Id</strong></td>
                <td>:<#if po.vendor.emailId??>&nbsp;${po.vendor.emailId}<#else>-- </#if></td>
                 <td><strong >Contact Person</strong></td>
                <td>:<#if po.vendorContactDetails.contactName??>&nbsp;${po.vendorContactDetails.contactName}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>:<#if po.vendorPayTypeAddress.city??>&nbsp;${po.vendorPayTypeAddress.city}<#else>--</#if></td>
                <td><strong >Ship From</strong></td>
                <td>:<#if po.vendorShippingAddress.city??>&nbsp;${po.vendorShippingAddress.city}<#else>--</#if></td>
                 <td><strong >Posting Date</strong></td>
                <td>:<#if po.postingDate??>&nbsp;${po.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                
                </tr>
                
                <tr>
                <td><strong>PO Doc#</strong></td>
                <td>:<#if po.docNumber??>&nbsp;${po.docNumber}<#else>--</#if></td>
                <td><strong>RFQ Doc#</strong></td>
                <td>:<#if po.referenceDocNumber??>&nbsp;${po.referenceDocNumber}<#else>--</#if></td>
                <td><strong>PR Doc#</strong></td>
                <td>:<#if po.rfqId?? && po.rfqId.purchaseReqId??>&nbsp;${po.rfqId.purchaseReqId.docNumber}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong >Doc Date</strong></td>
                <td>:<#if po.documentDate??>&nbsp;${po.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong >Required Date</strong></td>
                <td>:<#if po.requiredDate??>${po.requiredDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                <td><strong >Status</strong></td>
                 <td>:<#if po.status??>&nbsp;${po.status}<#else>--</#if></td>
                </tr>
                 <tr>
                 <td><strong >Remarks</strong></td>
                 <td>:<#if po.remark??>&nbsp;${po.remark}<#else>--</#if></td>
                </tr>
                 
                
             
            </table>
                <br></br>
                <#if po.category??>
                 <#assign sno = 1/>
               <#if po.category = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.No</strong></td>
                <td style="border: solid 1px ;"><strong >Product#</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >SKU</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Unit Price</strong></td>
                <td style="border: solid 1px ;"><strong >Tax %</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Total</strong></td>
                <td style="border: solid 1px ;"><strong >Total</strong></td>
                <td style="border: solid 1px ;"><strong >Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse	</strong></td>
                <td style="border: solid 1px ;"><strong >HSN Code</strong></td>
                </tr>
                <#list po.purchaseOrderlineItems as polist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if polist.prodouctNumber??>&nbsp;${polist.prodouctNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.description??>&nbsp;${polist.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.uom??>&nbsp;${polist.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.sku??>&nbsp;${polist.sku}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.requiredQuantity??>&nbsp;${polist.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.unitPrice??>&nbsp;${polist.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.taxDescription??>&nbsp;${polist.taxDescription}<#else>--</#if>
                 <!-- <#if polist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (polist.taxCode) == (value)>
                     <p> ${key}</p>
                     </#if>
                </#list>
                  </#if> -->
                </td>
                <td style="border: solid 1px ;"><#if polist.taxTotal??>&nbsp;${polist.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.total??>&nbsp;${polist.total}<#else>--</#if></td>
                <td style="border: solid 1px ;"> <#if polist.productGroup??>&nbsp;${polist.productGroup}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (polist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;"><#if polist.hsn??>&nbsp;${polist.hsn}<#else>--</#if></td>
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
                <#list po.purchaseOrderlineItems as polist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if polist.sacCode??>&nbsp;${polist.sacCode}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.description??>&nbsp;${polist.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.requiredQuantity??>&nbsp;${polist.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.unitPrice??>&nbsp;${polist.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
               <#if polist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (polist.taxCode) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                </#if>
                </td>
                <td style="border: solid 1px ;"><#if polist.taxTotal??>&nbsp;${polist.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.total??>&nbsp;${polist.total}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (polist.warehouse) == (key)>
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
                <td>:<#if po.totalDiscount??>${po.totalDiscount}<#else>--</#if></td>
                </tr>
                                                                                   
                <tr>
                <td><#if po.vendorShippingAddress.addressName??>${po.vendorShippingAddress.addressName}</#if></td>
                <td><#if po.vendorPayTypeAddress.addressName??>${po.vendorPayTypeAddress.addressName}</#if></td>
                <td><strong>Total Invoice Amount</strong></td>
                <td>:<#if po.totalBeforeDisAmt??> ${po.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                <td><#if po.vendorShippingAddress.street??>${po.vendorShippingAddress.street}</#if></td>
                 <td> <#if po.vendorPayTypeAddress.street??>${po.vendorPayTypeAddress.street}</#if></td>
                 <td><strong>Freight</strong></td>
                <td>:<#if po.freight??> ${po.freight} <#else>--</#if></td>
                </tr>
                <tr>
                <td><#if po.vendorShippingAddress.city??>${po.vendorShippingAddress.city}</#if> </td>
                <td><#if po.vendorPayTypeAddress.city??>${po.vendorPayTypeAddress.city}</#if></td>
                 <td><strong>Tax Amount</strong></td>
                <td>:<#if po.taxAmt??> ${po.taxAmt}<#else>--</#if></td>
                </tr>
                <tr>
                <td><#if po.vendorShippingAddress.zipCode??>${po.vendorShippingAddress.zipCode}</#if></td>
                <td> <#if po.vendorPayTypeAddress.zipCode??>${po.vendorPayTypeAddress.zipCode}</#if></td>
                <td><strong>Total</strong></td>
                <td>:<#if po.amtRounding??> ${po.amtRounding}<#else>-- </#if></td>
               
                </tr>
                <tr>
                <td><#if po.vendorShippingAddress.country.name??>${po.vendorShippingAddress.country.name}</#if></td>
                <td><#if po.vendorPayTypeAddress.country.name??>${po.vendorPayTypeAddress.country.name}</#if></td>
                <td><strong>Rounded Off</strong></td>
                <td>:<#if po.roundedOff??> ${po.roundedOff}<#else>--</#if></td>
                </tr>
                <tr>
                <td></td>
                <td></td>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if po.totalPayment??> ${po.totalPayment}<#else>--</#if></td>
                </tr>
            </table>
             <table style="width:100%">
                <tr>
                <td><strong>Deliver To :</strong></td>
                </tr>
                
                <tr>
                <td><#if po.deliverTo??>${po.deliverTo}</#if></td>
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