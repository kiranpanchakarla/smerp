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
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Goods Return</h2>
                
                
    <#if goodsRet??>
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>:<#if goodsRet.vendor.name??>&nbsp;${goodsRet.vendor.name}<#else>--</#if></td>
                <td><strong >Email Id</strong></td>
                <td>:<#if goodsRet.vendor.emailId??>&nbsp;${goodsRet.vendor.emailId}<#else>-- </#if></td>
                 <td><strong >Contact Person</strong></td>
                <td>:<#if goodsRet.vendorContactDetails.contactName??>&nbsp;${goodsRet.vendorContactDetails.contactName}<#else>--</#if> </td>
                </tr>
                
                 <tr>
                <td><strong>Pay To</strong></td>
                <td>:<#if goodsRet.vendorPayTypeAddress.city??>&nbsp;${goodsRet.vendorPayTypeAddress.city}<#else>--</#if></td>
                <td><strong >Ship From</strong></td>
                <td>:<#if goodsRet.vendorShippingAddress.city??>&nbsp;${goodsRet.vendorShippingAddress.city}<#else>--</#if></td>
                 <td><strong >Posting Date</strong></td>
                <td>:<#if goodsRet.postingDate??>&nbsp;${goodsRet.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>GRE Doc#</strong></td>
                <td>:<#if goodsRet.docNumber??>&nbsp;${goodsRet.docNumber}<#else>--</#if></td>
                <td><strong>GR Doc#</strong></td>
                <td>:<#if goodsRet.referenceDocNumber??>&nbsp;${goodsRet.referenceDocNumber}<#else>--</#if></td>
               <td><strong>PO Doc#</strong></td>
                <td>:<#if goodsRet.grId?? && goodsRet.grId.poId??>&nbsp;${goodsRet.grId.poId.docNumber}<#else>--</#if></td>
                 </tr>
                
                <tr>
                <td><strong>RFQ Doc#</strong></td>
                <td>:<#if goodsRet.grId?? && goodsRet.grId.poId.rfqId??>&nbsp;${goodsRet.grId.poId.rfqId.docNumber}<#else>--</#if></td>
                <td><strong>PR Doc#.</strong></td>
                <td>:<#if goodsRet.grId?? && goodsRet.grId.poId.rfqId.purchaseReqId??>&nbsp;${goodsRet.grId.poId.rfqId.purchaseReqId.docNumber}<#else>--</#if></td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if goodsRet.documentDate??>&nbsp;${goodsRet.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td>:<#if goodsRet.requiredDate??>${goodsRet.requiredDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                <td><strong>Status</strong></td>
				<td>: <#if goodsRet.status??> ${goodsRet.status}</#if></td>
				<td><strong>Remarks</strong></td>
				<td>: <#if goodsRet.remark??> ${goodsRet.remark}</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if goodsRet.category??>
                 <#assign sno = 1/>
               <#if goodsRet.category = "Item"> 
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
                <#list goodsRet.goodsReturnLineItems as goodsRetlist>
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
                <#list goodsRet.goodsReturnLineItems as goodsRetlist>
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
                <td>:<#if goodsRet.totalDiscount??>${goodsRet.totalDiscount}<#else>--</#if></td>
                </tr>
                                                                                   
                <tr>
                <td><#if goodsRet.vendorShippingAddress.addressName??>${goodsRet.vendorShippingAddress.addressName},</#if></td>
                <td><#if goodsRet.vendorPayTypeAddress.addressName??>${goodsRet.vendorPayTypeAddress.addressName}</#if></td>
                <td><strong>Total Invoice Amount</strong></td>
                <td>:<#if goodsRet.totalBeforeDisAmt??> ${goodsRet.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                <td><#if goodsRet.vendorShippingAddress.street??>${goodsRet.vendorShippingAddress.street}, </#if></td>
                 <td> <#if goodsRet.vendorPayTypeAddress.street??>${goodsRet.vendorPayTypeAddress.street},</#if></td>
                 <td><strong>Freight</strong></td>
                <td>:<#if goodsRet.freight??> ${goodsRet.freight} <#else>--</#if></td>
                </tr>
                <tr>
                <td><#if goodsRet.vendorShippingAddress.zipCode??>${goodsRet.vendorShippingAddress.zipCode}, </#if></td>
                <td> <#if goodsRet.vendorPayTypeAddress.zipCode??>${goodsRet.vendorPayTypeAddress.zipCode},</#if></td>
                <td><strong>Total</strong></td>
                <td>:<#if goodsRet.amtRounding??> ${goodsRet.amtRounding}<#else>-- </#if></td>
               
                </tr>
                <tr>
                <td><#if goodsRet.vendorShippingAddress.country.name??>${goodsRet.vendorShippingAddress.country.name}.</#if></td>
                <td><#if goodsRet.vendorPayTypeAddress.country.name??>${goodsRet.vendorPayTypeAddress.country.name}.</#if></td>
                <td><strong>Rounded Off</strong></td>
                <td>:<#if goodsRet.roundedOff??> ${goodsRet.roundedOff}<#else>--</#if></td>
                </tr>
                <tr>
                <td></td>
                <td></td>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if goodsRet.totalPayment??> ${goodsRet.totalPayment}<#else>--</#if></td>
                </tr>
            </table>
                 <table style="width:100%">
                <tr>
                <td><strong>Deliver To :</strong></td>
                </tr>
                <tr>
                <td><#if goodsRet.deliverTo??>${goodsRet.deliverTo},</#if></td>
                </tr>   
              </table>
     
     </#if>
     
                
                </div>
               </td>
              </tr>
              
              <tr>
              <td style="font-size:12px; line-height:18px; color:#545454">
               
              <p style="text-align: center;font-family: inherit;">Email Sent by SMERP, do not reply.<br>
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