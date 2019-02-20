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
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Credit Memo</h2>
                
                
    <#if credit??>
        <table style="width:100%">
                <tr>
                <td><strong>Name</strong></td>
                <td>:<#if credit.vendor.name??>&nbsp;${credit.vendor.name}<#else>--</#if></td>
                <td><strong >Email Id</strong></td>
                <td>:<#if credit.vendor.emailId??>&nbsp;${credit.vendor.emailId}<#else>-- </#if></td>
                 <td><strong >Contact Person</strong></td>
                <td>:<#if credit.vendorContactDetails.contactName??>&nbsp;${credit.vendorContactDetails.contactName}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong>Pay To</strong></td>
                <td>:<#if credit.vendorPayTypeAddress.city??>&nbsp;${credit.vendorPayTypeAddress.city}<#else>--</#if></td>
                <td><strong >Ship From</strong></td>
                <td>:<#if credit.vendorShippingAddress.city??>&nbsp;${credit.vendorShippingAddress.city}<#else>--</#if></td>
                <td><strong>Doc No.</strong></td>
                <td>:<#if credit.docNumber??>&nbsp;${credit.docNumber}<#else>--</#if></td>
                </tr>
                
                <tr>
                <td><strong>Ref Doc No.</strong></td>
                <td>:<#if credit.referenceDocNumber??>&nbsp;${credit.referenceDocNumber}<#else>--</#if></td>
                <td><strong >Posting Date</strong></td>
                <td>:<#if credit.postingDate??>&nbsp;${credit.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if credit.documentDate??>&nbsp;${credit.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td>:<#if credit.requiredDate??>${credit.requiredDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                </tr>
                
             
            </table>
                <br></br>
                <#if credit.category??>
                 <#assign sno = 1/>
               <#if credit.category = "Item"> 
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
                <#list credit.creditMemoLineItems as creditoiceList>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if creditoiceList.prodouctNumber??>&nbsp;${creditoiceList.prodouctNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.description??>&nbsp;${creditoiceList.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.uom??>&nbsp;${creditoiceList.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.sku??>&nbsp;${creditoiceList.sku}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.requiredQuantity??>&nbsp;${creditoiceList.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.unitPrice??>&nbsp;${creditoiceList.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                  <#if creditoiceList.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (creditoiceList.taxCode) == (value)>
                     <p> ${key}</p>
                     </#if>
                </#list>
                  </#if>
                </td>
                <td style="border: solid 1px ;"><#if creditoiceList.taxTotal??>&nbsp;${creditoiceList.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.total??>&nbsp;${creditoiceList.total}<#else>--</#if></td>
                <td style="border: solid 1px ;"> <#if creditoiceList.productGroup??>&nbsp;${creditoiceList.productGroup}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (creditoiceList.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;"><#if creditoiceList.hsn??>&nbsp;${creditoiceList.hsn}<#else>--</#if></td>
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
                <#list credit.creditMemoLineItems as creditoiceList>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if creditoiceList.sacCode??>&nbsp;${creditoiceList.sacCode}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.description??>&nbsp;${creditoiceList.description}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.requiredQuantity??>&nbsp;${creditoiceList.requiredQuantity}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.unitPrice??>&nbsp;${creditoiceList.unitPrice}<#else>--</#if></td>
                <td style="border: solid 1px ;">
               <#if creditoiceList.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (creditoiceList.taxCode) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                </#if>
                </td>
                <td style="border: solid 1px ;"><#if creditoiceList.taxTotal??>&nbsp;${creditoiceList.taxTotal}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if creditoiceList.total??>&nbsp;${creditoiceList.total}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (creditoiceList.warehouse) == (key)>
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
                <td>:<#if credit.totalDiscount??>${credit.totalDiscount}<#else>--</#if></td>
                </tr>
                                                                                   
                <tr>
                <td><#if credit.vendorShippingAddress.addressName??>${credit.vendorShippingAddress.addressName},</#if></td>
                <td><#if credit.vendorPayTypeAddress.addressName??>${credit.vendorPayTypeAddress.addressName}</#if></td>
                <td><strong>Total Before Discount</strong></td>
                <td>:<#if credit.totalBeforeDisAmt??> ${credit.totalBeforeDisAmt}<#else> --</#if></td>
                </tr>
                <tr>
                <td><#if credit.vendorShippingAddress.street??>${credit.vendorShippingAddress.street}, </#if></td>
                 <td> <#if credit.vendorPayTypeAddress.street??>${credit.vendorPayTypeAddress.street},</#if></td>
                 <td><strong>Freight</strong></td>
                <td>:<#if credit.freight??> ${credit.freight} <#else>--</#if></td>
                </tr>
                <tr>
                <td><#if credit.vendorShippingAddress.zipCode??>${credit.vendorShippingAddress.zipCode}, </#if></td>
                <td> <#if credit.vendorPayTypeAddress.zipCode??>${credit.vendorPayTypeAddress.zipCode},</#if></td>
                <td><strong>Total</strong></td>
                <td>:<#if credit.amtRounding??> ${credit.amtRounding}<#else>-- </#if></td>
               
                </tr>
                <tr>
                <td><#if credit.vendorShippingAddress.country.name??>${credit.vendorShippingAddress.country.name}.</#if></td>
                <td><#if credit.vendorPayTypeAddress.country.name??>${credit.vendorPayTypeAddress.country.name}.</#if></td>
                <td><strong>Rounded Off</strong></td>
                <td>:<#if credit.roundedOff??> ${credit.roundedOff}<#else>--</#if></td>
                </tr>
                <tr>
                <td></td>
                <td></td>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if credit.totalPayment??> ${credit.totalPayment}<#else>--</#if></td>
                </tr>
            </table>
                 
     
     </#if>
     
                
                </div>
               </td>
              </tr>
              
              <tr>
              <td style="font-size:12px; line-height:18px; color:#545454">
              <p style="text-align: center;font-family: inherit;">Email Sent by SMERP, do not reply. <br>
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