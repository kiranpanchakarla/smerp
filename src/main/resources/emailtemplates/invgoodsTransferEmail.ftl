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
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Inventory Goods Transfer</h2>
                
                
    <#if gr??>
         <table style="width:100%">
                
                <tr>
               <td><strong >Business Partner</strong></td>
                <td>:<#if gr.businessPartner??>&nbsp;${gr.businessPartner}<#else>--</#if> </td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if gr.documentDate??>&nbsp;${gr.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                </tr>
                
                <tr>
                
                <td><strong >Name</strong></td>
                <td>:<#if gr.user??>&nbsp;${gr.user}<#else>--</#if> </td>
                <td><strong >Status</strong></td>
                <td>:<#if gr.status??>&nbsp;${gr.status}<#else>--</#if> </td>
               
                </tr>
                
                <tr>
                 <td><strong >Posting Date</strong></td>
                <td>:<#if gr.postingDate??>&nbsp;${gr.postingDate?string("dd-MM-yyyy")!''}<#else>--</#if> </td>
                <td><strong>Doc#</strong></td>
                <td>:<#if gr.documentDate??>&nbsp;${gr.documentDate?string("dd-MM-yyyy")!''}<#else>--</#if></td>
                </tr>
                
                <tr>
                 <td><strong >Remarks</strong></td>
                <td>:<#if gr.remarks??>&nbsp;${gr.remarks}<#else>--</#if> </td>
                <td><strong >Ref Doc#</strong></td>
                <td>:<#if gr.referenceDocNumber??>&nbsp;${gr.referenceDocNumber}<#else>--</#if> </td>
                </tr>
            </table>
                <br></br>
                 <#assign sno = 1/>
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.No</strong></td>
                <td style="border: solid 1px ;"><strong >Product#</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >Ship From</strong></td>
                <td style="border: solid 1px ;"><strong >Ship To</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Unit Price</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Code</strong></td>
                <td style="border: solid 1px ;"><strong >Tax Total</strong></td>
                <td style="border: solid 1px ;"><strong >Total</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >HSN Code</strong></td>
                </tr>
                <#list gr.inventoryGoodsTransferList as goodsReclist>
                <tr>
                <td style="border: solid 1px ;text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if goodsReclist.productNumber??>&nbsp;${goodsReclist.productNumber}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.description??>&nbsp;${goodsReclist.description}<#else>--</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (goodsReclist.fromWarehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (goodsReclist.toWarehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
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
                 <td style="border: solid 1px ;"><#if goodsReclist.uom??>&nbsp;${goodsReclist.uom}<#else>--</#if></td>
                <td style="border: solid 1px ;"><#if goodsReclist.hsn??>&nbsp;${goodsReclist.hsn}<#else>--</#if></td>
                </tr>
                </#list>
                </table>
                <br></br><br></br>
                <table style="width:30%; float: right;" >
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