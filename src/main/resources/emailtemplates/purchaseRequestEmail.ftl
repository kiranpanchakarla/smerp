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
                <p style="text-align: left;font-family: inherit;">Dear <b>${pr.user.username},</b> </p>
                <p style="text-align: left;font-family: inherit;"></p>
                <div style="background:#e6ecf3; text-align:left;padding:15px">
                <h2 style="font-size: 1.4rem;color: #106570;font-weight: 600;margin: 10px 0; border-bottom: 1px solid rgba(0, 0, 0, 0.1); padding-bottom: 10px;font-family: inherit;">Purchase Request</h2>
                
                <table style="width:100%">
                <tr>
                <td><strong >User</strong></td>
                <td>: <#if pr.user.username??> ${pr.user.username}</#if></td>
                <td><strong >Doc No</strong></td>
                <td>:<#if pr.docNumber??> ${pr.docNumber}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Requester Name</strong></td>
                <td>:<#if pr.referenceUser.firstname??> ${pr.referenceUser.firstname}</#if></td>
                <td><strong >Posting Date </strong></td>
                <td>:<#if pr.postingDate??>${pr.postingDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Plant</strong></td>
                <td>:<#if pr.referenceUser.plant.plantName??> ${pr.referenceUser.plant.plantName}</#if></td>
                <td><strong >Doc Date</strong></td>
                <td>:<#if pr.documentDate??>${pr.documentDate?string("dd-MM-yyyy")!''}</#if> </td>
                </tr>
                
                <tr>
                <td><strong >Email- ID</strong></td>
                <td>:  <#if pr.referenceUser.userEmail??> ${pr.referenceUser.userEmail}</#if></td>
                <td><strong >Require Date</strong></td>
                <td>:<#if pr.requiredDate??>${pr.requiredDate?string("dd-MM-yyyy")!''}</#if></td>
                </tr>
                
                <tr>
                <td><strong >Type</strong></td>
                <td>: <#if pr.type??> ${pr.type}</#if></td>
                <td><strong >Status</strong></td>
                <td>: <#if pr.status??> ${pr.status}</#if></td> 
                </tr>
                
            </table>
                <br></br>
                <#if pr.type??>
                 <#assign sno = 1/>
               <#if pr.type = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px ;"><strong >S.no</strong></td>
                <td style="border: solid 1px ;"><strong >Product Name</strong></td>
                <td style="border: solid 1px ;"><strong >Description</strong></td>
                <td style="border: solid 1px ;"><strong >UOM</strong></td>
                <td style="border: solid 1px ;"><strong >SKU</strong></td>
                <td style="border: solid 1px ;"><strong >Quantity</strong></td>
                <td style="border: solid 1px ;"><strong >Product Group</strong></td>
                <td style="border: solid 1px ;"><strong >Warehouse</strong></td>
                <td style="border: solid 1px ;"><strong >HSN</strong></td>
                </tr>
                <#list pr.purchaseRequestLists as prlist>
                <tr>
                <td style="border: solid 1px ; text-align:center;">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if prlist.prodouctNumber??>&nbsp;${prlist.prodouctNumber}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.description??>&nbsp;${prlist.description}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.uom??> &nbsp;${prlist.uom}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.sku??> &nbsp;${prlist.sku}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.requiredQuantity??>&nbsp;${prlist.requiredQuantity}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.productGroup??>&nbsp;${prlist.productGroup}</#if></td>
                <td style="border: solid 1px ;">
                
                <#list plantMap as key, value>
                <#if (prlist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list>
                
                </td>
                <td style="border: solid 1px ;">&nbsp;${prlist.hsn}</td>
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
                <#list pr.purchaseRequestLists as prlist>
                <tr>
                <td style="border: solid 1px ;text-align:center; ">${sno}<#assign sno = sno + 1 /></td>
                <td style="border: solid 1px ;"><#if prlist.sacCode??>&nbsp;${prlist.sacCode}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.description??>&nbsp;${prlist.description}</#if></td>
                <td style="border: solid 1px ;"><#if prlist.requiredQuantity??>&nbsp;${prlist.requiredQuantity}</#if></td>
                <td style="border: solid 1px ;"> <#list plantMap as key, value>
                <#if (prlist.warehouse) == (key)>
                     <p>&nbsp;${value}</p>
                     </#if>
                </#list></td>
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