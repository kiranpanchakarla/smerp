-- View: vw_goods_received_lineitems_amount

-- DROP VIEW vw_goods_received_lineitems_amount;

CREATE OR REPLACE VIEW vw_goods_received_lineitems_amount AS 
 SELECT grh.id,
    grh.doc_number,
    COALESCE(grh.freight, 0::numeric::double precision) AS freight,
    COALESCE(grh.total_discount, 0::double precision) AS total_discount,
    grl.product_id,
    grl.product_number,
    grl.description,
    grl.uom,
    grl.sku_quantity,
    grl.product_group,
    grl.hsn,
    grl.warehouse,
    grl.required_quantity AS gr_quantity,
    COALESCE(gre.returned_quantity, 0::bigint) AS returned_quantity,
    COALESCE(cm.creditmemo_quantity, 0::bigint) AS creditmemo_quantity,
    grl.required_quantity - COALESCE(gre.returned_quantity, 0::bigint) - COALESCE(cm.creditmemo_quantity, 0::bigint) AS current_quantity,
    grl.tax_code,
    grl.tax_description,
    grl.unit_price,
    ((grl.required_quantity - COALESCE(gre.returned_quantity, 0::bigint) - COALESCE(cm.creditmemo_quantity, 0::bigint))::double precision * grl.unit_price * COALESCE(grl.tax_code, 0::double precision) / 100::double precision)::numeric(20,2) AS product_tax,
    ((grl.required_quantity - COALESCE(gre.returned_quantity, 0::bigint) - COALESCE(cm.creditmemo_quantity, 0::bigint))::double precision * grl.unit_price)::numeric(20,2) AS product_cost,
    ((grl.required_quantity - COALESCE(gre.returned_quantity, 0::bigint) - COALESCE(cm.creditmemo_quantity, 0::bigint))::double precision * grl.unit_price - (grl.required_quantity - COALESCE(gre.returned_quantity, 0::bigint) - COALESCE(cm.creditmemo_quantity, 0::bigint))::double precision * grl.unit_price * COALESCE(grh.total_discount, 0::double precision) / 100::double precision + (grl.required_quantity - COALESCE(gre.returned_quantity, 0::bigint) - COALESCE(cm.creditmemo_quantity, 0::bigint))::double precision * grl.unit_price * COALESCE(grl.tax_code, 0::double precision) / 100::double precision)::numeric(20,2) AS product_cost_tax,
    ((grl.required_quantity - COALESCE(gre.returned_quantity, 0::bigint) - COALESCE(cm.creditmemo_quantity, 0::bigint))::double precision * grl.unit_price + (grl.required_quantity - COALESCE(gre.returned_quantity, 0::bigint) - COALESCE(cm.creditmemo_quantity, 0::bigint))::double precision * grl.unit_price * COALESCE(grl.tax_code, 0::double precision) / 100::double precision)::numeric(20,2) AS product_cost_tax_without_discount
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
          GROUP BY cml.product_id, ih.gr_id) cm ON cm.gr_id = grh.id AND cm.product_id = grl.product_id;

ALTER TABLE vw_goods_received_lineitems_amount
  OWNER TO smerp_dev;
