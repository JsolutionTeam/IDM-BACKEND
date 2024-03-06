select 1 as col_0_0_
from shop shop0_
where (shop0_.id is not null)
  and (shop0_.deleted_at is null)
limit 1

select account0_.id         as id1_3_0_,
       account0_.crtd_dt    as crtd_dt2_3_0_,
       account0_.deleted_at as deleted_3_3_0_,
       account0_.updt_dt    as updt_dt4_3_0_,
       account0_.is_manager as is_manag5_3_0_,
       account0_.name       as name6_3_0_,
       account0_.phone      as phone7_3_0_,
       account0_.role       as role8_3_0_,
       account0_.shop_id    as shop_id9_3_0_
from tb_account account0_
where account0_.id = ?;

select *W
from tb_device d
where pet_nm like '%갤럭시 Z 플립 5%';

select *
from tb_device d
where pet_nm like '%갤럭시 Z 폴드 5%';
WWW


select subservice0_.id         as id1_16_0_,
       subservice0_.crtd_dt    as crtd_dt2_16_0_,
       subservice0_.deleted_at as deleted_3_16_0_,
       subservice0_.updt_dt    as updt_dt4_16_0_,
       subservice0_.exp        as exp5_16_0_,
       subservice0_.frequency  as frequenc6_16_0_,
       subservice0_.name       as name7_16_0_,
       subservice0_.point      as point8_16_0_,
       subservice0_.price      as price9_16_0_,
       subservice0_.telecom_id as telecom10_16_0_
from integration_mcall.tb_subservice subservice0_
where subservice0_.id = 1

alter table tb_subservice
    drop column id;

select series, d.idx as 'deviceId', c.idx as 'colorId'
from tb_device_info di
         left join tb_device d on d.idx = di.device_idx
         left join tb_color c on c.idx = di.color_idx;

select *
from tb_device_info di

where di.device_idx = 19
  and di.color_idx = 77;

# 19,15
# 19,77

# di.device_idx, di.color_idx가 2개 이상 있는 경우를 조회
select di.device_idx, di.color_idx, count(*) as cnt
from tb_device_info di
group by di.device_idx, di.color_idx
having count(*) > 1;


# 26
# 148

select *
from tb_shop_device
where device_info_idx = 148;

# delete device info by idx
delete
from tb_device_info
where idx = 148;
commit;

select *
from tb_device_info di
         left join tb_device d on di.device_idx = d.idx;
