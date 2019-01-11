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
                <div style="background:#e6ecf3; text-align:left;padding:15px">
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Request For Quotation</h2>
                
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
                 
                <table style="width:100%">
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
                <tr><td><#if rfq.vendorShippingAddress.zipCode??>${rfq.vendorShippingAddress.zipCode}, </#if></td>
                <td> <#if rfq.vendorPayTypeAddress.zipCode??>${rfq.vendorPayTypeAddress.zipCode},</#if></td>
                </tr>
                <tr><td><#if rfq.vendorShippingAddress.country.name??>${rfq.vendorShippingAddress.country.name}.</#if> </td>
                <td><#if rfq.vendorPayTypeAddress.country.name??>${rfq.vendorPayTypeAddress.country.name}.</#if></td>
                </tr>
            </table>
                 
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