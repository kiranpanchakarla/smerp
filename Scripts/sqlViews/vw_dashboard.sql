-- View: vw_dashboard

-- DROP VIEW vw_dashboard;

CREATE OR REPLACE VIEW vw_dashboard AS 
 SELECT a.status,
    COALESCE(pr.pr_count, 0::bigint) AS pr_count,
    COALESCE(rfq.rfq_count, 0::bigint) AS rfq_count,
    COALESCE(po.po_count, 0::bigint) AS po_count,
    COALESCE(gr.gr_count, 0::bigint) AS gr_count,
    COALESCE(gre.gre_count, 0::bigint) AS gre_count,
    COALESCE(inv.inv_count, 0::bigint) AS inv_count,
    COALESCE(cre.cre_count, 0::bigint) AS cre_count,
    COALESCE(invgr.invgr_count, 0::bigint) AS invgr_count,
    COALESCE(invgi.invgi_count, 0::bigint) AS invgi_count,
    COALESCE(invgt.invgt_count, 0::bigint) AS invgt_count
   FROM ( SELECT r.status
           FROM ( SELECT 'Open'::text AS status
                UNION
                 SELECT 'Cancelled'::text AS text
                UNION
                 SELECT 'Draft'::text AS text
                UNION
                 SELECT 'Approved'::text AS text
                UNION
                 SELECT 'ConvertedToRFQ'::text AS text
                UNION
                 SELECT 'Completed'::text AS text
                UNION
                 SELECT 'Rejected'::text AS text
                UNION
                 SELECT 'ConvertedToPO'::text AS text
                UNION
                 SELECT 'Partially_Received'::text AS text
                UNION
                 SELECT 'Partially_Returned'::text AS text
                UNION
                 SELECT 'Partially_Credited'::text AS text) r) a
     LEFT JOIN ( SELECT pr_1.status,
            count(pr_1.status) AS pr_count
           FROM tbl_purchase_purchase_req pr_1
          WHERE pr_1.is_active = true
          GROUP BY pr_1.status) pr ON pr.status::text = a.status
     LEFT JOIN ( SELECT rfq_1.status,
            count(rfq_1.status) AS rfq_count
           FROM tbl_admin_rfq rfq_1
          WHERE rfq_1.is_active = true
          GROUP BY rfq_1.status) rfq ON rfq.status::text = a.status
     LEFT JOIN ( SELECT po_1.status,
            count(po_1.status) AS po_count
           FROM tbl_purchase_order po_1
          WHERE po_1.is_active = true
          GROUP BY po_1.status) po ON po.status::text = a.status
     LEFT JOIN ( SELECT gr_1.status,
            count(gr_1.status) AS gr_count
           FROM tbl_goods_receipt gr_1
          WHERE gr_1.is_active = true
          GROUP BY gr_1.status) gr ON gr.status::text = a.status
     LEFT JOIN ( SELECT gre_1.status,
            count(gre_1.status) AS gre_count
           FROM tbl_goods_return gre_1
          WHERE gre_1.is_active = true
          GROUP BY gre_1.status) gre ON gre.status::text = a.status
     LEFT JOIN ( SELECT inv_1.status,
            count(inv_1.status) AS inv_count
           FROM tbl_invoice inv_1
          WHERE inv_1.is_active = true
          GROUP BY inv_1.status) inv ON inv.status::text = a.status
     LEFT JOIN ( SELECT cre_1.status,
            count(cre_1.status) AS cre_count
           FROM tbl_credit_memo cre_1
          WHERE cre_1.is_active = true
          GROUP BY cre_1.status) cre ON cre.status::text = a.status
     LEFT JOIN ( SELECT invgr_1.status,
            count(invgr_1.status) AS invgr_count
           FROM tbl_inventory_goods_receipt invgr_1
          WHERE invgr_1.is_active = true
          GROUP BY invgr_1.status) invgr ON invgr.status::text = a.status
     LEFT JOIN ( SELECT invgi_1.status,
            count(invgi_1.status) AS invgi_count
           FROM tbl_inventory_goods_issue invgi_1
          WHERE invgi_1.is_active = true
          GROUP BY invgi_1.status) invgi ON invgi.status::text = a.status
     LEFT JOIN ( SELECT invgt_1.status,
            count(invgt_1.status) AS invgt_count
           FROM tbl_inventory_goods_transfer invgt_1
          WHERE invgt_1.is_active = true
          GROUP BY invgt_1.status) invgt ON invgt.status::text = a.status
  ORDER BY a.status;

ALTER TABLE vw_dashboard
  OWNER TO smerp_dev;
