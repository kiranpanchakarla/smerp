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
                <td><p style="margin:5px 0;"><strong style="color: #106570;">User</strong></p></td>
                <td><p style="margin:5px 0;">: ${pr.user.username}</p></td>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Doc No</strong></p></td>
                <td><p style="margin:5px 0;">: ${pr.docNumber} </p></td>
                </tr>
                
                <tr>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Requester Name</strong></p></td>
                <td><p style="margin:5px 0;">: ${pr.referenceUser.firstname}</p></td>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Posting Date</strong></p></td>
                <td><p style="margin:5px 0;"><#if pr.postingDate??>${pr.postingDate?string("dd-MM-yyyy")!''}</#if></p> </td>
                </tr>
                
                <tr>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Plant</strong></p></td>
                <td><p style="margin:5px 0;">: ${pr.referenceUser.plant.plantName}</p></td>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Doc Date</strong></p></td>
                <td><p style="margin:5px 0;"><#if pr.documentDate??>${pr.documentDate?string("dd-MM-yyyy")!''}</#if></p> </td>
                </tr>
                
                <tr>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Email- ID</strong></p></td>
                <td><p style="margin:5px 0;">:   ${pr.referenceUser.userEmail}</p></td>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Require Date</strong></p></td>
                <td><p style="margin:5px 0;"><#if pr.requiredDate??>${pr.requiredDate?string("dd-MM-yyyy")!''}</#if></p></td>
                </tr>
                
                <tr>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Type</strong></p></td>
                <td><p style="margin:5px 0;">:  ${pr.type}</p></td>
                <td><p style="margin:5px 0;"><strong style="color: #106570;">Status</strong></p></td>
                <td><p style="margin:5px 0;">:  ${pr.status}</p></td> 
                </tr>
                
                </table>
                
                <#if pr.type??>
                 <#assign sno = 1/>
               <#if pr.type = "Item"> 
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">S.no</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">Product Name</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">Description</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">UOM</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">Quantity</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">Product Group</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">Warehouse	</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">HSN</strong></p></td>
                </tr>
                <#list pr.purchaseRequestLists as prlist>
                <tr>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${sno}<#assign sno = sno + 1 /></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.prodouctNumber}</p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.description}</p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.uom}</p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.requiredQuantity}</p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.productGroup}</p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">  
                <#list plantMap as key, value>
               <#if (prlist.warehouse) == (key?string)>
                    <p> ${value}</p>
                    </#if>
               </#list>
                </p></td>
                
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.hsn}</p></td>
                </tr>
                </#list>
                </table>
                <#else>
                <table style="width:100% ; border-collapse: collapse;" >
                <tr>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">S.no</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">SAC Code</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">Description</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">Quantity</strong></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;"><strong style="color: #106570;">Warehouse</strong></p></td>
                </tr>
                <#list pr.purchaseRequestLists as prlist>
                <tr>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${sno}<#assign sno = sno + 1 /></p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.sacCode}</p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.description}</p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.requiredQuantity}</p></td>
                <td style="border: solid 1px gray;"><p style="margin:5px;">${prlist.warehouse}</p></td>
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