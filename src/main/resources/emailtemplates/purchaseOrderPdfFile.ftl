<#ftl output_format="XML"  auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
<style>
.rotate {
width: 100%;
text-align:center;
color:#eee; 
transform: rotate(45deg);
transform-origin: 50% 50%;
opacity: .3;
position: fixed;
top:400px; 
font-size:60px;
}
</style>
</head>
<body>

<div class="header">
		<!-- <img
			src="data:image/jpeg;base64,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"
			style="width: 13%" alt="" />  -->
		<img src="${contextPath}/${user.company.logo}"
			style="width: 13%" alt="" /> 	
			 
	</div>
	<h2	class="header" style="position: fixed; left: 0; top: 0; width: 100%; color: black; text-align: center;">Purchase Order</h2>
	<br></br>
	
	<div class="rotate">
		 ${user.company.name}
	</div>

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
                <td><strong>Doc No.</strong></td>
                <td>:<#if po.docNumber??>&nbsp;${po.docNumber}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Ref Doc No.</strong></td>
                <td>:<#if po.referenceDocNumber??>&nbsp;${po.referenceDocNumber}<#else>--</#if></td>
                <td><strong >Posting Date</strong></td>
                <td>:<#if po.postingDate??>&nbsp;${po.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if po.documentDate??>&nbsp;${po.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td>:<#if po.requiredDate??>${po.requiredDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if po.category??>
                 <#assign sno = 1/>
               <#if po.category = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >Product Name</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Unit Price</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Code</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Total</strong></td>
                <td style="border: solid 1px ;"><strong >Total</strong></td>
                <td style="border: solid 1px ;"><strong >Product Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse	</strong></td>
                <td style="border: solid 1px ;"><strong >HSN Code</strong></td>
                </tr>
                <#list po.purchaseOrderlineItems as polist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if polist.prodouctNumber??>&nbsp;${polist.prodouctNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.uom??>&nbsp;${polist.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.requiredQuantity??>&nbsp;${polist.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.unitPrice??>&nbsp;${polist.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                  <#if polist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (polist.taxCode) == (key)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                  </#if>
                </td>
                <td style="border: solid 1px ;"><#if polist.taxTotal??>&nbsp;${polist.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if polist.total??>&nbsp;${polist.total}<#else>--</#if></td>
                <td style="border: solid 1px ;"> <#if polist.productGroup??>&nbsp;${polist.productGroup}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (polist.warehouse) == (key?string)>
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
                <#if (polist.warehouse) == (key?string)>
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
                <td><#if po.vendorShippingAddress.addressName??>${po.vendorShippingAddress.addressName},</#if></td>
                <td><#if po.vendorPayTypeAddress.addressName??>${po.vendorPayTypeAddress.addressName}</#if></td>
                <td><strong>Total Before Discount</strong></td>
                <td>:<#if po.totalBeforeDisAmt??> ${po.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                <td><#if po.vendorShippingAddress.street??>${po.vendorShippingAddress.street}, </#if></td>
                 <td> <#if po.vendorPayTypeAddress.street??>${po.vendorPayTypeAddress.street},</#if></td>
                 <td><strong>Freight</strong></td>
                <td>:<#if po.freight??> ${po.freight} <#else>--</#if></td>
                </tr>
                <tr>
                <td><#if po.vendorShippingAddress.city??>${po.vendorShippingAddress.city},</#if> </td>
                <td><#if po.vendorPayTypeAddress.city??>${po.vendorPayTypeAddress.city},</#if></td>
                <td><strong>Rounding</strong></td>
                <td>:<#if po.amtRounding??> ${po.amtRounding}<#else>-- </#if></td>
                </tr>
                <tr>
                <td><#if po.vendorShippingAddress.zipCode??>${po.vendorShippingAddress.zipCode}, </#if></td>
                <td> <#if po.vendorPayTypeAddress.zipCode??>${po.vendorPayTypeAddress.zipCode},</#if></td>
                <td><strong>Tax Amount</strong></td>
                <td>:<#if po.taxAmt??> ${po.taxAmt}<#else>--</#if></td>
                </tr>
                <tr>
                <td><#if po.vendorShippingAddress.country.name??>${po.vendorShippingAddress.country.name}.</#if></td>
                <td><#if po.vendorPayTypeAddress.country.name??>${po.vendorPayTypeAddress.country.name}.</#if></td>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if po.totalPayment??> ${po.totalPayment}<#else>--</#if></td>
                </tr>
            </table>
                 
     
     </#if>
     </div>
     <div style="position: fixed; left: 0;  bottom: 0; width: 100%; color: black;  text-align: center;">
     <p>Created by: ${user.username}</p>
     <p>Contact information: <a href="">help@manuhindia.com</a>.</p>
     </div>
    </body>
</html>