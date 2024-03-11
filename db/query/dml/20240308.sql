# 아이폰 15 프로 맥스
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 15 프로 맥스%'
order by c.name asc;

# 아이폰 15 프로
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 15 프로 %'
  and d.pet_nm not like '%맥스%'
order by c.name asc;

# 아이폰 15 플러스
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 15 플러스%'
order by c.name asc;

# 아이폰 15
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '아이폰 15'
order by c.name asc;

# 갤럭시 S24 Ultra
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 S24 Ultra%'
order by c.name asc;

# 갤럭시 S24+
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 S24+%'
order by c.name, di.idx asc;

# 갤럭시 S24
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm in ('갤럭시 S24', '갤럭시 S24 512')
order by c.name, di.idx asc;

# 갤럭시 S23 Ultra
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 S23 Ultra%'
order by c.name asc;


# 갤럭시 S23+
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 S23+%'
order by c.name, di.idx asc;

# 갤럭시 S23 FE
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 S23 FE%'
order by c.name, di.idx asc;

# 갤럭시 S23
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm in ('갤럭시 S23', '갤럭시 S23 512')
order by c.name, di.idx asc;

# 갤럭시 A25
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 A25%'
order by c.name, di.idx asc;

# 갤럭시 A34
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 A34%'
order by c.name, di.idx asc;

# 갤럭시 점프 3
select di.idx,
       di.image_url,
       d.model_nm,
       d.pet_nm,
       c.name
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx
         left join tb_color c on di.color_idx = c.idx
where d.pet_nm like '갤럭시 점프 3%'
order by c.name, di.idx asc;

select *
from tb_device_info di
where di.device_idx in (71, 72);

select *
from tb_device
where model_nm like '%921%';

update tb_device_info
set image_url = (select image_url
                 from tb_device_info
                 where idx = 102)
where idx in (
    108
#               ,214
#               ,206
    );

select count(companysub0_.id) as col_0_0_
from tb_company_subsidy companysub0_
where companysub0_.deleted_at is null
group by companysub0_.telecom_id, companysub0_.phone_plan_id, companysub0_.device_id, companysub0_.shop_id, companysub0_.open_type,
         companysub0_.discount_type
limit ?

select 1
from tb_company_subsidy companysub0_
group by companysub0_.telecom_id,
         companysub0_.phone_plan_id,
         companysub0_.device_id,
         companysub0_.shop_id
order by telecom_id, phone_plan_id, device_id, shop_id asc;

# 위 select 1 의 query 결과의 row수만큼을 반환하는 쿼리를 작성

SELECT COUNT(*) AS row_count
FROM (SELECT 1
      FROM tb_company_subsidy companysub0_
      GROUP BY companysub0_.telecom_id,
               companysub0_.phone_plan_id,
               companysub0_.device_id,
               companysub0_.shop_id
      ORDER BY telecom_id, phone_plan_id, device_id, shop_id ASC) AS subquery;

select sum(
               (select count(c.id) as count_0
                from tb_company_subsidy c
                where c.deleted_at is null
                group by c.telecom_id,
                         c.phone_plan_id,
                         c.device_id,
                         c.shop_id,
                         c.open_type,
                         c.discount_type)
       )
from dual;

select count(c.id) as count_0
from tb_company_subsidy c
where c.deleted_at is null
group by c.telecom_id,
         c.phone_plan_id,
         c.device_id,
         c.shop_id,
         c.open_type,
         c.discount_type;

select companysub0_.telecom_id     as col_0_0_,
       companysub0_.phone_plan_id  as col_1_0_,
       companysub0_.device_id      as col_2_0_,
       companysub0_.shop_id        as col_3_0_,
       companysub0_.telecom_id     as col_4_0_,
       telecom1_.name              as col_5_0_,
       companysub0_.phone_plan_id  as col_6_0_,
       phoneplan2_.name            as col_7_0_,
       phoneplan2_.price           as col_8_0_,
       phoneplan2_.category        as col_9_0_,
       phoneplan2_.call_exp        as col_10_0_,
       phoneplan2_.data_exp        as col_11_0_,
       phoneplan2_.mail_exp        as col_12_0_,
       companysub0_.device_id      as col_13_0_,
       device3_.pet_nm             as col_14_0_,
       device3_.model_nm           as col_15_0_,
       device3_.price              as col_16_0_,
       device3_.volume             as col_17_0_,
       device3_.series             as col_18_0_,
       device3_.crtd_dt            as col_19_0_,
       device3_.updt_dt            as col_20_0_,
       companysub0_.shop_id        as col_21_0_,
       shop4_.name                 as col_22_0_,
       companysub0_.id             as col_23_0_,
       companysub0_.discount_price as col_24_0_,
       companysub0_.open_type      as col_25_0_,
       companysub0_.discount_type  as col_26_0_,
       telecom1_.idx               as idx1_12_0_,
       phoneplan2_.phone_plan_id   as phone_pl1_15_1_,
       device3_.idx                as idx1_2_2_,
       shop4_.idx                  as idx1_8_3_,
       telecom1_.crtd_dt           as crtd_dt2_12_0_,
       telecom1_.deleted_at        as deleted_3_12_0_,
       telecom1_.updt_dt           as updt_dt4_12_0_,
       telecom1_.name              as name5_12_0_,
       phoneplan2_.crtd_dt         as crtd_dt2_15_1_,
       phoneplan2_.deleted_at      as deleted_3_15_1_,
       phoneplan2_.updt_dt         as updt_dt4_15_1_,
       phoneplan2_.call_exp        as call_exp5_15_1_,
       phoneplan2_.category        as category6_15_1_,
       phoneplan2_.data_exp        as data_exp7_15_1_,
       phoneplan2_.frequency       as frequenc8_15_1_,
       phoneplan2_.mail_exp        as mail_exp9_15_1_,
       phoneplan2_.name            as name10_15_1_,
       phoneplan2_.price           as price11_15_1_,
       phoneplan2_.telecom_id      as telecom12_15_1_,
       device3_.crtd_dt            as crtd_dt2_2_2_,
       device3_.deleted_at         as deleted_3_2_2_,
       device3_.updt_dt            as updt_dt4_2_2_,
       device3_.maker_idx          as maker_i10_2_2_,
       device3_.model_nm           as model_nm5_2_2_,
       device3_.pet_nm             as pet_nm6_2_2_,
       device3_.price              as price7_2_2_,
       device3_.series             as series8_2_2_,
       device3_.volume             as volume9_2_2_,
       shop4_.crtd_dt              as crtd_dt2_8_3_,
       shop4_.deleted_at           as deleted_3_8_3_,
       shop4_.updt_dt              as updt_dt4_8_3_,
       shop4_.manager_id           as manager_5_8_3_,
       shop4_.manager_nm           as manager_6_8_3_,
       shop4_.manager_tel_no       as manager_7_8_3_,
       shop4_.name                 as name8_8_3_,
       shop4_.tel_no               as tel_no9_8_3_,
       shop4_.role                 as role10_8_3_,
       shop4_.status               as status11_8_3_,
       shop4_.use_mcall_shop       as use_mca12_8_3_
from tb_company_subsidy companysub0_
         inner join integration_idm.tb_telecom telecom1_ on companysub0_.telecom_id = telecom1_.idx
         inner join integration_mcall.tb_phone_plan phoneplan2_ on companysub0_.phone_plan_id = phoneplan2_.phone_plan_id
         inner join integration_idm.tb_device device3_ on companysub0_.device_id = device3_.idx
         inner join integration_idm.tb_shop shop4_ on companysub0_.shop_id = shop4_.idx
where companysub0_.deleted_at is null
group by companysub0_.telecom_id, companysub0_.phone_plan_id, companysub0_.device_id, companysub0_.shop_id
limit 10;

select 1 as col_0_0_
from tb_company_subsidy companysub0_
where companysub0_.deleted_at is null
group by companysub0_.telecom_id, companysub0_.phone_plan_id, companysub0_.device_id, companysub0_.shop_id

select deviceinfo0_.idx           as idx1_3_,
       deviceinfo0_.crtd_dt       as crtd_dt2_3_,
       deviceinfo0_.deleted_at    as deleted_3_3_,
       deviceinfo0_.updt_dt       as updt_dt4_3_,
       deviceinfo0_.barcode       as barcode5_3_,
       deviceinfo0_.barcode_color as barcode_6_3_,
       deviceinfo0_.color_idx     as color_id8_3_,
       deviceinfo0_.device_idx    as device_i9_3_,
       deviceinfo0_.image_url     as image_ur7_3_
from integration_idm.tb_device_info deviceinfo0_
where deviceinfo0_.device_idx = 76
  and (deviceinfo0_.deleted_at is null)
limit 1

select *
from tb_device_info di;
# where di.device_idx = 76;
select *
from tb_device d
where idx = 76;
