-- View: vw_inv_warehouse_report

-- DROP VIEW vw_inv_warehouse_report;

CREATE OR REPLACE VIEW vw_inv_warehouse_report AS 
 SELECT vw.product_id,
    vw.product_no,
    vw.product_description,
    vw.product_group_description,
    vw.uom_name,
    COALESCE(sum(vw.instock_quantity)::double precision, 0::numeric::double precision) AS instock_quantity,
    COALESCE(sum(vw.instock_quantity) FILTER (WHERE vw.plant_name::text = 'Madurawada'::text)::double precision, 0::numeric::double precision) AS madurawada,
    COALESCE(sum(vw.instock_quantity) FILTER (WHERE vw.plant_name::text = 'Yelamanchili'::text)::double precision, 0::numeric::double precision) AS yelamanchili
   FROM vw_inventory_status_report vw
     JOIN tbl_inventory_product prod ON vw.product_id = prod.product_id
  GROUP BY vw.product_id, vw.product_no, vw.product_description, vw.product_group_description, vw.uom_name
  ORDER BY vw.product_id;

ALTER TABLE vw_inv_warehouse_report
  OWNER TO smerp_dev;
