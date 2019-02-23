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
                <strong style="font-size:20px;">Inventory Goods Receipt</strong><br/>
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
                 <td><strong >Document Number</strong></td>
                <td>:<#if gr.docNumber??>&nbsp;${gr.docNumber}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong >Posting Date</strong></td>
                <td>:<#if gr.postingDate??>&nbsp;${gr.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if gr.documentDate??>&nbsp;${gr.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong>Ref Doc No.</strong></td>
                <td>:<#if gr.referenceDocNumber??>&nbsp;${gr.referenceDocNumber}<#else>--</#if></td>
                </tr>
                 
            </table>
                <br></br>
                 <#assign sno = 1/>
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
                <#list gr.inventoryGoodsReceiptList as goodsReclist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if goodsReclist.productNumber??>&nbsp;${goodsReclist.productNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.description??>&nbsp;${goodsReclist.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.uom??>&nbsp;${goodsReclist.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.sku??>&nbsp;${goodsReclist.sku}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.requiredQuantity??>&nbsp;${goodsReclist.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.unitPrice??>&nbsp;${goodsReclist.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.taxDescription??>&nbsp;${goodsReclist.taxDescription}<#else>--</#if>
                 <!-- 
                  <#if goodsReclist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (goodsReclist.taxCode) ==  (value)>
                     <p> ${key}</p>
                     </#if>
                </#list>
                  </#if> -->
                </td>
                <td style="border: solid 1px ;"><#if goodsReclist.taxTotal??>&nbsp;${goodsReclist.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.total??>&nbsp;${goodsReclist.total}<#else>--</#if></td>
                <td style="border: solid 1px ;"> <#if goodsReclist.productGroup??>&nbsp;${goodsReclist.productGroup}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (goodsReclist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;"><#if goodsReclist.hsn??>&nbsp;${goodsReclist.hsn}<#else>--</#if></td>
                </tr>
                </#list>
                </table>
                <br></br><br></br>
                <table style="width:40%; float: right;" >
                <tr>
                <td><strong>Discount(%)</strong></td>
                <td>:<#if gr.totalDiscount??>${gr.totalDiscount}<#else>--</#if></td>
                </tr>
                                                                                   
                <tr>
                <td><strong>Total Invoice Amount</strong></td>
                <td>:<#if gr.totalBeforeDisAmt??> ${gr.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                 <td><strong>Freight</strong></td>
                <td>:<#if gr.freight??> ${gr.freight} <#else>--</#if></td>
                </tr>
                
                 <tr>
                 <td><strong>Tax Amount</strong></td>
                <td>:<#if gr.taxAmt??> ${gr.taxAmt}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Total</strong></td>
                <td>:<#if gr.amtRounding??> ${gr.amtRounding}<#else>-- </#if></td>
                </tr>
                
                <tr>
                <td><strong>Rounded Off</strong></td>
                <td>:<#if gr.roundedOff??> ${gr.roundedOff}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if gr.totalPayment??> ${gr.totalPayment}<#else>--</#if></td>
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