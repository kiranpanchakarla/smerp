<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title> Email Template</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body style="margin:0px; padding:0px; background-color:#88bdbf; font-family:Arial, Helvetica, sans-serif">
<table width="600" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td height="580" align="center" valign="top" bgcolor="#f3f3f3">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          
          <tr>
            <td height="220" align="center" valign="middle" bgcolor="#FFFFFF" style="padding:10px;"><table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <td align="center" valign="top" style="font-size:22px;"></td>
              </tr>
              <tr>
                <td height="80" align="center" valign="middle" style="font-size:12px; line-height:18px; color:#545454">
                <p style="text-align: left;font-family: inherit;"></p>
                <div style="background:#e6ecf3; text-align:left;padding:15px;overflow: hidden;">
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Goods Receipt</h2>
                
                
    <#if goodsRec??>
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>:<#if goodsRec.vendor.name??>&nbsp;${goodsRec.vendor.name}<#else>--</#if></td>
                <td><strong >Email Id</strong></td>
                <td>:<#if goodsRec.vendor.emailId??>&nbsp;${goodsRec.vendor.emailId}<#else>-- </#if></td>
                 <td><strong >Contact Person</strong></td>
                <td>:<#if goodsRec.vendorContactDetails.contactName??>&nbsp;${goodsRec.vendorContactDetails.contactName}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>:<#if goodsRec.vendorPayTypeAddress.city??>&nbsp;${goodsRec.vendorPayTypeAddress.city}<#else>--</#if></td>
                <td><strong >Ship From</strong></td>
                <td>:<#if goodsRec.vendorShippingAddress.city??>&nbsp;${goodsRec.vendorShippingAddress.city}<#else>--</#if></td>
                <td><strong>Doc No.</strong></td>
                <td>:<#if goodsRec.docNumber??>&nbsp;${goodsRec.docNumber}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Ref Doc No.</strong></td>
                <td>:<#if goodsRec.referenceDocNumber??>&nbsp;${goodsRec.referenceDocNumber}<#else>--</#if></td>
                <td><strong >Posting Date</strong></td>
                <td>:<#if goodsRec.postingDate??>&nbsp;${goodsRec.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if goodsRec.documentDate??>&nbsp;${goodsRec.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td>:<#if goodsRec.requiredDate??>${goodsRec.requiredDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if goodsRec.category??>
                 <#assign sno = 1/>
               <#if goodsRec.category = "Item"> 
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
                <#list goodsRec.goodsReceiptLineItems as goodsReclist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if goodsReclist.prodouctNumber??>&nbsp;${goodsReclist.prodouctNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.description??>&nbsp;${goodsReclist.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.uom??>&nbsp;${goodsReclist.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.sku??>&nbsp;${goodsReclist.sku}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.requiredQuantity??>&nbsp;${goodsReclist.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.unitPrice??>&nbsp;${goodsReclist.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                  <#if goodsReclist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (goodsReclist.taxCode) == (key)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                  </#if>
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
                <#list goodsRec.goodsReceiptLineItems as goodsReclist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if goodsReclist.sacCode??>&nbsp;${goodsReclist.sacCode}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.description??>&nbsp;${goodsReclist.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.requiredQuantity??>&nbsp;${goodsReclist.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.unitPrice??>&nbsp;${goodsReclist.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
               <#if goodsReclist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (goodsReclist.taxCode) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                </#if>
                </td>
                <td style="border: solid 1px ;"><#if goodsReclist.taxTotal??>&nbsp;${goodsReclist.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.total??>&nbsp;${goodsReclist.total}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (goodsReclist.warehouse) == (key)>
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
                <td>:<#if goodsRec.totalDiscount??>${goodsRec.totalDiscount}<#else>--</#if></td>
                </tr>
                                                                                   
                <tr>
                <td><#if goodsRec.vendorShippingAddress.addressName??>${goodsRec.vendorShippingAddress.addressName},</#if></td>
                <td><#if goodsRec.vendorPayTypeAddress.addressName??>${goodsRec.vendorPayTypeAddress.addressName}</#if></td>
                <td><strong>Total Before Discount</strong></td>
                <td>:<#if goodsRec.totalBeforeDisAmt??> ${goodsRec.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                <td><#if goodsRec.vendorShippingAddress.street??>${goodsRec.vendorShippingAddress.street}, </#if></td>
                 <td> <#if goodsRec.vendorPayTypeAddress.street??>${goodsRec.vendorPayTypeAddress.street},</#if></td>
                 <td><strong>Freight</strong></td>
                <td>:<#if goodsRec.freight??> ${goodsRec.freight} <#else>--</#if></td>
                </tr>
                <tr>
                <td><#if gr.vendorShippingAddress.zipCode??>${gr.vendorShippingAddress.zipCode}, </#if></td>
                <td> <#if gr.vendorPayTypeAddress.zipCode??>${gr.vendorPayTypeAddress.zipCode},</#if></td>
                <td><strong>Total</strong></td>
                <td>:<#if gr.amtRounding??> ${gr.amtRounding}<#else>-- </#if></td>
               
                </tr>
                <tr>
                <td><#if gr.vendorShippingAddress.country.name??>${gr.vendorShippingAddress.country.name}.</#if></td>
                <td><#if gr.vendorPayTypeAddress.country.name??>${gr.vendorPayTypeAddress.country.name}.</#if></td>
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
                 
     
     </#if>
     
                
                </div>
               </td>
              </tr>
              
              <tr>
              <td style="font-size:12px; line-height:18px; color:#545454">
              <p style="text-align: center;font-family: inherit;">This is an automatically generated email, please do not reply ...<br>
              <p style="text-align: center;font-family: inherit;">Email Sent by SMERP<br>
                                             Copyright &copy; 2019. All rights Reserved.
              </td>
              </tr>
              
            </table></td>
          </tr>
        </table></td>
      </tr>
      
    </table></td>
  </tr>
</table>
</body>
</html>