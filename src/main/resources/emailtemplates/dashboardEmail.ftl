<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title>Email Template</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<body style="margin:0px; padding:0px; background-color:#88bdbf; font-family:Arial, Helvetica, sans-serif">
<table width="600" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td height="580" align="center" valign="top" bgcolor="#f3f3f3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="220" align="center" valign="middle" bgcolor="#FFFFFF" style="padding:10px;"><table width="100%" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                      <td align="center" valign="top" style="font-size:26px;">Email Template </td>
                    </tr>
                    <tr>
                      <td><table style="width:100%;">
                          <tr>
                            <td><div class="card new_card_style" style="
    background: #e6ecf3;
">
                                <div class="card-body">
                                  <div class="card-block" style="
    padding: 10px;
">
                                    <h4 class="card-title" style="text-align: center;text-transform: capitalize;font-weight: 600;letter-spacing: 0.4px;margin: 0px;font-size: 14px;">Purchase Request</h4>
                                  </div>
                                  <ul class="list-group" style="margin: 0px;padding: 0px;"> 
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${prCount.open}</span> Open </li> 
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${prCount.draft}</span> Draft </li>
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${prCount.cancelled}</span>Cancelled </li> 
                                 <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${prCount.approved}</span> Approved</li> 
                                <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${prCount.rejected}</span> Rejected </li> 
                               <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${prCount.convertedToRFQ}</span>Converted to RFQ</li> 
                               <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${prCount.total}</span> Total Records </li> </ul>
                                </div>
                              </div></td>
                            <td><div class="card new_card_style" style="
    background: #e6ecf3;
">
                                <div class="card-body">
                                  <div class="card-block" style="
    padding: 10px;
">
                                    <h4 class="card-title" style="text-align: center;text-transform: capitalize;font-weight: 600;letter-spacing: 0.4px;margin: 0px;font-size: 14px;">Request For Quotation</h4>
                                  </div>
                                  <ul class="list-group" style="margin: 0px;padding: 0px;"> 
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${rfqCount.open}</span> Open </li> 
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${rfqCount.draft}</span> Draft </li>
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${rfqCount.cancelled}</span>Cancelled </li> 
                                 <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${rfqCount.approved}</span> Approved</li> 
                                <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${rfqCount.rejected}</span> Rejected </li> 
                               <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${rfqCount.convertedToPO}</span>Converted to PO</li> 
                               <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${rfqCount.total}</span>Total Records</li> </ul>
                                </div>
                              </div></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td><table style="width:100%;">
                          <tr>
                            <td><div class="card new_card_style" style="
    background: #e6ecf3;
">
                                <div class="card-body">
                                  <div class="card-block" style="
    padding: 10px;
">
                                    <h4 class="card-title" style="text-align: center;text-transform: capitalize;font-weight: 600;letter-spacing: 0.4px;margin: 0px;font-size: 14px;">Purchase Order</h4>
                                  </div>
                                  <ul class="list-group" style="margin: 0px;padding: 0px;"> 
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${poCount.open}</span> Open </li> 
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${poCount.draft}</span> Draft </li>
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${poCount.cancelled}</span>Cancelled </li> 
                                 <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${poCount.approved}</span> Approved</li> 
                                <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${poCount.rejected}</span> Rejected </li> 
                               <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${poCount.completed}</span>Completed</li> 
                               <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${poCount.total}</span>Total Records</li> </ul>
                                </div>
                              </div></td>
                            <td><div class="card new_card_style" style="
    background: #e6ecf3;
">
                                <div class="card-body">
                                  <div class="card-block" style="
    padding: 10px;
">
                                    <h4 class="card-title" style="text-align: center;text-transform: capitalize;font-weight: 600;letter-spacing: 0.4px;margin: 0px;font-size: 14px;">Goods Receipt</h4>
                                  </div>
                                  <ul class="list-group" style="margin: 0px;padding: 0px;"> 
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${grCount.open}</span> Open </li> 
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${grCount.draft}</span> Draft </li>
                                  <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${grCount.cancelled}</span>Cancelled </li> 
                                 <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${grCount.approved}</span> Approved</li> 
                                <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${grCount.rejected}</span> Rejected </li> 
                               <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${grCount.completed}</span>Completed</li> 
                               <li class="list-group-item" style=" list-style: none; padding: 10px; background-color: #fff; border: 1px solid #ddd; margin-bottom: -1px; font-size: 13px; "> <span class="tag" style=" background-color: #3BAFDA; padding: 3px; font-size: 11px; font-weight: 600; color: #fff; text-align: center; padding-right: 8px; padding-left: 8px; border-radius: 10px; float: right; ">${grCount.total}</span> Total Records </li> </ul>
                                </div>
                              </div></td>
                          </tr>
                        </table></td>
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
