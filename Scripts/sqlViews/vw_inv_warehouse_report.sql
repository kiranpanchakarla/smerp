-- View: vw_inv_warehouse_report

-- DROP VIEW vw_inv_warehouse_report;

CREATE OR REPLACE VIEW vw_inv_warehouse_report AS 
 SELECT vw1.product_id,
    vw1.product_no,
    vw1.product_description,
    vw1.product_group_description,
    vw1.uom_name,
    COALESCE(sum(vw1.instock_quantity)::double precision, 0::numeric::double precision) AS instock_quantity,
    vw2.madurawada,
    vw3.yelamanchili,
    vw4.mainoffice
   FROM ( SELECT vw_1.product_id,
            vw_1.product_no,
            vw_1.plant_id,
            vw_1.plant_name,
            vw_1.product_description,
            vw_1.product_group_description,
            vw_1.uom_name,
            vw_1.instock_quantity
           FROM vw_inventory_status_report vw_1) vw1
     LEFT JOIN ( SELECT vw_2.product_id,
            COALESCE(sum(vw_2.instock_quantity)::double precision, 0::numeric::double precision) AS madurawada
           FROM vw_inventory_status_report vw_2
          WHERE vw_2.plant_name::text = 'Madhurawada'::text
          GROUP BY vw_2.product_id
          ORDER BY vw_2.product_id) vw2 ON vw1.product_id = vw2.product_id
     LEFT JOIN ( SELECT vw_3.product_id,
            COALESCE(sum(vw_3.instock_quantity)::double precision, 0::numeric::double precision) AS yelamanchili
           FROM vw_inventory_status_report vw_3
          WHERE vw_3.plant_name::text = 'Yelamanchili'::text
          GROUP BY vw_3.product_id
          ORDER BY vw_3.product_id) vw3 ON vw1.product_id = vw3.product_id
     LEFT JOIN ( SELECT vw_4.product_id,
            COALESCE(sum(vw_4.instock_quantity)::double precision, 0::numeric::double precision) AS mainoffice
           FROM vw_inventory_status_report vw_4
          WHERE vw_4.plant_name::text = 'Main Office'::text
          GROUP BY vw_4.product_id
          ORDER BY vw_4.product_id) vw4 ON vw1.product_id = vw4.product_id
  GROUP BY vw1.product_id, vw1.product_no, vw1.product_description, vw1.product_group_description, vw1.uom_name, vw2.madurawada, vw3.yelamanchili, vw4.mainoffice
  ORDER BY vw1.product_id;

ALTER TABLE vw_inv_warehouse_report
  OWNER TO smerp_dev;
