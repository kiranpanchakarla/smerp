<#ftl output_format="XML"  auto_esc=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

</head>
<body>
	

		<p align="center" style="font-size: 17pt;">Request For Quotation</p>
              
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>: ${rfq.vendor.name}</td>
                <td><strong >Email Id</strong></td>
                <td>: ${rfq.vendor.emailId} </td>
                 <td><strong >Contact Person</strong></td>
                <td>: ${rfq.vendorContactDetails.contactName} </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>: ${rfq.vendorPayTypeAddress.city}</td>
                <td><strong >Ship From</strong></td>
                <td>: ${rfq.vendorShippingAddress.city}</td>
                <td><strong>Doc No.</strong></td>
                <td>: ${rfq.docNumber}</td>
                </tr>
                
                <tr>
                <td><strong>Ref Doc No.</strong></td>
                <td>: ${rfq.referenceDocNumber}</td>
                <td><strong >Posting Date</strong></td>
                <td><#if rfq.postingDate??>${rfq.postingDate?string("dd-MM-yyyy")!''}</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td><#if rfq.documentDate??>${rfq.documentDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td><#if rfq.requiredDate??>${rfq.requiredDate?string("dd-MM-yyyy")!''}</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if rfq.category??>
               <#if rfq.category = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
               
                <td style="border: solid 1px ;"><strong >Product Name</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Product Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse	</strong></td>
                <td style="border: solid 1px ;"><strong >HSN Code</strong></td>
                </tr>
                <#list rfq.lineItems as rfqlist>
                <tr>
                <td style="border: solid 1px ;">${rfqlist.prodouctNumber}</td>
                <td style="border: solid 1px ;">${rfqlist.uom}</td>
                <td style="border: solid 1px ;">${rfqlist.requiredQuantity}</td>
                <td style="border: solid 1px ;">${rfqlist.productGroup}</td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (rfqlist.warehouse) == (key?string)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;">${rfqlist.hsn}</td>
                </tr>
                </#list>
                </table>
                <#else>
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >SAC Code</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                </tr>
                <#list rfq.lineItems as rfqlist>
                <tr>
                <td style="border: solid 1px ;">${rfqlist.sacCode}</td>
                <td style="border: solid 1px ;">${rfqlist.description}</td>
                <td style="border: solid 1px ;">${rfqlist.requiredQuantity}</td>
               <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (rfqlist.warehouse) == (key?string)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                
                </td>
                </tr>
                </#list>
                </table>
                </#if></#if>
                
     
    </body>
</html>