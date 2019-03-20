-- View: vw_inventory_goods_issue_daily

-- DROP VIEW vw_inventory_goods_issue_daily;

CREATE OR REPLACE VIEW vw_inventory_goods_issue_daily AS 
 SELECT gi.id,
    gi.doc_number,
    to_char(gi.document_date, 'DD-MM-YYYY'::text) AS document_date,
    gil.product_id,
    gil.product_number,
    p.description AS product_description,
    pt.description AS product_group_description,
    u.uom_name,
    gil.required_quantity,
    gil.department_id,
    d.department_name,
    gi.remarks,
    gil.ware_house,
    gi.is_active
   FROM tbl_inventory_goods_issue gi
     JOIN tbl_inventory_goods_issue_list gil ON gi.id = gil.inv_goods_issue_id
     JOIN tbl_inventory_product p ON gil.product_id = p.product_id
     JOIN tbl_inventory_product_type pt ON pt.product_type_id = p.product_type_id
     JOIN tbl_inventory_uom u ON u.uom_id = p.inventory_uom_id
     JOIN tbl_admin_department d ON d.department_id = gil.department_id
  WHERE gi.created_at::date = 'now'::text::date;

ALTER TABLE vw_inventory_goods_issue_daily
  OWNER TO smerp_dev;
