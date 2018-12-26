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
                <p style="text-align: left;font-family: inherit;">Dear <b>${user.username}</b> </p>
                <p style="text-align: left;font-family: inherit;"></p>
                <div style="background:#e6ecf3; text-align:left;padding:15px;overflow: hidden;">
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Purchase Order</h2>
                
                 <#if po??>
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
                <td>:<#if po.postingDate??>${po.postingDate?string("dd-MM-yyyy")!''}</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if po.documentDate??>${po.documentDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Require Date</strong></td>
                <td>:<#if po.requiredDate??>${po.requiredDate?string("dd-MM-yyyy")!''}</#if></td>
                 <td><strong >Type</strong></td>
                <td>:<#if po.category??>${po.category}</#if> </td>
                <td><strong >Status</strong></td>
                <td>:<#if po.status??>${po.status}</#if> </td>
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
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                <td style="border: solid 1px ;"><strong >HSN Code</strong></td>
                </tr>
                <#list po.purchaseOrderlineItems as polist>
                <tr>
                <td style="border: solid 1px ;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;">${polist.prodouctNumber}</td>
                <td style="border: solid 1px ;">${polist.uom}</td>
                <td style="border: solid 1px ;">${polist.requiredQuantity}</td>
                <td style="border: solid 1px ;"><#if polist.unitPrice??>${polist.unitPrice}</#if></td>
                <td style="border: solid 1px ;">
                  <#if polist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (polist.taxCode) == (key)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                  </#if>
                </td>
                <td style="border: solid 1px ;"><#if polist.taxTotal??> ${polist.taxTotal}</#if></td>
                <td style="border: solid 1px ;"><#if polist.total??> ${polist.total}</#if></td>
                <td style="border: solid 1px ;"> ${polist.productGroup}</td>
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
                <td style="border: solid 1px ;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;">${polist.sacCode}</td>
                <td style="border: solid 1px ;">${polist.description}</td>
                <td style="border: solid 1px ;">${polist.requiredQuantity}</td>
              <td style="border: solid 1px ;"><#if polist.unitPrice??>${polist.unitPrice}</#if></td>
                <td style="border: solid 1px ;">
               <#if polist.taxCode??>
                <#list taxCodeMap as key, value>
                <#if (polist.taxCode) == (key)>
                     <p> ${value}</p>
                     </#if>
                </#list>
                </#if>
                </td>
                <td style="border: solid 1px ;"><#if polist.taxTotal??> ${polist.taxTotal}</#if></td>
                <td style="border: solid 1px ;"><#if polist.total??> ${polist.total}</#if></td>
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
                
                <br></br><br></br>
                <table align="right">
                
                <tr>
                <td><strong>Discount(%)</strong></td>
                <td>:<#if po.totalDiscount??> ${po.totalDiscount}</#if></td>
                </tr>
                
                <tr>
                <td><strong>Total Before Discount</strong></td>
                <td>:<#if po.totalBeforeDisAmt??> ${po.totalBeforeDisAmt}</#if></td>
                </tr>
                
                <tr>
                <td><strong>Freight</strong></td>
                <td>:<#if po.freight??> ${po.freight} </#if></td>
                </tr>
                
                <tr>
                <td><strong>Rounding</strong></td>
                <td>:<#if po.amtRounding??> ${po.amtRounding} </#if></td>
                </tr>
                
                <tr>
                <td><strong>Tax Amount</strong></td>
                <td>:<#if po.taxAmt??> ${po.taxAmt}</#if></td>
                </tr>
                
                <tr>
                <td><strong>Total Payment Due</strong></td>
                <td>:<#if po.totalPayment??> ${po.totalPayment}</#if></td>
                </tr>
                
                </table>
     
            </#if>
                
                </div>
               </td>
              </tr>
              
              <tr>
              <td style="font-size:12px; line-height:18px; color:#545454">
              <p style="text-align: center;font-family: inherit;">Email Sent by SMERP <br>
                                             Copyright &copy; 2018. All rights Reserved.
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