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
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>:<#if rfq.vendor.name??>${rfq.vendor.name}</#if></td>
                <td><strong >Email Id</strong></td>
                <td>:<#if rfq.vendor.emailId??>${rfq.vendor.emailId} </#if></td>
                 <td><strong >Contact Person</strong></td>
                <td>:<#if rfq.vendorContactDetails.contactName??>${rfq.vendorContactDetails.contactName}</#if> </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>: <#if rfq.vendorPayTypeAddress.city??>${rfq.vendorPayTypeAddress.city}</#if></td>
                <td><strong >Ship From</strong></td>
                <td>: <#if rfq.vendorShippingAddress.city??>${rfq.vendorShippingAddress.city}</#if></td>
                <td><strong>Doc No.</strong></td>
                <td>:<#if rfq.docNumber??>${rfq.docNumber}</#if></td>
                </tr>
                
                <tr>
                <td><strong>Ref Doc No.</strong></td>
                <td>:<#if rfq.referenceDocNumber??>${rfq.referenceDocNumber}</#if></td>
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
                 <#assign sno = 1/>
               <#if rfq.category = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >Product Name</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Product Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse	</strong></td>
                <td style="border: solid 1px ;"><strong >HSN Code</strong></td>
                </tr>
                <#list rfq.lineItems as rfqlist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if rfqlist.prodouctNumber??>&nbsp;${rfqlist.prodouctNumber}</#if></td>
                <td style="border: solid 1px ;"><#if rfqlist.uom??>&nbsp;${rfqlist.uom}</#if></td>
                <td style="border: solid 1px ;"><#if rfqlist.requiredQuantity??>&nbsp;${rfqlist.requiredQuantity}</#if></td>
                <td style="border: solid 1px ;"><#if rfqlist.productGroup??>&nbsp;${rfqlist.productGroup}</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (rfqlist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;"><#if rfqlist.hsn??>&nbsp;${rfqlist.hsn}</#if></td>
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
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                </tr>
                <#list rfq.lineItems as rfqlist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if rfqlist.sacCode??>&nbsp;${rfqlist.sacCode}</#if></td>
                <td style="border: solid 1px ;"><#if rfqlist.description??>&nbsp;${rfqlist.description}</#if></td>
                <td style="border: solid 1px ;"><#if rfqlist.requiredQuantity??>&nbsp;${rfqlist.requiredQuantity}</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (rfqlist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                </tr>
                </#list>
                </table>
                </#if></#if>
                <br></br>
                <table style="width:50%">
                <tr>
                <td><strong>Shipping From :</strong></td>
                <td><strong>Pay To :</strong></td>
                </tr>
                <tr>
                <td><#if rfq.vendorShippingAddress.addressName??>${rfq.vendorShippingAddress.addressName},</#if></td>
                <td><#if rfq.vendorPayTypeAddress.addressName??>${rfq.vendorPayTypeAddress.addressName}</#if></td>
                </tr>
                <tr>
                <td><#if rfq.vendorShippingAddress.street??>${rfq.vendorShippingAddress.street}, </#if></td>
                 <td> <#if rfq.vendorPayTypeAddress.street??>${rfq.vendorPayTypeAddress.street},</#if></td>
                </tr>
                <tr>
                <td><#if rfq.vendorShippingAddress.city??>${rfq.vendorShippingAddress.city},</#if> </td>
                <td><#if rfq.vendorPayTypeAddress.city??>${rfq.vendorPayTypeAddress.city},</#if></td>
                </tr>
                <tr>
                <td><#if rfq.vendorShippingAddress.zipCode??>${rfq.vendorShippingAddress.zipCode}, </#if></td>
                <td> <#if rfq.vendorPayTypeAddress.zipCode??>${rfq.vendorPayTypeAddress.zipCode},</#if></td>
                </tr>
                <tr>
                <td><#if rfq.vendorShippingAddress.country.name??>${rfq.vendorShippingAddress.country.name}.</#if></td>
                <td><#if rfq.vendorPayTypeAddress.country.name??>${rfq.vendorPayTypeAddress.country.name}.</#if></td>
                </tr>
            </table>
            </div>
     
    </body>
</html>