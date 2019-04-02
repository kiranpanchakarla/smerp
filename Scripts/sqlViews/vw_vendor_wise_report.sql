-- View: vw_vendor_wise_report

-- DROP VIEW vw_vendor_wise_report;

CREATE OR REPLACE VIEW vw_vendor_wise_report AS 
 SELECT po.vendor_id,
    v.vendor_code,
    v.name,
    sum(vpo.total_amount) AS "ANNUAL",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'APR'::text), 0::numeric) AS "APR",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'MAY'::text), 0::numeric) AS "MAY",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'JUN'::text), 0::numeric) AS "JUN",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'JUL'::text), 0::numeric) AS "JUL",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'AUG'::text), 0::numeric) AS "AUG",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'SEPT'::text), 0::numeric) AS "SEPT",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'OCT'::text), 0::numeric) AS "OCT",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'NOV'::text), 0::numeric) AS "NOV",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'DEC'::text), 0::numeric) AS "DEC",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'JAN'::text), 0::numeric) AS "JAN",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'FEB'::text), 0::numeric) AS "FEB",
    COALESCE(sum(vpo.total_amount) FILTER (WHERE to_char(po.created_at, 'MON'::text) = 'MAR'::text), 0::numeric) AS "MAR"
   FROM tbl_purchase_order po
     JOIN vw_purchase_order_amount vpo ON vpo.id = po.id
     JOIN tbl_admin_vendor v ON v.vendor_id = po.vendor_id
  WHERE to_char(po.created_at, 'YYYY'::text)::integer = to_char('now'::text::date::timestamp with time zone, 'YYYY'::text)::integer
  GROUP BY po.vendor_id, v.name, v.vendor_code
  ORDER BY po.vendor_id;

ALTER TABLE vw_vendor_wise_report
  OWNER TO smerp_dev;
