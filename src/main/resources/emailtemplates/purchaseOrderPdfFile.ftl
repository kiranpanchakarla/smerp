<#ftl output_format="XML"  auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

</head>
<body>


		<p align="center" style="font-size: 17pt;">Purchase Order</p>
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
                <table align="right">
                
                <tr>
                <td><strong>Discount(%)</strong></td>
                <td>:<#if po.totalDiscount??>${po.totalDiscount}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Total Before Discount</strong></td>
                <td>:<#if po.totalBeforeDisAmt??> ${po.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                
                <tr>
                <td><strong>Freight</strong></td>
                <td>:<#if po.freight??> ${po.freight} <#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Rounding</strong></td>
                <td>:<#if po.amtRounding??> ${po.amtRounding}<#else>-- </#if></td>
                </tr>
                
                <tr>
                <td><strong>Tax Amount</strong></td>
                <td>:<#if po.taxAmt??> ${po.taxAmt}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if po.totalPayment??> ${po.totalPayment}<#else>--</#if></td>
                </tr>
                
                </table>
     
     </#if>
     
    </body>
</html>