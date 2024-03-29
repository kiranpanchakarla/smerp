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
    <#if gr??>
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>:<#if gr.vendor.name??>&nbsp;${gr.vendor.name}<#else>--</#if></td>
                <td><strong >Email Id</strong></td>
                <td>:<#if gr.vendor.emailId??>&nbsp;${gr.vendor.emailId}<#else>-- </#if></td>
                 <td><strong >Contact Person</strong></td>
                <td>:<#if gr.vendorContactDetails.contactName??>&nbsp;${gr.vendorContactDetails.contactName}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>:<#if gr.vendorPayTypeAddress.city??>&nbsp;${gr.vendorPayTypeAddress.city}<#else>--</#if></td>
                <td><strong >Ship From</strong></td>
                <td>:<#if gr.vendorShippingAddress.city??>&nbsp;${gr.vendorShippingAddress.city}<#else>--</#if></td>
                 <td><strong >Posting Date</strong></td>
                <td>:<#if gr.postingDate??>&nbsp;${gr.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>GRE Doc#</strong></td>
                <td>:<#if gr.docNumber??>&nbsp;${gr.docNumber}<#else>--</#if></td>
                <td><strong>GR Doc#</strong></td>
                <td>:<#if gr.referenceDocNumber??>&nbsp;${gr.referenceDocNumber}<#else>--</#if></td>
                <td><strong>PO Doc#</strong></td>
                <td>:<#if gr.grId?? && gr.grId.poId??>&nbsp;${gr.grId.poId.docNumber}<#else>--</#if></td>
                 </tr>
                
                <tr>
                <td><strong>RFQ Doc#</strong></td>
                <td>:<#if gr.grId?? && gr.grId.poId?? && gr.grId.poId.rfqId??>&nbsp;${gr.grId.poId.rfqId.docNumber}<#else>--</#if></td>
                <td><strong>PR Doc#.</strong></td>
                <td>:<#if gr.grId??  && gr.grId.poId?? && gr.grId.poId.rfqId?? && gr.grId.poId.rfqId.purchaseReqId??>&nbsp;${gr.grId.poId.rfqId.purchaseReqId.docNumber}<#else>--</#if></td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if gr.documentDate??>&nbsp;${gr.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td>:<#if gr.requiredDate??>${gr.requiredDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                <td><strong>Status</strong></td>
				<td>: <#if gr.status??> ${gr.status}</#if></td>
				<td><strong>Remarks</strong></td>
				<td>: <#if gr.remark??> ${gr.remark}</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if gr.category??>
                 <#assign sno = 1/>
               <#if gr.category = "Item"> 
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
                <#list gr.goodsReturnLineItems as goodsRetlist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.prodouctNumber??>&nbsp;${goodsRetlist.prodouctNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.description??>&nbsp;${goodsRetlist.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.uom??>&nbsp;${goodsRetlist.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.sku??>&nbsp;${goodsRetlist.sku}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.requiredQuantity??>&nbsp;${goodsRetlist.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.unitPrice??>&nbsp;${goodsRetlist.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.taxDescription??>&nbsp;${goodsRetlist.taxDescription}<#else>--</#if>
                 <!-- 
                  <#if goodsRetlist.taxCode??>
               <#list taxCodeMap as key, value>
                <#if (goodsRetlist.taxCode) == (value)>
                     <p> ${key}</p>
                     </#if>
                </#list>
                  </#if> -->
                </td>
                <td style="border: solid 1px ;"><#if goodsRetlist.taxTotal??>&nbsp;${goodsRetlist.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.total??>&nbsp;${goodsRetlist.total}<#else>--</#if></td>
                <td style="border: solid 1px ;"> <#if goodsRetlist.productGroup??>&nbsp;${goodsRetlist.productGroup}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (goodsRetlist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;"><#if goodsRetlist.hsn??>&nbsp;${goodsRetlist.hsn}<#else>--</#if></td>
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
                <#list gr.goodsReturnLineItems as goodsRetlist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.sacCode??>&nbsp;${goodsRetlist.sacCode}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.description??>&nbsp;${goodsRetlist.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.requiredQuantity??>&nbsp;${goodsRetlist.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.unitPrice??>&nbsp;${goodsRetlist.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
               <#if goodsRetlist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (goodsRetlist.taxCode) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                </#if>
                </td>
                <td style="border: solid 1px ;"><#if goodsRetlist.taxTotal??>&nbsp;${goodsRetlist.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsRetlist.total??>&nbsp;${goodsRetlist.total}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (goodsRetlist.warehouse) == (key)>
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
                <td>:<#if gr.totalDiscount??>${gr.totalDiscount}<#else>--</#if></td>
                </tr>
                                                                                   
                <tr>
                <td><#if gr.vendorShippingAddress.addressName??>${gr.vendorShippingAddress.addressName}</#if></td>
                <td><#if gr.vendorPayTypeAddress.addressName??>${gr.vendorPayTypeAddress.addressName}</#if></td>
                <td><strong>Total Invoice Amount</strong></td>
                <td>:<#if gr.totalBeforeDisAmt??> ${gr.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                <td><#if gr.vendorShippingAddress.street??>${gr.vendorShippingAddress.street}</#if></td>
                 <td> <#if gr.vendorPayTypeAddress.street??>${gr.vendorPayTypeAddress.street}</#if></td>
                 <td><strong>Freight</strong></td>
                <td>:<#if gr.freight??> ${gr.freight} <#else>--</#if></td>
                </tr>
                 <tr>
                <td><#if gr.vendorShippingAddress.city??>${gr.vendorShippingAddress.city}</#if> </td>
                <td><#if gr.vendorPayTypeAddress.city??>${gr.vendorPayTypeAddress.city}</#if></td>
                 <td><strong>Tax Amount</strong></td>
                <td>:<#if gr.taxAmt??> ${gr.taxAmt}<#else>--</#if></td>
                </tr>
                <tr>
                <td><#if gr.vendorShippingAddress.zipCode??>${gr.vendorShippingAddress.zipCode}</#if></td>
                <td> <#if gr.vendorPayTypeAddress.zipCode??>${gr.vendorPayTypeAddress.zipCode}</#if></td>
                <td><strong>Total</strong></td>
                <td>:<#if gr.amtRounding??> ${gr.amtRounding}<#else>-- </#if></td>
               
                </tr>
                <tr>
                <td><#if gr.vendorShippingAddress.country.name??>${gr.vendorShippingAddress.country.name}</#if></td>
                <td><#if gr.vendorPayTypeAddress.country.name??>${gr.vendorPayTypeAddress.country.name}</#if></td>
                <td><strong>Rounded Off</strong></td>
                <td>:<#if gr.roundedOff??> ${gr.roundedOff}<#else>--</#if></td>
                </tr>
                <tr>
                <td></td>
                <td></td>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if gr.totalPayment??> ${gr.totalPayment}<#else>--</#if></td>
                </tr>
            </table>
              
              <table style="width:100%">
                <tr>
                <td><strong>Deliver To :</strong></td>
                </tr>
                <tr>
                <td><#if gr.deliverTo??>${gr.deliverTo}</#if></td>
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