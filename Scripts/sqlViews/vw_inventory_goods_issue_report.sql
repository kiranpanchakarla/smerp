-- View: vw_inventory_goods_issue_report

-- DROP VIEW vw_inventory_goods_issue_report;

CREATE OR REPLACE VIEW vw_inventory_goods_issue_report AS 
 SELECT gi.id,
    gi.doc_number,
    to_char(gi.document_date, 'DD-MM-YYYY'::text) AS document_date,
    gil.product_number AS product_no,
    p.description AS product_description,
    pt.description AS product_group_description,
    pl.plant_name,
    gil.required_quantity,
    d.department_name,
    COALESCE(NULLIF(gi.remarks::text, ''::text), 'No Remarks'::text) AS remarks
   FROM tbl_inventory_goods_issue gi
     JOIN tbl_inventory_goods_issue_list gil ON gi.id = gil.inv_goods_issue_id
     JOIN tbl_inventory_product p ON gil.product_id = p.product_id
     JOIN tbl_inventory_product_type pt ON pt.product_type_id = p.product_type_id
     JOIN tbl_admin_department d ON d.department_id = gil.department_id
     JOIN tbl_admin_plant pl ON pl.plant_id = gi.plant_id
  ORDER BY gi.id;

ALTER TABLE vw_inventory_goods_issue_report
  OWNER TO smerp_dev;
