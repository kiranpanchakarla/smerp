-- View: vw_dashboard

-- DROP VIEW vw_dashboard;

CREATE OR REPLACE VIEW vw_dashboard AS 
 SELECT a.plant_id,
    a.plant_name,
    a.status,
    COALESCE(pr.pr_count, 0::bigint) AS pr_count,
    COALESCE(rfq.rfq_count, 0::bigint) AS rfq_count,
    COALESCE(po.po_count, 0::bigint) AS po_count,
    COALESCE(gr.gr_count, 0::bigint) AS gr_count,
    COALESCE(gre.gre_count, 0::bigint) AS gre_count
   FROM ( SELECT p.plant_id,
            p.plant_name,
            r.status
           FROM ( SELECT 'Open'::text AS status
                UNION
                 SELECT 'Cancelled'::text
                UNION
                 SELECT 'Draft'::text
                UNION
                 SELECT 'Approved'::text
                UNION
                 SELECT 'ConvertedToRFQ'::text
                UNION
                 SELECT 'Completed'::text
                UNION
                 SELECT 'Rejected'::text
                UNION
                 SELECT 'ConvertedToPO'::text) r
             CROSS JOIN tbl_admin_plant p) a
     LEFT JOIN ( SELECT pr_1.status,
            prl.ware_house,
            count(pr_1.status) AS pr_count
           FROM tbl_purchase_purchase_req pr_1
             JOIN tbl_purchase_purchase_req_list prl ON pr_1.purchase_req_id = prl.purchase_req_id
          WHERE pr_1.is_active = true
          GROUP BY pr_1.status, prl.ware_house) pr ON pr.status::text = a.status AND pr.ware_house = a.plant_id
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
  ORDER BY a.plant_id, a.status;

ALTER TABLE vw_dashboard
  OWNER TO smerp_dev;
