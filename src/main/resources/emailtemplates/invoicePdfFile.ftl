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
    <#if inv??>
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>:<#if inv.vendor.name??>&nbsp;${inv.vendor.name}<#else>--</#if></td>
                <td><strong >Email Id</strong></td>
                <td>:<#if inv.vendor.emailId??>&nbsp;${inv.vendor.emailId}<#else>-- </#if></td>
                 <td><strong >Contact Person</strong></td>
                <td>:<#if inv.vendorContactDetails.contactName??>&nbsp;${inv.vendorContactDetails.contactName}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>:<#if inv.vendorPayTypeAddress.city??>&nbsp;${inv.vendorPayTypeAddress.city}<#else>--</#if></td>
                <td><strong >Ship From</strong></td>
                <td>:<#if inv.vendorShippingAddress.city??>&nbsp;${inv.vendorShippingAddress.city}<#else>--</#if></td>
                <td><strong>Doc No.</strong></td>
                <td>:<#if inv.docNumber??>&nbsp;${inv.docNumber}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Ref Doc No.</strong></td>
                <td>:<#if inv.referenceDocNumber??>&nbsp;${inv.referenceDocNumber}<#else>--</#if></td>
                <td><strong >Posting Date</strong></td>
                <td>:<#if inv.postingDate??>&nbsp;${inv.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if inv.documentDate??>&nbsp;${inv.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td>:<#if inv.requiredDate??>${inv.requiredDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if inv.category??>
                 <#assign sno = 1/>
               <#if inv.category = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >Product Name</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >SKU</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Unit Price</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Code</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Total</strong></td>
                <td style="border: solid 1px ;"><strong >Total</strong></td>
                <td style="border: solid 1px ;"><strong >Product Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse	</strong></td>
                <td style="border: solid 1px ;"><strong >HSN Code</strong></td>
                </tr>
                <#list inv.inVoiceLineItems as invoiceList>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if invoiceList.prodouctNumber??>&nbsp;${invoiceList.prodouctNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.description??>&nbsp;${invoiceList.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.uom??>&nbsp;${invoiceList.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.sku??>&nbsp;${invoiceList.sku}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.requiredQuantity??>&nbsp;${invoiceList.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.unitPrice??>&nbsp;${invoiceList.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                  <#if invoiceList.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (invoiceList.taxCode) == (key)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                  </#if>
                </td>
                <td style="border: solid 1px ;"><#if invoiceList.taxTotal??>&nbsp;${invoiceList.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.total??>&nbsp;${invoiceList.total}<#else>--</#if></td>
                <td style="border: solid 1px ;"> <#if invoiceList.productGroup??>&nbsp;${invoiceList.productGroup}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (invoiceList.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;"><#if invoiceList.hsn??>&nbsp;${invoiceList.hsn}<#else>--</#if></td>
                </tr>
                </#list>
                </table>
                <#else>
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >SAC Code</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Unit Price</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Code</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Total</strong></td>
                <td style="border: solid 1px ;"><strong >Total</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                </tr>
                <#list inv.inVoiceLineItems as invoiceList>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if invoiceList.sacCode??>&nbsp;${invoiceList.sacCode}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.description??>&nbsp;${invoiceList.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.requiredQuantity??>&nbsp;${invoiceList.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.unitPrice??>&nbsp;${invoiceList.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
               <#if invoiceList.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (invoiceList.taxCode) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                </#if>
                </td>
                <td style="border: solid 1px ;"><#if invoiceList.taxTotal??>&nbsp;${invoiceList.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if invoiceList.total??>&nbsp;${invoiceList.total}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (invoiceList.warehouse) == (key)>
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
                <td>:<#if inv.totalDiscount??>${inv.totalDiscount}<#else>--</#if></td>
                </tr>
                                                                                   
                <tr>
                <td><#if inv.vendorShippingAddress.addressName??>${inv.vendorShippingAddress.addressName},</#if></td>
                <td><#if inv.vendorPayTypeAddress.addressName??>${inv.vendorPayTypeAddress.addressName}</#if></td>
                <td><strong>Total Before Discount</strong></td>
                <td>:<#if inv.totalBeforeDisAmt??> ${inv.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                <td><#if inv.vendorShippingAddress.street??>${inv.vendorShippingAddress.street}, </#if></td>
                 <td> <#if inv.vendorPayTypeAddress.street??>${inv.vendorPayTypeAddress.street},</#if></td>
                 <td><strong>Freight</strong></td>
                <td>:<#if inv.freight??> ${inv.freight} <#else>--</#if></td>
                </tr>
                <tr>
                <td><#if inv.vendorShippingAddress.city??>${inv.vendorShippingAddress.city},</#if> </td>
                <td><#if inv.vendorPayTypeAddress.city??>${inv.vendorPayTypeAddress.city},</#if></td>
                <td><strong>Rounding</strong></td>
                <td>:<#if inv.amtRounding??> ${inv.amtRounding}<#else>-- </#if></td>
                </tr>
                <tr>
                <td><#if inv.vendorShippingAddress.zipCode??>${inv.vendorShippingAddress.zipCode}, </#if></td>
                <td> <#if inv.vendorPayTypeAddress.zipCode??>${inv.vendorPayTypeAddress.zipCode},</#if></td>
                <td><strong>Tax Amount</strong></td>
                <td>:<#if inv.taxAmt??> ${inv.taxAmt}<#else>--</#if></td>
                </tr>
                <tr>
                <td><#if inv.vendorShippingAddress.country.name??>${inv.vendorShippingAddress.country.name}.</#if></td>
                <td><#if inv.vendorPayTypeAddress.country.name??>${inv.vendorPayTypeAddress.country.name}.</#if></td>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if inv.totalPayment??> ${inv.totalPayment}<#else>--</#if></td>
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