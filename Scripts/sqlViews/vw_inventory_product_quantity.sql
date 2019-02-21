-- View: vw_inventory_product_quantity

-- DROP VIEW vw_inventory_product_quantity;

CREATE OR REPLACE VIEW vw_inventory_product_quantity AS 
 SELECT p.product_id,
    p.product_no,
    w.plant_id,
    w.plant_name,
    COALESCE(r1.instock_quantity, 0::numeric) AS instock_quantity,
    COALESCE(r1.ordered_quantity, 0::numeric) AS ordered_quantity,
    COALESCE(r1.available_quantity, 0::numeric) AS available_quantity
   FROM tbl_inventory_product p
     CROSS JOIN tbl_admin_plant w
     LEFT JOIN ( SELECT r.product_id,
            r.warehouse,
            sum(r.instock_quantity) AS instock_quantity,
            sum(r.ordered_quantity) AS ordered_quantity,
            sum(r.instock_quantity) + sum(r.ordered_quantity) AS available_quantity
           FROM ( SELECT po_products.product_id,
                    po_products.warehouse,
                    sum(po_products.instock_quantity) AS instock_quantity,
                    sum(po_products.ordered_quantity) AS ordered_quantity
                   FROM ( SELECT pol.product_id,
                            pol.warehouse,
                            COALESCE(gr_new.gr_quantity, 0::bigint)::numeric - COALESCE(gr_new.returned_quantity, 0::numeric) - COALESCE(gr_new.creditmemo_quantity, 0::numeric) AS instock_quantity,
                            pol.required_quantity::numeric - (COALESCE(gr_new.gr_quantity, 0::bigint)::numeric - COALESCE(gr_new.returned_quantity, 0::numeric) - COALESCE(gr_new.creditmemo_quantity, 0::numeric)) AS ordered_quantity
                           FROM tbl_purchase_order poh
                             JOIN tbl_purchase_order_lineitems pol ON poh.id = pol.po_id
                             LEFT JOIN ( SELECT grh.po_id,
                                    grl.product_id,
                                    grl.product_number,
                                    grl.warehouse,
                                    sum(grl.required_quantity) AS gr_quantity,
                                    sum(COALESCE(gre.returned_quantity, 0::bigint)) AS returned_quantity,
                                    sum(COALESCE(cm.creditmemo_quantity, 0::bigint)) AS creditmemo_quantity
                                   FROM tbl_goods_receipt grh
                                     JOIN tbl_goods_receipt_lineitems grl ON grh.id = grl.gr_id
                                     LEFT JOIN ( SELECT greh.gr_id,
    grel.product_id,
    grel.warehouse,
    COALESCE(sum(grel.required_quantity), 0::bigint) AS returned_quantity
   FROM tbl_goods_return greh
     JOIN tbl_goods_return_lineitems grel ON greh.id = grel.gre_id
  WHERE greh.status::text = 'Approved'::text
  GROUP BY greh.gr_id, grel.product_id, grel.warehouse) gre ON gre.gr_id = grh.id AND gre.product_id = grl.product_id AND gre.warehouse = grl.warehouse
                                     LEFT JOIN ( SELECT ih.gr_id,
    cml.product_id,
    cml.warehouse,
    COALESCE(sum(cml.required_quantity), 0::bigint) AS creditmemo_quantity
   FROM tbl_credit_memo cmh
     JOIN tbl_credit_memo_lineitems cml ON cmh.id = cml.cre_id
     JOIN tbl_invoice ih ON ih.id = cmh.inv_id
  WHERE cmh.status::text = 'Approved'::text
  GROUP BY cml.product_id, ih.gr_id, cml.warehouse) cm ON cm.gr_id = grh.id AND cm.product_id = grl.product_id AND cm.warehouse = grl.warehouse
                                  WHERE grh.status::text <> 'Rejected'::text
                                  GROUP BY grh.po_id, grl.warehouse, grl.product_id, grl.product_number) gr_new ON gr_new.po_id = poh.id AND gr_new.product_id = pol.product_id AND gr_new.warehouse = pol.warehouse) po_products
                  GROUP BY po_products.product_id, po_products.warehouse
                UNION ALL
                 SELECT grl.product_id,
                    grl.warehouse,
                    sum(grl.required_quantity)::numeric - (sum(COALESCE(gre.returned_quantity, 0::bigint)) + sum(COALESCE(cm.creditmemo_quantity, 0::bigint))) AS instock_quantity,
                    0 AS ordered_quantity
                   FROM tbl_goods_receipt grh
                     JOIN tbl_goods_receipt_lineitems grl ON grh.id = grl.gr_id
                     LEFT JOIN ( SELECT greh.gr_id,
                            grel.product_id,
                            grel.warehouse,
                            COALESCE(sum(grel.required_quantity), 0::bigint) AS returned_quantity
                           FROM tbl_goods_return greh
                             JOIN tbl_goods_return_lineitems grel ON greh.id = grel.gre_id
                          WHERE greh.status::text = 'Approved'::text
                          GROUP BY greh.gr_id, grel.product_id, grel.warehouse) gre ON gre.gr_id = grh.id AND gre.product_id = grl.product_id AND gre.warehouse = grl.warehouse
                     LEFT JOIN ( SELECT ih.gr_id,
                            cml.product_id,
                            cml.warehouse,
                            COALESCE(sum(cml.required_quantity), 0::bigint) AS creditmemo_quantity
                           FROM tbl_credit_memo cmh
                             JOIN tbl_credit_memo_lineitems cml ON cmh.id = cml.cre_id
                             JOIN tbl_invoice ih ON ih.id = cmh.inv_id
                          WHERE cmh.status::text = 'Approved'::text
                          GROUP BY cml.product_id, cml.warehouse, ih.gr_id) cm ON cm.gr_id = grh.id AND cm.product_id = grl.product_id AND cm.warehouse = grl.warehouse
                  WHERE grh.status::text <> 'Rejected'::text AND grh.po_id IS NULL
                  GROUP BY grl.product_id, grl.warehouse
                UNION ALL
                 SELECT il.product_id,
                    il.warehouse,
                    sum(il.required_quantity)::numeric - sum(COALESCE(cm.creditmemo_quantity, 0::bigint)) AS instock_quantity,
                    0 AS ordered_quantity
                   FROM tbl_invoice ih
                     JOIN tbl_invoice_lineitems il ON ih.id = il.inv_id
                     LEFT JOIN ( SELECT ih_1.id,
                            cml.product_id,
                            cml.warehouse,
                            COALESCE(sum(cml.required_quantity), 0::bigint) AS creditmemo_quantity
                           FROM tbl_credit_memo cmh
                             JOIN tbl_credit_memo_lineitems cml ON cmh.id = cml.cre_id
                             JOIN tbl_invoice ih_1 ON ih_1.id = cmh.inv_id
                          WHERE cmh.status::text = 'Approved'::text AND ih_1.gr_id IS NULL
                          GROUP BY ih_1.id, cml.product_id, cml.warehouse) cm ON ih.id = cm.id AND il.product_id = cm.product_id AND cm.warehouse = il.warehouse
                  WHERE ih.status::text <> 'Rejected'::text AND ih.gr_id IS NULL
                  GROUP BY il.product_id, il.warehouse
                UNION ALL
                 SELECT igil.product_id,
                    igil.ware_house AS warehouse,
                    (-1) * sum(igil.required_quantity) AS instock_quantity,
                    0 AS ordered_quantity
                   FROM tbl_inventory_goods_issue igih
                     JOIN tbl_inventory_goods_issue_list igil ON igih.id = igil.inv_goods_issue_id
                  WHERE igih.status::text <> 'Rejected'::text
                  GROUP BY igil.product_id, igil.ware_house
                UNION ALL
                 SELECT igrl.product_id,
                    igrl.ware_house AS warehouse,
                    sum(igrl.required_quantity) AS instock_quantity,
                    0 AS ordered_quantity
                   FROM tbl_inventory_goods_receipt igrh
                     JOIN tbl_inventory_goods_receipt_list igrl ON igrh.id = igrl.inv_goods_receipt_id
                  WHERE igrh.status::text <> 'Rejected'::text
                  GROUP BY igrl.product_id, igrl.ware_house
                UNION ALL
                 SELECT igtl.product_id,
                    igtl.from_warehouse AS warehouse,
                    (-1) * sum(igtl.required_quantity) AS instock_quantity,
                    0 AS ordered_quantity
                   FROM tbl_inventory_goods_transfer igth
                     JOIN tbl_inventory_goods_transfer_list igtl ON igth.id = igtl.inv_goods_transfer_id
                  WHERE igth.status::text <> 'Rejected'::text
                  GROUP BY igtl.product_id, igtl.from_warehouse
                UNION ALL
                 SELECT igtl.product_id,
                    igtl.to_warehouse AS warehouse,
                    sum(igtl.required_quantity) AS instock_quantity,
                    0 AS ordered_quantity
                   FROM tbl_inventory_goods_transfer igth
                     JOIN tbl_inventory_goods_transfer_list igtl ON igth.id = igtl.inv_goods_transfer_id
                  WHERE igth.status::text <> 'Rejected'::text
                  GROUP BY igtl.product_id, igtl.to_warehouse) r
          GROUP BY r.product_id, r.warehouse) r1 ON r1.product_id = p.product_id AND r1.warehouse = w.plant_id
  ORDER BY p.product_id, w.plant_id;

ALTER TABLE vw_inventory_product_quantity
  OWNER TO smerp_dev;
