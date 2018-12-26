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
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Purchase Request</h2>
                
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
               <td>:<#if rfq.requiredDate??>${rfq.requiredDate?string("dd-MM-yyyy")!''}</#if></td>
               <td><strong >Type</strong></td>
               <td>:<#if rfq.category??>${rfq.category}</#if> </td>
               <td><strong >Status</strong></td>
               <td>:<#if rfq.status??>${rfq.status}</#if> </td>
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
                <td style="border: solid 1px ;">${sno}<#assign sno = sno + 1 /></td>
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
                 <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >SAC Code</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                </tr>
                <#list rfq.lineItems as rfqlist>
                <tr>
                <td style="border: solid 1px ;">${sno}<#assign sno = sno + 1 /></td>
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