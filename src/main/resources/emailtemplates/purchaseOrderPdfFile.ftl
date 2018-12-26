<#ftl output_format="XML"  auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

</head>
<body>
	

		<p align="center" style="font-size: 17pt;">Purchase Order </p>
              
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>: ${po.vendor.name}</td>
                <td><strong >Email Id</strong></td>
                <td>: ${po.vendor.emailId} </td>
                 <td><strong >Contact Person</strong></td>
                <td>: ${po.vendorContactDetails.contactName} </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>: ${po.vendorPayTypeAddress.city}</td>
                <td><strong >Ship From</strong></td>
                <td>: ${po.vendorShippingAddress.city}</td>
                <td><strong>Doc No.</strong></td>
                <td>: ${po.docNumber}</td>
                </tr>
                
                <tr>
                <td><strong>Ref Doc No.</strong></td>
                <td>: ${po.referenceDocNumber}</td>
                <td><strong >Posting Date</strong></td>
                <td><#if po.postingDate??>${po.postingDate?string("dd-MM-yyyy")!''}</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td><#if po.documentDate??>${po.documentDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td><#if po.requiredDate??>${po.requiredDate?string("dd-MM-yyyy")!''}</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if po.category??>
               <#if po.category = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
               
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
                <td style="border: solid 1px ;">${polist.prodouctNumber}</td>
                <td style="border: solid 1px ;">${polist.uom}</td>
                <td style="border: solid 1px ;">${polist.requiredQuantity}</td>
                <td style="border: solid 1px ;">${polist.unitPrice}</td>
                <td style="border: solid 1px ;">
                <#list taxCodeMap as key, value>
                <#if (polist.taxCode) == (key)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                </td>
                <td style="border: solid 1px ;">${polist.taxTotal}</td>
                <td style="border: solid 1px ;">${polist.Total}</td>
                <td style="border: solid 1px ;">${polist.productGroup}</td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (polist.warehouse) == (key?string)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;">${polist.hsn}</td>
                </tr>
                </#list>
                </table>
                <#else>
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
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
                <td style="border: solid 1px ;">${polist.sacCode}</td>
                <td style="border: solid 1px ;">${polist.description}</td>
                <td style="border: solid 1px ;">${polist.requiredQuantity}</td>
                <td style="border: solid 1px ;">${polist.unitPrice}</td>
                <td style="border: solid 1px ;">
                <#list taxCodeMap as key, value>
                <#if (polist.taxCode) == (key)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                </td>
                <td style="border: solid 1px ;">${polist.taxTotal}</td>
                <td style="border: solid 1px ;">${polist.Total}</td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (polist.warehouse) == (key?string)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                
                </td>
                </tr>
                </#list>
                </table>
                </#if></#if>
                <table>
                
                <tr>
                <td><strong>Discount(%)</strong></td>
                <td>: ${po.totalDiscount}</td>
                </tr>
                
                <tr>
                <td><strong>Total Before Discount</strong></td>
                <td>: ${po.totalBeforeDisAmt}</td>
                </tr>
                
                <tr>
                <td><strong>Freight</strong></td>
                <td>: ${po.freight}</td>
                </tr>
                
                <tr>
                <td><strong>Rounding</strong></td>
                <td>: ${po.amtRounding}</td>
                </tr>
                
                <tr>
                <td><strong>Tax Amount</strong></td>
                <td>: ${po.taxAmt}</td>
                </tr>
                
                <tr>
                <td><strong>Total Payment Due</strong></td>
                <td>: ${po.totalPayment}</td>
                </tr>
                
                </table>
     
    </body>
</html>