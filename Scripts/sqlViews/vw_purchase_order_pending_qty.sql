-- View: vw_purchase_order_pending_qty

-- DROP VIEW vw_purchase_order_pending_qty;

CREATE OR REPLACE VIEW vw_purchase_order_pending_qty AS 
 SELECT poh.id,
    pol.product_id,
    pol.product_number,
    pol.required_quantity AS po_quantity,
    COALESCE(gr_new.gr_quantity, 0::bigint) AS gr_quantity,
    COALESCE(gr_new.returned_quantity, 0::numeric) AS returned_quantity,
    COALESCE(gr_new.creditmemo_quantity, 0::numeric) AS creditmemo_quantity,
    COALESCE(gr_new.gr_quantity, 0::bigint)::numeric - COALESCE(gr_new.returned_quantity, 0::numeric) - COALESCE(gr_new.creditmemo_quantity, 0::numeric) AS available_quantity,
    pol.required_quantity::numeric - (COALESCE(gr_new.gr_quantity, 0::bigint)::numeric - COALESCE(gr_new.returned_quantity, 0::numeric) - COALESCE(gr_new.creditmemo_quantity, 0::numeric)) AS pending_quantity
   FROM tbl_purchase_order poh
     JOIN tbl_purchase_order_lineitems pol ON poh.id = pol.po_id
     LEFT JOIN ( SELECT grh.po_id,
            grl.product_id,
            grl.product_number,
            sum(grl.required_quantity) AS gr_quantity,
            sum(COALESCE(gre.returned_quantity, 0::bigint)) AS returned_quantity,
            sum(COALESCE(cm.creditmemo_quantity, 0::bigint)) AS creditmemo_quantity
           FROM tbl_goods_receipt grh
             JOIN tbl_goods_receipt_lineitems grl ON grh.id = grl.gr_id
             LEFT JOIN ( SELECT greh.gr_id,
                    grel.product_id,
                    COALESCE(sum(grel.required_quantity), 0::bigint) AS returned_quantity
                   FROM tbl_goods_return greh
                     JOIN tbl_goods_return_lineitems grel ON greh.id = grel.gre_id
                  WHERE greh.status::text = 'Approved'::text
                  GROUP BY greh.gr_id, grel.product_id) gre ON gre.gr_id = grh.id AND gre.product_id = grl.product_id
             LEFT JOIN ( SELECT ih.gr_id,
                    cml.product_id,
                    COALESCE(sum(cml.required_quantity), 0::bigint) AS creditmemo_quantity
                   FROM tbl_credit_memo cmh
                     JOIN tbl_credit_memo_lineitems cml ON cmh.id = cml.cre_id
                     JOIN tbl_invoice ih ON ih.id = cmh.inv_id
                  WHERE cmh.status::text = 'Approved'::text
                  GROUP BY cml.product_id, ih.gr_id) cm ON cm.gr_id = grh.id AND cm.product_id = grl.product_id
          WHERE grh.status::text <> 'Rejected'::text
          GROUP BY grh.po_id, grl.product_id, grl.product_number) gr_new ON gr_new.po_id = poh.id AND gr_new.product_id = pol.product_id;

ALTER TABLE vw_purchase_order_pending_qty
  OWNER TO smerp_dev;
